package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyDeductionController;

/**
 * 
 * @author Rajul Tiwari
 * 
 */

public class CompanyDeductionControllerTest extends TestRoot {

	@Autowired
	private CompanyDeductionController companyDeductionController;

	@Autowired
	private CompanyService companyService;

	// @Test
	public void showDeductionList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/deduction/showList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionListPage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyDeductionList");
	}

	// @Test
	public void getDeductions() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/deduction/getDeductions/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "deductionList");
		System.out.println("Deduction " + mav.getModel().get("deductionList"));
	}

	// @Test
	public void showAddForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/deduction/showAddForm");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyDeductionForm");
	}

	// @Test
	public void addDeduction() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/deduction/add");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("displayName", "");
		request.setParameter("deductionCategoryId", "1");
		request.setParameter("deductionId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		// ModelAndViewAssert.assertViewName(mav, "companyDeductionListPage");
		// when validation failed
		ModelAndViewAssert.assertViewName(mav, "companyDeductionForm");
	}

	@Test
	public void deleteDeduction() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/deduction/delete/2");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionListPage");
	}

	// @Test
	public void showUpdateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/deduction/showUpdateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyDeductionForm");
	}

	// @Test
	public void updateCompanyDeductionDisplayName() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/deduction/updateCompanyDeductionDisplayName");
		// set parameters value of requested object
		request.setParameter("id", "1");
		request.setParameter("displayName", "ABC");
		request.setParameter("deductionCategoryId", "1");
		request.setParameter("deductionId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDeductionController);
		ModelAndViewAssert.assertViewName(mav, "companyDeductionListPage");
		// when validation failed
		// ModelAndViewAssert.assertViewName(mav, "companyDeductionForm");
	}
}