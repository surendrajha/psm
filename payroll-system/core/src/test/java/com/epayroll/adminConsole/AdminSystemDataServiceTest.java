package com.epayroll.adminConsole;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epayroll.TestRoot;
import com.epayroll.entity.AllowanceRate;
import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.DeductionCap;
import com.epayroll.entity.StandardDeductionRate;
import com.epayroll.form.adminConsole.AllowanceRateForm;
import com.epayroll.form.adminConsole.DeductionCapForm;
import com.epayroll.form.adminConsole.StandardDeductionRateForm;
import com.epayroll.form.adminConsole.TaxAuthorityDepositCycleForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * 
 * @author Uma
 * 
 */
public class AdminSystemDataServiceTest extends TestRoot {

	@Autowired
	private AdminSystemDataService adminSystemDataService;

	// @Test
	public void getFederalAllowanceRates() {
		System.out.println("getFederalAllowanceRates...");
		boolean testSuccess = false;
		try {
			List<AllowanceRate> allowanceRates = adminSystemDataService.getFederalAllowanceRates();
			System.out.println("allowanceRates::::" + allowanceRates);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateAllowanceRates() {
		System.out.println("getStateAllowanceRates...");
		boolean testSuccess = false;
		try {
			List<AllowanceRate> allowanceRates = adminSystemDataService.getStateAllowanceRates(1L);
			System.out.println("allowanceRates::::" + allowanceRates);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalAllowanceTypes() {
		System.out.println("getFederalAllowanceTypes...");
		boolean testSuccess = false;
		try {
			List<AllowanceType> allowanceTypes = adminSystemDataService.getFederalAllowanceTypes();
			System.out.println("allowanceTypes::::" + allowanceTypes);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateAllowanceTypes() {
		System.out.println("getStateAllowanceTypes...");
		boolean testSuccess = false;
		try {
			List<AllowanceType> allowanceTypes = adminSystemDataService.getStateAllowanceTypes(1L);
			System.out.println("allowanceTypes::::" + allowanceTypes);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@SuppressWarnings("deprecation")
	// @Test
	public void addFederalAllowanceRate() {
		System.out.println("addFederalAllowanceRate...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
			allowanceRateForm.setAllowanceTypeId(2L);
			allowanceRateForm.setRate(new BigDecimal(34));
			allowanceRateForm.setWef(new Date(12, 12, 12));
			adminSystemDataService.addFederalAllowanceRate(allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@SuppressWarnings("deprecation")
	// @Test
	public void addStateAllowanceRate() {
		System.out.println("addStateAllowanceRate...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
			allowanceRateForm.setAllowanceTypeId(2L);
			allowanceRateForm.setRate(new BigDecimal(34));
			allowanceRateForm.setWef(new Date(12, 12, 12));
			allowanceRateForm.setStateId(1L);
			adminSystemDataService.addStateAllowanceRate(allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalAllowanceRateForm() {
		System.out.println("getFederalAllowanceRateForm...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = adminSystemDataService
					.getFederalAllowanceRateForm(1L);
			System.out.println("allowanceRateForm::::" + allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateAllowanceRateForm() {
		System.out.println("getStateAllowanceRateForm...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = adminSystemDataService
					.getStateAllowanceRateForm(11L);
			System.out.println("allowanceRateForm::::" + allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@SuppressWarnings("deprecation")
	// @Test
	public void updateFederalAllowanceRate() {
		System.out.println("updateFederalAllowanceRate...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
			allowanceRateForm.setAllowanceTypeId(2L);
			allowanceRateForm.setRate(new BigDecimal(300));
			allowanceRateForm.setWef(new Date(11, 11, 12));
			allowanceRateForm.setId(4L);
			adminSystemDataService.updateFederalAllowanceRate(allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@SuppressWarnings("deprecation")
	// @Test
	public void updateStateAllowanceRate() {
		System.out.println("updateStateAllowanceRate...");
		boolean testSuccess = false;
		try {
			AllowanceRateForm allowanceRateForm = new AllowanceRateForm();
			allowanceRateForm.setAllowanceTypeId(2L);
			allowanceRateForm.setRate(new BigDecimal(300));
			allowanceRateForm.setWef(new Date(11, 11, 12));
			allowanceRateForm.setId(11L);
			allowanceRateForm.setStateId(2L);
			adminSystemDataService.updateStateAllowanceRate(allowanceRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteFederalAllowanceRate() {
		System.out.println("deleteFederalAllowanceRate...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteFederalAllowanceRate(4L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteStateAllowanceRate() {
		System.out.println("deleteStateAllowanceRate...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteStateAllowanceRate(6L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalStandardDeductions() {
		System.out.println("getFederalStandardDeductions...");
		boolean testSuccess = false;
		try {
			List<StandardDeductionRate> standardDeductionRates = adminSystemDataService
					.getFederalStandardDeductions();
			System.out.println("standardDeductionRates::::" + standardDeductionRates);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateStandardDeductions() {
		System.out.println("getStateStandardDeductions...");
		boolean testSuccess = false;
		try {
			List<StandardDeductionRate> standardDeductionRates = adminSystemDataService
					.getStateStandardDeductions(1L);
			System.out.println("standardDeductionRates::::" + standardDeductionRates);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addFederalStandardDeductionRate() {
		System.out.println("addFederalStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
			standardDeductionRateForm.setFilingStatusId(1L);
			standardDeductionRateForm.setMaxValue(new BigDecimal(23));
			standardDeductionRateForm.setMinValue(new BigDecimal(34));
			standardDeductionRateForm.setRate(new BigDecimal(300));
			standardDeductionRateForm.setWef(new Date(2012, 12, 12));
			adminSystemDataService.addFederalStandardDeductionRate(standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addStateStandardDeductionRate() {
		System.out.println("addStateStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
			standardDeductionRateForm.setFilingStatusId(1L);
			standardDeductionRateForm.setMaxValue(new BigDecimal(23));
			standardDeductionRateForm.setMinValue(new BigDecimal(34));
			standardDeductionRateForm.setRate(new BigDecimal(300));
			standardDeductionRateForm.setWef(new Date(2012, 12, 12));
			standardDeductionRateForm.setStateId(1L);
			adminSystemDataService.addStateStandardDeductionRate(standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalStandardDeductionRateForm() {
		System.out.println("getFederalStandardDeductionRateForm...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = adminSystemDataService
					.getFederalStandardDeductionRateForm(1L);
			System.out.println("federalStandardDeductionRateForm::::" + standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateStandardDeductionRateForm() {
		System.out.println("getStateStandardDeductionRateForm...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = adminSystemDataService
					.getStateStandardDeductionRateForm(3L);
			System.out.println("federalStandardDeductionRateForm::::" + standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateFederalStandardDeductionRate() {
		System.out.println("updateFederalStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
			standardDeductionRateForm.setFilingStatusId(1L);
			standardDeductionRateForm.setId(2L);
			standardDeductionRateForm.setMaxValue(new BigDecimal(23));
			standardDeductionRateForm.setMinValue(new BigDecimal(24));
			standardDeductionRateForm.setRate(new BigDecimal(4440));
			standardDeductionRateForm.setWef(new Date(2012, 10, 12));
			adminSystemDataService.updateFederalStandardDeductionRate(standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateStateStandardDeductionRate() {
		System.out.println("updateStateStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			StandardDeductionRateForm standardDeductionRateForm = new StandardDeductionRateForm();
			standardDeductionRateForm.setFilingStatusId(1L);
			standardDeductionRateForm.setId(2L);
			standardDeductionRateForm.setMaxValue(new BigDecimal(230000));
			standardDeductionRateForm.setMinValue(new BigDecimal(240000));
			standardDeductionRateForm.setRate(new BigDecimal(4440000));
			standardDeductionRateForm.setWef(new Date(2012, 10, 12));
			standardDeductionRateForm.setStateId(2L);
			adminSystemDataService.updateStateStandardDeductionRate(standardDeductionRateForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteFederalStandardDeductionRate() {
		System.out.println("deleteFederalStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteFederalStandardDeductionRate(2L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteStateStandardDeductionRate() {
		System.out.println("deleteStateStandardDeductionRate...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteStateStandardDeductionRate(2L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalDeductionCap() {
		System.out.println("getFederalDeductionCap...");
		boolean testSuccess = false;
		try {
			List<DeductionCap> deductionCaps = adminSystemDataService.getFederalDeductionCap();
			System.out.println("deductionCaps::::" + deductionCaps);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateDeductionCap() {
		System.out.println("getStateDeductionCap...");
		boolean testSuccess = false;
		try {
			List<DeductionCap> deductionCaps = adminSystemDataService.getStateDeductionCap(2L);
			System.out.println("deductionCaps::::" + deductionCaps);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getCompanyList() {
		System.out.println("getCompanyList...");
		boolean testSuccess = false;
		try {
			List<Company> companies = adminSystemDataService.getCompanyList();
			System.out.println("companies:::" + companies);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getCompanyDeductionList() {
		System.out.println("getCompanyDeductionList...");
		boolean testSuccess = false;
		try {
			List<CompanyDeduction> companyDeductions = adminSystemDataService
					.getCompanyDeductionList(1L);
			System.out.println("companyDeductions:::" + companyDeductions);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addFederalDeductionCap() {
		System.out.println("addFederalDeductionCap...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = new DeductionCapForm();
			deductionCapForm.setCapAmount(new BigDecimal(23));
			deductionCapForm.setCompanyDeductionId(1L);
			deductionCapForm.setWef(new Date(2012, 11, 11));
			adminSystemDataService.addFederalDeductionCap(deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addStateDeductionCap() {
		System.out.println("addStateDeductionCap...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = new DeductionCapForm();
			deductionCapForm.setCapAmount(new BigDecimal(23));
			deductionCapForm.setCompanyDeductionId(1L);
			deductionCapForm.setWef(new Date(2012, 11, 11));
			deductionCapForm.setStateId(1L);
			adminSystemDataService.addStateDeductionCap(deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFederalDeductionCapForm() {
		System.out.println("getFederalDeductionCapForm...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = adminSystemDataService
					.getFederalDeductionCapForm(1L);
			System.out.println("federalDeductionCapForm::::" + deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getStateDeductionCapForm() {
		System.out.println("getFederalDeductionCapForm...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = adminSystemDataService.getStateDeductionCapForm(3L);
			System.out.println("federalDeductionCapForm::::" + deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateFederalDeductionCap() {
		System.out.println("addFederalDeductionCap...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = new DeductionCapForm();
			deductionCapForm.setCapAmount(new BigDecimal(33));
			deductionCapForm.setCompanyDeductionId(2L);
			deductionCapForm.setWef(new Date(2012, 11, 11));
			deductionCapForm.setId(3L);
			adminSystemDataService.updateFederalDeductionCap(deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateStateDeductionCap() {
		System.out.println("addFederalDeductionCap...");
		boolean testSuccess = false;
		try {
			DeductionCapForm deductionCapForm = new DeductionCapForm();
			deductionCapForm.setCapAmount(new BigDecimal(33));
			deductionCapForm.setCompanyDeductionId(2L);
			deductionCapForm.setWef(new Date(2012, 11, 11));
			deductionCapForm.setId(2L);
			deductionCapForm.setStateId(1L);
			adminSystemDataService.updateStateDeductionCap(deductionCapForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteFederalDeductionCap() {
		System.out.println("deleteFederalDeductionCap...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteFederalDeductionCap(3L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteStateDeductionCap() {
		System.out.println("deleteStateDeductionCap...");
		boolean testSuccess = false;
		try {
			adminSystemDataService.deleteStateDeductionCap(4L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Test
	public void getTaxAuthorityDepositCycleForm() {
		System.out.println("getTaxAuthorityDepositCycleForm...");
		boolean testSuccess = false;
		try {
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
					.getTaxAuthorityDepositCycleForm(2L);
			System.out.println("taxAuthorityDepositCycleForm:::" + taxAuthorityDepositCycleForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}
}
