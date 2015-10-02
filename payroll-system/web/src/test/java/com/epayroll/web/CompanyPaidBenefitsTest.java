package com.epayroll.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.form.company.CompanyPaidBenefitsForm;
import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyPaidBenefitsController;

public class CompanyPaidBenefitsTest extends TestRoot {

	private Logger logger = LoggerFactory.getLogger(CompanyPaidBenefitsTest.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CompanyPaidBenefitsController controller;

	private HttpSession session;

	@Before
	public void setCompanyInSession() throws Exception {
		logger.debug("before");
		session = request.getSession();
		session.setAttribute("company", companyService.findCompany(1L));
	}

	@After
	public void removeCompanyFromSession() {
		logger.debug("after...");
		session.removeAttribute("company");
		session.invalidate();
	}

	// @Ignore
	@Test
	public void showPaidBenefitsForm() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/showPaidBenefitsForm");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);

		ModelAndViewAssert.assertViewName(mav, "paid-benefits");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyEarnings");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

	@Ignore
	@Test
	public void getPaidBenefits() throws Exception {

		request.setMethod("GET");
		request.setRequestURI("/getPaidBenefits/2");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);

		ModelAndViewAssert.assertViewName(mav, "paid-benefits");
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "companyPaidBenefitsForm",
				CompanyPaidBenefitsForm.class);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "paidBenefits");
	}

	// @Ignore
	@Test
	public void addPaidBenefitsForm() throws Exception {

		request.setMethod("POST");
		request.setRequestURI("/addPaidBenefits");

		request.setParameter("companyEarningId", "1");
		request.setParameter("accrualMode", "Pay Hourly");
		// request.setParameter("rate", "2.62");
		request.setParameter("carryForwardHours", "Some Hours");
		request.setParameter("noOfHours", "1.2");
		request.setParameter("isHoursAccrueOnLeave", "0");

		ModelAndView mav = handlerAdapter.handle(request, response, controller);

		ModelAndViewAssert.assertViewName(mav, "paid-benefits");

	}
}
