package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminTaxDepositCycleMasterController;

/**
 * @author Rajul Tiwari
 */

public class AdminTaxDepositCycleMasterControllerTest extends TestRoot {

	@Autowired
	private AdminTaxDepositCycleMasterController adminTaxDepositCycleMasterController;

	@Ignore
	@Test
	public void showTaxDepositCycleMasterForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showTaxDepositCycleMasterForm");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxDepositCycleMasterController);
		ModelAndViewAssert.assertViewName(mav, "taxDepositCycleMasterForm");
	}

	@Ignore
	@Test
	public void addTaxDepositCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addTaxDepositCycle");
		// set parameters value of requested object
		request.setParameter("depositCycle", "Yearly");
		request.setParameter("description", "yearly");
		request.setParameter("noOfDays", "365");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxDepositCycleMasterController);
		ModelAndViewAssert.assertViewName(mav, "taxDepositCycleMasterForm");

	}

	@Ignore
	@Test
	public void showUpdateTaxDepositCycleMasterForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateTaxDepositCycleMasterForm/5");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxDepositCycleMasterController);
		ModelAndViewAssert.assertViewName(mav, "taxDepositCycleMasterForm");
	}

	@Ignore
	@Test
	public void updateTaxDepositCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateTaxDepositCycle");
		request.setParameter("id", "5");
		request.setParameter("depositCycle", "Yearly");
		request.setParameter("description", "yearly");
		request.setParameter("noOfDays", "365");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxDepositCycleMasterController);
		ModelAndViewAssert.assertViewName(mav, "taxDepositCycleMasterForm");
	}

	@Ignore
	@Test
	public void deleteTaxDepositCycle() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteTaxDepositCycle/5");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxDepositCycleMasterController);
		ModelAndViewAssert.assertViewName(mav, "taxDepositCycleMasterForm");
	}

}