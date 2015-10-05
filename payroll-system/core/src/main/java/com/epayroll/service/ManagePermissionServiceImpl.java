package com.epayroll.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.dao.RoleTypeSectionMapDao;
import com.epayroll.dao.company.RoleDao;
import com.epayroll.dao.company.SectionRolePermissionMapDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.RoleTypeSectionMap;
import com.epayroll.entity.SectionRolePermissionMap;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ManagePermission;
import com.epayroll.form.ManagePermissionForm;
import com.epayroll.form.RoleTypeSectionMapForm;
import com.epayroll.service.company.CompanyManagePermissionService;

@Service
public class ManagePermissionServiceImpl implements ManagePermissionService {

	private Logger logger = LoggerFactory.getLogger(ManagePermissionServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SectionRolePermissionMapDao sectionRolePermissionMapDao;

	@Autowired
	private CompanyManagePermissionService companyManagePermissionService;

	@Autowired
	private RoleTypeSectionMapDao roleTypeSectionMapDao;

	@Override
	public ManagePermissionForm getRolePermissionsForm(Company company,
			ManagePermissionForm managePermissionForm) throws InstanceNotFoundException {
		logger.debug(" in getCompanyRolePermissions");

		ManagePermissionForm permissionForm = new ManagePermissionForm();
		permissionForm.setRoleId(managePermissionForm.getRoleId());
		permissionForm.setRoleName(managePermissionForm.getRoleName());

		ManagePermission managePermission = null;
		List<ManagePermission> managePermissions = new ArrayList<>();

		List<Section> sections = companyManagePermissionService.getSections(managePermissionForm
				.getRoleId());

		for (Section section : sections) {
			logger.debug("section :: " + section);

			managePermission = new ManagePermission();
			managePermission.setSection(section);
			managePermission.setSubMenu(section.getSubMenu());
			managePermission.setMenu(section.getSubMenu().getMenu());
			managePermission.setApplication(section.getSubMenu().getMenu().getApplication());

			Long companyId = null;
			if (company != null) {
				companyId = company.getId();
			}
			managePermission.setPermissionTypes(sectionRolePermissionMapDao.getPermissionTypes(
					companyId, permissionForm.getRoleId(), section));
			managePermissions.add(managePermission);
		}
		System.out.println("managePermissions : " + managePermissions);
		permissionForm.setManagePermissions(managePermissions);
		permissionForm.setListSize(managePermissions.size());
		return permissionForm;
	}

	@Override
	@Transactional
	public void addRolePermissions(ManagePermissionForm managePermissionForm, Company company) {
		logger.debug(" >> addCompanyRolePermissions");
		try {
			SectionRolePermissionMap rolePermission = null;
			Role role = roleDao.find(managePermissionForm.getRoleId());

			Long companyId = null;
			if (company != null) {
				companyId = company.getId();
			}

			// delete existing permission
			sectionRolePermissionMapDao.deletePermission(companyId,
					managePermissionForm.getRoleId());
			// end
			logger.debug("Adding...");
			for (ManagePermission managePermission : managePermissionForm.getManagePermissions()) {
				logger.debug("section :: " + managePermission.getSection());

				for (PermissionType permissionType : managePermission.getPermissionTypes()) {
					logger.debug("permissionType :: " + permissionType);
					rolePermission = new SectionRolePermissionMap();
					rolePermission.setCompany(company);
					rolePermission.setRole(role);
					rolePermission.setSection(managePermission.getSection());
					rolePermission.setPermissionType(permissionType);
					sectionRolePermissionMapDao.saveOrUpdate(rolePermission);
				}
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in addCompanyRolePermissions ::", e);
		}
		logger.debug(" addCompanyRolePermissions >> ");
	}

	@Override
	public List<Role> getRoles(Long roleId) throws InstanceNotFoundException {
		Role role = roleDao.find(roleId);
		logger.debug(" >> getRoles ");
		List<RoleType> roleTypes = new ArrayList<>();
		switch (role.getRoleType()) {
		case SYSTEM_SUPER_USER:
			roleTypes.add(RoleType.SYSTEM_SUPER_USER);
			roleTypes.add(RoleType.SYSTEM_USER);
			break;
		case SYSTEM_USER:
			roleTypes.add(RoleType.SYSTEM_USER);
			break;
		case COMPANY_SUPER_USER:
			roleTypes.add(RoleType.COMPANY_SUPER_USER);
			roleTypes.add(RoleType.COMPANY_USER);
			break;
		case COMPANY_USER:
			roleTypes.add(RoleType.COMPANY_USER);
			break;
		}
		logger.debug("getRoles >>");
		return roleDao.getRoles(roleTypes);
	}

	@Override
	public List<RoleType> getRoleTypes(Long roleId) throws InstanceNotFoundException {
		Role role = roleDao.find(roleId);
		logger.debug(" >> getRoleTypes ");
		List<RoleType> roleTypes = new ArrayList<>();
		switch (role.getRoleType()) {
		case SYSTEM_SUPER_USER:
			roleTypes.add(RoleType.SYSTEM_SUPER_USER);
			roleTypes.add(RoleType.SYSTEM_USER);
			break;
		case SYSTEM_USER:
			roleTypes.add(RoleType.SYSTEM_USER);
			break;
		case COMPANY_SUPER_USER:
			roleTypes.add(RoleType.COMPANY_SUPER_USER);
			roleTypes.add(RoleType.COMPANY_USER);
			break;
		case COMPANY_USER:
			roleTypes.add(RoleType.COMPANY_USER);
			break;
		}
		logger.debug("getRoleTypes >>");
		return roleTypes;
	}

	@Override
	public Object getRoleTypeSectionForm(RoleTypeSectionMapForm roleTypeSectionMapForm) {
		logger.debug(" in getCompanyRolePermissions");

		List<Section> sectionList = companyManagePermissionService
				.getSections(roleTypeSectionMapForm.getRoleType());

		Section[] sections = new Section[sectionList.size()];
		int i = 0;
		for (Section section : sectionList) {
			sections[i++] = section;
		}
		roleTypeSectionMapForm.setSections(sections);
		return roleTypeSectionMapForm;
	}

	@Override
	@Transactional
	public void addRoleTypeSections(RoleTypeSectionMapForm roleTypeSectionMapForm) {
		logger.debug(" >> addRoleTypeSections");
		RoleTypeSectionMap roleTypeSectionMap = null;

		// delete existing permission
		roleTypeSectionMapDao.deleteSections(roleTypeSectionMapForm.getRoleType());
		// end
		logger.debug("Adding...");
		for (Section section : roleTypeSectionMapForm.getSections()) {
			logger.debug("section :: " + section);
			roleTypeSectionMap = new RoleTypeSectionMap();
			roleTypeSectionMap.setRoleType(roleTypeSectionMapForm.getRoleType());
			roleTypeSectionMap.setSection(section);
			roleTypeSectionMapDao.save(roleTypeSectionMap);
		}

		logger.debug(" addRoleTypeSections >> ");
	}
}
