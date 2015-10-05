package com.epayroll.dao.employee;

import java.util.Date;
import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmploymentHistory;

/**
 * @author Rajul Tiwari
 */
public interface EmploymentHistoryDao extends GenericDao<EmploymentHistory, Long> {

	EmploymentHistory getEmploymentHistory(Long employeeId);

	List<EmploymentHistory> getEmploymentHistoryForViewHistory(Long employeeId);

	Date getHireDateForPayStubs(Long employeeId);
}