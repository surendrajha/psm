package com.epayroll.ui.contoller.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotPasswordStep1Form;
import com.epayroll.form.employeeAccess.EmployeeAccessForgotUserIdStep1Form;
import com.epayroll.model.MailMessage;

/**
 * @author Rajul Tiwari
 */
@Component
public class EmployeeAccessControllerHelper {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccessControllerHelper.class);

	// public MailMessage prepareMailForUserId(
	// EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form,
	// String link,
	// String userName) {
	// logger.debug(">> prepareMailForUserId");
	// MailMessage mailMessage = new MailMessage();
	// mailMessage.setMailTo(new String[] {
	// employeeAccessForgotUserIdStep1Form.getEmailId() });
	// mailMessage.setSubject("User ID Information");
	// mailMessage.setContent("<html><body>Hello," + " <br/> Your User ID is " +
	// userName
	// + " For Login : <a href='" + link + "'>Click Here</a></body></html>");
	// logger.debug("prepareMailForUserId >>");
	// return mailMessage;
	// }
	//
	// public MailMessage prepareMailForUserId(String emailId, String link,
	// String userName) {
	// logger.debug(">> prepareMailForUserId");
	// MailMessage mailMessage = new MailMessage();
	// mailMessage.setMailTo(new String[] { emailId });
	// mailMessage.setSubject("User ID Information");
	// mailMessage.setContent("<html><body>Hello," + " <br/> Your User ID is " +
	// userName
	// + " For Login : <a href='" + link + "'>Click Here</a></body></html>");
	// logger.debug("prepareMailForUserId >>");
	// return mailMessage;
	// }
	//
	// public MailMessage prepareMailForUserId(
	// EmployeeAccessForgotPasswordStep1Form
	// employeeAccessForgotPasswordStep1Form,
	// String link, String emailId) {
	// logger.debug(">> prepareMailForUserId");
	// MailMessage mailMessage = new MailMessage();
	// mailMessage.setMailTo(new String[] { emailId });
	// mailMessage.setSubject("User ID Information");
	// mailMessage.setContent("<html><body>Hello," + " <br/> Your User ID is "
	// + employeeAccessForgotPasswordStep1Form.getUserId() +
	// " For Login : <a href='"
	// + link + "'>Click Here</a></body></html>");
	// logger.debug("prepareMailForUserId >>");
	// return mailMessage;
	// }

	public MailMessage prepareMailForPassword(
			EmployeeAccessForgotPasswordStep1Form employeeAccessForgotPasswordStep1Form, String link,
			String email) throws InstanceNotFoundException {
		logger.debug(">> prepareMailForPassword");
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { email });
		mailMessage.setSubject("Password Reset Link");
		mailMessage.setContent("<html><body>Hello," + employeeAccessForgotPasswordStep1Form.getUserId()
				+ " <br/> This is your password reset link : <a href='" + link
				+ "'>Change Password</a></body></html>");
		logger.debug("prepareMailForPassword >>");
		return mailMessage;
	}

	public MailMessage prepareMailForUserId(
			EmployeeAccessForgotUserIdStep1Form employeeAccessForgotUserIdStep1Form, String link, String userName)
			throws InstanceNotFoundException {
		logger.debug(">> prepareMailForUserId");
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { employeeAccessForgotUserIdStep1Form.getEmailId() });
		mailMessage.setSubject("User ID Information");
		mailMessage.setContent("<html><body>Hello," + " <br/> Your User ID is " + userName
				+ " For Login : <a href='" + link + "'>Click Here</a></body></html>");
		logger.debug("prepareMailForUserId >>");
		return mailMessage;
	}
}
