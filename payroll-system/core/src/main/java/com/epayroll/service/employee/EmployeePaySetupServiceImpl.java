package com.epayroll.service.employee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.dao.company.PayrollFrequencyDao;
import com.epayroll.dao.employee.EmployeePaySetupDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeePaySetup;
import com.epayroll.entity.EmployeePaySetup.PayType;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorPaySetupForm;
import com.epayroll.form.employee.EmployeePaySetupForm;

@Service
public class EmployeePaySetupServiceImpl implements EmployeePaySetupService {

	private Logger logger = LoggerFactory.getLogger(EmployeePaySetupServiceImpl.class);

	@Autowired
	private EmployeePaySetupDao employeePaySetupDao;

	@Autowired
	private PayrollFrequencyDao payrollFrequencyDao;

	@Override
	public EmployeePaySetupForm getEmployeePaySetup(Employee employee) {
		logger.debug(" >>  getEmployeePaySetup employee:" + employee.getId());
		EmployeePaySetup paySetup;
		EmployeePaySetupForm employeePaySetupForm = new EmployeePaySetupForm();
		try {
			paySetup = employeePaySetupDao.getEmployeePaySetup(employee);
			employeePaySetupForm.setId(paySetup.getId());
			employeePaySetupForm.setStandardHours(paySetup.getRegularHours());
			employeePaySetupForm.setOtherRate(paySetup.getOtherRate());
			employeePaySetupForm.setPayType(paySetup.getPayType());
			employeePaySetupForm.setPayCycle(paySetup.getPayrollFrequency().getId());
			logger.debug("paySetup.getPayType()::" + paySetup.getPayType());
			if (paySetup.getPayType().equals(PayType.SALARY)) {
				employeePaySetupForm.setSalary(paySetup.getSalary());
			}
			if (paySetup.getPayType().equals(PayType.HOURLY)) {
				employeePaySetupForm.setHourlyRate(paySetup.getHourlyRate());
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in fetching PaySetupForm: " + e.getLocalizedMessage());
		}
		return employeePaySetupForm;
	}

	@Override
	public ContractorPaySetupForm getContractorPaySetup(Employee employee) {
		logger.debug(" >>  getContractorPaySetup employee:" + employee.getId());
		EmployeePaySetup paySetup;
		ContractorPaySetupForm contractorPaySetupForm = new ContractorPaySetupForm();
		try {
			paySetup = employeePaySetupDao.getEmployeePaySetup(employee);
			contractorPaySetupForm.setId(paySetup.getId());
			contractorPaySetupForm.setPayType(paySetup.getPayType());
			contractorPaySetupForm.setPayCycle(paySetup.getPayrollFrequency().getId());
			contractorPaySetupForm.setPayRate(paySetup.getPayRate());
		} catch (InstanceNotFoundException e) {
			logger.error("Error in fetching ContractorPaySetup: " + e.getLocalizedMessage());
		}
		return contractorPaySetupForm;
	}

	@Override
	public void updateEmployeePaySetup(EmployeePaySetupForm employeePaySetupForm, Employee employee) {
		logger.debug(" >> updateEmployeePaySetup .. employeePaySetupForm::" + employeePaySetupForm);
		EmployeePaySetup paySetup = null;
		try {
			if (employeePaySetupForm.getId() != null && employeePaySetupForm.getId() > 0) {
				paySetup = employeePaySetupDao.find(employeePaySetupForm.getId());
			} else {
				paySetup = new EmployeePaySetup();
				paySetup.setEmployee(employee);
			}
			paySetup.setPayType(employeePaySetupForm.getPayType());

			paySetup.setPayrollFrequency(payrollFrequencyDao.find(employeePaySetupForm
					.getPayCycle()));
			paySetup.setRegularHours(employeePaySetupForm.getStandardHours());
			paySetup.setOtherRate(employeePaySetupForm.getOtherRate());
			logger.debug("employeePaySetupForm.getPayType()::" + employeePaySetupForm.getPayType());
			if (employeePaySetupForm.getPayType().equals(PayType.SALARY)) {
				paySetup.setSalary(employeePaySetupForm.getSalary());
			}
			if (employeePaySetupForm.getPayType().equals(PayType.HOURLY)) {
				paySetup.setHourlyRate(employeePaySetupForm.getHourlyRate());
			}
			if (employeePaySetupForm.getId() == null) {
				employeePaySetupDao.save(paySetup);
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in updateEmployeePaySetup ::" + e.getLocalizedMessage());
		}
	}

	@Override
	public void updateContractorPaySetup(ContractorPaySetupForm contractorPaySetupForm,
			Employee employee) {
		logger.debug(">> updateContractorPaySetup ");
		EmployeePaySetup paySetup = null;
		try {
			if (contractorPaySetupForm.getId() != null && contractorPaySetupForm.getId() > 0) {
				paySetup = employeePaySetupDao.find(contractorPaySetupForm.getId());
			} else {
				paySetup = new EmployeePaySetup();
				paySetup.setEmployee(employee);
			}
			paySetup.setPayType(contractorPaySetupForm.getPayType());
			paySetup.setPayrollFrequency(payrollFrequencyDao.find(contractorPaySetupForm
					.getPayCycle()));
			paySetup.setPayRate(contractorPaySetupForm.getPayRate());
			if (contractorPaySetupForm.getId() == null) {
				employeePaySetupDao.save(paySetup);
			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in updateEmployeePaySetup ::" + e.getLocalizedMessage());
		}
	}

	@Override
	public PayType[] getPayTypes() {
		logger.debug(" >> getPayTypes()..");
		return PayType.values();
	}

	@Override
	public List<PayrollFrequency> getPayCycles(Employee employee) throws InstanceNotFoundException {
		logger.debug(" >> getPayCycles .. ");
		return employeePaySetupDao.getPayrollFrequencies(employee.getCompany());
	}
}
