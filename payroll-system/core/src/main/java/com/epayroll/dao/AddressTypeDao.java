package com.epayroll.dao;

import com.epayroll.entity.AddressType;

public interface AddressTypeDao extends GenericDao<AddressType, Long> {

	AddressType getAddressType(String type);

	boolean getAddress(String addressType, Long addressTypeId);
}
