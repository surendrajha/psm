package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeAllowanceRecord;

public interface EmployeeAllowanceRecordDao extends GenericDao<EmployeeAllowanceRecord, Long> {

	EmployeeAllowanceRecord getemployeeAllowance(Long employeeTaxId, Long allowanceTypeId);

	List<EmployeeAllowanceRecord> getemployeeAllowanceRecords(Long employeeId);

	List<EmployeeAllowanceRecord> getEmployeeAllowanceList(Long allowanceTypeId);

}