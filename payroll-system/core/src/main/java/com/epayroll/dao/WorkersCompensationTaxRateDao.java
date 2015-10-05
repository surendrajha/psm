package com.epayroll.dao;

import java.util.Date;
import java.util.List;

import com.epayroll.entity.WorkersCompensationTaxRate;

public interface WorkersCompensationTaxRateDao extends GenericDao<WorkersCompensationTaxRate, Long> {

	Date getDate();

	List<WorkersCompensationTaxRate> getworkersCompensationTaxRateList(Date fromDate, Date toDate);

	WorkersCompensationTaxRate getWorkerCompensationTaxRateList(Long companyWorkersCompensationId);

	List<WorkersCompensationTaxRate> getWorkerCompensationTaxRates(Long companyId);

}
