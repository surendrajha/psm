package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.AllowanceType;

@Repository
public class AllowanceTypeDaoImpl extends GenericDaoImpl<AllowanceType, Long> implements
		AllowanceTypeDao {
	private Logger logger = LoggerFactory.getLogger(AllowanceTypeDaoImpl.class);

	@Override
	public List<AllowanceType> getFederalAllowanceTypes() {
		logger.debug(">> getFederalAllowanceTypes");
		Query query = getSession()
				.createQuery(
						"select at from AllowanceType at where at.id not in(select ar.allowanceType.id from AllowanceRate ar where ar.usState IS NULL)");
		@SuppressWarnings("unchecked")
		List<AllowanceType> allowanceTypes = query.list();
		logger.debug("allowanceTypes ::" + allowanceTypes);
		logger.debug("getFederalAllowanceTypes >>");
		return allowanceTypes;
	}

	@Override
	public List<AllowanceType> getStateAllowanceTypes(Long stateId) {
		logger.debug(">> getStateAllowanceTypes");
		Query query = getSession()
				.createQuery(
						"select at from AllowanceType at where at.id not in(select ar.allowanceType.id from AllowanceRate ar where ar.usState.id=?)");
		query.setParameter(0, stateId);
		@SuppressWarnings("unchecked")
		List<AllowanceType> allowanceTypes = query.list();
		logger.debug("allowanceTypes ::" + allowanceTypes);
		logger.debug("getStateAllowanceTypes >>");
		return allowanceTypes;
	}

}
