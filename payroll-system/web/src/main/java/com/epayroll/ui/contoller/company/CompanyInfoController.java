package com.epayroll.ui.contoller.company;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.Company;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.AuthorizedSignatoryContactForm;
import com.epayroll.form.company.BillingContactForm;
import com.epayroll.form.company.BusinessInfoForm;
import com.epayroll.form.company.LegalInfoForm;
import com.epayroll.form.company.PrimaryContactForm;
import com.epayroll.form.company.ShippingInfoForm;
import com.epayroll.model.ServerResponse;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.utils.ErrorUtils;

/**
 * @author Uma
 */
@Controller
@RequestMapping("/company")
public class CompanyInfoController {
	private Logger logger = LoggerFactory.getLogger(CompanyInfoController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

	private Company getCompanyFromSession() {
		Company company = commonService.getCompany(AuthorizationUtil.getLoginUser().getId());
		return company;
	}

	@RequestMapping(value = "/showInfoForm", method = RequestMethod.GET)
	public ModelAndView showInfoForm() {
		logger.debug(">> showInfoForm");
		logger.debug("controller");
		ModelAndView modelView = new ModelAndView("companyInfo");
		logger.debug("showInfoForm >> ");
		return modelView;
	}

	@ModelAttribute
	public void getCompanyInfoAttibutes(Model model) {
		model.addAttribute("legalInfoForm",
				companyService.getLegalInfoForm(getCompanyFromSession()));
		model.addAttribute("businessInfoForm",
				companyService.getBusinessInfoForm(getCompanyFromSession()));
		model.addAttribute("shippingInfoForm",
				companyService.getShippingInfoForm(getCompanyFromSession()));
		model.addAttribute("authorizedSignatoryContactForm",
				companyService.getAuthorizedSignatoryContactForm(getCompanyFromSession()));
		model.addAttribute("primaryContactForm",
				companyService.getPrimaryContactForm(getCompanyFromSession()));
		model.addAttribute("billingContactForm",
				companyService.getBillingContactForm(getCompanyFromSession()));
		model.addAttribute("states", commonService.findStates());
		model.addAttribute("counties", commonService.findCounties());
		model.addAttribute("cities", commonService.findCitites());

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LEGAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showLegalInfoForm", method = RequestMethod.GET)
	public ModelAndView showLegalInfoForm(HttpSession session) {
		logger.debug(">>Show Legal Info Form");
		logger.debug("controller");
		ModelAndView modelView = new ModelAndView("companyForm");
		Company company = getCompanyFromSession();
		LegalInfoForm legalInfoForm = companyService.getLegalInfoForm(company);
		modelView.addObject("legalInfoForm", legalInfoForm);
		logger.debug("Show Legal Info Form >> ");
		return modelView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LEGAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addUpdateLegalInfo", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addUpdateLegalInfo(@Valid LegalInfoForm legalInfoForm,
			BindingResult bindingResult) {
		logger.debug(">> addLegalInfo");
		logger.debug("legalInfoForm :: " + legalInfoForm);
		ServerResponse serverResponse = new ServerResponse();
		if (bindingResult.hasErrors()) {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		} else {
			try {
				Company company = companyService.addOrUpdateLegalInfo(legalInfoForm);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}
		}
		logger.debug("addUpdateLegalInfo >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LEGAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateLegalInfoForm", method = RequestMethod.POST)
	public ModelAndView updateLegalInfoForm(@ModelAttribute @Valid LegalInfoForm legalInfoForm,
			BindingResult bindingResult, HttpSession session) throws InstanceNotFoundException {
		ModelAndView modelView = new ModelAndView("companyform");
		logger.debug(">> updateLegalInfoForm");
		if (!bindingResult.hasErrors()) {
			Company company = getCompanyFromSession();
			companyService.updateLegalInfo(company, legalInfoForm);
			modelView.addObject("legalInfoForm", legalInfoForm);
		} else {
			logger.error("Validation Error :: " + bindingResult.toString());
		}
		logger.debug("updateLegalInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).BUSINESS_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showBusinessInfoForm", method = RequestMethod.GET)
	public ModelAndView showBusinessInfoForm(HttpSession session) {
		logger.debug(">>Show Business Info Form");
		ModelAndView modelView = new ModelAndView("companyform");
		Company company = getCompanyFromSession();
		BusinessInfoForm businessInfoForm = companyService.getBusinessInfoForm(company);
		modelView.addObject("businessInfoForm", businessInfoForm);
		logger.debug("Show Business Info Form>>");
		return modelView;
	}

	@RequestMapping(value = "/addOrUpdateBusinessInfo", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addOrUpdateBusinessInfo(@Valid BusinessInfoForm businessInfoForm,
			BindingResult bindingResult, HttpSession session) throws InstanceNotFoundException {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">> addBusinessInfoForm");
		if (!bindingResult.hasErrors()) {
			try {
				Company company = companyService.addOrUpdateBusinessInfo(getCompanyFromSession(),
						businessInfoForm);
				session.setAttribute("company", company);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}

		} else {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		}
		logger.debug("addOrUpdateBusinessInfo >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).SHIPPING_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showShippingInfoForm", method = RequestMethod.GET)
	public ModelAndView showShippingInfoForm(HttpSession session) {
		ModelAndView modelView = new ModelAndView("companyform");
		logger.debug(">>Show Shipping Info Form");
		Company company = getCompanyFromSession();
		ShippingInfoForm shippingInfoForm = companyService.getShippingInfoForm(company);
		modelView.addObject("shippingInfoForm", shippingInfoForm);
		logger.debug("Show Shipping Info Form>>");
		return modelView;
	}

	@RequestMapping(value = "/addOrUpdateShippingInfo", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addOrUpdateShippingInfo(@Valid ShippingInfoForm shippingInfoForm,
			BindingResult bindingResult, HttpSession session) throws InstanceNotFoundException {
		logger.debug(">> addOrUpdateShippingInfo");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				Company company = companyService.addOrUpdateShippingInfo(getCompanyFromSession(),
						shippingInfoForm);
				session.setAttribute("company", company);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}

		} else {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		}
		logger.debug("addOrUpdateShippingInfo >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).AUTHORIZED_SIGNATORY_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showAuthorizedSignatoryContactForm", method = RequestMethod.GET)
	public ModelAndView showAuthorizedSignatoryContactForm(HttpSession session) {
		logger.debug(">>Show Authorized Signatory Contact Form");
		Company company = getCompanyFromSession();
		ModelAndView modelView = new ModelAndView("companyform");
		AuthorizedSignatoryContactForm authorizedSignatoryContactForm = companyService
				.getAuthorizedSignatoryContactForm(company);
		modelView.addObject("authorizedSignatoryContactForm", authorizedSignatoryContactForm);
		logger.debug("Shown>>");
		return modelView;
	}

	@RequestMapping(value = "/addOrUpdateAuthorizedSignatoryContact", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addOrUpdateAuthorizedSignatoryContact(
			@Valid AuthorizedSignatoryContactForm authorizedSignatoryContactForm,
			BindingResult bindingResult, HttpSession session) {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">> addOrUpdateAuthorizedSignatoryContact");
		if (!bindingResult.hasErrors()) {
			try {
				Company company = companyService.addOrUpdateAuthorizedSignatoryContact(
						getCompanyFromSession(), authorizedSignatoryContactForm);
				session.setAttribute("company", company);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}

		} else {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		}
		logger.debug("addOrUpdateAuthorizedSignatoryContact >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).PRIMARY_CONTACT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showPrimaryContactForm", method = RequestMethod.GET)
	public ModelAndView showPrimaryContactForm(HttpSession session) {
		logger.debug(">>Show Primary Contact Form");
		Company company = getCompanyFromSession();
		ModelAndView modelView = new ModelAndView("companyform");
		PrimaryContactForm primaryContactForm = companyService.getPrimaryContactForm(company);
		logger.debug("primaryContactForm.." + primaryContactForm);
		modelView.addObject("primaryContactForm", primaryContactForm);
		logger.debug("Show Primary Contact Form>>");
		return modelView;
	}

	@RequestMapping(value = "/addOrUpdatePrimaryContact", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addOrUpdatePrimaryContact(@Valid PrimaryContactForm primaryContactForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> addOrUpdatePrimaryContact");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				Company company = companyService.addOrUpdatePrimaryContact(getCompanyFromSession(),
						primaryContactForm);
				session.setAttribute("company", company);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}

		} else {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		}
		logger.debug("addOrUpdatePrimaryContact >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).BILLING_CONTACT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showBillingContactForm", method = RequestMethod.GET)
	public ModelAndView showBillingContactForm(HttpSession session) {
		logger.debug(">>show Billing Contact Form");
		Company company = getCompanyFromSession();
		ModelAndView modelView = new ModelAndView("companyform");
		BillingContactForm billingContactForm = companyService.getBillingContactForm(company);
		logger.debug("billingContactForm::" + billingContactForm);
		modelView.addObject("billingContactForm", billingContactForm);
		logger.debug("Show Billing Contact Form>>");
		return modelView;
	}

	@RequestMapping(value = "/addOrUpdateBillingContact", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addOrUpdateBillingContact(
			@ModelAttribute @Valid BillingContactForm billingContactForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> addOrUpdateBillingContact");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				Company company = companyService.addOrUpdateBillingContact(getCompanyFromSession(),
						billingContactForm);
				session.setAttribute("company", company);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Information Added Successfully ");
				serverResponse.setData(company.getId());
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}
		} else {
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setErrorMessages(ErrorUtils.getErrorMessages(bindingResult
					.getFieldErrors()));
		}
		logger.debug("addOrUpdateBillingContact >>");
		return serverResponse;
	}

}