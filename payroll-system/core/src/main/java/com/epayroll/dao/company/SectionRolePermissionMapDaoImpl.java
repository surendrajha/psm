package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.SectionRolePermissionMap;

@Repository
@SuppressWarnings({ "unchecked" })
public class SectionRolePermissionMapDaoImpl extends GenericDaoImpl<SectionRolePermissionMap, Long>
		implements SectionRolePermissionMapDao {

	Logger logger = LoggerFactory.getLogger(SectionRolePermissionMapDaoImpl.class);

	@Override
	public List<SectionRolePermissionMap> getRolePermissions(Long companyId, Long roleId) {
		logger.debug(">> getRolePermissions");
		Criteria criteria = getCriteria();
		if (companyId != null) {
			criteria.add(Restrictions.eq("company.id", companyId)).add(
					Restrictions.eq("role.id", roleId));
		} else {
			criteria.add(Restrictions.eq("role.id", roleId));
		}
		List<SectionRolePermissionMap> sectionRolePermissionMaps = criteria.list();
		logger.debug("sectionRolePermissionMaps :: " + sectionRolePermissionMaps);
		logger.debug("getRolePermissions >>");
		return sectionRolePermissionMaps;
	}

	@Override
	public PermissionType[] getPermissionTypes(Long companyId, Long roleId, Section section) {
		logger.debug(" >> getPermissionTypes ");

		Criteria criteria = getCriteria();

		if (companyId != null) {
			criteria.add(Restrictions.eq("company.id", companyId))
					.add(Restrictions.eq("role.id", roleId))
					.add(Restrictions.eq("section", section));
		} else {
			criteria.add(Restrictions.eq("role.id", roleId)).add(
					Restrictions.eq("section", section));
		}
		criteria.setProjection(Projections.property("permissionType"));
		List<PermissionType> permTypeList = criteria.list();

		PermissionType[] permissionTypes = new PermissionType[permTypeList.size()];

		for (int i = 0; i < permTypeList.size(); i++) {
			permissionTypes[i] = permTypeList.get(i);
		}

		logger.debug("permTypeList size :: " + permTypeList.size());

		return permissionTypes;
	}

	@Override
	public void deletePermission(Long companyId, Long roleId) {
		logger.debug(" >>  deletePermission");

		Query query = null;

		if (companyId != null) {
			query = getSession()
					.createQuery(
							"delete from SectionRolePermissionMap c where c.company.id=:compId and c.role.id=:roleId");
			query.setParameter("compId", companyId);
		} else {
			query = getSession().createQuery(
					"delete from SectionRolePermissionMap c where c.role.id=:roleId");
		}

		query.setParameter("roleId", roleId);
		Integer rowsDeleted = query.executeUpdate();
		logger.debug("No. of Deleted : " + rowsDeleted);
		logger.debug("deletePermission >> ");

	}
}
