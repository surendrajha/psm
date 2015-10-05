package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.UsState;
import com.epayroll.ui.contoller.StateContoller;

/**
 * @author Surendra Jha
 */
public class StateControllerTests extends TestRoot {

	private Logger logger = LoggerFactory.getLogger(StateControllerTests.class);

	@Autowired
	private StateContoller controller;

	// @Ignore
	@Test
	public void getStates() throws Exception {
		logger.info("in  getStates ");

		request.setMethod("GET");
		request.setRequestURI("/state/getStates");
		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		ModelAndViewAssert.assertViewName(mav, "state");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "state", UsState.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "stateList");

	}

	@Ignore
	@Test
	public void addState() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/add");
		// set parameters value of requested object
		// request.setParameter("stateName", "CVVVVVVVVVVVV");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		ModelAndViewAssert.assertViewName(mav, "redirect:/getStates");
	}

	@Ignore
	@Test
	public void deleteState() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/delete/2");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		ModelAndViewAssert.assertViewName(mav, "redirect:/getStates");
	}
}