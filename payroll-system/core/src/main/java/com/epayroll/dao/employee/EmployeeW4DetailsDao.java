package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeW4Detail;

public interface EmployeeW4DetailsDao extends GenericDao<EmployeeW4Detail, Long> {

	EmployeeW4Detail getEmployeeW4Detail(Long employeeTaxId);

	List<EmployeeW4Detail> getEmployeeW4Details(Long employeeId);

}