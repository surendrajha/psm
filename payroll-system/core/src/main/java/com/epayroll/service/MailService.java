package com.epayroll.service;

import com.epayroll.exception.MailNotSentException;
import com.epayroll.model.MailMessage;

public interface MailService {

	void send(MailMessage mailMessage) throws MailNotSentException;
}
