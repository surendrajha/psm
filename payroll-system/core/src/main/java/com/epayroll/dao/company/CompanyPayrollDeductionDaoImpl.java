package com.epayroll.dao.company;

import java.math.BigDecimal;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyPayrollDeduction;

@Repository
public class CompanyPayrollDeductionDaoImpl extends GenericDaoImpl<CompanyPayrollDeduction, Long>
		implements CompanyPayrollDeductionDao {
	@Override
	public BigDecimal getCompanyPayrollDeductionSum(Long companyDeductionId, Long employeeId) {

		return (BigDecimal) getCriteria().createAlias("employeePayroll", "ep")
				.createAlias("ep.employee", "emp")
				.add(Restrictions.eq("companyDeduction.id", companyDeductionId))
				.add(Restrictions.eq("emp.id", employeeId)).setProjection(Projections.sum("value"))
				.uniqueResult();
	}

	@Override
	public CompanyPayrollDeduction getCompanyPayrollDeduction(Long companyDeductionId,
			Long employeePayrollId) {

		return (CompanyPayrollDeduction) getCriteria()
				.add(Restrictions.eq("companyDeduction.id", companyDeductionId))
				.add(Restrictions.eq("employeePayroll.id", employeePayrollId)).uniqueResult();
	}
}
