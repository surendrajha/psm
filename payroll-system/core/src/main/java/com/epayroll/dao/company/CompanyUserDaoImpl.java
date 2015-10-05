package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.Company.Status;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.Role.RoleType;

@SuppressWarnings("unchecked")
@Repository
public class CompanyUserDaoImpl extends GenericDaoImpl<CompanyUser, Long> implements CompanyUserDao {

	private Logger logger = LoggerFactory.getLogger(CompanyUserDaoImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public CompanyUser getCompanyAdminUser(Company company) {

		return (CompanyUser) getCriteria()
				.add(Restrictions.eq("company.id", company.getId()))
				.add(Restrictions.eq("user.role.id", roleDao.getRole(RoleType.COMPANY_SUPER_USER)
						.getId())).uniqueResult();

	}

	@Override
	public List<CompanyUser> getCompanyUsersExceptAdmin(Company company) {
		return getCriteria().add(Restrictions.eq("company.id", company.getId()))
				.add(Restrictions.ne("companyRole", roleDao.getRole(RoleType.COMPANY_USER))).list();
	}

	@Override
	public Integer deactivateCompanyUsers(Long companyId) {
		logger.debug(">> deactivateCompanyUsers");
		Query query = getSession()
				.createQuery(
						"update CompanyUser cu set cu.user.status=:status, cu.user.password=:pass where cu.company.id=:companyId");

		query.setParameter("status", Status.INACTIVE);
		query.setParameter("pass", null);
		query.setParameter("companyId", companyId);
		Integer rows = query.executeUpdate();
		logger.debug("deactivateCompanyUsers >>");
		return rows;
	}

	@Override
	public Integer reactivateCompanyUsers(Long companyId) {
		logger.debug(">> reactivateCompanyUsers");
		Query query = getSession().createQuery(
				"update CompanyUser cu set cu.user.status=:status where cu.company.id=:companyId");
		query.setParameter("status", Status.ACTIVE);
		query.setParameter("companyId", companyId);
		Integer rows = query.executeUpdate();
		logger.debug("reactivateCompanyUsers >>");
		return rows;
	}
}