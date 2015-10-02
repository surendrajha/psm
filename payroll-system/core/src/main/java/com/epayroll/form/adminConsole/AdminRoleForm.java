package com.epayroll.form.adminConsole;

import com.epayroll.entity.Role.RoleType;

/**
 * @author Uma
 */
public class AdminRoleForm {

	private Long id;
	private String role;
	private String description;
	private RoleType roleType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminRoleForm [id=");
		builder.append(id);
		builder.append(", role=");
		builder.append(role);
		builder.append(", description=");
		builder.append(description);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append("]");
		return builder.toString();
	}

}