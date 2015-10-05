package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EarningCategory;

@Repository
public class EarningCategoryDaoImpl extends GenericDaoImpl<EarningCategory, Long> implements
		EarningCategoryDao {

}