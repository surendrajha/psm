package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.EmployeeSection;

/**
 * @author Uma
 */
public interface EmployeeSectionDao extends GenericDao<EmployeeSection, Long> {

	List<EmployeeSection> getEmployeeSections(Long companyId);

}