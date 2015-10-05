package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;

public interface PayrollFrequencyDao extends GenericDao<PayrollFrequency, Long> {

	List<PayrollFrequency> getPayrollFrequencies(Long companyId);

	List<PayrollFrequency> getpayrollFrequencies(Long payFrequencyTypeId);

	List<PayFrequencyType> getPayFrequencyType(Long companyId);

}