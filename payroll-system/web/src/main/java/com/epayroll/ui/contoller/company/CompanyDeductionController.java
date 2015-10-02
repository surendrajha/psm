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
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyDeductionForm;
import com.epayroll.service.company.CompanyService;

/**
 * @author Rajul Tiwari
 */
@Controller
public class CompanyDeductionController {
	private Logger logger = LoggerFactory.getLogger(CompanyDeductionController.class);
	@Autowired
	private CompanyService companyService;

	private Company getCompanyFromSession(HttpSession session) {
		logger.debug(">> getCompanyFromSession");
		User user = (User) session.getAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("Company " + company);
		logger.debug("getCompanyFromSession >>");
		return company;
	}

	// TODO Validation
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/company/deduction/showList", method = RequestMethod.GET)
	public ModelAndView showList(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("companyDeductionList",
					companyService.getCompanyDeductions(getCompanyFromSession(session)));
			modelView.setViewName("companyDeductionListPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("showList >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/company/deduction/getDeductions/{deductionCategoryId}", method = RequestMethod.GET)
	public ModelAndView getDeductions(@PathVariable Long deductionCategoryId) {
		logger.debug(">> getDeductions");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("deductionList", companyService.getDeductions(deductionCategoryId));
			modelView.setViewName("companyDeductionForm");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("getDeductions >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/company/deduction/showAddForm", method = RequestMethod.GET)
	public ModelAndView showAddForm() {
		logger.debug(">> showAddForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("companyDeductionForm", new CompanyDeductionForm());
		modelView.addObject("deductionCategoryList", companyService.getDeductionCategories());
		modelView.setViewName("companyDeductionForm");
		logger.debug("showAddForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/company/deduction/add", method = RequestMethod.POST)
	public ModelAndView add(@Valid @ModelAttribute CompanyDeductionForm companyDeductionForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> add");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				companyService.addCompanyDeduction(getCompanyFromSession(session),
						companyDeductionForm);
				modelView.setViewName("companyDeductionListPage");
				result = "Deduction Added Successfully";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				result = "Some error occurred while adding the record : " + e.getMessage();
			}
		} else {
			modelView.setViewName("companyDeductionForm");
			logger.error("Validation Error" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("add >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/company/deduction/showUpdateForm/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateForm(@PathVariable Long id) {
		logger.debug(">> showUpdateForm");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("companyDeductionForm", companyService.getCompanyDeductionForm(id));
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		modelView.setViewName("companyDeductionForm");
		logger.debug("showUpdateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/company/deduction/updateCompanyDeductionDisplayName", method = RequestMethod.POST)
	public ModelAndView updateCompanyDeductionDisplayName(
			@Valid @ModelAttribute CompanyDeductionForm companyDeductionForm,
			BindingResult bindingResult) {
		logger.debug(">> updateCompanyDeductionDisplayName");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		if (!bindingResult.hasErrors()) {
			try {
				companyService.updateCompanyDeductionDisplayName(companyDeductionForm);
				result = "Deduction Updated Successfully";
				modelView.setViewName("companyDeductionListPage");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				result = "Some error occurred while updating the record : " + e.getMessage();
			}
		} else {
			modelView.setViewName("companyDeductionForm");
			logger.error("Validation Error" + bindingResult.toString());
			result = "Validation Error";
		}
		modelView.addObject("result", result);
		logger.debug("updateCompanyDeductionDisplayName >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEDUCTION, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/company/deduction/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id) {
		logger.debug(">> delete");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (companyService.verifyAndDeleteCompanyDeduction(id)) {
				result = "Record Deleted Successfully";
			} else {
				result = "Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.";
			}
			modelView.setViewName("companyDeductionListPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}

		modelView.addObject("result", result);
		logger.debug("delete >>");
		return modelView;
	}

}