package com.epayroll.dao.company;

import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.SystemMessage;

@Repository
public class SystemMessageDaoImpl extends GenericDaoImpl<SystemMessage, Long> implements
		SystemMessageDao {
}