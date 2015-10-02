package com.epayroll.dao.company;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Earning;

public interface EarningDao extends GenericDao<Earning, Long> {
	public Criteria getCriteriaForEarnings(Long earningCategoryId);

}