package com.epayroll.form;

import java.util.List;

public class ManagePermissionForm {

	private Long roleId;
	private String roleName;
	private List<ManagePermission> managePermissions;
	private Integer listSize;
	private Integer listRowNo;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ManagePermission> getManagePermissions() {
		return managePermissions;
	}

	public void setManagePermissions(List<ManagePermission> managePermissions) {
		this.managePermissions = managePermissions;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public Integer getListRowNo() {
		return listRowNo;
	}

	public void setListRowNo(Integer listRowNo) {
		this.listRowNo = listRowNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManagePermissionForm [roleId=");
		builder.append(roleId);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", managePermissions=");
		builder.append(managePermissions);
		builder.append(", listSize=");
		builder.append(listSize);
		builder.append("]");
		return builder.toString();
	}

}
