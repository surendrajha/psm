package com.epayroll.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.company.CompanyDepartmentDao;
import com.epayroll.dao.employee.EmployeeDepartmentAllocationDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeDepartmentAllocation;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeDepartmentAllocationForm;
import com.epayroll.form.employee.EmployeeDeptAllocation;
import com.epayroll.model.employee.DepartmentAllocation;

@Service
@SuppressWarnings("unchecked")
public class EmployeeDepartmentServiceImpl implements EmployeeDepartmentService {

	private Logger logger = LoggerFactory.getLogger(EmployeeDepartmentServiceImpl.class);

	@Autowired
	private CompanyDepartmentDao companyDepartmentDao;

	@Autowired
	private EmployeeDepartmentAllocationDao departmentAllocationDao;

	@Override
	public List<CompanyDepartment> getCompanyDepartments(Company company) {
		logger.debug(" >> getCompanyDepartments");
		return companyDepartmentDao.getCriteriaForDepartmentList(company).list();
	}

	@Override
	public List<DepartmentAllocation> getEmployeeDepartmentAllocations(Employee employee) {
		logger.debug(" >> getEmployeeDepartmentAllocations");
		DepartmentAllocation departmentAllocation = null;
		List<DepartmentAllocation> departmentAllocations = new ArrayList<>();
		List<EmployeeDepartmentAllocation> allocations = departmentAllocationDao
				.getEmployeeDepartmentAllocations(employee);

		if (allocations.size() == 0) {
			logger.debug("No allocations found so department comming from CompanyDepartment");
			List<CompanyDepartment> departments = companyDepartmentDao
					.getCriteriaForDepartmentList(employee.getCompany()).list();
			for (CompanyDepartment companyDepartment : departments) {
				departmentAllocation = new DepartmentAllocation();
				departmentAllocation.setDeptId(companyDepartment.getId());
				departmentAllocation.setDepartmentCode(companyDepartment.getDeptId());
				departmentAllocation.setDepartmentDesciption(companyDepartment.getDeptName());
				departmentAllocations.add(departmentAllocation);
			}
		} else {
			logger.debug(" allocations : " + allocations.size());
			for (EmployeeDepartmentAllocation allocation : allocations) {
				departmentAllocation = new DepartmentAllocation();
				departmentAllocation.setId(allocation.getId());
				departmentAllocation.setDeptId(allocation.getCompanyDepartment().getId());
				departmentAllocation.setDepartmentCode(allocation.getCompanyDepartment()
						.getDeptId());
				departmentAllocation.setDepartmentDesciption(allocation.getCompanyDepartment()
						.getDeptName());
				departmentAllocation.setAllocationPercentage(allocation.getAllocationPerentage());
				departmentAllocations.add(departmentAllocation);
			}
		}
		return departmentAllocations;
	}

	@Override
	@Transactional
	public void saveEmployeeDepartmentAllocations(EmployeeDepartmentAllocationForm allocationForm,
			Employee employee) {

		EmployeeDepartmentAllocation departmentAllocation = null;
		try {
			for (EmployeeDeptAllocation allocation : allocationForm.getAllocations()) {
				departmentAllocation = new EmployeeDepartmentAllocation();
				logger.debug("allocation.getId()::" + allocation.getId());
				if (allocation.getId() != null) {
					departmentAllocation.setId(allocation.getId());
				}
				departmentAllocation.setEmployee(employee);
				departmentAllocation.setCompanyDepartment(companyDepartmentDao.find(allocation
						.getDeptId()));
				departmentAllocation.setAllocationPerentage(allocation.getAllocationPercentage());
				departmentAllocationDao.saveOrUpdate(departmentAllocation);
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in saveEmployeeDepartmentAllocations:", e.getMessage());
		}
	}
}
