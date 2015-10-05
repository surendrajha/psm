package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.EmployeeEarning.EarningValueType;

public interface EmployeeEarningDao extends GenericDao<EmployeeEarning, Long> {

	List<EmployeeEarning> getEmployeeEarnings(Long employeeId);

	List<EmployeeEarning> getEmployeeEarnings(EmployeeEarning employeeEarning);

	EmployeeEarning getEmployeeEarnings(Long employeeEarningId, EarningValueType earningValueType);

}