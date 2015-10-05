package com.epayroll.service.employeeAccess;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.dao.PersonDao;
import com.epayroll.dao.SecurityQuestionDao;
import com.epayroll.dao.employee.EmployeeBankAccountDao;
import com.epayroll.dao.employee.EmployeeDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.entity.Person;
import com.epayroll.entity.SecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employeeAccess.ChangeEmailForm;
import com.epayroll.form.employeeAccess.ChangePasswordForm;
import com.epayroll.form.employeeAccess.ChangeSecurityQnAForm;
import com.epayroll.utils.RandomNumberUtils;

/**
 * @author Rajul Tiwari
 */
@Service
public class EmployeeAccessServiceImpl implements EmployeeAccessService {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccessServiceImpl.class);

	@Autowired
	private EmployeeBankAccountDao employeeBankAccountDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private SecurityQuestionDao securityQuestionDao;

	@Override
	public List<EmployeeBankAccount> getEmployeeBankAccountInfo(Long employeeId) {
		logger.debug(">> getEmployeeBankAccountInfo");
		@SuppressWarnings("unchecked")
		List<EmployeeBankAccount> employeeBankAccountList = employeeBankAccountDao
				.getCriteriaForEmployeeBankAccounts(employeeId).list();
		logger.debug("employeeBankAccountList :: " + employeeBankAccountList);
		logger.debug("getEmployeeBankAccountInfo >>");
		return employeeBankAccountList;
	}

	private Employee getEmployee(Long employeeId, String password) {
		Employee employee = (Employee) employeeDao.getCriteriaForEmployee(employeeId,
				RandomNumberUtils.encode(password)).uniqueResult();
		return employee;
	}

	@Override
	public void verifyEmployeeAndChangePassword(ChangePasswordForm changePasswordForm)
			throws InstanceNotFoundException {
		logger.debug(">> verifyEmployeeAndChangePassword");

		// if
		// (changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmNewPassword()))
		// {
		Employee employee = getEmployee(changePasswordForm.getId(),
				changePasswordForm.getCurrentPassword());
		employee.setPassword(RandomNumberUtils.encode(changePasswordForm.getNewPassword()));
		employeeDao.update(employee);

		logger.debug("verifyEmployeeAndChangePassword >>");

	}

	@Override
	public ChangeEmailForm getChangeEmailForm(Employee employee) {
		ChangeEmailForm changeEmailForm = new ChangeEmailForm();
		changeEmailForm.setId(employee.getId());
		changeEmailForm.setCurrentEmail(employee.getPerson().getEmail());
		return changeEmailForm;
	}

	@Override
	public void verifyEmployeeAndChangeEmail(ChangeEmailForm changeEmailForm)
			throws InstanceNotFoundException {
		logger.debug(">> verifyEmployeeAndChangeEmail");

		// if
		// (changeEmailForm.getNewEmail().equals(changeEmailForm.getConfirmNewEmail()))
		// {
		Employee employee = getEmployee(changeEmailForm.getId(), changeEmailForm.getPassword());
		logger.debug("employee person id::" + employee.getPerson().getId());
		Person person = personDao.find(employee.getPerson().getId());
		person.setEmail(changeEmailForm.getNewEmail());
		employee.setPerson(person);
		employeeDao.update(employee);
		logger.debug("verifyEmployeeAndChangeEmail >>");
	}

	@Override
	public ChangeSecurityQnAForm getChangeSecurityQnAForm(Employee employee) {
		ChangeSecurityQnAForm changeSecurityQnAForm = new ChangeSecurityQnAForm();
		changeSecurityQnAForm.setId(employee.getId());
		changeSecurityQnAForm.setSecurityQuestion(employee.getSecurityQuestion());
		changeSecurityQnAForm.setSecurityAnswer(employee.getSecurityAnswer());
		return changeSecurityQnAForm;
	}

	@Override
	public void verifyEmployeeAndChangeSecurityQnA(ChangeSecurityQnAForm changeSecurityQnAForm)
			throws InstanceNotFoundException {
		logger.debug(">> verifyEmployeeAndChangeSecurityQnA");
		Employee employee = getEmployee(changeSecurityQnAForm.getId(),
				changeSecurityQnAForm.getPassword());
		employee.setSecurityQuestion(changeSecurityQnAForm.getSecurityQuestion());
		employee.setSecurityAnswer(changeSecurityQnAForm.getSecurityAnswer());
		employeeDao.update(employee);
		logger.debug("verifyEmployeeAndChangeSecurityQnA >>");

	}

	@Override
	public List<SecurityQuestion> getSecurityQuestions() {
		logger.debug(">> getSecurityQuestions");
		List<SecurityQuestion> securityQuestionList = securityQuestionDao.getQuestionsFromGroup1();
		logger.debug("securityQuestionList ::" + securityQuestionList);
		logger.debug("getSecurityQuestions >>");
		return securityQuestionList;
	}

}
