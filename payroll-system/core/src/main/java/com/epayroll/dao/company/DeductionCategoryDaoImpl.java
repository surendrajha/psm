package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.DeductionCategory;

@Repository
public class DeductionCategoryDaoImpl extends GenericDaoImpl<DeductionCategory, Long> implements
		DeductionCategoryDao {

}
