package com.epayroll.adminConsole;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epayroll.TestRoot;
import com.epayroll.form.adminConsole.AllowanceTypeForm;
import com.epayroll.form.adminConsole.FundCategoryForm;
import com.epayroll.form.adminConsole.PaymentModeForm;
import com.epayroll.form.adminConsole.PayrollPlanForm;
import com.epayroll.form.adminConsole.PlanFeatureForm;
import com.epayroll.form.adminConsole.TransactionBodyForm;
import com.epayroll.service.adminConsole.AdminOtherDataService;

/**
 * 
 * @author Uma
 * 
 */
public class AdminOtherDataServiceTest extends TestRoot {

	@Autowired
	private AdminOtherDataService adminOtherDataService;

	// @Test
	public void addAllowanceType() {
		System.out.println("addAllowanceType...");
		boolean testSuccess = false;
		try {
			AllowanceTypeForm allowanceTypeForm = new AllowanceTypeForm();
			allowanceTypeForm.setDescription("desc");
			allowanceTypeForm.setType("type");
			adminOtherDataService.addAllowanceType(allowanceTypeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getUpdateAllowanceTypeForm() {
		System.out.println("getUpdateAllowanceTypeForm...");
		boolean testSuccess = false;
		try {
			AllowanceTypeForm allowanceTypeForm = adminOtherDataService
					.getUpdateAllowanceTypeForm(1L);
			System.out.println("allowanceTypeForm::" + allowanceTypeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateAllowanceType() {
		System.out.println("updateAllowanceType...");
		boolean testSuccess = false;
		try {
			AllowanceTypeForm allowanceTypeForm = new AllowanceTypeForm();
			allowanceTypeForm.setDescription("desc updated");
			allowanceTypeForm.setType("type updated");
			allowanceTypeForm.setId(3L);
			adminOtherDataService.updateAllowanceType(allowanceTypeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteAllowanceType() {
		System.out.println("deleteAllowanceType...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.verifyAnddeleteAllowanceType(3L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addPayrollPlan() {
		System.out.println("addPayrollPlan...");
		boolean testSuccess = false;
		try {
			PayrollPlanForm payrollPlanForm = new PayrollPlanForm();
			payrollPlanForm.setPlanName("plan");
			payrollPlanForm.setDescription("plan1");
			adminOtherDataService.addPayrollPlan(payrollPlanForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getPayrollPlanForm() {
		System.out.println("getPayrollPlanForm...");
		boolean testSuccess = false;
		try {
			PayrollPlanForm payrollPlanForm = adminOtherDataService.getPayrollPlanForm(1L);
			System.out.println("payrollPlanForm::" + payrollPlanForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updatePayrollPlan() {
		System.out.println("updatePayrollPlan...");
		boolean testSuccess = false;
		try {
			PayrollPlanForm payrollPlanForm = new PayrollPlanForm();
			payrollPlanForm.setPlanName("plan updated");
			payrollPlanForm.setDescription("plan1 updated");
			payrollPlanForm.setId(1L);
			adminOtherDataService.updatePayrollPlan(payrollPlanForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deletePayrollPlan() {
		System.out.println("deletePayrollPlan...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.verifyAnddeletePayrollPlan(1L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addPlanFeature() {
		System.out.println("addPlanFeature...");
		boolean testSuccess = false;
		try {
			PlanFeatureForm planFeatureForm = new PlanFeatureForm();
			planFeatureForm.setDescription("desc");
			planFeatureForm.setPayrollPlanId(2L);
			planFeatureForm.setService("service");
			adminOtherDataService.addPlanFeature(planFeatureForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getPlanFeatureForm() {
		System.out.println("getPlanFeatureForm...");
		boolean testSuccess = false;
		try {
			PlanFeatureForm planFeatureForm = adminOtherDataService.getPlanFeatureForm(1L);
			System.out.println("planFeatureForm::" + planFeatureForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updatePlanFeature() {
		System.out.println("updatePlanFeature...");
		boolean testSuccess = false;
		try {
			PlanFeatureForm planFeatureForm = new PlanFeatureForm();
			planFeatureForm.setDescription("desc updated");
			planFeatureForm.setPayrollPlanId(2L);
			planFeatureForm.setService("service updated");
			planFeatureForm.setId(1L);
			adminOtherDataService.updatePlanFeature(planFeatureForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deletePlanFeature() {
		System.out.println("deletePlanFeature...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.verifyAnddeletePlanFeature(1L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addFundCategory() {
		System.out.println("addFundCategory...");
		boolean testSuccess = false;
		try {
			FundCategoryForm fundCategoryForm = new FundCategoryForm();
			fundCategoryForm.setCategoryName("categoryName");
			fundCategoryForm.setDescription("description");
			adminOtherDataService.addFundCategory(fundCategoryForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getFundCategoryForm() {
		System.out.println("getFundCategoryForm...");
		boolean testSuccess = false;
		try {
			FundCategoryForm fundCategoryForm = adminOtherDataService.getFundCategoryForm(1L);
			System.out.println("fundCategoryForm::" + fundCategoryForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateFundCategory() {
		System.out.println("updateFundCategory...");
		boolean testSuccess = false;
		try {
			FundCategoryForm fundCategoryForm = new FundCategoryForm();
			fundCategoryForm.setCategoryName("categoryName updated");
			fundCategoryForm.setDescription("description updated");
			fundCategoryForm.setId(1L);
			adminOtherDataService.updateFundCategory(fundCategoryForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deleteFundCategory() {
		System.out.println("deleteFundCategory...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.verifyAnddeleteFundCategory(1L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addPaymentMode() {
		System.out.println("addPaymentMode...");
		boolean testSuccess = false;
		try {
			PaymentModeForm paymentModeForm = new PaymentModeForm();
			paymentModeForm.setDescription("desc");
			paymentModeForm.setMode("mode");
			adminOtherDataService.addPaymentMode(paymentModeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getPaymentModeForm() {
		System.out.println("getPaymentModeForm...");
		boolean testSuccess = false;
		try {
			PaymentModeForm paymentModeForm = adminOtherDataService.getPaymentModeForm(0L);
			System.out.println("paymentModeForm::" + paymentModeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updatePaymentMode() {
		System.out.println("updatePaymentMode...");
		boolean testSuccess = false;
		try {
			PaymentModeForm paymentModeForm = new PaymentModeForm();
			paymentModeForm.setDescription("desc updated");
			paymentModeForm.setMode("mode updated");
			paymentModeForm.setId(0L);
			adminOtherDataService.updatePaymentMode(paymentModeForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void deletePaymentMode() {
		System.out.println("deletePaymentMode...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.verifyAnddeletePaymentMode(0L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void addTransactionBody() {
		System.out.println("addTransactionBody...");
		boolean testSuccess = false;
		try {
			TransactionBodyForm transactionBodyForm = new TransactionBodyForm();
			transactionBodyForm.setDescription("desc");
			transactionBodyForm.setName("name");
			adminOtherDataService.addTransactionBody(transactionBodyForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void getTransactionBodyForm() {
		System.out.println("getTransactionBodyForm...");
		boolean testSuccess = false;
		try {
			TransactionBodyForm transactionBodyForm = adminOtherDataService
					.getTransactionBodyForm(1L);
			System.out.println("transactionBodyForm::" + transactionBodyForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Test
	public void updateTransactionBody() {
		System.out.println("updateTransactionBody...");
		boolean testSuccess = false;
		try {
			TransactionBodyForm transactionBodyForm = new TransactionBodyForm();
			transactionBodyForm.setDescription("desc updated");
			transactionBodyForm.setName("name updated");
			transactionBodyForm.setId(1L);
			adminOtherDataService.updateTransactionBody(transactionBodyForm);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Test
	public void deleteTransactionBody() {
		System.out.println("deleteTransactionBody...");
		boolean testSuccess = false;
		try {
			adminOtherDataService.deleteTransactionBody(1L);
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

}
