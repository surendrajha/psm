package com.epayroll.service.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.constant.Application;
import com.epayroll.constant.Menu;
import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.constant.SubMenu;
import com.epayroll.dao.RoleTypeSectionMapDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.RoleDao;
import com.epayroll.dao.company.SectionRolePermissionMapDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.SectionRolePermissionMap;
import com.epayroll.exception.InstanceNotFoundException;

@Service
public class CompanyManagePermissionServiceImpl implements CompanyManagePermissionService {

	private Logger logger = LoggerFactory.getLogger(CompanyManagePermissionServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SectionRolePermissionMapDao sectionRolePermissionMapDao;

	@Autowired
	private RoleTypeSectionMapDao roleTypeSectionMapDao;

	@Autowired
	private CompanyDao companyDao;

	@Override
	public List<Role> getCompanyRolesExpectAdmin() {
		logger.debug(" in getCompanyRolesExpectAdmin ");
		return roleDao.getCompanyRolesExceptAdmin();
	}

	@Override
	public PermissionType[] getPermissionTypes() {
		logger.debug(">>  getPermissionTypes");
		PermissionType[] permissionTypes = PermissionType.values();
		logger.debug(" getPermissionTypes >> ");
		return permissionTypes;
	}

	@Override
	public Application[] getApplications() {
		logger.debug(">>  getApplications");
		Application[] applications = Application.values();
		logger.debug(" getApplications >> ");
		return applications;
	}

	@Override
	public List<Menu> getMenus(Long roleId) throws InstanceNotFoundException {
		logger.debug(">>  getMenus");
		List<SubMenu> subMenus = getSubMenus(roleId);
		Set<Menu> menuSet = new TreeSet<>();
		for (SubMenu subMenu : subMenus) {
			menuSet.add(subMenu.getMenu());
		}
		List<Menu> menus = new ArrayList<>();
		for (Menu menu : menuSet) {
			menus.add(menu);
		}
		logger.debug("menus :: " + menus);
		logger.debug(" getMenus >> ");
		return menus;
	}

	@Override
	public List<SubMenu> getSubMenus(Long roleId) throws InstanceNotFoundException {
		logger.debug(">>  getSubMenus");
		List<Section> sections = getSections(roleId);
		Set<SubMenu> subMenuSet = new TreeSet<>();
		for (Section section : sections) {
			subMenuSet.add(section.getSubMenu());
		}
		List<SubMenu> subMenus = new ArrayList<>();
		for (SubMenu subMenu : subMenuSet) {
			subMenus.add(subMenu);
		}
		logger.debug("subMenus :: " + subMenus);
		logger.debug(" getSubMenus >> ");
		return subMenus;
	}

	@Override
	public List<SectionRolePermissionMap> getRolePermissions(Company company, Role role) {
		Long companyId = null;
		if (company != null) {
			companyId = company.getId();
		}
		return sectionRolePermissionMapDao.getRolePermissions(companyId, role.getId());
	}

	@Override
	public List<com.epayroll.constant.Section> getSections(Long roleId)
			throws InstanceNotFoundException {
		Role role = roleDao.find(roleId);
		List<Section> sections = roleTypeSectionMapDao.getSections(role.getRoleType());
		return sections;
	}

	@Override
	public List<com.epayroll.constant.Section> getSections(RoleType roleType) {
		List<Section> sections = roleTypeSectionMapDao.getSections(roleType);
		return sections;
	}

	@Override
	public Company getCompany(Long userId) {
		Company company = companyDao.getCompany(userId);
		return company;
	}

}
