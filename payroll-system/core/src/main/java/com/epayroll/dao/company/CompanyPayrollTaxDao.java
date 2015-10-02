package com.epayroll.dao.company;

import java.math.BigDecimal;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.CompanyPayrollTax;

public interface CompanyPayrollTaxDao extends GenericDao<CompanyPayrollTax, Long> {

	BigDecimal getCalculatedTaxY2D(Long companyTaxId);

	List<CompanyPayrollTax> getCompanyPayrollTaxes(Long employeePayrollId);

	CompanyPayrollTax getCompanyPayrollTaxRecord(Long companyTaxId, Long employeePayrollId);

}
