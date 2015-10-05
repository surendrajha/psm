package com.epayroll.dao.company;

import org.hibernate.Criteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Deduction;

public interface DeductionDao extends GenericDao<Deduction, Long> {
	public Criteria getCriteriaForDeductions(Long deductionCategoryId);

}