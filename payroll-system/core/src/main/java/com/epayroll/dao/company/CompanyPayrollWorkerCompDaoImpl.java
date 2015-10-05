package com.epayroll.dao.company;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyPayrollWorkerComp;

@Repository
public class CompanyPayrollWorkerCompDaoImpl extends GenericDaoImpl<CompanyPayrollWorkerComp, Long>
		implements CompanyPayrollWorkerCompDao {
	@Override
	public CompanyPayrollWorkerComp getCompanyPayrollWorkerComp(Long companyWorkerCompensationId,
			Long employeePayrollId) {

		return (CompanyPayrollWorkerComp) getCriteria()
				.add(Restrictions.eq("companyWorkerCompensation.id", companyWorkerCompensationId))
				.add(Restrictions.eq("employeePayroll.id", employeePayrollId)).uniqueResult();
	}
}
