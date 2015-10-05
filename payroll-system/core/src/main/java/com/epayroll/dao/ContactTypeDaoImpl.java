/**
 * 
 */
package com.epayroll.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.ContactType;

/**
 * @author Surendra Jha
 */
@Repository
public class ContactTypeDaoImpl extends GenericDaoImpl<ContactType, Long> implements ContactTypeDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epayroll.dao.ContactTypeDao#getContactType(java.lang.String)
	 */
	@Override
	public ContactType getContactType(String type) {
		return (ContactType) getCriteria().add(Restrictions.eq("type", type)).uniqueResult();
	}

}
