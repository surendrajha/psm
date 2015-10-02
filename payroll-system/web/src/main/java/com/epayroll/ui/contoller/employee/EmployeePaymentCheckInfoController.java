package com.epayroll.ui.contoller.employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;
import com.epayroll.form.employee.PaymentCheckInfoForm;
import com.epayroll.service.employee.EmployeePaymentCheckInfoService;
import com.epayroll.ui.contoller.helper.EmployeePaymentCheckInfoControllerHelper;

/**
 * @author Rajul Tiwari
 */

// @RequestMapping("/employee/paymentCheckInfo")
@Controller
public class EmployeePaymentCheckInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeePaymentCheckInfoController.class);
	@Autowired
	private EmployeePaymentCheckInfoService employeePaymentCheckInfoService;

	@Autowired
	private EmployeePaymentCheckInfoControllerHelper employeePaymentCheckInfoControllerHelper;

	private Employee getEmployeeFromSession(HttpSession session) {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = (Employee) session.getAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	@RequestMapping(value = "/showPaymentCheckInfoForm", method = RequestMethod.GET)
	public ModelAndView showPaymentCheckInfoForm(HttpSession session) {
		logger.debug(">> showPaymentCheckInfoForm");
		ModelAndView modelView = new ModelAndView("payCheckInfoPage");
		modelView.addObject("paymentCheckInfoForm", new PaymentCheckInfoForm());
		Employee employee = getEmployeeFromSession(session);
		logger.debug("Employee::" + employee);
		List<Date> payDateList = employeePaymentCheckInfoService.getPayDates(employee.getId());
		session.setAttribute("payDateList", payDateList);
		List<EmployeePayroll> paymentList = employeePaymentCheckInfoService.getPaymentes(employee
				.getId());
		modelView.addObject("paymentList", paymentList);
		logger.debug("showPaymentCheckInfoForm >>");
		return modelView;
	}

	@RequestMapping(value = "/showPaymentCheckInfo", method = RequestMethod.POST)
	public ModelAndView showPaymentCheckInfo(
			@ModelAttribute PaymentCheckInfoForm paymentCheckInfoForm, HttpSession session) {
		logger.debug(">> showPaymentCheckInfo");
		ModelAndView modelView = new ModelAndView("payCheckInfoPage");
		try {
			Employee employee = getEmployeeFromSession(session);
			session.getAttribute("payDateList");
			// modelView.addObject("payDateList", payDateList);
			List<EmployeePayroll> searchedPaymentList = employeePaymentCheckInfoService
					.getSearchedPaymentes(employee.getId(), paymentCheckInfoForm);
			modelView.addObject("paymentList", searchedPaymentList);
			modelView.addObject("paymentCheckInfoForm", paymentCheckInfoForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR in showPaymentCheckInfo::" + e.getMessage());
		}
		logger.debug("showPaymentCheckInfo >>");
		return modelView;
	}

	@RequestMapping(value = "/showPaymentDetails/{employeePayrollId}", method = RequestMethod.GET)
	public ModelAndView showPaymentDetails(@PathVariable Long employeePayrollId, HttpSession session) {
		logger.debug(">> showPaymentDetails");
		ModelAndView modelView = new ModelAndView("paymentDetailsPage");
		try {
			List<EmployeePayrollDeduction> employeePayrollDeductions = employeePaymentCheckInfoService
					.getEmployeeDeductionList(employeePayrollId);
			modelView.addObject("employeePayrollDeductions", employeePayrollDeductions);
			BigDecimal deductionTotal = employeePaymentCheckInfoControllerHelper
					.getDeductionTotal(employeePayrollDeductions);
			modelView.addObject("deductionTotal", deductionTotal);
			if (getEmployeeFromSession(session).getEmployeeType().equals(Type.EMPLOYEE)) {
				List<EmployeePayrollEarning> employeePayrollEarnings = employeePaymentCheckInfoService
						.getEmployeeEarningList(employeePayrollId);
				modelView.addObject("payrollEarnings", employeePayrollEarnings);
				BigDecimal earningTotal = employeePaymentCheckInfoControllerHelper
						.getEarningTotal(employeePayrollEarnings);
				modelView.addObject("earningTotal", earningTotal);
				List<EmployeePayrollTax> employeePayrollTaxes = employeePaymentCheckInfoService
						.getEmployeeTaxList(employeePayrollId);
				modelView.addObject("employeePayrollTaxes", employeePayrollTaxes);
				BigDecimal taxTotal = employeePaymentCheckInfoControllerHelper
						.getTaxTotal(employeePayrollTaxes);
				modelView.addObject("taxTotal", taxTotal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("showPaymentDetails >>");
		return modelView;
	}

}
