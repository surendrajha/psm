package com.epayroll.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminEarningDeductionController;

/**
 * 
 * @author Uma
 * 
 */

public class AdminEarningDeductionControllerTest extends TestRoot {

	@Autowired
	private AdminEarningDeductionController adminEarningDeductionController;

	// @Test
	public void showEarningDeductionForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEarningDeductionForm");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void addEarningCategory() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEarningCategory");
		request.setParameter("category", "category 1");
		request.setParameter("description", "description1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void showUpdateEarningCategoryForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateEarningCategoryForm/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void updateEarningCategory() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEarningCategory");
		request.setParameter("category", "category new");
		request.setParameter("description", "description new");
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void deleteEarningCategory() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteEarningCategory/2");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void addEarning() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEarning");
		request.setParameter("earningCategoryId", "1");
		request.setParameter("earning", "category 1");
		request.setParameter("description", "description1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void showUpdateEarningForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateEarningForm/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void updateEarning() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEarning");
		request.setParameter("id", "1");
		request.setParameter("earningCategoryId", "1");
		request.setParameter("earning", "category new");
		request.setParameter("description", "description new");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void deleteEarning() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteEarning/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void addDeductionCategory() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addDeductionCategory");
		request.setParameter("category", "category 1");
		request.setParameter("description", "description1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void showUpdateDeductionCategoryForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateDeductionCategoryForm/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void updateDeductionCategory() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateDeductionCategory");
		request.setParameter("category", "category updated");
		request.setParameter("description", "description updated");
		request.setParameter("id", "1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void deleteDeductionCategory() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteDeductionCategory/2");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void addDeduction() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addDeduction");
		request.setParameter("deductionCategoryId", "1");
		request.setParameter("deduction", "category 1");
		request.setParameter("description", "description1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void showUpdateDeductionForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateDeductionForm/1");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void updateDeduction() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateDeduction");
		request.setParameter("id", "1");
		request.setParameter("deductionCategoryId", "1");
		request.setParameter("deduction", "deduction new");
		request.setParameter("description", "deduction description new");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

	// @Test
	public void deleteDeduction() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteDeduction/2");
		ModelAndView mav = handlerAdapter
				.handle(request, response, adminEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "earningDeductionView");
	}

}