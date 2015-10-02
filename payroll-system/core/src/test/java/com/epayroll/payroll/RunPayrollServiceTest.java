/**
 * 
 */
package com.epayroll.payroll;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.epayroll.TestRoot;
import com.epayroll.dao.company.UserDao;
import com.epayroll.service.payroll.PayrollService;

/**
 * @author USER
 */
public class RunPayrollServiceTest extends TestRoot {

	@Autowired
	PayrollService payrollService;
	@Autowired
	private HibernateTemplate ht;
	@Autowired
	private UserDao userDao;

	@Ignore
	@Test
	public void getPayrollFrequencies() {
		System.out.println("getPayrollfrequencies...");
		boolean testSuccess = false;
		try {
			System.out.println("Frequencies ::" + payrollService.getPayrollFrequencies(1L));
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	// @Ignore
	// @Test
	// public void getPayrollSchedule() {
	// System.out.println("get Payroll Schedule...");
	// boolean testSuccess = false;
	// try {
	// System.out.println("PayRoll Dates ::"
	// + payrollService.getCurrentPayrollSchedule(1L, 1L));
	// testSuccess = true;
	// } catch (Exception e) {
	// System.out.println("ERROR::" + e.getLocalizedMessage());
	// }
	// Assert.assertTrue(testSuccess);
	// System.out.println("Test Completed !");
	// }

	@Ignore
	@Test
	public void getemployeepayrollData() {
		System.out.println("get Payroll Data...");
		boolean testSuccess = false;
		try {

			// List<Employee> empList = payrollService.getEmployees(1L, 1L);
			// List<Employee> contractorList = payrollService.getContractors(
			// 1L, 1L);
			//
			// System.out.println(empList.size());
			// System.out.println(empList);
			// System.out.println(contractorList.size());
			// System.out.println(contractorList);
			// System.out.println(empList.get(0).getPerson().getFirstName());
			// System.out.println(empList.get(0).getEmployeeDeductions());
			// System.out.println(empList.get(0).getEmployeeEarnings());

			// ht.getSessionFactory().getCurrentSession().close();
			// System.out.println("Removing...");
			// boolean b = empList.get(0).getEmployeeEarnings().remove(0);
			// System.out.println(b);
			// System.out.println(empList.get(0).getEmployeeEarnings());

			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Ignore
	@Test
	public void geEmployeePrimaryTaxList() {
		System.out.println("get PrimaryTax List...");
		boolean testSuccess = false;
		try {
			System.out.println("Tax List ::" + payrollService.getEmployeePrimaryTaxList(1L));
			System.out
					.println("Tax Name ::: "
							+ payrollService.getEmployeePrimaryTaxList(1L).get(0).getTaxType()
									.getTaxName());
			testSuccess = true;
		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Test
	public void getEmployeePayroll() {
		boolean testSuccess = false;
		try {

			System.out.println("Calculating Taxes");
			payrollService.calculatePayroll(33L, 1L);
			testSuccess = true;
		} catch (Exception e) {
			// System.out.println("ERROR::" + e.getLocalizedMessage());
			e.printStackTrace();
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Ignore
	@Test
	public void setCheckStubMessage() {
		boolean testSuccess = false;
		try {

			int i = payrollService.setCheckStubMessage(33L, "VJS Payroll");
			List<String> ls = userDao.getApprovers(1L);
			System.out.println(ls);
			if (i == 1)
				System.out.println("Update Successful");
			else
				System.out.println("Update Unsuccessful");
			testSuccess = true;
		} catch (Exception e) {
			// System.out.println("ERROR::" + e.getLocalizedMessage());
			e.printStackTrace();
			System.out.println("ERROR::" + e.getLocalizedMessage());
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

	@Ignore
	@Test
	public void getTaxAndDeductionFromPayroll() {
		boolean testSuccess = false;
		try {

			payrollService.getTaxAndDeductionFromPayroll(64L);
			testSuccess = true;

		} catch (Exception e) {
			System.out.println("ERROR::" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		Assert.assertTrue(testSuccess);
		System.out.println("Test Completed !");
	}

}
