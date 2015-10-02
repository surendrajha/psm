package com.epayroll.service;

import java.util.List;

import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ManagePermissionForm;
import com.epayroll.form.RoleTypeSectionMapForm;

public interface ManagePermissionService {

	ManagePermissionForm getRolePermissionsForm(Company company,
			ManagePermissionForm managePermissionForm) throws InstanceNotFoundException;

	void addRolePermissions(ManagePermissionForm managePermissionForm, Company company);

	List<RoleType> getRoleTypes(Long id) throws InstanceNotFoundException;

	List<Role> getRoles(Long roleId) throws InstanceNotFoundException;

	Object getRoleTypeSectionForm(RoleTypeSectionMapForm roleTypeSectionMapForm);

	void addRoleTypeSections(RoleTypeSectionMapForm roleTypeSectionMapForm);

}
