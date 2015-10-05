package com.epayroll.ui.contoller.employee.access;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.entity.Payroll;
import com.epayroll.form.employeeAccess.PayStubsForm;
import com.epayroll.model.employeeAccess.PayStubModel;
import com.epayroll.service.employeeAccess.EmployeeAccessPayStubsInfoService;
import com.epayroll.spring.authorization.EmployeeAccessAuthorizationUtils;

/**
 * 
 * @author Rajul Tiwari
 * 
 */

@RequestMapping("/employeeAccess/payStub")
@Controller
public class EmployeePayStubsInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeePayStubsInfoController.class);

	@Autowired
	private EmployeeAccessPayStubsInfoService employeeAccessPayStubsInfoService;

	private Employee getEmployeeFromSession(HttpSession session) {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = EmployeeAccessAuthorizationUtils.getLoginUser();
		// Employee employee = (Employee) session.getAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	@RequestMapping(value = "/showPayStubsForm", method = RequestMethod.GET)
	public ModelAndView showPayStubsForm(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView("payStub");
		Employee employee = getEmployeeFromSession(session);
		modelView.addObject("employee", employee);
		modelView.addObject("payStubsForm", new PayStubsForm());
		modelView.addObject("yearList",
				employeeAccessPayStubsInfoService.getYearListForPayStubs(employee.getId()));
		logger.debug("showList >>");
		return modelView;
	}

	@RequestMapping(value = "/getCheckDateList", method = RequestMethod.POST)
	public ModelAndView getCheckDateList(@ModelAttribute PayStubsForm payStubsForm,
			HttpSession session) {
		logger.debug(">> getCheckDateList");
		ModelAndView modelView = new ModelAndView("payStub");
		Employee employee = getEmployeeFromSession(session);
		modelView.addObject("employee", employee);
		logger.debug("EMPLOYEE ID :: " + employee.getId());
		// For Populating Filter form
		modelView.addObject("yearList",
				employeeAccessPayStubsInfoService.getYearListForPayStubs(employee.getId()));
		List<Payroll> payrollCheckDateList;
		try {
			payrollCheckDateList = employeeAccessPayStubsInfoService.getPayrollCheckDateList(
					payStubsForm.getYear(), employee.getId());
			modelView.addObject("checkDateList", payrollCheckDateList);
		} catch (Exception e) {
			logger.error("ERROR:: " + e.getMessage());
		}
		logger.debug("getCheckDateList >>");
		return modelView;
	}

	@RequestMapping(value = "/showPayStubsDetail", method = RequestMethod.POST)
	public ModelAndView showPayStubsDetail(@ModelAttribute PayStubsForm payStubsForm,
			HttpSession session) {
		logger.debug(">> showPayStubsDetail");
		ModelAndView modelView = new ModelAndView("payStub");
		try {
			Employee employee = getEmployeeFromSession(session);

			// PayStubModel For map all the values
			PayStubModel payStubModel = employeeAccessPayStubsInfoService.getPayStubModel(employee,
					payStubsForm);
			modelView.addObject("payStubModel", payStubModel);

			// For Populating Filter form
			modelView.addObject("yearList",
					employeeAccessPayStubsInfoService.getYearListForPayStubs(employee.getId()));
			List<Payroll> payrollCheckDateList;
			payrollCheckDateList = employeeAccessPayStubsInfoService.getPayrollCheckDateList(
					payStubsForm.getYear(), employee.getId());
			modelView.addObject("checkDateList", payrollCheckDateList);
		} catch (Exception e) {
			logger.error("ERROR:: " + e.getMessage());
		}
		logger.debug("showPayStubsDetail >>");
		return modelView;
	}

	// previous method

	// @RequestMapping(value = "/showPayStubsDetail", method =
	// RequestMethod.POST)
	// public ModelAndView showPayStubsDetail(@ModelAttribute PayStubsForm
	// payStubsForm,
	// HttpSession session) {
	// logger.debug(">> showPayStubsDetail");
	// ModelAndView modelView = new ModelAndView("payStubsForm");
	// try {
	// Employee employee = getEmployeeFromSession(session);
	// modelView.addObject("employee", employee);
	//
	// // For Displaying Pay Stub
	// EmployeePayroll employeePayroll = employeeAccessPayStubsInfoService
	// .getEmployeePayroll(payStubsForm.getXxxxxxxxxPayrollId());
	// modelView.addObject("employeePayroll", employeePayroll);
	//
	// Address companyAddress =
	// employeeAccessPayStubsInfoService.getCompanyAddress(employee
	// .getCompany().getId());
	// modelView.addObject("companyAddress", companyAddress);
	//
	// Address employeeAddress =
	// employeeAccessPayStubsInfoService.getEmployeeAddress(employee
	// .getId());
	// modelView.addObject("employeeAddress", employeeAddress);
	//
	// modelView.addObject("employeeW4Details",
	// employeeAccessPayStubsInfoService.getEmployeeW4Details(employee.getId()));
	// modelView.addObject("employeePayrollEarningList",
	// employeeAccessPayStubsInfoService
	// .getEmployeePayrollEarningList(employeePayroll.getId()));
	// modelView.addObject("employeePayrollDeductionList",
	// employeeAccessPayStubsInfoService
	// .getEmployeePayrollDeductionList(employeePayroll.getId()));
	// modelView.addObject("employeePayrollTaxList",
	// employeeAccessPayStubsInfoService
	// .getEmployeePayrollTaxList(employeePayroll.getId()));
	// // For Populating Filter form
	// modelView.addObject("yearList",
	// employeeAccessPayStubsInfoService.getYearListForPayStubs(employee.getId()));
	// List<Payroll> payrollCheckDateList;
	// payrollCheckDateList =
	// employeeAccessPayStubsInfoService.getCheckDateList(
	// payStubsForm.getYear(), employee.getId());
	// modelView.addObject("checkDateList", payrollCheckDateList);
	// } catch (Exception e) {
	// logger.error("ERROR:: " + e.getMessage());
	// }
	// logger.debug("showPayStubsDetail >>");
	// return modelView;
	// }

}
