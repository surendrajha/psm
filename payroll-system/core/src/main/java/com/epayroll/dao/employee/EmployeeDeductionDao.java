package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;

public interface EmployeeDeductionDao extends GenericDao<EmployeeDeduction, Long> {

	List<EmployeeDeduction> getEmployeeDeductions(Long employeeId);

	List<EmployeeDeduction> getEmployeeDeductions(EmployeeDeduction employeeDeduction);

	EmployeeDeduction getEmployeeDeductions(Long employeeDeductionId,
			DeductionValueType deductionValueType);

}