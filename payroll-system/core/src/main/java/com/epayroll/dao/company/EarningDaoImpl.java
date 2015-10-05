package com.epayroll.dao.company;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Earning;

@Repository
public class EarningDaoImpl extends GenericDaoImpl<Earning, Long> implements EarningDao {
	private Logger logger = LoggerFactory.getLogger(EarningDaoImpl.class);

	@Override
	public Criteria getCriteriaForEarnings(Long earningCategoryId) {
		logger.debug(">> getCriteriaForEarnings");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("earningCategory.id", earningCategoryId));
		logger.debug(" getCriteriaForEarnings >>");
		return criteria;

	}

}
