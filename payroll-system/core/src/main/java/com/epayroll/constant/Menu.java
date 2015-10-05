/**
 * 
 */
package com.epayroll.constant;

/**
 * @author Basit Azeem Sheikh
 */
public enum Menu {

	// Payroll Company Menus
	COMPANY("Company", Application.PAYROLL), // Company Menu
	EMPLOYEES("Employees", Application.PAYROLL), // Employees Menu
	PAYROLL("Payroll", Application.PAYROLL), // Payroll Menu
	REPORTS("Reports", Application.PAYROLL), // Reports Menu

	// Payroll Admin Menus
	MANAGE_USERS("Manage Users", Application.PAYROLL), //
	MANAGE_COMPANIES("Manage Companies", Application.PAYROLL), //
	MANAGE_PAYROLLS("Manage Payrolls", Application.PAYROLL), //
	MANAGE_SYSTEM_DATA("Manage System Data", Application.PAYROLL), //
	MANAGE_FUND_TRANSACTIONS("Manage Fund Transactions", Application.PAYROLL), //
	TAX_DEPOSIT_AND_REPORITNG("Tax Deposit And Reporitng", Application.PAYROLL), //
	DATA_IMPORT_AND_EXPORT("Data Import And Export", Application.PAYROLL), //
	ADMIN_REPORTS("Admin Reports", Application.PAYROLL); //

	private String name;
	private Application application;

	private Menu(String name, Application application) {
		this.name = name;
		this.application = application;
	}

	public String getName() {
		return name;
	}

	public Application getApplication() {
		return application;
	}
}
