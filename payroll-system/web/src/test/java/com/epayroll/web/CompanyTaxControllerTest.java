package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyTaxController;

/**
 * 
 * @author Rajul Tiwari
 * 
 */

public class CompanyTaxControllerTest extends TestRoot {

	@Autowired
	private CompanyTaxController companyTaxController;

	@Autowired
	private CompanyService companyService;

	// @Test
	public void showTaxList() throws Exception {
		System.out.println("showTaxList >>");
		request.setMethod("GET");
		request.setRequestURI("/company/tax/showList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyTaxController);
		ModelAndViewAssert.assertViewName(mav, "companyTaxListPage");
		System.out.println(">> showTaxList");
	}

	// @Test
	public void showUpdateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/tax/showUpdateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyTaxController);
		ModelAndViewAssert.assertViewName(mav, "companyTaxForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyTaxForm");
	}

	// @Test
	public void updateTaxDepositCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/tax/updateDepositCycle");
		request.setParameter("id", "1");
		request.setParameter("depositCycleId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyTaxController);
		ModelAndViewAssert.assertViewName(mav, "companyTaxListPage");
	}

	// @Test
	public void updateCompanyTax() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/tax/updateCompanyTax");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.setParameter("id", "1");
		request.setParameter("depositCycleId", "2");
		request.setParameter("taxTypeId", "2");
		request.setParameter("ein", "1");
		request.setParameter("exempted", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyTaxController);
		ModelAndViewAssert.assertViewName(mav, "companyTaxListPage");
	}

	@Test
	public void showAddTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/tax/showAddTaxForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyTaxController);
		ModelAndViewAssert.assertViewName(mav, "companyTaxListPage");
	}

}