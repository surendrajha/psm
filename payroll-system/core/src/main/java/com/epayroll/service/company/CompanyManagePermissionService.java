package com.epayroll.service.company;

import java.util.List;

import com.epayroll.constant.Application;
import com.epayroll.constant.Menu;
import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.constant.SubMenu;
import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.SectionRolePermissionMap;
import com.epayroll.exception.InstanceNotFoundException;

public interface CompanyManagePermissionService {

	List<Role> getCompanyRolesExpectAdmin();

	PermissionType[] getPermissionTypes();

	List<SectionRolePermissionMap> getRolePermissions(Company company, Role role);

	Application[] getApplications();

	List<Menu> getMenus(Long roleId) throws InstanceNotFoundException;

	List<SubMenu> getSubMenus(Long roleId) throws InstanceNotFoundException;

	List<com.epayroll.constant.Section> getSections(Long roleId) throws InstanceNotFoundException;

	Company getCompany(Long id);

	List<Section> getSections(RoleType roleType);

}
