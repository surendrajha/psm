package com.epayroll.ui.contoller.helper;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.epayroll.entity.EmployeePayrollDeduction;
import com.epayroll.entity.EmployeePayrollEarning;
import com.epayroll.entity.EmployeePayrollTax;

/**
 * @author Rajul Tiwari
 */
@Component
public class EmployeePaymentCheckInfoControllerHelper {
	private Logger logger = LoggerFactory.getLogger(EmployeePaymentCheckInfoControllerHelper.class);

	public BigDecimal getEarningTotal(List<EmployeePayrollEarning> employeePayrollEarnings) {
		BigDecimal total = new BigDecimal(0);
		Iterator<EmployeePayrollEarning> iterator = employeePayrollEarnings.iterator();
		while (iterator.hasNext()) {
			EmployeePayrollEarning employeePayrollEarning = iterator.next();
			BigDecimal value = employeePayrollEarning.getValue();
			total = total.add(value);
		}
		logger.debug("total");
		return total;
	}

	public BigDecimal getTaxTotal(List<EmployeePayrollTax> employeePayrollTaxes) {
		BigDecimal total = new BigDecimal(0);
		Iterator<EmployeePayrollTax> iterator = employeePayrollTaxes.iterator();
		while (iterator.hasNext()) {
			EmployeePayrollTax employeePayrollTax = iterator.next();
			BigDecimal value = employeePayrollTax.getCalculatedTax();
			total = total.add(value);
		}
		logger.debug("total");
		return total;
	}

	public BigDecimal getDeductionTotal(List<EmployeePayrollDeduction> employeePayrollDeductions) {
		BigDecimal total = new BigDecimal(0);
		Iterator<EmployeePayrollDeduction> iterator = employeePayrollDeductions.iterator();
		while (iterator.hasNext()) {
			EmployeePayrollDeduction employeePayrollDeduction = iterator.next();
			BigDecimal value = employeePayrollDeduction.getCalculatedDeduction();
			total = total.add(value);
		}
		logger.debug("total");
		return total;
	}
}
