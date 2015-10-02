/**
 * 
 */
package com.epayroll.constant;

/**
 * @author Basit Azeem Sheikh
 */
public enum Application {

	PAYROLL("Payroll"), ACCOUNTING("Accounting");

	private String name;

	private Application(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
