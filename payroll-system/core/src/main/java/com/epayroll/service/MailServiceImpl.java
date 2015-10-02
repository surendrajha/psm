package com.epayroll.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.epayroll.exception.MailNotSentException;
import com.epayroll.model.MailMessage;

@Component
public class MailServiceImpl implements MailService {

	private Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public void send(MailMessage mailMessage) throws MailNotSentException {
		logger.debug("Sending mail to " + mailMessage);
		int retryCount = 0;
		String errorMessage = new String();
		while (retryCount < 3) {
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, false);
				messageHelper.setTo(mailMessage.getMailTo());
				messageHelper.setSubject(mailMessage.getSubject());
				messageHelper.setText(mailMessage.getContent(), true);
				mailSender.send(message);
				logger.debug(".... mail Sent!");
				break;
			} catch (Exception e) {
				retryCount++;
				logger.error("Mail sending failed : " + e.getLocalizedMessage());
				e.printStackTrace();
				errorMessage = e.getMessage();
			}
		}
		if (retryCount == 3) {
			throw new MailNotSentException(errorMessage);
		}
	}

}
