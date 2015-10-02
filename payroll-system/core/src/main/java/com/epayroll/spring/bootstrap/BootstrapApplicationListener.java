package com.epayroll.spring.bootstrap;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.RoleDao;
import com.epayroll.dao.company.SectionRolePermissionMapDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.Role;
import com.epayroll.entity.SectionRolePermissionMap;

@SuppressWarnings("rawtypes")
public class BootstrapApplicationListener implements ApplicationListener {

	private Logger logger = LoggerFactory.getLogger(BootstrapApplicationListener.class);

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SectionRolePermissionMapDao rolePermissionDao;

	@Override
	@Transactional
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("onApplicationEvent.................." + companyDao + roleDao);
		// ServletContext applicationContext = event.
		// System.out.println("applicationContext::" + applicationContext);

		List<Company> companies = companyDao.getEntities();
		List<Role> roles = roleDao.getEntities();

		logger.info("companies:" + companies + ", companyRoles:" + roles);

		for (Company company : companies) {
			for (Role role : roles) {
				List<SectionRolePermissionMap> rolePermissions = rolePermissionDao
						.getRolePermissions(company.getId(), role.getId());
				logger.info("rolePermissions::" + rolePermissions);
				String key = company.getId() + "!" + role.getId();
				logger.info("key::" + key);
				// applicationContext.setAttribute(key, rolePermissions);
			}
		}
	}
}
