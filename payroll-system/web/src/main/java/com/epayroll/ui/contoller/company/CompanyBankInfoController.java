package com.epayroll.ui.contoller.company;

import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyBankInfo;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.service.company.CompanyService;

/**
 * @author Rajul Tiwari
 */
@Controller
public class CompanyBankInfoController {
	private Logger logger = LoggerFactory.getLogger(CompanyBankInfoController.class);

	@Autowired
	private CompanyService companyService;

	private Company getCompanyFromSession(HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		return company;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_BANK_INFORMATION, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/company/bankInfo/show", method = RequestMethod.GET)
	public ModelAndView show(HttpSession session) {
		logger.debug(">> showBankInfo");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("companyBankInfo",
					companyService.getCompanyBankInfo(getCompanyFromSession(session)));
			modelView.setViewName("companyBankInfoForm");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("showBankInfo >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_BANK_INFORMATION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/company/bankInfo/addCompanyBankInfo", method = RequestMethod.POST)
	public ModelAndView addCompanyBankInfo(@ModelAttribute @Valid CompanyBankInfo companyBankInfo,
			BindingResult bindingResult, HttpSession session) throws InstanceNotFoundException {

		logger.debug(">> addCompanyBankInfo");
		logger.debug("CompanyBankInfo :: " + companyBankInfo);
		ModelAndView modelView = new ModelAndView("companyBankInfoForm");
		if (!bindingResult.hasErrors()) {
			Company company = getCompanyFromSession(session);
			companyService.addBankInfo(companyBankInfo, company);
			modelView.addObject("companyBankInfoForm", companyBankInfo);
		} else {
			logger.error("Validation Error :: " + bindingResult.toString());
		}
		logger.debug("addCompanyBankInfo >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_BANK_INFORMATION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/company/bankInfo/showUpdateCompanyBankInfo/{companyBankInfoId}", method = RequestMethod.GET)
	public ModelAndView showUpdateCompanyBankInfo(@PathVariable Long companyBankInfoId)
			throws InstanceNotFoundException {
		logger.debug(">> showUpdateCompanyBankInfo");
		ModelAndView modelView = new ModelAndView("companyBankInfoForm");
		CompanyBankInfo cBankInfo = companyService.getBankInfo(companyBankInfoId);
		modelView.addObject("companyBankInfo", cBankInfo);

		logger.debug("showUpdateCompanyBankInfo >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_BANK_INFORMATION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/company/bankInfo/updateCompanyBankInfo", method = RequestMethod.POST)
	public ModelAndView updateCompanyBankInfo(
			@ModelAttribute @Valid CompanyBankInfo companyBankInfo, BindingResult bindingResult,
			HttpSession session) throws InstanceNotFoundException {
		ModelAndView modelView = new ModelAndView("companyBankInfoForm");
		logger.debug(">> updateCompanyBankInfo");
		if (!bindingResult.hasErrors()) {
			companyBankInfo.setCompany(getCompanyFromSession(session));
			companyService.updateCompanyBankInfo(companyBankInfo);
		} else {
			logger.error("Validation Error :: " + bindingResult.toString());
		}
		logger.debug("updateCompanyBankInfo >>");
		return modelView;
	}

}