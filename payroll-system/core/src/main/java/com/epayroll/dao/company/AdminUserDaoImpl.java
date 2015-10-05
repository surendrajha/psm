package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.AdminUser;
import com.epayroll.entity.Role.RoleType;

@Repository
public class AdminUserDaoImpl extends GenericDaoImpl<AdminUser, Long> implements AdminUserDao {

	private Logger logger = LoggerFactory.getLogger(AdminUserDaoImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public AdminUser getSystemAdminUser() {
		return (AdminUser) getCriteria().add(
				Restrictions
						.eq("user.role.id", roleDao.getRole(RoleType.SYSTEM_SUPER_USER).getId()))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminUser> getSystemUsersExceptAdmin() {
		return getCriteria().createAlias("user", "u").createAlias("u.role", "r")
				.add(Restrictions.eq("r.roleType", RoleType.SYSTEM_USER)).list();
		// List<Role> roles = roleDao.getRoles(RoleType.SYSTEM_USER);
		// logger.debug("roles:::" + roles);
		// return getCriteria().createAlias("user",
		// "u").add(Restrictions.in("u.role", roles)).list();
	}
	// @Override
	// public List<CompanyUser> getCompanyUsersExceptAdmin(Company company) {
	// return getCriteria().add(Restrictions.eq("company.id", company.getId()))
	// .add(Restrictions.ne("companyRole",
	// roleDao.getRole(RoleType.COMPANY_USER))).list();
	// }
	//
	// @Override
	// public Integer deactivateCompanyUsers(Long companyId) {
	// logger.debug(">> deactivateCompanyUsers");
	// Query query = getSession()
	// .createQuery(
	// "update CompanyUser cu set cu.user.status=:status, cu.user.password=:pass where cu.company.id=:companyId");
	//
	// query.setParameter("status", Status.INACTIVE);
	// query.setParameter("pass", null);
	// query.setParameter("companyId", companyId);
	// Integer rows = query.executeUpdate();
	// logger.debug("deactivateCompanyUsers >>");
	// return rows;
	// }
	//
	// @Override
	// public Integer reactivateCompanyUsers(Long companyId) {
	// logger.debug(">> reactivateCompanyUsers");
	// Query query = getSession().createQuery(
	// "update CompanyUser cu set cu.user.status=:status where cu.company.id=:companyId");
	// query.setParameter("status", Status.ACTIVE);
	// query.setParameter("companyId", companyId);
	// Integer rows = query.executeUpdate();
	// logger.debug("reactivateCompanyUsers >>");
	// return rows;
	// }
}