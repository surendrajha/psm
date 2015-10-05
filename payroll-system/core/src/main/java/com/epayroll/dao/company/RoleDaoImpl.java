package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {
	private Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Override
	public Role getRole(RoleType roleType) {
		return (Role) getCriteria().add(Restrictions.eq("roleType", roleType)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(RoleType roleType) {
		return getCriteria().add(Restrictions.eq("roleType", roleType)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getCompanyRolesExceptAdmin() {
		return getCriteria().add(Restrictions.ne("roleType", RoleType.COMPANY_SUPER_USER)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(List<RoleType> roleTypes) {
		List<Role> roles = getCriteria().add(Restrictions.in("roleType", roleTypes)).list();
		logger.debug("roles :: " + roles);
		return roles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAdminUserRolesExceptAdmin() {
		return getCriteria().add(Restrictions.ne("roleType", RoleType.SYSTEM_SUPER_USER)).list();
	}

}
