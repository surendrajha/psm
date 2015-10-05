package com.epayroll.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.entity.Address;
import com.epayroll.entity.Employee;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address, Long> implements AddressDao {
	private Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);

	@Override
	public Address getAddress(Long employeeId, String addressType) {
		logger.debug(">> getAddress");
		String hqlQuery = "select empAddress from Employee e Inner Join e.addresses empAddress where e.id=? and empAddress.addressType.type=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, employeeId);
		query.setString(1, addressType);
		Address address = (Address) query.uniqueResult();
		logger.debug("Address::" + address);
		logger.debug("getAddress >>");
		return address;
	}

	@Override
	public Address getCompanyAddress(Long companyId, String addressType) {
		logger.debug(">> getAddress");
		String hqlQuery = "select companyAddress from Company c Inner Join c.addresses companyAddress where c.id=? and companyAddress.addressType.type=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, companyId);
		query.setString(1, addressType);
		Address address = (Address) query.uniqueResult();
		logger.debug("Address::" + address);
		logger.debug("getAddress >>");
		return address;
	}

	@Override
	public Long getEmployeeWorkState(Long employeeId) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("addresses", "add").createAlias("add.addressType", "add_type")
				.add(Restrictions.eq("add_type.type", AddressType.WORK))
				.add(Restrictions.eq("id", employeeId))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		HashMap temp = (HashMap) criteria.uniqueResult();
		return ((Address) temp.get("add")).getUsState().getId();
	}

	@Override
	public Long getEmployeeResidentialState(Long employeeId) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("addresses", "add").createAlias("add.addressType", "add_type")
				.add(Restrictions.eq("add_type.type", AddressType.RESIDENCE))
				.add(Restrictions.eq("id", employeeId))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		HashMap temp = (HashMap) criteria.uniqueResult();
		return ((Address) temp.get("add")).getUsState().getId();
	}

	@Override
	public Long getEmployeeResidentialCounty(Long employeeId) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.createAlias("addresses", "add").createAlias("add.addressType", "add_type")
				.add(Restrictions.eq("add_type.type", AddressType.RESIDENCE))
				.add(Restrictions.eq("id", employeeId))
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		HashMap temp = (HashMap) criteria.uniqueResult();
		return ((Address) temp.get("add")).getUsCounty().getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressList(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddresses(Long usCountyId) {
		return getCriteria().add(Restrictions.eq("usCounty.id", usCountyId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAddressesList(Long usCityId) {
		return getCriteria().add(Restrictions.eq("usCity.id", usCityId)).list();
	}

}
