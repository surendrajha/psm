package com.epayroll.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.UsState;

@Repository
public class UsStateDaoImpl extends GenericDaoImpl<UsState, Long> implements UsStateDao {
	private Logger logger = LoggerFactory.getLogger(UsStateDaoImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public List<UsState> findStates() {
		Query query = getSession().createSQLQuery("CALL GetStates()").addEntity(UsState.class);
		return query.list();
		// return getCurrentSession().createCriteria(getEntityClass()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsState> getCriteriaForUsState(Long stateId) {
		return getCriteria().add(Restrictions.eq("id", stateId)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UsState> getStates() {
		logger.debug(">> getStates");
		String bHQL = "select distinct empAddresses.usState from Employee e Inner Join e.addresses empAddresses";
		Query query = getSession().createQuery(bHQL);
		List<UsState> usStateList = query.list();
		logger.debug("usStateList::" + usStateList);
		logger.debug("getStates >>");
		return usStateList;
	}
}