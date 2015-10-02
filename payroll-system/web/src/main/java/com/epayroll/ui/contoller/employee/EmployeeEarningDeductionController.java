package com.epayroll.ui.contoller.employee;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
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
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeDeductionForm;
import com.epayroll.form.employee.EmployeeEarningForm;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.spring.authorization.AuthorizationUtil;

/**
 * @author Uma
 */
// @RequestMapping("/employee")
@Controller
public class EmployeeEarningDeductionController {
	private Logger logger = LoggerFactory.getLogger(EmployeeEarningDeductionController.class);

	@Autowired
	private EmployeeService employeeService;

	private Company getCompanyFromSession() {
		logger.debug(">> getCompanyFromSession");
		User user = (User) AuthorizationUtil.getSessionAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("getCompanyFromSession >>");
		return company;
	}

	private Employee getEmployeeFromSession() {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = (Employee) AuthorizationUtil.getSessionAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showEmployeeEarningsAndDeductionAddForm/{taskId}", method = RequestMethod.GET)
	public ModelAndView showEmployeeEarningsAndDeductionAddForm(@PathVariable Long taskId,
			HttpSession session) {
		logger.debug(">> show Employee Earnings And Deduction Add Form");
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		Employee employee = getEmployeeFromSession();
		session.setAttribute("taskId", taskId);
		if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
			EmployeeEarningForm employeeEarningForm = new EmployeeEarningForm();
			employeeEarningForm.setEmployeeId(employee.getId());
			modelView.addObject("employeeEarningValueTypeList",
					employeeService.getEarningValueType());
			logger.debug("employeeEarningForm::::::" + employeeEarningForm);
			modelView.addObject("employeeEarningForm", employeeEarningForm);
		}
		if (employee.getEmployeeType().equals(Type.EMPLOYEE)
				|| employee.getEmployeeType().equals(Type.CONTRACTOR)) {
			EmployeeDeductionForm employeeDeductionForm = new EmployeeDeductionForm();
			employeeDeductionForm.setEmployeeId(employee.getId());
			modelView.addObject("employeeDeductionValueTypeList",
					employeeService.getDeductionValueType());
			modelView.addObject("employeeDeductionTypeList", employeeService.getDeductionType());
			logger.debug("employeeDeductionForm::::::" + employeeDeductionForm);
			modelView.addObject("employeeDeductionForm", employeeDeductionForm);
		}
		logger.debug(" show Employee Earnings And Deduction Add Form>>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeEarning", method = RequestMethod.POST)
	public ModelAndView addEmployeeEarning(
			@Valid @ModelAttribute EmployeeEarningForm employeeEarningForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		Company company = getCompanyFromSession();
		logger.debug(">> add Employee Earning");
		logger.debug("employeeEarningForm:::::::" + employeeEarningForm);
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			employeeService.addEmployeeEarnings(employeeEarningForm, company, employeeId);
			modelView.addObject("employeeEarningForm", employeeEarningForm);
			logger.debug("add Employee Earning >>");
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeEarningAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addEmployeeEarningAndCompleteLater(
			@Valid @ModelAttribute EmployeeEarningForm employeeEarningForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		Company company = getCompanyFromSession();
		logger.debug(">> add Employee Earning");
		logger.debug("employeeEarningForm:::::::" + employeeEarningForm);
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			employeeService.addEmployeeEarnings(employeeEarningForm, company, employeeId);
			modelView.addObject("employeeEarningForm", employeeEarningForm);
			logger.debug("add Employee Earning >>");
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeDeduction", method = RequestMethod.POST)
	public ModelAndView addEmployeeDeduction(
			@Valid @ModelAttribute EmployeeDeductionForm employeeDeductionForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> add Employee Deduction");
		Company company = getCompanyFromSession();
		logger.debug("employeeDeductionForm:::::::" + employeeDeductionForm);
		ModelAndView modelView = new ModelAndView("employeeDeductionView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			employeeService.addEmployeeDeductions(employeeDeductionForm, company, employeeId);
			modelView.addObject("employeeDeductionForm", employeeDeductionForm);
			logger.debug(" add Employee Deduction >>");
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showDirectDepositForm";
			employeeService.updateTask(taskId, url);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeDeductionAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addEmployeeDeductionAndCompleteLater(
			@Valid @ModelAttribute EmployeeDeductionForm employeeDeductionForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> add Employee Deduction");
		Company company = getCompanyFromSession();
		logger.debug("employeeDeductionForm:::::::" + employeeDeductionForm);
		ModelAndView modelView = new ModelAndView("employeeDeductionView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			employeeService.addEmployeeDeductions(employeeDeductionForm, company, employeeId);
			modelView.addObject("employeeDeductionForm", employeeDeductionForm);
			logger.debug(" add Employee Deduction >>");
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showDirectDepositForm";
			employeeService.updateTask(taskId, url);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	@RequestMapping(value = "/showEmployeeEarningsAndDeduction", method = RequestMethod.GET)
	public ModelAndView showEmployeeEarningsAndDeduction(HttpSession session) {
		logger.debug(">> show Employee Earnings And Deduction");
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		try {
			Employee employee = getEmployeeFromSession();

			if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
				EmployeeEarningForm employeeEarningForm = employeeService
						.getEmployeeEarningForm(employee);

				logger.debug("employeeEarningForm::::::" + employeeEarningForm);
				modelView.addObject("employeeEarningForm", employeeEarningForm);
			}
			if (employee.getEmployeeType().equals(Type.EMPLOYEE)
					|| employee.getEmployeeType().equals(Type.CONTRACTOR)) {
				EmployeeDeductionForm employeeDeductionForm = employeeService
						.getEmployeeDeductionForm(employee);
				logger.debug("employeeDeductionForm::::::" + employeeDeductionForm);
				modelView.addObject("employeeDeductionForm", employeeDeductionForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug(" show Employee Earnings And Deduction >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateEmployeeEarningForm/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployeeEarningForm(@PathVariable Long id, HttpSession session) {
		logger.debug(">> Show Update EmployeeEarning Form");
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		try {
			EmployeeEarning employeeEarning = employeeService.getEmployeeEearnings(id);
			EmployeeEarningForm employeeEarningForm = employeeService
					.getEmployeeEarningUpdateForm(employeeEarning);
			modelView.addObject("earningValueTypeList", employeeService.getEarningValueType());
			logger.debug("employeeEarningForm::::::" + employeeEarningForm);
			modelView.addObject("employeeEarningForm", employeeEarningForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("  Show Update EmployeeEarning Form >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateEmployeeDeductionForm/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateEmployeeDeductionForm(@PathVariable Long id, HttpSession session) {
		logger.debug(">> show Update Employee Deduction Form");
		ModelAndView modelView = new ModelAndView("employeeDeductionView");
		try {
			EmployeeDeduction employeeDeduction = employeeService.getEmployeeDeductions(id);
			EmployeeDeductionForm employeeDeductionForm = employeeService
					.getEmployeeDeductionUpdateForm(employeeDeduction);
			modelView.addObject("deductionValueTypeList", employeeService.getDeductionValueType());
			logger.debug("employeeDeductionForm::::::" + employeeDeductionForm);
			modelView.addObject("employeeDeductionForm", employeeDeductionForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug(" show Update Employee Deduction Form >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEmployeeEarning", method = RequestMethod.POST)
	public ModelAndView updateEmployeeEarning(
			@Valid @ModelAttribute EmployeeEarningForm employeeEarningForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> update Employee Earning");
		logger.debug("employeeEarningForm:::::::" + employeeEarningForm);
		ModelAndView modelView = new ModelAndView("employeeEarningView");
		employeeService.updateEmployeeEarnings(employeeEarningForm);
		modelView.addObject("employeeEarningForm", employeeEarningForm);
		logger.debug("update Employee Earning >>");
		return modelView;

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEmployeeDeduction", method = RequestMethod.POST)
	public ModelAndView updateEmployeeDeduction(
			@Valid @ModelAttribute EmployeeDeductionForm employeeDeductionForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> update Employee Deduction");
		logger.debug("employeeDeductionForm:::::::" + employeeDeductionForm);
		ModelAndView modelView = new ModelAndView("employeeDeductionView");
		employeeService.updateEmployeeDeductions(employeeDeductionForm);
		modelView.addObject("employeeDeductionForm", employeeDeductionForm);
		logger.debug(" update Employee Deduction >>");
		return modelView;

	}

}
