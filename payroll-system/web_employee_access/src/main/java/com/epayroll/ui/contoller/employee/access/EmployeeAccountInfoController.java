package com.epayroll.ui.contoller.employee.access;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employeeAccess.ChangeEmailForm;
import com.epayroll.form.employeeAccess.ChangePasswordForm;
import com.epayroll.form.employeeAccess.ChangeSecurityQnAForm;
import com.epayroll.service.employeeAccess.EmployeeAccessService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/employeeAccess")
@Controller
public class EmployeeAccountInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccountInfoController.class);

	@Autowired
	private EmployeeAccessService employeeAccessService;

	private Employee getEmployeeFromSession(HttpSession session) {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = (Employee) session.getAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	@RequestMapping(value = "/showEmployeeInfoForm", method = RequestMethod.GET)
	public ModelAndView showEmployeeInfoForm(HttpSession session) {
		logger.debug(">> showEmployeeInfoForm");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		Employee employee = getEmployeeFromSession(session);
		modelView.addObject("employee", employee);
		List<EmployeeBankAccount> employeeBankAccountList = employeeAccessService
				.getEmployeeBankAccountInfo(employee.getId());
		modelView.addObject("employeeBankAccountList", employeeBankAccountList);
		logger.debug("showEmployeeInfoForm >>");
		return modelView;
	}

	@RequestMapping(value = "/showChangePasswordForm", method = RequestMethod.GET)
	public ModelAndView showChangePasswordForm(HttpSession session) {
		logger.debug(">> showChangePasswordForm");
		ModelAndView modelView = new ModelAndView("changePasswordForm");
		ChangePasswordForm changePasswordForm = new ChangePasswordForm();
		// TODO set id in hidden variable
		Employee employee = getEmployeeFromSession(session);
		changePasswordForm.setId(employee.getId());
		modelView.addObject("changePasswordForm", changePasswordForm);
		logger.debug("showChangePasswordForm >>");
		return modelView;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(
			@Valid @ModelAttribute ChangePasswordForm changePasswordForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> changePassword");
		ModelAndView modelView = new ModelAndView("changePasswordForm");
		if (!bindingResult.hasErrors()) {
			try {
				employeeAccessService.verifyEmployeeAndChangePassword(changePasswordForm);
				modelView.setViewName("employeeInfoForm");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR ::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("changePassword >>");
		return modelView;
	}

	@RequestMapping(value = "/showChangeEmailForm", method = RequestMethod.GET)
	public ModelAndView showChangeEmailForm(HttpSession session) {
		logger.debug(">> showChangeEmailForm");
		ModelAndView modelView = new ModelAndView("changeEmailForm");
		Employee employee = getEmployeeFromSession(session);
		ChangeEmailForm changeEmailForm = employeeAccessService.getChangeEmailForm(employee);
		// TODO set id in hidden variable
		modelView.addObject("changeEmailForm", changeEmailForm);
		logger.debug("showChangeEmailForm >>");
		return modelView;
	}

	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public ModelAndView changeEmail(@Valid @ModelAttribute ChangeEmailForm changeEmailForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> changeEmail");
		ModelAndView modelView = new ModelAndView("changeEmailForm");
		if (!bindingResult.hasErrors()) {
			try {
				employeeAccessService.verifyEmployeeAndChangeEmail(changeEmailForm);
				modelView.setViewName("employeeInfoForm");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR ::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("changeEmail >>");
		return modelView;
	}

	@RequestMapping(value = "/showChangeSecurityQnAForm", method = RequestMethod.GET)
	public ModelAndView showChangeSecurityQnAForm(HttpSession session) {
		logger.debug(">> showChangeSecurityQnAForm");
		ModelAndView modelView = new ModelAndView("changeSecurityQnAForm");
		Employee employee = getEmployeeFromSession(session);
		ChangeSecurityQnAForm changeSecurityQnAForm = employeeAccessService
				.getChangeSecurityQnAForm(employee);
		// TODO set id in hidden variable
		List<SecurityQuestion> securityQuestionList = employeeAccessService.getSecurityQuestions();
		modelView.addObject("securityQuestionList", securityQuestionList);
		modelView.addObject("changeSecurityQnAForm", changeSecurityQnAForm);
		logger.debug("showChangeSecurityQnAForm >>");
		return modelView;
	}

	@RequestMapping(value = "/changeSecurityQnA", method = RequestMethod.POST)
	public ModelAndView changeSecurityQnA(
			@Valid @ModelAttribute ChangeSecurityQnAForm changeSecurityQnAForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> changeSecurityQnA");
		ModelAndView modelView = new ModelAndView("changeSecurityQnAForm");
		if (!bindingResult.hasErrors()) {
			try {
				employeeAccessService.verifyEmployeeAndChangeSecurityQnA(changeSecurityQnAForm);
				modelView.setViewName("employeeInfoForm");
			} catch (InstanceNotFoundException e) {
				List<SecurityQuestion> securityQuestionList = employeeAccessService
						.getSecurityQuestions();
				modelView.addObject("securityQuestionList", securityQuestionList);
				logger.error("ERROR ::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("changeSecurityQnA >>");
		return modelView;
	}
}
