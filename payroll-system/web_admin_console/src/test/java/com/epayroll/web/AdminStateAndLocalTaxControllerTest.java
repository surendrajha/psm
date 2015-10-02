package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.CountyTaxRate.ResidentStatus;
import com.epayroll.service.adminConsole.AdminSystemDataService;
import com.epayroll.ui.contoller.admin.console.AdminStateAndLocalTaxController;

public class AdminStateAndLocalTaxControllerTest extends TestRoot {

	@Autowired
	private AdminStateAndLocalTaxController adminStateAndLocalTaxController;
	@Autowired
	private AdminSystemDataService adminSystemDataService;

	@Ignore
	@Test
	public void showStateListForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showStateListForm");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");
	}

	@Ignore
	@Test
	public void showStateAndLocalTaxForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showStateAndLocalTaxForm");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(1L));
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void showStateTaxList() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showStateTaxList");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(1L));
		request.setParameter("id", "1");
		request.setParameter("filingStatus", "Single");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void addStateTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addStateTaxRate");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "5");
		request.setParameter("filingStatusId", "1");
		request.setParameter("minIncome", "44");
		request.setParameter("maxIncome", "444");
		request.setParameter("taxRate", "4");
		request.setParameter("taxAmount", "4444");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void showUpdateStateTaxRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateStateTaxRateForm/9");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(1L));
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void updateStateTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateStateTaxRate");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "5");
		request.setParameter("filingStatusId", "1");
		request.setParameter("federalStateTaxRateId", "9");
		request.setParameter("minIncome", "9");
		request.setParameter("maxIncome", "99");
		request.setParameter("taxRate", "9");
		request.setParameter("taxAmount", "999");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void deleteStateTaxRate() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteStateTaxRate/5");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");
	}

	@Ignore
	@Test
	public void addCountyTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addCountyTaxRate");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(2L));
		// set parameters value of requested object
		request.setParameter("countyId", "8");
		request.setParameter("residentStatus", ResidentStatus.RESIDENT.toString());
		request.setParameter("taxRate", "22");
		request.setParameter("ceiling", "22");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void showUpdateCountyTaxRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateCountyTaxRateForm/2");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(2L));
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void updateCountyTaxRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateCountyTaxRate");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(2L));
		// set parameters value of requested object
		request.setParameter("id", "1");
		request.setParameter("countyId", "6");
		request.setParameter("residentStatus", ResidentStatus.RESIDENT.toString());
		request.setParameter("taxRate", "666");
		request.setParameter("ceiling", "6");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void deleteCountyTaxRate() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteCountyTaxRate/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");
	}

	// @Test
	public void showSutaCompanyRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showSutaCompanyRateForm");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(1L));
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	// @Test
	public void addSutaCompanyRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addSutaCompanyRate");
		request.setParameter("taxTypeId", "2");
		request.setParameter("companyId", "1");
		request.setParameter("wef", "23/08/1992");
		request.setParameter("rate", "20");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	// @Test
	public void showUpdateSutaCompanyRateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateSutaCompanyRateForm/3");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	// @Test
	public void updateSutaCompanyRate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateSutaCompanyRate");
		request.setParameter("futaSutaTaxRateId", "3");
		request.setParameter("taxTypeId", "2");
		request.setParameter("companyId", "2");
		request.setParameter("wef", "23/08/1992");
		request.setParameter("rate", "23");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void deleteSutaCompanyRate() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteSutaCompanyRate/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void showSutaForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showSutaForm");
		request.getSession().setAttribute("usStateForm", adminSystemDataService.getUsState(1L));
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

	@Ignore
	@Test
	public void addUpdateSuta() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUpdateSuta");
		// set parameters value of requested object
		request.setParameter("taxTypeId", "6");
		request.setParameter("federalStateTaxRateId", "17");
		request.setParameter("ceiling", "999");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminStateAndLocalTaxController);
		ModelAndViewAssert.assertViewName(mav, "state&LocalForm");

	}

}
