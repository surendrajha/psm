package com.epayroll.ui.contoller.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.form.employee.EmployeeDepartmentAllocationForm;
import com.epayroll.service.employee.EmployeeDepartmentService;
import com.epayroll.spring.authorization.AuthorizationUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeDepartmentController {

	private Logger logger = LoggerFactory.getLogger(EmployeeDepartmentController.class);

	@Autowired
	private EmployeeDepartmentService employeeDepartmentService;

	@RequestMapping(value = "/departmentAllocation", method = RequestMethod.GET)
	// @PreAuthorize("hasPermission('" +
	// PayrollSectionName.SHOW_EMPLOYEE_DEPARTMENT
	// + "', T(com.epayroll.constant.PermissionType).VIEW)")
	public ModelAndView getDepartmentAllocation() {
		logger.debug(" >> getDepartmentAllocation..");
		ModelAndView modelAndView = new ModelAndView("departmentAllocation");
		try {
			modelAndView.addObject("allocationList", employeeDepartmentService
					.getEmployeeDepartmentAllocations(getEmployeeFromSession()));
			modelAndView.addObject("allocationForm", new EmployeeDepartmentAllocationForm());
			logger.debug(" getEmployeeDepartmentAllocation >> ");
		} catch (Exception e) {
			logger.error("Error in getDepartmentAllocation:", e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/saveDepartmentAllocation", method = RequestMethod.POST)
	public ModelAndView saveDepartmentAllocation(
			@ModelAttribute("allocationForm") EmployeeDepartmentAllocationForm employeeDepartmentAllocationForm) {
		logger.debug(" >> saveDepartmentAllocation..");
		ModelAndView modelAndView = new ModelAndView("departmentAllocation");
		employeeDepartmentService.saveEmployeeDepartmentAllocations(
				employeeDepartmentAllocationForm, getEmployeeFromSession());
		return modelAndView;
	}

	private Employee getEmployeeFromSession() {
		logger.debug(" >> getEmployeeFromSession");
		/*
		 * Getting employee from the SessionContext
		 */
		return (Employee) AuthorizationUtil.getSessionAttribute("employee");

	}

}
