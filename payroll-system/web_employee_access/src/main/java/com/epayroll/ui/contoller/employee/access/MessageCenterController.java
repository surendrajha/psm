package com.epayroll.ui.contoller.employee.access;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.exception.CommonException;
import com.epayroll.form.employee.MessageCenterForm;
import com.epayroll.service.employee.MessageCenterService;
import com.epayroll.spring.authorization.EmployeeAccessAuthorizationUtils;

@Controller
@RequestMapping("/employeeAccess/messageCenter")
public class MessageCenterController {

	private Logger logger = LoggerFactory.getLogger(MessageCenterController.class);

	@ModelAttribute
	public MessageCenterForm getMessageCenterForm() {
		return new MessageCenterForm();
	}

	@Autowired
	private MessageCenterService messageCenterService;

	@RequestMapping(value = "/showSendMessageForm", method = RequestMethod.GET)
	public ModelAndView showSendMessageForm() {
		logger.debug(">>  showSendMessageForm ");
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		modelAndView.addObject(
				"personList",
				messageCenterService.getPersons(EmployeeAccessAuthorizationUtils.getLoginUser()
						.getCompany().getId()));
		return modelAndView;
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public ModelAndView sendMessage(@ModelAttribute MessageCenterForm messageCenterForm,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		logger.info("Sending message...");
		try {
			messageCenterService.addMessage(messageCenterForm, EmployeeAccessAuthorizationUtils
					.getLoginUser().getPerson().getId());
			modelAndView.addObject("successMessage", "Message sent !");
			logger.info("message sent !");
		} catch (CommonException e) {
			logger.error("Error in sendMessage", e);
			modelAndView.addObject("errorMessage", e.getMessage());
		} catch (Exception e) {
			logger.error("Error :: ", e);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/getInboxMessage", method = RequestMethod.GET)
	public ModelAndView getInboxMessage() {
		logger.debug(">>  getInboxMessage ");
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		modelAndView.addObject(
				"inboxList",
				messageCenterService.getReceivedMessages(EmployeeAccessAuthorizationUtils
						.getLoginUser().getPerson().getId()));
		return modelAndView;
	}

	@RequestMapping(value = "/getSentMessage", method = RequestMethod.GET)
	public ModelAndView getSentMessage() {
		logger.debug(">>  getSentMessage ");
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		modelAndView.addObject(
				"sentList",
				messageCenterService.getSentMessages(EmployeeAccessAuthorizationUtils
						.getLoginUser().getPerson().getId()));
		return modelAndView;
	}

	@RequestMapping(value = "/getMessageDetail/{messageId}/{comeFrom}", method = RequestMethod.GET)
	public ModelAndView getMessageDetail(@PathVariable Long messageId, @PathVariable String comeFrom) {
		logger.debug("in getMessageDetail..");
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		try {
			modelAndView.addObject("messageDetail",
					messageCenterService.getPersonMessage(messageId));
			if (comeFrom.equals("INBOX")) {
				modelAndView.addObject(
						"inboxList",
						messageCenterService.getReceivedMessages(EmployeeAccessAuthorizationUtils
								.getLoginUser().getPerson().getId()));
			} else {
				modelAndView.addObject(
						"sentList",
						messageCenterService.getSentMessages(EmployeeAccessAuthorizationUtils
								.getLoginUser().getPerson().getId()));
			}
		} catch (CommonException e) {
			logger.error("Error", e);
			modelAndView.addObject("errorMessage", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/replyTo", method = RequestMethod.POST)
	public ModelAndView replyTo(@ModelAttribute MessageCenterForm messageCenterForm,
			BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("employeeMessage");
		logger.info("replying message...");
		try {
			messageCenterForm.setToPersionId(Arrays.asList(messageCenterForm.getToPerson()));
			modelAndView.addObject(
					"personList",
					messageCenterService.getPersons(EmployeeAccessAuthorizationUtils.getLoginUser()
							.getCompany().getId()));
			modelAndView.addObject("messageCenterForm", messageCenterForm);
		} catch (Exception e) {
			logger.error("Error :: ", e);
		}
		return modelAndView;
	}
}
