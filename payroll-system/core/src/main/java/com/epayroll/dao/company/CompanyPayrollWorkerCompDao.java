package com.epayroll.dao.company;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.CompanyPayrollWorkerComp;

public interface CompanyPayrollWorkerCompDao extends GenericDao<CompanyPayrollWorkerComp, Long> {

	CompanyPayrollWorkerComp getCompanyPayrollWorkerComp(Long companyWorkerCompensationId,
			Long employeePayrollId);

}
