package com.epayroll.dao.company;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Deduction;

@Repository
public class DeductionDaoImpl extends GenericDaoImpl<Deduction, Long> implements DeductionDao {
	private Logger logger = LoggerFactory.getLogger(DeductionDaoImpl.class);

	@Override
	public Criteria getCriteriaForDeductions(Long deductionCategoryId) {
		logger.debug(">> getCriteriaForDeductions");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("deductionCategory.id", deductionCategoryId));
		logger.debug("getCriteriaForDeductions >>");
		return criteria;
	}

}
