package com.epayroll.dao.employee;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.FilingStatus;

@Repository
public class FilingStatusDaoImpl extends GenericDaoImpl<FilingStatus, Long> implements
		FilingStatusDao {

}
