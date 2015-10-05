package com.epayroll.service.employeeAccess;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.SecurityQuestionDao;
import com.epayroll.dao.employee.EmployeeDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep2Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep2Form;
import com.epayroll.form.employeeAccess.EmployeeAccessLoginForm;
import com.epayroll.form.employeeAccess.EmployeeAccessRegistrationForm;
import com.epayroll.utils.RandomNumberUtils;

/**
 * 
 * @author Uma
 * 
 */
@Service
public class EmployeeAccessLoginServiceImpl implements EmployeeAccessLoginService {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccessLoginServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private SecurityQuestionDao securityQuestionDao;

	@Override
	@Transactional
	public boolean checkTemporaryPassword(Long companyId, String password) {
		Employee employee = employeeDao.checkTemporaryPassword(companyId, password);
		if (employee == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	@Transactional
	public Employee checkUserId(String userName) {
		Employee employee = employeeDao.checkUserId(userName);
		return employee;
	}

	@Override
	public List<SecurityQuestion> getSecurityQuestions() {

		return securityQuestionDao.getQuestionsFromGroup1();
	}

	@Override
	@Transactional
	public Long registration(EmployeeAccessRegistrationForm employeeAccessRegistrationForm)
			throws InstanceNotFoundException {
		Employee employee = employeeDao.find(employeeAccessRegistrationForm.getEmployeeId());
		employee.setUserName(employeeAccessRegistrationForm.getUserId());
		employee.setPassword(employeeAccessRegistrationForm.getPassword());
		SecurityQuestion securityQuestion = securityQuestionDao.find(employeeAccessRegistrationForm
				.getSecurityQuestionId());
		employee.setSecurityAnswer(securityQuestion.getQuestion());
		employee.setSecurityAnswer(employeeAccessRegistrationForm.getAnswer());
		employeeDao.update(employee);
		return employeeAccessRegistrationForm.getEmployeeId();

	}

	@Override
	@Transactional
	public Employee checkLogin(EmployeeAccessLoginForm employeeAccessLoginForm)
			throws InstanceNotFoundException {
		Employee employee = employeeDao.checkLogin(employeeAccessLoginForm.getUserName(),
				employeeAccessLoginForm.getPassword());
		if (employee != null) {
			return null;
		} else {
			return employee;
		}
	}

	@Override
	public EmployeeAccessForgotUserIdStep2Form getEmployeeAccessForgotUserIdStep2Form(
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form)
			throws InstanceNotFoundException {
		logger.debug(">> getEmployeeAccessForgotUserIdStep2Form");
		EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form = new EmployeeAccessForgotUserIdStep2Form();
		Employee employee = getEmployeeFromEmailId(employeeAccessForgotUserIdStep1Form.getEmailId());
		if (employee != null) {
			employeeAccessForgotUserIdStep2Form = loadUserQuestionsIntoForm(
					employeeAccessForgotUserIdStep2Form, employee);
		} else {
			logger.debug("getSecurityQuestionsForUserId >>");
			return null;
		}
		logger.debug("getEmployeeAccessForgotUserIdStep2Form >>");
		return employeeAccessForgotUserIdStep2Form;
	}

	private EmployeeAccessForgotUserIdStep2Form loadUserQuestionsIntoForm(
			EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form,
			Employee employee) {
		logger.debug(">> loadUserQuestionsIntoForm");
		employeeAccessForgotUserIdStep2Form.setSecurityQuestion1(employee.getSecurityQuestion());
		logger.debug("loadUserQuestionsIntoForm >>");
		return employeeAccessForgotUserIdStep2Form;

	}

	@Override
	public Employee getEmployeeFromEmailId(String emailId) throws InstanceNotFoundException {
		logger.debug(">> getEmployee");
		Employee employee = employeeDao.getEmployeeForSentEmailFromEmailId(emailId);
		logger.debug("getEmployee >>");
		return employee;
	}

	@Override
	public Employee getEmployeeFromUserName(String userName) throws InstanceNotFoundException {
		logger.debug(">> getEmployee");
		Employee employee = employeeDao.getEmployeeForSentEmailFromUserName(userName);
		logger.debug("getEmployee >>");
		return employee;
	}

	@Override
	public String checkSecurityAnswersAndGetUserIdForUserId(
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form,
			EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form)
			throws InstanceNotFoundException {
		logger.debug(">> checkSecurityAnswersAndGetUserIdForUserId");
		String userName = null;
		Employee employee = getEmployeeFromEmailId(employeeAccessForgotUserIdStep1Form.getEmailId());
		if (employee != null) {
			if (isSecurityAnswersValidMethod(employeeAccessForgotUserIdStep2Form, employee)) {
				userName = employee.getUserName();
			}
		}
		logger.debug("checkSecurityAnswersAndGetUserIdForUserId >>");
		return userName;
	}

	private boolean isSecurityAnswersValidMethod(
			EmployeeAccessForgotUserIdStep2Form employeeAccessForgotUserIdStep2Form,
			Employee employee) {
		logger.debug(">> isSecurityAnswersValidMethod");
		if (employeeAccessForgotUserIdStep2Form.getSecurityAnswer1().equalsIgnoreCase(
				employee.getSecurityAnswer())) {
			return true;
		} else {
			logger.debug("isSecurityAnswersValidMethod >>");
			return false;
		}
	}

	@Override
	public EmployeeAccessForgotPasswordStep2Form getEmployeeAccessForgotPasswordStep2Form(
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form)
			throws InstanceNotFoundException {
		logger.debug(">> getEmployeeAccessForgotPasswordStep2Form");
		EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form = new EmployeeAccessForgotPasswordStep2Form();
		Employee employee = getEmployeeFromUserName(employeeAccessForgotPasswordStep1Form
				.getUserId());
		if (employee != null) {
			employeeAccessForgotPasswordStep2Form = loadUserQuestionsIntoForm(
					employeeAccessForgotPasswordStep2Form, employee);
		} else {
			logger.debug("getSecurityQuestionsForUserId >>");
			return null;
		}
		logger.debug("getEmployeeAccessForgotPasswordStep2Form >>");
		return employeeAccessForgotPasswordStep2Form;
	}

	private EmployeeAccessForgotPasswordStep2Form loadUserQuestionsIntoForm(
			EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form,
			Employee employee) {
		logger.debug(">> loadUserQuestionsIntoForm");
		employeeAccessForgotPasswordStep2Form.setSecurityQuestion1(employee.getSecurityQuestion());
		logger.debug("loadUserQuestionsIntoForm >>");
		return employeeAccessForgotPasswordStep2Form;

	}

	@Override
	public String checkSecurityAnswersAndGetEmailForPassword(
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form,
			EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form)
			throws InstanceNotFoundException {
		logger.debug(">> checkSecurityAnswersAndGetUserIdForUserId");
		String emailId = null;
		Employee employee = getEmployeeFromUserName(employeeAccessForgotPasswordStep1Form
				.getUserId());
		if (employee != null) {
			if (isSecurityAnswersValidMethod(employeeAccessForgotPasswordStep2Form, employee)) {
				employee.setForgetProcessInitiated(true);
				emailId = employee.getWorkEmail();
			}
		}
		logger.debug("checkSecurityAnswersAndGetUserIdForUserId >>");
		return emailId;
	}

	private boolean isSecurityAnswersValidMethod(
			EmployeeAccessForgotPasswordStep2Form employeeAccessForgotPasswordStep2Form,
			Employee employee) {
		logger.debug(">> isSecurityAnswersValidMethod");
		if (employeeAccessForgotPasswordStep2Form.getSecurityAnswer1().equalsIgnoreCase(
				employee.getSecurityAnswer())) {
			return true;
		} else {
			logger.debug("isSecurityAnswersValidMethod >>");
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean changePassword(Employee employee, ResetPasswordForm resetPasswordForm) {
		logger.debug(">> changePassword");
		Boolean result = false;
		if (resetPasswordForm.getNewPassword().equals(resetPasswordForm.getConfirmPassword())) {

			// companyUser
			// .setPassword(passwordEncryption(companyUserResetPasswordForm
			// .getNewPassword()));
			employee.setPassword(RandomNumberUtils.encode(resetPasswordForm.getNewPassword()));
			employee.setForgetProcessInitiated(false);
			employeeDao.update(employee);
			result = true;
		}
		logger.debug("changePassword >>");
		return result;
	}

}
