package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Company.Status;
import com.epayroll.ui.contoller.admin.console.AdminManageCompaniesController;

/**
 * @author Rajul Tiwari
 */

public class AdminManageCompaniesControllerTest extends TestRoot {

	@Autowired
	private AdminManageCompaniesController adminManageCompaniesController;

	@Ignore
	@Test
	public void showCompanyForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showCompanyForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
	}

	@Ignore
	@Test
	public void searchCompany() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/searchCompany");
		// set parameters value of requested object
		// request.setParameter("legalName", "MyCompany");
		request.setParameter("status", Status.ACTIVE.toString());
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
	}

	@Ignore
	@Test
	public void VerifyDeleteCompany() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/verifyDeleteCompany/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		// ModelAndViewAssert.assertViewName(mav, "delete-form");
		ModelAndViewAssert.assertViewName(mav, "InActive-form");
	}

	// @Ignore
	@Test
	public void deleteCompany() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteCompany/3");
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
	}

	@Ignore
	@Test
	public void deactivateCompany() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deactivateCompany/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
	}

	@Ignore
	@Test
	public void reactivateCompany() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/reactivateCompany/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminManageCompaniesController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
	}

}