/**
 * 
 */
package com.epayroll.constant;

/**
 * @author Surendra Jha
 */
public enum PermissionType {

	NONE("None"), VIEW("View"), INSERT("Insert"), UPDATE("Update"), DELETE("Delete");

	private String name;

	private PermissionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
