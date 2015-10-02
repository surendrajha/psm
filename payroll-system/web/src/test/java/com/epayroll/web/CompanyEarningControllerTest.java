package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyEarningAndDeductionController;

/**
 * 
 * @author Rajul Tiwari
 * 
 */
public class CompanyEarningControllerTest extends TestRoot {

	@Autowired
	private CompanyEarningAndDeductionController companyEarningAndDeductionController;

	@Autowired
	private CompanyService companyService;

	// @Test
	public void showEarningList() throws Exception {
		System.out.println("showEarningList >>");
		request.setMethod("GET");
		request.setRequestURI("/company/earning/showList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningListPage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyEarningList");
		System.out.println(">> showEarningList");
	}

	// @Test
	public void showAddForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/earning/showAddForm");
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyEarningForm");
	}

	@Test
	public void addEarning() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/earning/add");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("displayName", "");
		request.setParameter("earningCategoryId", "1");
		request.setParameter("earningId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningListPage");
		// when validation failed
		// ModelAndViewAssert.assertViewName(mav, "companyEarningForm");
	}

	// @Test
	public void getEarnings() throws Exception {
		System.out.println("getEarnings >>");
		request.setMethod("GET");
		request.setRequestURI("/company/earning/getEarnings/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningForm");
		System.out.println("Earning " + mav.getModel().get("earningList"));
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "earningList");
		System.out.println(">> getEarnings");
	}

	// @Test
	public void deleteEarning() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/earning/delete/1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningListPage");
	}

	// @Test
	public void showUpdateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/earning/showUpdateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyEarningForm");
	}

	// @Test
	public void updateCompanyEarningDisplayName() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/earning/updateCompanyEarningDisplayName");
		// set parameters value of requested object
		request.setParameter("id", "1");
		request.setParameter("displayName", "RRR");
		request.setParameter("earningCategoryId", "1");
		request.setParameter("earningId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyEarningAndDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyEarningListPage");
		// when validation failed
		// ModelAndViewAssert.assertViewName(mav, "companyEarningForm");
	}
}