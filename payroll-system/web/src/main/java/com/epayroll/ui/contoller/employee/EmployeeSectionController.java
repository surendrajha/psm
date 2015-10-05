package com.epayroll.ui.contoller.employee;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.entity.Company;
import com.epayroll.form.employee.EmployeeSectionForm;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.spring.authorization.AuthorizationUtil;

/**
 * @author Uma
 */
@RequestMapping("/employee")
@Controller
public class EmployeeSectionController {
	private Logger logger = LoggerFactory.getLogger(EmployeeSectionController.class);

	@Autowired
	private EmployeeService employeeService;

	private Company getCompanyFromSession() {
		logger.debug(">> getCompanyFromSession");
		return AuthorizationUtil.getLoginCompany();
	}

	@RequestMapping(value = "/showEmployeeSections", method = RequestMethod.GET)
	public ModelAndView showEmployeeSections() {
		logger.debug("showEmployeeSections");
		ModelAndView modelView = new ModelAndView("employeeSectionView");
		modelView.addObject("sectionList", employeeService.getEmployeeSections());
		modelView.addObject("employeeSectionForm",
				employeeService.getEmployeeSectionForm(getCompanyFromSession()));
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeSections", method = RequestMethod.POST)
	public ModelAndView addEmployeeSections(
			@ModelAttribute EmployeeSectionForm employeeSectionForm, HttpServletRequest request) {
		logger.debug(">> add Employee Sections");
		logger.debug("employeeSectionForm:::::::" + employeeSectionForm);
		ModelAndView modelView = new ModelAndView("employeeSectionView");
		employeeService.addEmployeeSections(employeeSectionForm, getCompanyFromSession().getId());
		modelView.setView(new RedirectView(request.getContextPath()
				+ "/employee/employeeSectionView"));
		logger.debug("add Employee Sections >>");
		return modelView;
	}
}
