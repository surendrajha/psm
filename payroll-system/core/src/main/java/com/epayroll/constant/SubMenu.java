/**
 * 
 */
package com.epayroll.constant;

/**
 * @author Basit Azeem Sheikh
 */
public enum SubMenu {

	// Company Portal Sub Menus
	// Company Sub Menus
	COMPANY_INFO("Company Info", Menu.COMPANY), //
	COMPANY_MANAGE_USER("Manage Users", Menu.COMPANY), //
	COMPANY_BANK_INFO("Bank Info", Menu.COMPANY), //
	COMPANY_PAYROLL_SCHEDULE("Payroll Schedule", Menu.COMPANY), //
	COMPANY_TAX_INFO("Tax Info", Menu.COMPANY), //
	COMPANY_EARNINGS_AND_DEDUCTIONS("Earnings & Deductions", Menu.COMPANY), //
	COMPANY_DEPARTMENT_LIST("Department List", Menu.COMPANY), //
	COMPANY_PAID_BENEFITS("Paid Benefits", Menu.COMPANY), //
	COMPANY_WORKERS_COMPENSATION("Worker Compensation", Menu.COMPANY), //
	COMPANY_PRODUCT_FEATURES("Product Features", Menu.COMPANY), //
	COMPANY_PRODUCT_INTEGRATION("Product Integration", Menu.COMPANY), //

	// Employees Sub Menus
	EMPLOYEE_CONTRACTOR_LIST("Employee Contractor List", Menu.EMPLOYEES), //
	EMPLOYEE_HOME("Employee Home", Menu.EMPLOYEES), //
	CONTRACTOR_HOME("Contractor Home", Menu.EMPLOYEES), //
	EMPLOYEE_INFO("Employee Info", Menu.EMPLOYEES), //
	CONTRACTOR_INFO("Contractor Info", Menu.EMPLOYEES), //
	PAY_SETUP("Pay Setup", Menu.EMPLOYEES), //
	EMPLOYEE_EARNINGS_AND_DEDUCTIONS("Earnings & Deductions", Menu.EMPLOYEES), //
	CONTRACTOR_DEDUCTIONS("Contractor Deductions", Menu.EMPLOYEES), //
	EMPLOYEE_BANK_INFO("Bank Info", Menu.EMPLOYEES), //
	EMPLOYEE_TAX_INFO("Tax Info", Menu.EMPLOYEES), //
	DEPARTMENT_ALLOCATION("Department Allocation", Menu.EMPLOYEES), //

	// Payroll Sub Menus
	PAYROLL_HOME("Payroll Home", Menu.PAYROLL), //
	NORMAL_PAYROLL("Normal Payroll", Menu.PAYROLL), //
	ONE_CLICK_PAYROLL("One Click Payroll", Menu.PAYROLL), //
	SPECIAL_PAYROLL("Special Payroll", Menu.PAYROLL), //
	CALCULATE_AND_RECORD_MANUAL_CHECKS("Calculate & Record Manual Checks", Menu.PAYROLL), //
	VOID_A_CHECK("Void A Check", Menu.PAYROLL), //
	THIRD_PARTY_SICK_PAY("3rd Party Sick Pay (3PSP)", Menu.PAYROLL), //
	PAYMENT_RECONCILIATION("Payment Reconciliation", Menu.PAYROLL), //

	// Reports Sub Menus
	PAYROLL_REPORTS("Payroll Reports", Menu.REPORTS), //
	TAX_REPORTS("Tax Reports", Menu.REPORTS), //
	EMPLOYEE_REPORTS("Employee Reports", Menu.REPORTS), //
	DEPARTMENT_REPORTS("Department Reports", Menu.REPORTS), //
	BENEFIT_REPORTS("Benefit Reports", Menu.REPORTS), //
	OTHER_REPORTS("Other Reports", Menu.REPORTS), //

	// Admin Portal Sub Menus
	// Manage Users Sub Menus
	MY_ACCOUNT("My Account", Menu.MANAGE_USERS), //
	ADD_UPDATE_USERS("Add Update Users", Menu.MANAGE_USERS), //
	MANAGE_ROLE_PERMISSIONS("Manage Role Permissions", Menu.MANAGE_USERS), //

	// Manage Companies (It is not required because it can be managed from
	// Company Sub Menus)

	// Manage Payrolls (It is not required because it can be managed from
	// Payroll Sub Menus)

	// Manage System Data Sub Menus
	TAX_RELATED_DATA("Tax Related Data", Menu.MANAGE_SYSTEM_DATA), //
	EARNINGS_AND_DEDUCTIONS_DATA("Earnings And Deductions Data", Menu.MANAGE_SYSTEM_DATA), //
	OTHER_DATA("Other Data", Menu.MANAGE_SYSTEM_DATA), //

	// Manage Fund Transactions Sub Menus
	SCHEDULED_BATCHES("Scheduled Batches", Menu.MANAGE_FUND_TRANSACTIONS), //
	ALL_BATCHES("All Batches", Menu.MANAGE_FUND_TRANSACTIONS), //
	ALL_TRANSACTIONS("All Transactions", Menu.MANAGE_FUND_TRANSACTIONS), //

	// Tax Deposit and Reporting Sub Menus
	TAX_DEPOSITING("Tax Depositing", Menu.TAX_DEPOSIT_AND_REPORITNG), //
	TAX_REPORTING("Tax Reporting", Menu.TAX_DEPOSIT_AND_REPORITNG), //

	// Data Import and Export Sub Menus
	IMPORT_DATA("Import Data", Menu.DATA_IMPORT_AND_EXPORT), //
	EXPORT_DATA("Export Data", Menu.DATA_IMPORT_AND_EXPORT), //

	// Reports Sub Menus
	INVOICE_REPORT("Invoices", Menu.ADMIN_REPORTS), //
	PAYROLL_REPORT("Payrolls", Menu.ADMIN_REPORTS), //
	COMPANY_REPORT("Company Details", Menu.ADMIN_REPORTS), //
	USER_ACTIVITY_REPORTS("User Activity Report", Menu.ADMIN_REPORTS), //
	FUND_TRANSACTION_REPORT("Fund Transaction Report", Menu.ADMIN_REPORTS);

	private String name;
	private Menu menu;

	private SubMenu(String name, Menu menu) {
		this.name = name;
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public Menu getMenu() {
		return menu;
	}

}
