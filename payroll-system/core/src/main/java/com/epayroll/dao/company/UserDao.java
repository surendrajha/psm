package com.epayroll.dao.company;

import java.util.Date;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;

public interface UserDao extends GenericDao<User, Long> {

	User getUser(String email, Date dob) throws InstanceNotFoundException;

	User getUser(String userName) throws InstanceNotFoundException;

	User verifyPin(String userName, String pin);

	List<String> getApprovers(Long companyId);

	User checkUserPassword(String userName, String password) throws InstanceNotFoundException;

}