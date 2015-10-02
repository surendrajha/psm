package com.epayroll.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.CompanyBankInfo.AccountType;
import com.epayroll.entity.CompanyBankInfo.Status;
import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyBankInfoController;

/**
 * 
 * @author Rajul Tiwari
 * 
 */
public class CompanyBankInfoControllerTest extends TestRoot {

	@Autowired
	private CompanyBankInfoController companyBankInfoController;

	@Autowired
	private CompanyService companyService;

	@Ignore
	@Test
	public void showBankInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/bankInfo/show");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(3L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyBankInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyBankInfoForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyBankInfoForm");
	}

	// @Ignore
	@Test
	public void addCompanyBankInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/bankInfo/addCompanyBankInfo");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(3L));
		request.setParameter("bankName", "BOA");
		request.setParameter("routingNumber", "13533");
		request.setParameter("accountNumber", "134533");
		request.setParameter("accountType", AccountType.SAVING.toString());
		request.setParameter("status", Status.ACTIVE.toString());
		ModelAndView mav = handlerAdapter.handle(request, response, companyBankInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyBankInfoForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyBankInfoForm");
	}

	@Ignore
	@Test
	public void showUpdateCompanyBankInfo() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/bankInfo/showUpdateCompanyBankInfo/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(3L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyBankInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyBankInfoForm");
	}

	@Ignore
	@Test
	public void updateCompanyBankInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/bankInfo/updateCompanyBankInfo");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(3L));
		request.setParameter("id", "1");
		request.setParameter("bankName", "BOA");
		request.setParameter("routingNumber", "1");
		request.setParameter("accountNumber", "1");
		request.setAttribute("accountType", AccountType.CHECKING);
		request.setAttribute("status", Status.ACTIVE);
		ModelAndView mav = handlerAdapter.handle(request, response, companyBankInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyBankInfoForm");
	}
}