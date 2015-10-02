package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.ui.contoller.admin.console.AdminTaxTypeAndAuthorityController;

/**
 * @author Rajul Tiwari
 */

public class AdminTaxTypeAndAuthorityControllerTest extends TestRoot {

	@Autowired
	private AdminTaxTypeAndAuthorityController adminTaxTypeAndAuthorityController;

	@Ignore
	@Test
	public void showTaxTypeAndAuthorityForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showTaxTypeAndAuthorityForm");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxTypeAndAuthorityController);
		ModelAndViewAssert.assertViewName(mav, "form");
	}

	@Ignore
	@Test
	public void addTaxAuthority() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addTaxAuthority");
		// set parameters value of requested object
		request.setParameter("usStateId", "1");
		request.setParameter("usCountyId", "1");
		request.setParameter("usCityId", "1");
		request.setParameter("country", "3eeee");
		request.setParameter("streetAddress", "asd  fd fga");
		request.setParameter("pinZip", "123");
		request.setParameter("authorityName", "aassa");
		request.setParameter("emailId", "aaaa@gmail.com");
		request.setParameter("webAddress", "aa");
		request.setParameter("phone", "11111111");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxTypeAndAuthorityController);
		ModelAndViewAssert.assertViewName(mav, "form");

	}

	@Ignore
	@Test
	public void addTaxType() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addTaxType");
		// set parameters value of requested object
		request.setParameter("taxAuthorityId", "6");
		request.setParameter("taxName", "1");
		request.setParameter("paidByEmployee", "1");
		request.setParameter("paidByCompany", "0");
		request.setParameter("description", "asd  fd fga");
		ModelAndView mav = handlerAdapter.handle(request, response,
				adminTaxTypeAndAuthorityController);
		ModelAndViewAssert.assertViewName(mav, "form");

	}

}