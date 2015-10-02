package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminFederalTaxController;

public class AdminFederalTaxControllerTest extends TestRoot {
	@Autowired
	private AdminFederalTaxController adminFederalTaxController;

	@Ignore
	@Test
	public void showFederalTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showFederalTaxForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");
	}

	@Ignore
	@Test
	public void showFederalTaxList() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showFederalTaxList");
		request.setParameter("id", "1");
		request.setParameter("filingStatus", "Single");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	@Ignore
	@Test
	public void addFederalStateTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addFederalStateTaxRate");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "1");
		request.setParameter("filingStatusId", "1");
		request.setParameter("minIncome", "11");
		request.setParameter("maxIncome", "111");
		request.setParameter("taxRate", "11");
		request.setParameter("taxAmount", "111");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void showFutaForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showFutaForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	@Ignore
	@Test
	public void addUpdateFuta() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUpdateFuta");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "2");
		request.setParameter("federalStateTaxRateId", "3");
		request.setParameter("ceiling", "7086");
		request.setParameter("maximumFutaCredit", "7");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	@Ignore
	@Test
	public void addUpdateMedicareSocialSecurity() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUpdateMedicareSocialSecurity");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "2");
		request.setParameter("federalStateTaxRateId", "3");
		request.setParameter("ceiling", "7086");
		request.setParameter("maximumFutaCredit", "7");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	@Ignore
	@Test
	public void showUpdateFederalStateTaxRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateFederalStateTaxRateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	@Ignore
	@Test
	public void updateFederalStateTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateFederalStateTaxRate");
		// set parameters value of requested object
		request.setParameter("federalStateTaxRateId", "1");
		request.setParameter("minIncome", "2222222");
		request.setParameter("maxIncome", "123346");
		request.setParameter("taxRate", "1113");
		request.setParameter("taxAmount", "1324");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void showTaxAuthorityDepositCycleForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showTaxAuthorityDepositCycleForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void addTaxAuthorityDepositCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addTaxAuthorityDepositCycle");
		request.setParameter("taxDepositCycleId", "1");
		request.setParameter("taxAuthorityId", "1");
		request.setParameter("minTaxAmount", "200");
		request.setParameter("maxTaxAmount", "500");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void showUpdateTaxAuthorityDepositCycleForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateTaxAuthorityDepositCycleForm/2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void updateTaxAuthorityDepositCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateTaxAuthorityDepositCycle");
		request.setParameter("taxAuthorityDepositCycleId", "2");
		request.setParameter("taxDepositCycleId", "2");
		request.setParameter("taxAuthorityId", "2");
		request.setParameter("minTaxAmount", "200");
		request.setParameter("maxTaxAmount", "500");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void deleteTaxAuthorityDepositCycle() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteTaxAuthorityDepositCycle/2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void showFutaCompanyRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showFutaCompanyRateForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void addFutaCompanyRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addFutaCompanyRate");
		request.setParameter("taxTypeId", "2");
		request.setParameter("companyId", "1");
		request.setParameter("wef", "23/08/1992");
		request.setParameter("rate", "20");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void showUpdateFutaCompanyRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateFutaCompanyRateForm/2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void updateFutaCompanyRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateFutaCompanyRate");
		request.setParameter("futaSutaTaxRateId", "2");
		request.setParameter("taxTypeId", "2");
		request.setParameter("companyId", "2");
		request.setParameter("wef", "23/08/1992");
		request.setParameter("rate", "23");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}

	// @Test
	public void deleteFutaCompanyRate() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteFutaCompanyRate/2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminFederalTaxController);
		ModelAndViewAssert.assertViewName(mav, "federalForm");

	}
}
