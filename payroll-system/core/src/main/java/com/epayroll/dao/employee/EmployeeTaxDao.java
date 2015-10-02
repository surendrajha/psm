package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.model.EmployeeW4DetailModel;

public interface EmployeeTaxDao extends GenericDao<EmployeeTax, Long> {

	// ///////// employeeAccess

	List<EmployeeTax> getFederalTaxList(Long employeeId);

	List<EmployeeTax> getStateTaxList(Long employeeId);

	List<EmployeeTax> getLocalTaxList(Long employeeId);

	EmployeeW4DetailModel getFITRecord(Long employeeId);

	EmployeeW4DetailModel getSITRecord(Long employeeId);

	List<EmployeeAllowanceRecord> getAllowanceRecords(Long employeeTaxId);

	List<EmployeeTax> getEmployeeTaxs(Long employeeId);

	// EmployeeTax getEmployeeTaxListForUpdate(Long employeeTaxId);

	// ////////
}