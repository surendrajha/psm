package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminWorkersCompensationTaxRateController;

/**
 * @author Rajul Tiwari
 */

public class AdminWorkersCompensationTaxRateControllerTest extends TestRoot {

	@Autowired
	private AdminWorkersCompensationTaxRateController adminWorkersCompensationTaxRateController;

	@Ignore
	@Test
	public void showForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showForm");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");
	}

	@Ignore
	@Test
	public void showWorkersCompensationTaxRateForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showWorkersCompensationTaxRateForm");
		// set parameters value of requested object
		request.setParameter("year", "2010");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");

	}

	@Ignore
	@Test
	public void getWorkersCompensationCode() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/getWorkersCompensationCode/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");
	}

	@Ignore
	@Test
	public void addWorkersCompensationTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addWorkersCompensationTaxRate");
		// set parameters value of requested object
		request.setParameter("year", "2010");
		request.setParameter("rate", "21");
		request.setParameter("companyId", "1");
		request.setParameter("WorkComCodeId", "1");
		request.setParameter("wef", "11/01/2001");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");

	}

	@Ignore
	@Test
	public void showUpdateWorkersCompensationTaxRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateWorkersCompensationTaxRateForm/1");
		request.setParameter("year", "2010");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");
	}

	@Ignore
	@Test
	public void updateWorkersCompensationTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateWorkersCompensationTaxRate");
		// set parameters value of requested object
		request.setParameter("year", "2010");
		request.setParameter("id", "1");
		request.setParameter("rate", "9");
		request.setParameter("companyId", "2");
		request.setParameter("WorkComCodeId", "2");
		request.setParameter("wef", "12/01/2003");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");

	}

	@Ignore
	@Test
	public void deleteWorkersCompensationTaxRate() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteWorkersCompensationTaxRate/3");
		request.setParameter("year", "2010");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminWorkersCompensationTaxRateController);
		ModelAndViewAssert.assertViewName(mav, "workersCompensationTaxRateForm");
	}

}