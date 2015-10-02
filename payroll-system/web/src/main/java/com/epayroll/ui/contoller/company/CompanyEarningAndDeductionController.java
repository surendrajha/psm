package com.epayroll.ui.contoller.company;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyDeductionForm;
import com.epayroll.form.company.CompanyEarningForm;
import com.epayroll.model.ServerResponse;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.utils.ErrorUtils;

/**
 * @author Rajul Tiwari
 */
@Controller
@RequestMapping("/company/earningAndDeduction")
public class CompanyEarningAndDeductionController {
	private Logger logger = LoggerFactory.getLogger(CompanyEarningAndDeductionController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

	private Company getCompanyFromSession() {
		Company company = commonService.getCompany(AuthorizationUtil.getLoginUser().getId());
		return company;
	}

	// TODO Validation

	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public ModelAndView showList(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView("companyEarningAndDeduction");
		try {
			modelView.addObject("companyEarningList",
					companyService.getCompanyEarnings(getCompanyFromSession()));
			modelView.addObject("companyDeductionList",
					companyService.getCompanyDeductions(getCompanyFromSession()));
			modelView.addObject("companyDeductionForm", new CompanyDeductionForm());
			// modelView.setViewName();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("showList >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/getEarnings/{earningCategoryId}", method = RequestMethod.GET)
	public ModelAndView getEarnings(@PathVariable Long earningCategoryId) {
		logger.debug(">> getEarnings");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("earningList", companyService.getEarnings(earningCategoryId));
			modelView.setViewName("companyEarningForm");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("getEarnings >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showAddEarningForm", method = RequestMethod.GET)
	public ModelAndView showAddEarningForm() {
		logger.debug(">> showAddEarningForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("companyEarningForm", new CompanyEarningForm());
		modelView.addObject("earningCategoryList", companyService.getEarningCategories());
		modelView.setViewName("companyEarningForm");
		logger.debug("showAddEarningForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEarning", method = RequestMethod.POST)
	public ModelAndView addEarning(@Valid @ModelAttribute CompanyEarningForm companyEarningForm,
			BindingResult bindingResult) {
		logger.debug(">> addEarning");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				companyService.addCompanyEarning(getCompanyFromSession(), companyEarningForm);
				result = "Earning Added Successfully";
				modelView.setViewName("companyEarningListPage");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				result = "Some error occurred while adding the record : " + e.getMessage();
			}
		} else {
			modelView.setViewName("companyEarningForm");
			logger.error("Validation Error" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("addEarning >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateEarningForm/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateEarningForm(@PathVariable Long id) {
		logger.debug(">> showUpdateEarningForm");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("companyEarningForm", companyService.getCompanyEarningForm(id));
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		modelView.setViewName("companyEarningForm");
		logger.debug("showUpdateEarningForm >>");
		return modelView;
	}

	// for Update Company earning display name only
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEarningDisplayName", method = RequestMethod.POST)
	public ModelAndView updateEarningDisplayName(
			@Valid @ModelAttribute CompanyEarningForm companyEarningForm,
			BindingResult bindingResult) {
		logger.debug(">> updateEarningDisplayName");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				companyService.updateCompanyEarningDisplayName(companyEarningForm);
				result = "Earning Updated Successfully";
				modelView.setViewName("companyEarningListPage");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				result = "Some error occurred while updating the record : " + e.getMessage();
			}
		} else {
			modelView.setViewName("companyEarningForm");
			logger.error("Validation Error" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("updateEarningDisplayName >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_EARNING, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteEarning/{id}", method = RequestMethod.GET)
	public ModelAndView deleteEarning(@PathVariable Long id) {
		logger.debug(">> deleteEarning");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (companyService.verifyAndDeleteCompanyEarning(id)) {

				result = "Record Deleted Successfully";
			} else {
				result = "Payroll Records are associated with this Earning. So you cannot delete this Earning from system. If you don't want to use this earning for future payrolls you can make it inactive.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		modelView.setViewName("companyEarningListPage");
		logger.debug("deleteEarning >>");
		return modelView;
	}

	@RequestMapping(value = "/getDeductions", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getDeductions(@RequestParam Long deductionCategoryId) {
		logger.debug(">> getDeductions");
		ServerResponse serverResponse = new ServerResponse();
		try {
			List<Deduction> deductionList = companyService.getDeductions(deductionCategoryId);
			logger.debug("deductionList : " + deductionList);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(deductionList);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("getDeductions >>");
		return serverResponse;
	}

	@RequestMapping(value = "/getDeductionDescription", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getDeductionDescription(@RequestParam Long deductionId) {
		logger.debug(">> getDeductionDescription");
		ServerResponse serverResponse = new ServerResponse();
		try {
			Deduction deduction = companyService.getDeduction(deductionId);
			logger.debug("deduction : " + deduction);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(deduction);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("getDeductionDescription >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showAddDeductionForm", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse showAddDeductionForm() {
		logger.debug(">> showAddDeductionForm");
		ServerResponse serverResponse = new ServerResponse();

		try {
			List<DeductionCategory> deductionCategoryList = companyService.getDeductionCategories();
			logger.debug("deductionCategoryList : " + deductionCategoryList);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(deductionCategoryList);
		} catch (Exception e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("showAddDeductionForm >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@ResponseBody
	@RequestMapping(value = "/addDeduction", method = RequestMethod.POST)
	public ServerResponse addDeduction(@Valid CompanyDeductionForm companyDeductionForm,
			BindingResult bindingResult) {
		logger.debug(">> addDeduction");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				CompanyDeduction companyDeduction = companyService.addCompanyDeduction(
						getCompanyFromSession(), companyDeductionForm);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Deduction Added Successfully");
				serverResponse.setData(companyDeduction.getId());
			} catch (Exception e) {
				logger.error("ERROR :: " + e);
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage(e.getMessage());
			}
		} else {
			logger.error("Validation Error" + bindingResult.toString());
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("addDeduction >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateDeductionForm", method = RequestMethod.GET)
	@ResponseBody
	public CompanyDeductionForm showUpdateDeductionForm(@RequestParam Long id) {
		logger.debug(">> showUpdateDeductionForm");
		CompanyDeductionForm companyDeductionForm = null;
		try {
			companyDeductionForm = companyService.getCompanyDeductionForm(id);

		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		logger.debug("showUpdateDeductionForm >>");
		return companyDeductionForm;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateCompanyDeductionDisplayName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServerResponse updateCompanyDeductionDisplayName(
			@Valid CompanyDeductionForm companyDeductionForm, BindingResult bindingResult) {
		logger.debug(">> updateCompanyDeductionDisplayName");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				CompanyDeduction companyDeduction = companyService
						.updateCompanyDeductionDisplayName(companyDeductionForm);
				serverResponse.setMessage("Deduction Updated Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setData(companyDeduction);
				logger.debug("serverResponse.getdata()" + serverResponse.getData());
			} catch (Exception e) {
				logger.error("ERROR :: " + e);
				serverResponse.setMessage("Some error occurred while updating the record : "
						+ e.getMessage());
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} else {
			logger.error("Validation Error" + bindingResult.toString());
			serverResponse.setMessage(ErrorUtils.getValidationErrorMessage(bindingResult
					.getAllErrors()));
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("updateCompanyDeductionDisplayName >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteDeduction", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse deleteDeduction(@RequestParam Long id) {
		logger.debug(">> deleteDeduction");
		ServerResponse serverResponse = new ServerResponse();
		try {
			if (companyService.verifyAndDeleteCompanyDeduction(id)) {
				serverResponse.setMessage("Record Deleted Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
			} else {
				serverResponse
						.setMessage("Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.");
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			serverResponse.setMessage("Some error occurred while deleting the record : "
					+ e.getMessage());
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("deleteDeduction >>");
		return serverResponse;
	}

}