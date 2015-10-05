/**
 * 
 */
package com.epayroll.ui.contoller.payroll;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.entity.CompanyEarning;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.payroll.PayRollForm;
import com.epayroll.form.payroll.PayrollDeductionForm;
import com.epayroll.service.payroll.PayrollService;

/**
 * @author Kamran
 */

@Controller
@RequestMapping("/payroll")
public class PayrollController {

	private Logger logger = LoggerFactory.getLogger(PayrollController.class);

	@Autowired
	private PayrollService payrollService;

	@RequestMapping(value = "/showNormalPayroll")
	public ModelAndView showNormalPayroll() {
		logger.debug(">> showNormalPayroll ");
		ModelAndView modelView = new ModelAndView("runPayroll");
		modelView.addObject("pfList", payrollService.getPayrollFrequencies(1L));// companyID
		modelView.addObject("payRollForm", new PayRollForm());
		logger.debug("showNormalPayroll >> ");
		return modelView;
	}

	@RequestMapping(value = "/showPayrollData", method = RequestMethod.POST)
	public ModelAndView showPayrollData(@ModelAttribute PayRollForm payRollForm) {

		logger.debug(">> showPayrollData  , payRollForm::" + payRollForm);

		ModelAndView modelView = new ModelAndView("runPayroll");

		List<CompanyEarning> companyEarnings = payrollService.getCompanyEarnings(1L);
		modelView.addObject("companyEarningList", companyEarnings);

		payRollForm = payrollService.getPayRollForm(1L, payRollForm);
		payRollForm.setNoOfEarning(companyEarnings.size());
		modelView.addObject("payRollForm", payRollForm);

		modelView.addObject("pfList", payrollService.getPayrollFrequencies(1L));// companyID

		return modelView;
	}

	@RequestMapping(value = "/saveAndProcessLater", method = RequestMethod.POST)
	public ModelAndView saveAndProcessLater(@ModelAttribute PayRollForm payRollForm) {
		logger.debug("in saveAndProcessLater()::payRollForm::" + payRollForm);
		ModelAndView modelAndView = new ModelAndView("runPayroll");

		payrollService.updatePayrollData(payRollForm);

		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView deletePayroll(@ModelAttribute PayRollForm payRollForm) {
		logger.debug("try to deleting..");

		ModelAndView modelAndView = new ModelAndView("runPayroll");
		payrollService.deletePayroll(payRollForm.getPayrollId());
		return modelAndView;
	}

	@RequestMapping(value = "/getTaxesAndDeductions/{employeePayrollId}", method = RequestMethod.GET)
	public ModelAndView getTaxesAndDeductions(@PathVariable Long employeePayrollId) {

		ModelAndView modelAndView = new ModelAndView("overrideTaxDeduction");

		// list of override options
		modelAndView.addObject("taxOverrideOptions", payrollService.getTaxOverrideTypes());

		// list of deduction types
		modelAndView.addObject("deductionTypes", payrollService.getDeductionOverrideTypes());

		PayrollDeductionForm payrollDeductionForm = payrollService
				.getTaxAndDeductionFromPayroll(employeePayrollId);
		modelAndView.addObject("payrollDeductionForm", payrollDeductionForm);

		return modelAndView;
	}

	@RequestMapping(value = "/saveOrUpdateTaxesAndDeductions", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateTaxesAndDeductions(
			@ModelAttribute PayrollDeductionForm payrollDeductionForm, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("overrideTaxDeduction");
		payrollService.updateTaxAndDeductions(payrollDeductionForm);
		modelAndView.setView(new RedirectView(request.getContextPath()
				+ "/payroll/showNormalPayroll"));

		return modelAndView;
	}

	@RequestMapping(value = "/getPreview", method = RequestMethod.POST)
	public ModelAndView previewPayroll(@ModelAttribute PayRollForm payRollForm)
			throws InstanceNotFoundException {
		logger.debug("In previewPayroll");

		ModelAndView modelAndView = new ModelAndView("previewPayroll");
		modelAndView.addObject("payrollPreviewForm", payrollService.calculatePayroll(33L, 2L));

		return modelAndView;
	}
}
