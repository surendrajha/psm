package com.epayroll.service.employeeAccess;

import java.util.List;

import com.epayroll.entity.EmployeeTax;
import com.epayroll.model.EmployeeW4DetailModel;

/**
 * @author Rajul Tiwari
 */

public interface EmployeeAccessTaxInfoService {

	List<EmployeeTax> getEmployeeFederalTaxList(Long employeeId);

	EmployeeW4DetailModel getFederalFITDetail(Long employeeId);

	EmployeeW4DetailModel getStateSITDetail(Long employeeId);

	List<EmployeeTax> getEmployeeStateTaxList(Long employeeId);

	List<EmployeeTax> getEmployeeLocalTaxList(Long employeeId);

}
