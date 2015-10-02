package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.WorkersCompensationTaxRateView;

public interface WorkersCompensationTaxRateViewDao extends
		GenericDao<WorkersCompensationTaxRateView, Long> {

	List<WorkersCompensationTaxRateView> getWorkersCompensationRates(Long companyId);
}