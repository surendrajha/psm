package com.epayroll.employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epayroll.TestRoot;
import com.epayroll.form.employee.EmployeeFederalTaxForm;
import com.epayroll.form.employee.EmployeeLocalTaxForm;
import com.epayroll.form.employee.EmployeeStateTaxForm;
import com.epayroll.model.employee.EmployeeAllowanceModel;
import com.epayroll.model.employee.TaxTypeList;
import com.epayroll.service.employee.EmployeeService;

/**
 * 
 * @author Rajul Tiwari
 * 
 */
public class EmployeeServiceTest extends TestRoot {

	@Autowired
	private EmployeeService employeeService;

	// @Test
	public void getCities() {
		System.out.println("getCities...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			System.out.println("Cities ::" + employeeService.getUsCities());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Ignore
	@Test
	public void getStates() {
		System.out.println("getStates...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			System.out.println("States ::" + employeeService.getUsStates());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addEmployeeStateTax() {
		System.out.println("addEmployeeStateTax...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			EmployeeStateTaxForm employeeStateTaxForm = new EmployeeStateTaxForm();
			employeeStateTaxForm.setAdditionalWithholding(new BigDecimal(500));
			employeeStateTaxForm.setEmployeeId(2L);
			employeeStateTaxForm.setSitExempted(false);
			employeeStateTaxForm.setSutaExempted(true);
			employeeStateTaxForm.setTaxFillingStatusId(1L);
			List<EmployeeAllowanceModel> allowanceModels = new ArrayList<>();
			allowanceModels.add(new EmployeeAllowanceModel(1L, "Personal", 1L));
			allowanceModels.add(new EmployeeAllowanceModel(2L, "Dependent", 2L));
			employeeStateTaxForm.setAllowanceModels(allowanceModels);

			System.out.println("Add Result ::"
					+ employeeService.addEmployeeStateTax(employeeStateTaxForm));
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addFedralEmployeeTax() {
		System.out.println("addFedralEmployeeTax...");
		boolean testSuccess = false;
		try {

			EmployeeFederalTaxForm employeeFederalTaxForm = new EmployeeFederalTaxForm();
			employeeFederalTaxForm.setAdditionalWithholding(new BigDecimal(500));
			employeeFederalTaxForm.setEmployeeId(2L);
			employeeFederalTaxForm.setTaxFillingStatusId(1L);
			employeeFederalTaxForm.setNoOfAllowances(5L);
			employeeFederalTaxForm.setAllowanceTypeId(1L);
			List<TaxTypeList> taxTypeLists = new ArrayList<>();
			taxTypeLists.add(new TaxTypeList(1L, "fit", true, true));
			taxTypeLists.add(new TaxTypeList(2L, "medicare", true, false));
			taxTypeLists.add(new TaxTypeList(4L, "futa", true, false));
			taxTypeLists.add(new TaxTypeList(6L, "socialSecurity", true, false));

			employeeFederalTaxForm.setExemptedList(taxTypeLists);

			System.out.println("Add Result ::"
					+ employeeService.addEmployeeFederalTax(employeeFederalTaxForm, 2L));
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Test
	public void addEmployeeLocalTax() {
		System.out.println("addEmployeeLocalTax...");
		boolean testSuccess = false;
		try {

			EmployeeLocalTaxForm employeeLocalTaxForm = new EmployeeLocalTaxForm();

			employeeLocalTaxForm.setEmployeeId(2L);
			employeeLocalTaxForm.setExempted(true);
			System.out.println("Add Result ::"
					+ employeeService.addEmployeeLocalTax(employeeLocalTaxForm, 2L));
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateEmployeeStateTax() {
		System.out.println("updateEmployeeStateTax...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			EmployeeStateTaxForm employeeStateTaxForm = new EmployeeStateTaxForm();
			employeeStateTaxForm.setAdditionalWithholding(new BigDecimal(1000));
			employeeStateTaxForm.setEmployeeId(1L);
			employeeStateTaxForm.setSitExempted(true);
			employeeStateTaxForm.setSutaExempted(false);
			employeeStateTaxForm.setTaxFillingStatusId(2L);
			employeeStateTaxForm.setEmployeeSitId(21L);
			employeeStateTaxForm.setEmployeeSutaId(22L);
			List<EmployeeAllowanceModel> allowanceModels = new ArrayList<>();
			allowanceModels.add(new EmployeeAllowanceModel(2L, "Dependent", 10L));
			allowanceModels.add(new EmployeeAllowanceModel(1L, "Personal", 20L));
			employeeStateTaxForm.setAllowanceModels(allowanceModels);
			employeeService.updateEmployeeStateTax(employeeStateTaxForm);
			// System.out.println("Add Result ::"
			// + employeeService.updateEmployeeStateTax(employeeStateTaxForm));
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateEmployeeFederalTax() {
		System.out.println("addFedralEmployeeTax...");
		boolean testSuccess = false;
		try {

			EmployeeFederalTaxForm employeeFederalTaxForm = new EmployeeFederalTaxForm();
			employeeFederalTaxForm.setAdditionalWithholding(new BigDecimal(5000));
			employeeFederalTaxForm.setEmployeeId(1L);
			employeeFederalTaxForm.setTaxFillingStatusId(1L);
			employeeFederalTaxForm.setNoOfAllowances(5000L);
			employeeFederalTaxForm.setAllowanceTypeId(1L);
			List<TaxTypeList> taxTypeLists = new ArrayList<>();
			taxTypeLists.add(new TaxTypeList(1L, "federaltax", true, true));
			taxTypeLists.add(new TaxTypeList(2L, "medicare", false, false));
			taxTypeLists.add(new TaxTypeList(4L, "futa", false, false));
			taxTypeLists.add(new TaxTypeList(6L, "socialSecurity incometax", true, false));

			employeeFederalTaxForm.setExemptedList(taxTypeLists);

			employeeService.updateEmployeeFederalTax(employeeFederalTaxForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateEmployeeLocalTax() {
		System.out.println("addEmployeeLocalTax...");
		boolean testSuccess = false;
		try {

			EmployeeLocalTaxForm employeeLocalTaxForm = new EmployeeLocalTaxForm();
			employeeLocalTaxForm.setEmployeeTaxId(23L);
			employeeLocalTaxForm.setEmployeeId(2L);
			employeeLocalTaxForm.setExempted(true);

			employeeService.updateEmployeeLocalTax(employeeLocalTaxForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

}
