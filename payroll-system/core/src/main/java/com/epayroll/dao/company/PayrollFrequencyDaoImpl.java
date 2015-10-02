package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;

@Repository
public class PayrollFrequencyDaoImpl extends GenericDaoImpl<PayrollFrequency, Long> implements
		PayrollFrequencyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollFrequency> getPayrollFrequencies(Long companyId) {
		return getCriteria().add(Restrictions.eq("company.id", companyId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollFrequency> getpayrollFrequencies(Long payFrequencyTypeId) {
		return getCriteria().add(Restrictions.eq("payFrequencyType.id", payFrequencyTypeId)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayFrequencyType> getPayFrequencyType(Long companyId) {
		return getCriteria().add(Restrictions.eq("company.id", companyId))
				.setProjection(Projections.property("payFrequencyType")).list();
	}

}