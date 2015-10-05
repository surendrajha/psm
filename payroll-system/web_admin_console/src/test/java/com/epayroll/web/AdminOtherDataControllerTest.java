package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminOtherDataController;

/**
 * 
 * @author Uma
 * 
 */

public class AdminOtherDataControllerTest extends TestRoot {

	@Autowired
	private AdminOtherDataController adminOtherDataController;

	// @Test
	public void addUsState() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUsState");
		request.setParameter("stateName", "State1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usStateView");
	}

	// @Test
	public void showUpdateUsStateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateUsStateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usStateView");
	}

	// @Test
	public void updateUsState() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateUsState");
		request.setParameter("stateName", "State1 NEW");
		request.setParameter("id", "5");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usStateView");
	}

	// @Test
	public void deleteUsState() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteUsState/5");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usStateView");
	}

	// @Test
	public void showUsCountyForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUsCountyForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void addUsCounty() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUsCounty");
		request.setParameter("usStateId", "2");
		request.setParameter("countyName", "countyName1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void showUpdateUsCountyForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateUsCountyForm/2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void updateUsCounty() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateUsCounty");
		request.setParameter("id", "2");
		request.setParameter("usStateId", "2");
		request.setParameter("countyName", "countyupdate2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void deleteUsCounty() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteUsCounty/3");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void showUsCityForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUsCityForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCountyView");
	}

	// @Test
	public void addUsCity() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addUsCity");
		request.setParameter("usStateId", "2");
		request.setParameter("cityName", "cityName1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCityView");
	}

	// @Test
	public void showUpdateUsCityForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateUsCityForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCityView");
	}

	// @Test
	public void updateUsCity() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateUsCity");
		request.setParameter("id", "1");
		request.setParameter("usStateId", "2");
		request.setParameter("cityName", "cityupdate2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCityView");
	}

	// @Test
	public void deleteUsCity() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteUsCity/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "usCityView");
	}

	// @Test
	public void showFederalAndStateHolidayForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showFederalAndStateHolidayForm");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "federalAndStateHolidayView");
	}

	// @Test
	public void addFederalAndStateHoliday() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addFederalAndStateHoliday");
		request.setParameter("usStateId", "2");
		request.setParameter("holidayDate", "08/23/1992");
		request.setParameter("description", "2");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "federalAndStateHolidayView");
	}

	// @Test
	public void showUpdateFederalAndStateHolidayForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateFederalAndStateHolidayForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "federalAndStateHolidayView");
	}

	// @Test
	public void updateFederalAndStateHoliday() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateFederalAndStateHoliday");
		request.setParameter("usStateId", "2");
		request.setParameter("id", "1");
		request.setParameter("holidayDate", "09/23/1992");
		request.setParameter("description", "desc");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "federalAndStateHolidayView");
	}

	// @Test
	public void deleteFederalAndStateHoliday() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteFederalAndStateHoliday/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "federalAndStateHolidayView");
	}

	// @Test
	public void addPayCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addPayCycle");
		request.setParameter("type", "22");
		request.setParameter("noOfDays", "22");
		request.setParameter("description", "22");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "payCycleView");
	}

	// @Test
	public void showUpdatePayCycleForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdatePayCycleForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "payCycleView");
	}

	// @Test
	public void updatePayCycle() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updatePayCycle");
		request.setParameter("id", "3");
		request.setParameter("type", "23");
		request.setParameter("noOfDays", "23");
		request.setParameter("description", "23");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "payCycleView");
	}

	@Test
	public void deletePayCycle() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deletePayCycle/3");
		ModelAndView mav = handlerAdapter.handle(request, response, adminOtherDataController);
		ModelAndViewAssert.assertViewName(mav, "payCycleView");
	}
	// Remaining methods tested from service layer
}