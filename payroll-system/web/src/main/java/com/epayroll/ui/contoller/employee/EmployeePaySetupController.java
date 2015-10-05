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
import com.epayroll.entity.Employee.Type;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorPaySetupForm;
import com.epayroll.form.employee.EmployeePaySetupForm;
import com.epayroll.service.employee.EmployeePaySetupService;
import com.epayroll.spring.authorization.AuthorizationUtil;

@Controller
@RequestMapping("/employee")
public class EmployeePaySetupController {

	private Logger logger = LoggerFactory.getLogger(EmployeePaySetupController.class);

	@Autowired
	private EmployeePaySetupService employeePaySetupService;

	@RequestMapping(value = "/getPaySetup", method = RequestMethod.GET)
	public ModelAndView getPaySetup() {
		Employee employee = getEmployeeFromSession();
		logger.info("Getting pay-setup for employee");
		logger.debug(" >> getPaySetup employee ID : " + employee.getId());
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.addObject("payTypeList", employeePaySetupService.getPayTypes());
			modelAndView.addObject("payCycleList", employeePaySetupService.getPayCycles(employee));
			if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
				modelAndView.setViewName("employeePaySetup");

				modelAndView.addObject("employeePaySetupForm",
						employeePaySetupService.getEmployeePaySetup(employee));
			}
			if (employee.getEmployeeType().equals(Type.CONTRACTOR)) {
				modelAndView.setViewName("contractorPaySetup");
				modelAndView.addObject("contractorPaySetupForm",
						employeePaySetupService.getContractorPaySetup(employee));
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in getPaySetup:", e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/updateEmployeePaySetup", method = RequestMethod.POST)
	public ModelAndView updateEmployeePaySetup(
			@ModelAttribute EmployeePaySetupForm employeePaySetupForm) {
		logger.info("Updating pay-setup for employee");
		logger.debug(" >> updateEmployeePaySetup");
		ModelAndView modelAndView = new ModelAndView("employeePaySetup");
		employeePaySetupService.updateEmployeePaySetup(employeePaySetupForm,
				getEmployeeFromSession());
		return modelAndView;
	}

	@RequestMapping(value = "/updateContractorPaySetup", method = RequestMethod.POST)
	public ModelAndView updateContractorPaySetup(
			@ModelAttribute ContractorPaySetupForm contractorPaySetupForm) {
		logger.info("Updating pay-setup for Contractor");
		logger.debug(" >> gupdateContractorPaySetup");
		ModelAndView modelAndView = new ModelAndView("contractorPaySetup");
		employeePaySetupService.updateContractorPaySetup(contractorPaySetupForm,
				getEmployeeFromSession());
		return modelAndView;
	}

	private Employee getEmployeeFromSession() {
		logger.debug(" >> getEmployeeFromSession");
		/*
		 * Getting employee from the SessionContext
		 */
		return (Employee) AuthorizationUtil.getSessionAttribute("employee");

		// this is temporary
		// try {
		// return employeeService.getEmployee(1L);
		// } catch (InstanceNotFoundException e) {
		// e.printStackTrace();
		// }
		// return null;
		// end
	}
}
