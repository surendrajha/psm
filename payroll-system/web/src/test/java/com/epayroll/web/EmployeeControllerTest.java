package com.epayroll.web;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.EmployeeDeduction.DeductionValueType;
import com.epayroll.entity.EmployeeEarning.EarningValueType;
import com.epayroll.entity.EmployeePaySetup.PayType;
import com.epayroll.entity.EmployeePayrollTax.TaxOverrideType;
import com.epayroll.service.company.CompanyService;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.ui.contoller.employee.EmployeeDepartmentController;
import com.epayroll.ui.contoller.employee.EmployeeEarningDeductionController;
import com.epayroll.ui.contoller.employee.EmployeeInfoController;
import com.epayroll.ui.contoller.employee.EmployeePaySetupController;
import com.epayroll.ui.contoller.employee.EmployeeSectionController;
import com.epayroll.ui.contoller.employee.EmployeeTaxInfoController;

/**
 * @author Rajul Tiwari
 */

public class EmployeeControllerTest extends TestRoot {

	@Autowired
	private EmployeeInfoController employeeController;

	@Autowired
	private EmployeeSectionController employeeSectionController;

	@Autowired
	private EmployeeEarningDeductionController employeeEarningDeductionController;

	@Autowired
	private EmployeeTaxInfoController employeeTaxInfoController;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeDepartmentController departmentController;

	@Autowired
	private EmployeePaySetupController employeePaySetupController;

	// @Test
	public void showEmployeeList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
	}

	@Ignore
	// @Test
	public void employeeSearch() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/employeeSearch");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("usStateList", employeeService.getUsStates());
		request.getSession().setAttribute("usCityList", employeeService.getUsCities());
		// set parameters value of requested object
		// request.setParameter("name", "Rajul");
		// request.setParameter("ssn", "1");
		// request.setParameter("streetAddress", "address");
		// request.setParameter("zip", "55");
		// request.setParameter("cityId", "1");
		// request.setParameter("stateId", "address");
		// request.setParameter("status", "1");
		// request.setParameter("phone", "1");
		// request.setParameter("email", "address");
		// request.setParameter("accessToEmployee", "enabled");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeSearchForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "constractorSearchForm");
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

	// @Test
	public void contractorSearch() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/contractorSearch");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("name", "Rajul");
		// request.setParameter("ssn", "1");
		// request.setParameter("streetAddress", "address");
		// request.setParameter("zip", "1");
		// request.setParameter("cityId", "1");
		// request.setParameter("stateId", "address");
		// request.setParameter("status", "1");
		// request.setParameter("phone", "1");
		// request.setParameter("email", "address");
		// request.setParameter("accessToEmployee", "enabled");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "constractorSearchForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeSearchForm");
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

	// @Test
	public void showTerminateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeTerminateForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "terminateForm");
	}

	// @Test
	public void terminate() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/terminate");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("isEmployeeTerminate", "1");
		request.setParameter("dateOfTermination", "01/11/2013");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
	}

	// @Test
	public void showRehireForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeRehireForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "rehireForm");
	}

	// @Test
	public void rehire() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/rehire");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("isEmployeeRehire", "1");
		request.setParameter("dateOfRehire", "01/09/2013");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
	}

	// @Test
	public void showHomePage() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showHomePage/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		// if emoloyeeType employee
		ModelAndViewAssert.assertViewName(mav, "employeeHomePage");

		// else if emoloyeeType contractor
		// ModelAndViewAssert.assertViewName(mav, "contractorHomePage");
	}

	// @Test
	public void updateEmployeeInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/updateEmployeeInfoForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		// if emoloyeeType employee
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");
		// else if emoloyeeType contractor
		// ModelAndViewAssert.assertViewName(mav, "contractorHomePage");
	}

	// @Test
	public void updateEmployeePersonalInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEmployeePersonalInfo");
		// request.getSession().setAttribute("loggedInUser",
		// companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		// set parameters value of requested object
		request.setParameter("firstName", "Rahul");
		request.setParameter("ssn", "1");
		request.setParameter("lastName", "Tiwari");
		request.setParameter("phone", "55");
		request.setParameter("dob", "10/30/1988");
		request.setParameter("gender", "male");
		request.setParameter("email", "rhlmailcom");
		request.setParameter("streetAddress", "address");
		request.setParameter("zip", "55");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "EmployeeInfoForm");

	}

	// @Test
	public void updateEmployeeEmploymentInfo() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEmployeeEmploymentInfo");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		// set parameters value of requested object
		request.setParameter("employeeId", "EMPLOYEE001");
		request.setParameter("zip", "10101");
		request.setParameter("workersCompCode", "workersCompCode001");
		request.setParameter("dateOfHireing", "10/10/12");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");

		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		// ModelAndViewAssert.assertModelAttributeAvailable(mav,
		// "employeeSearchForm");
		// ModelAndViewAssert.assertModelAttributeAvailable(mav,
		// "constractorSearchForm");
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

	// @Test
	public void showEmploymentHistory() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmploymentHistory/1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employmentHistoryPage");
	}

	@Ignore
	// @Test
	public void showManageAccessForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showManageAccessForm/3");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(3L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeAccessView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeAccessForm");
	}

	// @Test
	public void sendMailOfEmployeeAccess() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/sendMailOfEmployeeAccess");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(3L));
		request.setParameter("emailAddress", "uma@i-techsoftware.com");
		request.setParameter("allowAccessCheckBox", "false");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeAccessForm");
	}

	// @Test
	public void showEmployeeSections() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeSections/3");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(3L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeSectionController);
		ModelAndViewAssert.assertViewName(mav, "employeeSectionView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeSectionForm");
	}

	// @Test
	public void addSections() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addSections");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(3L));
		request.setParameter("companyId", "1");
		// List<CompanyEmployeeSection> companyEmployeeSections =
		// employeeService
		// .getCompanyEmployeeSections(1L);
		// request.setParameter("companyEmployeeSections",
		// "companyEmployeeSections");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeSectionController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeAccessForm");
	}

	// ///////////////// EMPLOYEE DEPARTMENT ////////////////////
	@Ignore
	// @Test
	public void getDepartmentAllocation() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/departmentAllocation");

		ModelAndView mav = handlerAdapter.handle(request, response, departmentController);

		ModelAndViewAssert.assertViewName(mav, "departmentAllocation");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "allocationList");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// ///// END ///////

	// @Test
	public void showEmployeeEarningsAndDeductionAddForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeEarningsAndDeductionAddForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeEarningView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeEarningForm");
	}

	// @Test
	public void addEmployeeEarning() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeeEarning");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("earningValueType", EarningValueType.AMOUNT.toString());
		request.setParameter("value", "100");
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeEarningView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeEarningForm");
	}

	// @Test
	public void addEmployeeDeduction() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeeDeduction");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("deductionValueType", DeductionValueType.PERCENTAGE.toString());
		request.setParameter("value", "100");
		request.setParameter("targetValue", "2000");
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeDeductionView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeDeductionForm");
	}

	// @Test
	public void showEmployeeEarningsAndDeduction() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeEarningsAndDeduction");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeEarningView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeEarningForm");
	}

	// @Test
	public void showUpdateEmployeeEarningForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateEmployeeEarningForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeEarningView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeEarningForm");
	}

	// @Test
	public void showUpdateEmployeeDeductionForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateEmployeeDeductionForm/1");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeDeductionView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeDeductionForm");
	}

	// @Test
	public void updateEmployeeEarning() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEmployeeEarning");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("employeeEarningId", "1");
		request.setParameter("earningValueType", EarningValueType.AMOUNT.toString());
		request.setParameter("value", "100");
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeEarningView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeEarningForm");
	}

	// @Test
	public void updateEmployeeDeduction() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEmployeeDeduction");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		request.setParameter("employeeDeductionId", "1");
		request.setParameter("deductionValueType", DeductionValueType.PERCENTAGE.toString());
		request.setParameter("value", "100");
		request.setParameter("targetValue", "2000");
		ModelAndView mav = handlerAdapter.handle(request, response,
				employeeEarningDeductionController);
		ModelAndViewAssert.assertViewName(mav, "employeeDeductionView");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeeDeductionForm");
	}

	// /////////////// EMPLOYEE PAY SETUP //////////////////////
	@Ignore
	@Test
	public void getEmployeePaySetup() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/getPaySetup");

		ModelAndView mav = handlerAdapter.handle(request, response, employeePaySetupController);

		ModelAndViewAssert.assertViewName(mav, "employeePaySetup");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "payTypeList");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "payCycleList");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "employeePaySetupForm");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	@Ignore
	@Test
	public void updateEmployeePaySetup() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateEmployeePaySetup");
		request.setParameter("id", "2");
		request.setParameter("payType", PayType.HOURLY.name());
		request.setParameter("payCycle", "1");
		request.setParameter("hourlyRate", "105");
		request.setParameter("standardHours", "2");
		request.setParameter("overtimeRate", "1");
		request.setParameter("otherRate", "0");

		ModelAndView mav = handlerAdapter.handle(request, response, employeePaySetupController);

		ModelAndViewAssert.assertViewName(mav, "employeePaySetup");

		for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue() + "\n");
		}
	}

	// //////////// END /////////////

	// @Test
	public void showEmployeeTaxList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeTaxList");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxListPage");
	}

	// @Test
	public void showUpdateFederalTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateFederalTaxForm/17");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void showUpdateStateTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateStateTaxForm/21");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void showUpdateLocalTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showUpdateLocalTaxForm/23");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void showAddEmployeeTaxForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showAddEmployeeTaxForm/2");
		request.getSession().setAttribute("employee", employeeService.getEmployee(1L));
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void updateFedralEmployeeTax() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateFedralEmployeeTax");
		request.setParameter("employeeTaxId", "5");
		request.setParameter("allowanceTypeId", "1");
		request.setParameter("noOfAllowances", "3");
		request.setParameter("TaxFillingStatusId", "1");
		request.setParameter("fitExempted", "1");
		request.setParameter("additionalWithholding", "6000");
		request.setParameter("taxOverrideType", TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT.toString());
		request.setParameter("taxOverrideValue", "1300");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void updateStateEmployeeTax() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateStateEmployeeTax");
		request.setParameter("employeeTaxId", "23");
		request.setParameter("allowanceTypeId", "1");
		request.setParameter("noOfAllowances", "3");
		request.setParameter("TaxFillingStatusId", "1");
		request.setParameter("fitExempted", "1");
		request.setParameter("additionalWithholding", "6000");
		request.setParameter("taxOverrideType", TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT.toString());
		request.setParameter("taxOverrideValue", "1300");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	// @Test
	public void updateLocalEmployeeTax() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/updateLocalEmployeeTax");
		request.setParameter("employeeTaxId", "23");
		request.setParameter("allowanceTypeId", "1");
		request.setParameter("noOfAllowances", "3");
		request.setParameter("TaxFillingStatusId", "1");
		request.setParameter("exempted", "1");
		request.setParameter("additionalWithholding", "6000");
		request.setParameter("taxOverrideType", TaxOverrideType.ADDITIONAL_DOLLAR_AMOUNT.toString());
		request.setParameter("taxOverrideValue", "1300");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeTaxInfoController);
		ModelAndViewAssert.assertViewName(mav, "employeeTaxView");
	}

	@Ignore
	@Test
	public void showEmployeeParsonalInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeParsonalInfoForm");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

	// @Ignore
	@Test
	public void addEmployeePersonalForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeePersonalForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("firstName", "basit");
		request.setParameter("ssn", "1");
		request.setParameter("lastName", "Sharcvma");
		request.setParameter("phone", "53422");
		request.setParameter("dob", "10/01/1990");
		request.setParameter("gender", "Female");
		request.setParameter("email", "raerwni@mailcom");
		request.setParameter("streetAddress", "address");
		request.setParameter("zip", "51215");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

	// @Test
	public void addEmployeePersonalFormAndCompleteLater() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeePersonalFormAndCompleteLater");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("firstName", "dsdrgfd");
		request.setParameter("ssn", "1");
		request.setParameter("lastName", "dfrma");
		request.setParameter("phone", "5225");
		request.setParameter("dob", "10/01/1990");
		request.setParameter("gender", "Male");
		request.setParameter("email", "yrs@mailcom");
		request.setParameter("streetAddress", "sdf address");
		request.setParameter("zip", "5133");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");
	}

	@Ignore
	@Test
	public void showEmployeeEmploymentInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showEmployeeEmploymentInfoForm/4");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

	// @Test
	public void addEmployeeEmploymentForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeeEmploymentForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("taskId", 7L);
		// set parameters value of requested object
		request.setParameter("employeeId", "EMPLOYEE001");
		request.setParameter("zip", "10101");
		request.setParameter("workersCompCodeId", "1");
		request.setParameter("dateOfHireing", "10/11/2012");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("streetAddress", "ad11dress");
		request.setParameter("workFax", "1451");
		request.setParameter("workEmail", "sdc11@gh.com");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);

		ModelAndViewAssert.assertViewName(mav, "employeeInfoForm");

	}

	// @Test
	public void addEmployeeEmploymentFormAndCompleteLater() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addEmployeeEmploymentFormAndCompleteLater");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("taskId", 4L);
		// set parameters value of requested object
		request.setParameter("employeeId", "contractor01");
		request.setParameter("zip", "101601");
		request.setParameter("workersCompCodeId", "1");
		request.setParameter("dateOfHireing", "10/16/2012");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("streetAddress", "a6e6s");
		request.setParameter("workFax", "161");
		request.setParameter("workEmail", "rr611@grh.com");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);

		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

	// @Ignore
	// @Test
	public void showContractorParsonalInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showContractorParsonalInfoForm");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "contractorInfoForm");

	}

	// @Test
	public void addContractorPersonalForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addContractorPersonalForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("firstName", "chul");
		request.setParameter("ssn", "1");
		request.setParameter("lastName", "tiwari");
		request.setParameter("phone", "55222");
		request.setParameter("dob", "10/01/1990");
		request.setParameter("gender", "Female");
		request.setParameter("email", "chul@mailcom");
		request.setParameter("streetAddress", "address");
		request.setParameter("zip", "545");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "contractorInfoForm");

	}

	// @Test
	public void addContractorPersonalFormAndCompleteLater() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addContractorPersonalFormAndCompleteLater");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("firstName", "chul");
		request.setParameter("ssn", "1");
		request.setParameter("lastName", "tiwari");
		request.setParameter("phone", "55222");
		request.setParameter("dob", "10/01/1990");
		request.setParameter("gender", "Female");
		request.setParameter("email", "chul@mailcom");
		request.setParameter("streetAddress", "address");
		request.setParameter("zip", "545");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

	// @Ignore
	// @Test
	public void showContractorEmploymentInfoForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/showContractorEmploymentInfoForm/5");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "contractorInfoForm");

	}

	// @Test
	public void addContractorEmploymentForm() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addContractorEmploymentForm");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("taskId", 5L);
		// set parameters value of requested object
		request.setParameter("employeeId", "con001");
		request.setParameter("zip", "10101");
		request.setParameter("dateOfHireing", "10/11/2012");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("streetAddress", "ad11dress");
		request.setParameter("workFax", "1451");
		request.setParameter("workEmail", "sdc11@gh.com");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);

		ModelAndViewAssert.assertViewName(mav, "contractorInfoForm");

	}

	// @Test
	public void addContractorEmploymentFormAndCompleteLater() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/addContractorEmploymentFormAndCompleteLater");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		request.getSession().setAttribute("taskId", 6L);
		// set parameters value of requested object
		request.setParameter("employeeId", "contractor01");
		request.setParameter("zip", "101601");
		request.setParameter("dateOfHireing", "10/16/2012");
		request.setParameter("countyId", "1");
		request.setParameter("cityId", "1");
		request.setParameter("stateId", "1");
		request.setParameter("streetAddress", "a6e6s");
		request.setParameter("workFax", "161");
		request.setParameter("workEmail", "rr611@grh.com");
		ModelAndView mav = handlerAdapter.handle(request, response, employeeController);
		ModelAndViewAssert.assertViewName(mav, "employeeListPage");

	}

}