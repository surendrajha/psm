package com.epayroll.dao;

import java.util.List;

import com.epayroll.entity.Person;
import com.epayroll.exception.InstanceNotFoundException;

public interface PersonDao extends GenericDao<Person, Long> {

	Person findPerson(String email, String mobileNumber) throws InstanceNotFoundException;

	Person getCompanyContact(Long companyId, String contactType);

	List<Person> getPersons(Long companyId);
}
