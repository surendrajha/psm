package com.epayroll.dao.company;

import java.math.BigDecimal;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.CompanyPayrollDeduction;

public interface CompanyPayrollDeductionDao extends GenericDao<CompanyPayrollDeduction, Long> {

	BigDecimal getCompanyPayrollDeductionSum(Long companyDeductionId, Long employeeId);

	CompanyPayrollDeduction getCompanyPayrollDeduction(Long companyDeductionId,
			Long employeePayrollId);

}
