package com.epayroll.dao;

import com.epayroll.entity.ContactType;

public interface ContactTypeDao extends GenericDao<ContactType, Long> {

	ContactType getContactType(String type);
}
