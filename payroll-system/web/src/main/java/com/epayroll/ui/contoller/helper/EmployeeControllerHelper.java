package com.epayroll.ui.contoller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epayroll.entity.Employee.Access;
import com.epayroll.entity.Employee.Status;
import com.epayroll.form.employee.EmployeeAccessForm;
import com.epayroll.model.MailMessage;

/**
 * @author Rajul Tiwari
 */
@Component
public class EmployeeControllerHelper {
	private Logger logger = LoggerFactory.getLogger(EmployeeControllerHelper.class);

	public Status[] getStatus() {
		Status[] status = { Status.ACTIVE, Status.TERMINATED };
		logger.debug("status" + status);
		return status;
	}

	public String[] getAccessToEmployee() {
		String[] accessToEmployee = { Access.ENABLED.toString(), Access.DISABLED.toString() };
		logger.debug("accessToEmployee" + accessToEmployee);
		return accessToEmployee;
	}

	public String[] getGenderList() {
		String[] gender = { "Male", "Female" };
		logger.debug("gender" + gender);
		return gender;
	}

	public MailMessage prepareMail(String userName, String employeePassword,
			EmployeeAccessForm employeeAccessForm) {
		System.out.println("in prepareMail");

		String MAIL_CONTENT = "Dear Employee, your User Name is : " + userName + "And Password is:"
				+ employeePassword;

		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { employeeAccessForm.getEmailAddress() });
		mailMessage.setSubject("Employee Access User Name and Password");
		mailMessage.setContent(MAIL_CONTENT);

		return mailMessage;
	}

	public MailMessage prepareMail(String userName, EmployeeAccessForm employeeAccessForm) {
		System.out.println("in prepareMail");

		String MAIL_CONTENT = "Dear Employee, your User Name is : " + userName
				+ "Your Access is Disabled";
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { employeeAccessForm.getEmailAddress() });
		mailMessage.setSubject("Employee Access Disabled");
		mailMessage.setContent(MAIL_CONTENT);
		return mailMessage;
	}
}
