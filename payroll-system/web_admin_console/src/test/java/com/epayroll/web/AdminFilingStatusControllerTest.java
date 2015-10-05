package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminFilingStatusController;

/**
 * @author Rajul Tiwari
 */

public class AdminFilingStatusControllerTest extends TestRoot {

	@Autowired
	private AdminFilingStatusController adminFilingStatusController;

	@Ignore
	@Test
	public void showFilingStatusForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showFilingStatusForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFilingStatusController);
		ModelAndViewAssert.assertViewName(mav, "filingStatusForm");
	}

	@Ignore
	@Test
	public void addFilingStatus() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addFilingStatus");
		// set parameters value of requested object
		request.setParameter("filingStatus", "aa");
		request.setParameter("description", "aaaeaaaa");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFilingStatusController);
		ModelAndViewAssert.assertViewName(mav, "filingStatusForm");

	}

	@Ignore
	@Test
	public void showUpdateFilingStatusForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateFilingStatusForm/3");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFilingStatusController);
		ModelAndViewAssert.assertViewName(mav, "filingStatusForm");
	}

	@Ignore
	@Test
	public void updateFilingStatus() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateFilingStatus");
		request.setParameter("id", "3");
		request.setParameter("filingStatus", "aaaa");
		request.setParameter("description", "aaaaaaaaaaa");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFilingStatusController);
		ModelAndViewAssert.assertViewName(mav, "filingStatusForm");
	}

	@Ignore
	@Test
	public void deleteFilingStatus() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteFilingStatus/3");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFilingStatusController);
		ModelAndViewAssert.assertViewName(mav, "filingStatusForm");
	}
}