package com.epayroll.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.Section;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.RoleTypeSectionMap;

@Repository
public class RoleTypeSectionMapDaoImpl extends GenericDaoImpl<RoleTypeSectionMap, Long> implements
		RoleTypeSectionMapDao {
	private Logger logger = LoggerFactory.getLogger(RoleTypeSectionMapDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Section> getSections(RoleType roleType) {
		logger.debug(">> getSections");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("roleType", roleType)).setProjection(
				Projections.property("section"));
		List<Section> sections = criteria.list();
		logger.debug("sections :: " + sections);
		logger.debug("getSections >>");
		return sections;
	}

	@Override
	public void deleteSections(RoleType roleType) {
		logger.debug(" >>  deleteSections");

		Query query = getSession().createQuery(
				"delete from RoleTypeSectionMap rtsm where rtsm.roleType=:roleType");
		query.setParameter("roleType", roleType);

		Integer rowsDeleted = query.executeUpdate();
		logger.debug("No. of Deleted : " + rowsDeleted);
		logger.debug("deleteSections >> ");

	}

}
