/**
 * 
 */
package com.epayroll.ui.contoller.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epayroll.entity.SystemMessage;
import com.epayroll.entity.SystemTask;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ForgotPasswordAndPinStep1Form;
import com.epayroll.form.ForgotUserIdStep1Form;
import com.epayroll.form.GenerateOTPForm;
import com.epayroll.form.company.UserAddForm;
import com.epayroll.form.company.UserForm;
import com.epayroll.model.MailMessage;
import com.epayroll.utils.RandomNumberUtils;

/**
 * @author Surendra Jha
 */
@Component
public class AdminUserControllerHelper {
	public Logger logger = LoggerFactory.getLogger(AdminUserControllerHelper.class);

	public MailMessage prepareMail(UserAddForm userForm) {
		System.out.println("in prepareMail");
		ResourceBundle bundle = ResourceBundle.getBundle("mail");

		String LINK = bundle.getString("LINK");

		LINK = LINK.replaceAll("LOGIN_ID", userForm.getUserName());
		LINK = LINK.replaceAll("PWD", RandomNumberUtils.encode(userForm.getPassword()));
		LINK = LINK.replaceAll("COMPANY_USER_ID", userForm.getUserId().toString());

		String MAIL_CONTENT = bundle.getString("MAIL.CONTENT");

		MAIL_CONTENT = MAIL_CONTENT.replace("USER_NAME", userForm.getUserName());
		MAIL_CONTENT = MAIL_CONTENT.replace("USER",
				userForm.getFirstName() + " " + userForm.getLastName());
		MAIL_CONTENT = MAIL_CONTENT.replace("TEMP_PASSWORD", userForm.getPassword());
		MAIL_CONTENT = MAIL_CONTENT.replaceAll("LINK", LINK);

		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { userForm.getEmail() });
		mailMessage.setSubject("User Credencial");
		mailMessage.setContent(MAIL_CONTENT);

		return mailMessage;
	}

	public MailMessage prepareMail(GenerateOTPForm otpForm) {
		System.out.println("in prepareMail");

		String MAIL_CONTENT = "Dear User, your OTP Password is : " + otpForm.getOtp();

		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { otpForm.getEmail() });
		mailMessage.setSubject("OTP for Change Security Info");
		mailMessage.setContent(MAIL_CONTENT);

		return mailMessage;
	}

	public MailMessage prepareMail(UserForm userForm) {
		System.out.println("in prepareMail");

		ResourceBundle bundle = ResourceBundle.getBundle("mail");

		String MAIL_CONTENT = bundle.getString("RESET.MAIL.CONTENT");

		MAIL_CONTENT = MAIL_CONTENT.replace("USER_NAME", userForm.getUserName());
		MAIL_CONTENT = MAIL_CONTENT.replace("TEMP_PASSWORD", userForm.getPassword());
		MAIL_CONTENT = MAIL_CONTENT.replace("USER",
				userForm.getFirstName() + " " + userForm.getLastName());

		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { userForm.getEmail() });
		mailMessage.setSubject("Reset User Credencial");
		mailMessage.setContent(MAIL_CONTENT);

		return mailMessage;
	}

	public MailMessage prepareMailForPassword(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form, String link, String email)
			throws InstanceNotFoundException {
		logger.debug(">> prepareMailForPassword");
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { email });
		mailMessage.setSubject("Password Reset Link");
		mailMessage.setContent("<html><body>Hello," + forgotPasswordAndPinStep1Form.getUserName()
				+ " <br/> This is your password reset link : <a href='" + link
				+ "'>Change Password</a></body></html>");
		logger.debug("prepareMailForPassword >>");
		return mailMessage;
	}

	public MailMessage prepareMailForPin(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form, String link, String email)
			throws InstanceNotFoundException {
		logger.debug(">> prepareMailForPin");
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { email });
		mailMessage.setSubject("Pin Reset Link");
		mailMessage.setContent("<html><body>Hello," + forgotPasswordAndPinStep1Form.getUserName()
				+ " <br/> This is your pin reset link : <a href='" + link
				+ "'>Change Pin</a></body></html>");
		logger.debug("prepareMailForPin >>");
		return mailMessage;
	}

	public MailMessage prepareMailForUserId(ForgotUserIdStep1Form forgotUserIdStep1Form,
			String link, String userName) throws InstanceNotFoundException {
		logger.debug(">> prepareMailForUserId");
		MailMessage mailMessage = new MailMessage();
		mailMessage.setMailTo(new String[] { forgotUserIdStep1Form.getEmailId() });
		mailMessage.setSubject("User ID Information");
		mailMessage.setContent("<html><body>Hello," + " <br/> Your User ID is " + userName
				+ " For Login : <a href='" + link + "'>Click Here</a></body></html>");
		logger.debug("prepareMailForUserId >>");
		return mailMessage;
	}

	public List<SystemMessage> getSortedMessages(Set<SystemMessage> systemMessages) {
		logger.debug(">> getSortedMessages");
		Set<SystemMessage> sortedSystemMessage = new TreeSet<>(Collections.reverseOrder());
		Iterator<SystemMessage> iterator = systemMessages.iterator();
		while (iterator.hasNext()) {
			sortedSystemMessage.add(iterator.next());
		}
		List<SystemMessage> messages = new ArrayList<SystemMessage>();
		iterator = sortedSystemMessage.iterator();
		while (iterator.hasNext()) {
			messages.add(iterator.next());
		}
		logger.debug("getSortedMessages >>");
		return messages;

	}

	public List<SystemTask> getSortedTaskes(Set<SystemTask> systemTask) {
		logger.debug(">> getSortedTaskes");
		Set<SystemTask> sortedSystemTask = new TreeSet<>(Collections.reverseOrder());
		Iterator<SystemTask> iterator = systemTask.iterator();
		while (iterator.hasNext()) {
			sortedSystemTask.add(iterator.next());
		}
		List<SystemTask> taskes = new ArrayList<SystemTask>();
		iterator = sortedSystemTask.iterator();
		while (iterator.hasNext()) {
			taskes.add(iterator.next());
		}
		logger.debug("getSortedTaskes >>");
		return taskes;

	}
}
