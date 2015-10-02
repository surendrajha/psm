package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.TaxAuthority;

public interface TaxAuthorityDao extends GenericDao<TaxAuthority, Long> {

	List<TaxAuthority> getStateTaxAuthorities(Long usStateId);

	Long getFederalAuhtorityId();

	Long getStateAuhtorityId(Long stateId);

}