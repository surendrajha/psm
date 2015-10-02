package com.epayroll.company;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epayroll.TestRoot;
import com.epayroll.entity.Company;
import com.epayroll.form.company.CompanyStateLocalTaxFrom;
import com.epayroll.form.company.CompanyTaxForm;
import com.epayroll.model.company.CompanyStateLocalTaxModel;
import com.epayroll.service.company.CompanyService;

/**
 * 
 * @author Surendra Jha
 * 
 */
public class CompanyServiceTest extends TestRoot {

	@Autowired
	private CompanyService companyService;

	// @Autowired
	// private EmployeeService employeeService;
	// @Test
	public void businessInfo() {
		System.out.println("start Business info [Test]");
		boolean testSuccess = false;
		try {
			// get the company
			Company company = companyService.findCompany(1L);
			System.out.println("company::" + company);
			System.out.println("Address::" + company.getAddresses());

			// update information
			// List<Address> addresses = company.getAddresses();
			// Address businessAddress = (Address) addresses.get(0);
			// System.out.println("BusinessAddress::" + businessAddress);
			// //
			// businessAddress.setStreetAddress("mp nagar");
			// businessAddress.setCity("Bhopal");
			// businessAddress.setCountry("India1");
			// businessAddress.setPinZip("425452");
			//
			// company.setBusinessName("Sri Ganesh");
			// company.setBusinessPhone("077522222222");
			// company.setBusinessFax("07551245215");
			// company.setWebAddress("www.sriganesh.com");
			//
			// // call update method
			// company = companyService.updateBusinessInfo(company);

			System.out.println("Updated ::" + company);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getSystemMessages() {
		System.out.println("start Business info [Test]");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			System.out.println("Messages ::" + companyService.getMessages());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getCities() {
		System.out.println("getCities...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			// System.out.println("Cities ::" + employeeService.getUsCities());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStates() {
		System.out.println("getStates...");
		boolean testSuccess = false;
		try {
			// Set<SystemMessage> systemMessages = companyService.getMessages();
			// System.out.println("States ::" + employeeService.getUsStates());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addFederalTax() {
		System.out.println("addFederalTax...");
		boolean testSuccess = false;
		try {
			CompanyTaxForm companyTaxForm = new CompanyTaxForm();
			companyTaxForm.setCompanyId(1L);
			companyTaxForm.setDepositCycleId(1L);
			companyTaxForm.setEin("123");
			companyTaxForm.setExempted(true);
			companyTaxForm.setTaxTypeId(1L);
			companyService.addFederalTax(companyTaxForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addStateTax() {
		System.out.println("addStateTax...");
		boolean testSuccess = false;
		try {
			CompanyStateLocalTaxFrom companyStateLocalTaxFrom = new CompanyStateLocalTaxFrom();
			companyStateLocalTaxFrom.setCompanyId(1L);
			List<CompanyStateLocalTaxModel> companyStateLocalTaxModels = new ArrayList<>();
			companyStateLocalTaxModels
					.add(new CompanyStateLocalTaxModel(5L, "sit", true, "123", 1L));
			companyStateLocalTaxModels.add(new CompanyStateLocalTaxModel(6L, "suta", true, "133",
					1L));
			companyStateLocalTaxFrom.setCompanyStateLocalTaxModels(companyStateLocalTaxModels);
			companyService.addStateTax(companyStateLocalTaxFrom);

			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Test
	public void addLocalTax() {
		System.out.println("addLocalTax...");
		boolean testSuccess = false;
		try {
			CompanyStateLocalTaxFrom companyStateLocalTaxFrom = new CompanyStateLocalTaxFrom();
			companyStateLocalTaxFrom.setCompanyId(1L);
			List<CompanyStateLocalTaxModel> companyStateLocalTaxModels = new ArrayList<>();
			companyStateLocalTaxModels.add(new CompanyStateLocalTaxModel(10L, "local1", true,
					"1234", 1L));
			companyStateLocalTaxModels.add(new CompanyStateLocalTaxModel(11L, "local2", true,
					"1334", 1L));
			companyStateLocalTaxFrom.setCompanyStateLocalTaxModels(companyStateLocalTaxModels);
			companyService.addLocalTax(companyStateLocalTaxFrom);

			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}
}
