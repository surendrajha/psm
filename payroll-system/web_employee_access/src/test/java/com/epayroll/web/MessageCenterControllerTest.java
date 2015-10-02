/**
 * 
 */
package com.epayroll.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.employee.access.MessageCenterController;

/**
 * @author Surendra Jha
 */
public class MessageCenterControllerTest extends TestRoot {

	private Logger logger = LoggerFactory.getLogger(MessageCenterControllerTest.class);

	@Autowired
	private MessageCenterController messageCenterController;

	// @Ignore
	@Test
	public void showSendMessageForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showSendMessageForm");
		ModelAndView mav = handlerAdapter.handle(request, response, messageCenterController);

		ModelAndViewAssert.assertViewName(mav, "employeeMessage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "personList");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + ", entry.getValue()::"
					+ entry.getValue());
		}
	}

	@Ignore
	@Test
	public void getInboxMessage() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getInboxMessage");
		ModelAndView mav = handlerAdapter.handle(request, response, messageCenterController);

		ModelAndViewAssert.assertViewName(mav, "employeeMessage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "inboxList");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + ", entry.getValue()::"
					+ entry.getValue());
		}
	}

	@Ignore
	@Test
	public void getsentMessage() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getSentMessage");
		ModelAndView mav = handlerAdapter.handle(request, response, messageCenterController);

		ModelAndViewAssert.assertViewName(mav, "employeeMessage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "sentList");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + ", entry.getValue()::"
					+ entry.getValue());
		}
	}

	@Ignore
	@Test
	public void getMessageDetail() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getMessageDetail/2");
		ModelAndView mav = handlerAdapter.handle(request, response, messageCenterController);

		ModelAndViewAssert.assertViewName(mav, "employeeMessage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "messageDetail");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + ", entry.getValue()::"
					+ entry.getValue());
		}
	}

	@Ignore
	@Test
	public void sendMessage() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/sendMessage");
		List<Long> toPersionId = new ArrayList<>();
		toPersionId.add(2L);
		toPersionId.add(7L);
		request.setParameter("toPersionId", "2");
		request.setParameter("subject", "sdsdsd");
		request.setParameter("messageText", "messageText");

		ModelAndView mav = handlerAdapter.handle(request, response, messageCenterController);

		ModelAndViewAssert.assertViewName(mav, "employeeMessage");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("entry.getKey()::" + entry.getKey() + ", entry.getValue()::"
					+ entry.getValue());
		}
	}

}
