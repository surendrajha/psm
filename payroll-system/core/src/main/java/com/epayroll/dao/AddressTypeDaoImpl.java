package com.epayroll.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.AddressType;

@Repository
public class AddressTypeDaoImpl extends GenericDaoImpl<AddressType, Long> implements AddressTypeDao {

	@Override
	public AddressType getAddressType(String type) {
		return (AddressType) getCriteria().add(Restrictions.eq("type", type)).uniqueResult();
	}

	@Override
	public boolean getAddress(String addressType, Long addressTypeId) {
		Object row = getCriteria().add(Restrictions.eq("type", addressType))
				.add(Restrictions.eq("id", addressTypeId)).uniqueResult();
		if (row != null) {
			return true;
		} else {
			return false;
		}
	}
}
