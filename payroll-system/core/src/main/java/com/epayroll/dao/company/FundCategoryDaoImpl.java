package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.FundCategory;

@Repository
public class FundCategoryDaoImpl extends GenericDaoImpl<FundCategory, Long> implements
		FundCategoryDao {

}
