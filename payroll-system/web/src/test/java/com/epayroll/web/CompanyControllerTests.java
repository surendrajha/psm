package com.epayroll.web;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyInfoController;
import com.epayroll.ui.contoller.company.CompanyPayrollScheduleController;
import com.epayroll.ui.contoller.company.CompanyWorkersCompensationController;

/**
 * 
 * @author Uma Saraswat
 * 
 */
public class CompanyControllerTests extends TestRoot {

	@Autowired
	private CompanyInfoController companyInfoController;

	@Autowired
	private CompanyPayrollScheduleController companyPayrollScheduleController;

	@Autowired
	private CompanyWorkersCompensationController companyWorkersCompensationController;

	@Autowired
	private CompanyService companyService;

	// @Test
	public void showLegalInfoForm() throws Exception {
		System.out.println("showLegalInfoFormTest >>");
		request.setMethod("GET");
		request.setRequestURI("/showLegalInfoForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "legalInfoForm");
		System.out.println(">> showLegalInfoFormTest");
	}

	// @Test
	public void showBusinessInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showBusinessInfoForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "businessInfoForm");
		ModelAndViewAssert.assertViewName(mav, "companyform");

	}

	// @Test
	public void showShippingInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showShippingInfoForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "shippingInfoForm");
		ModelAndViewAssert.assertViewName(mav, "companyform");

	}

	// @Test
	public void showAuthorizedSignatoryContactForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showAuthorizedSignatoryContactForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "authorizedSignatoryContactForm");
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void showPrimaryContactForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showPrimaryContactForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "primaryContactForm");
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void showBillingContactForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showBillingContactForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "billingContactForm");
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void addOrUpdateShippingInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addOrUpdateShippingInfo");
		request.setParameter("streetAddress", "12");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("countyId", "1");
		request.setParameter("pinZip", "1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void addOrUpdateAuthorizedSignatoryContact() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addOrUpdateAuthorizedSignatoryContact");
		request.setParameter("firstName", "Rajul");
		request.setParameter("lastName", "Tiwari");
		request.setParameter("designation", "ABC");
		request.setParameter("email", "rajul@gmail.com");
		request.setParameter("phone", "22");
		request.setParameter("ext", "22");
		request.setParameter("fax", "22");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void addOrUpdatePrimaryContact() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addOrUpdatePrimaryContact");
		request.setParameter("firstName", "RT");
		request.setParameter("lastName", "33");
		request.setParameter("designation", "33");
		request.setParameter("email", "33");
		request.setParameter("phone", "33");
		request.setParameter("ext", "33");
		request.setParameter("fax", "33");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	@Test
	public void addOrUpdateBillingContact() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addOrUpdateBillingContact");
		request.setParameter("firstName", "2eeeee");
		request.setParameter("lastName", "22");
		request.setParameter("email", "22");
		request.setParameter("phone", "22");
		request.setParameter("ext", "22");
		request.setParameter("fax", "22");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	// @Test
	public void showPayrollFrequencyList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showPayrollFrequencyList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "PayrollFrequencyList");
	}

	// @Test
	public void addPayrollFrequency() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addPayrollFrequency");
		request.setParameter("periodStartDate", "12/01/12");
		request.setParameter("periodEndDate", "01/01/13");
		request.setParameter("checkDate", "01/15/13");
		request.setParameter("holidayCheckDateOption", "before");
		request.setParameter("payFrequencyType.id", "1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "PayrollScheduleList");
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "success");
	}

	// @Test
	public void showPayrollFrequencyForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/showPayrollFrequencyForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
	}

	// @Test
	public void updatePayrollFrequency() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updatePayrollFrequency");
		request.setParameter("id", "1");
		request.setParameter("periodStartDate", "01/01/13");
		request.setParameter("periodEndDate", "02/01/13");
		request.setParameter("checkDate", "02/15/13");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void deletePayRollFrequency() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deletePayRollFrequency/1");
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
	}

	// @Test
	public void deletePayRollschedule() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deletePayRollschedule/4");
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyPayrollScheduleController);
		ModelAndViewAssert.assertViewName(mav, "payrollFrequencyForm");
	}

	// @Test
	public void showWorkersCompensationList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showWorkersCompensationList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "compensationTaxRateViewList");
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
	}

	// @Test
	public void showWorkersCompensationAddForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showWorkersCompensationAddForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "usStateList");
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
	}

	// @Test
	public void addWorkersCompensation() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addWorkersCompensation");
		request.setParameter("stateId", "1");
		request.setParameter("code", "003");
		request.setParameter("description", "vxcxgdg");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void showWorkersCompensationForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showWorkersCompensationForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "usStateList");
	}

	// @Test
	public void updateWorkersCompensation() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateWorkersCompensation");
		request.setParameter("id", "1");
		request.setParameter("stateId", "1");
		// request.setParameter("code", "1234567");
		request.setParameter("description", "workers Compensation updated");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// @Test
	public void deleteWorkersCompensation() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/deleteWorkersCompensation/2");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				companyWorkersCompensationController);
		ModelAndViewAssert.assertViewName(mav, "companyWorkersCompensationForm");
	}

	@Ignore
	@Test
	public void addLegalInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addLegalInfo");
		request.setParameter("legalName", "MyCompany");
		request.setParameter("streetAddress", "12/24");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("countyId", "1");
		request.setParameter("pinZip", "91462030");
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

	@Ignore
	@Test
	public void addOrUpdateBusinessInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addOrUpdateBusinessInfo");
		request.setParameter("businessName", "RRT");
		request.setParameter("businessPhone", "9787878787");
		request.setParameter("businessFax", "121");
		request.setParameter("webAddress", "aaaa");
		request.setParameter("streetAddress", "bpl");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("countyId", "1");
		request.setParameter("pinZip", "912030");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyInfoController);
		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
		ModelAndViewAssert.assertViewName(mav, "companyform");
	}

}
