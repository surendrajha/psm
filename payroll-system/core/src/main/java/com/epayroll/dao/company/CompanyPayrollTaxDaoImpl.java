package com.epayroll.dao.company;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyPayrollTax;

@Repository
@SuppressWarnings("unchecked")
public class CompanyPayrollTaxDaoImpl extends GenericDaoImpl<CompanyPayrollTax, Long> implements
		CompanyPayrollTaxDao {

	@Override
	public BigDecimal getCalculatedTaxY2D(Long companyTaxId) {
		return (BigDecimal) getCriteria().add(Restrictions.eq("companyTax.id", companyTaxId))
				.setProjection(Projections.sum("taxCalculated")).uniqueResult();
	}

	@Override
	public List<CompanyPayrollTax> getCompanyPayrollTaxes(Long employeePayrollId) {

		return getCriteria().add(Restrictions.eq("employeePayroll.id", employeePayrollId)).list();
	}

	@Override
	public CompanyPayrollTax getCompanyPayrollTaxRecord(Long companyTaxId, Long employeePayrollId) {

		return (CompanyPayrollTax) getCriteria()
				.add(Restrictions.eq("companyTax.id", companyTaxId))
				.add(Restrictions.eq("employeePayroll.id", employeePayrollId)).uniqueResult();
	}
}
