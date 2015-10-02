/**
 * 
 */
package com.epayroll.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.employee.EmployeeBankAccountDao;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.entity.EmployeeBankAccount.AccountType;
import com.epayroll.entity.EmployeeBankAccount.DepositValueType;
import com.epayroll.exception.CommonException;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeBankInfoForm;
import com.epayroll.model.employee.EmployeeBankInfo;

/**
 * @author Surendra Jha
 */
@Service
public class EmployeeBankInfoServiceImp implements EmployeeBankInfoService {

	private Logger logger = LoggerFactory.getLogger(EmployeeBankInfoServiceImp.class);

	@Autowired
	private EmployeeBankAccountDao employeeBankAccountDao;

	@Override
	public List<EmployeeBankInfo> getEmployeeBankAccounts(Employee employee)
			throws InstanceNotFoundException {
		logger.debug(" in getEmployeeBankAccounts ... ");
		List<EmployeeBankInfo> bankInfos = new ArrayList<>();
		EmployeeBankInfo bankInfo = null;
		for (EmployeeBankAccount bankAccount : employeeBankAccountDao
				.getEmployeeBankAccounts(employee)) {
			bankInfo = new EmployeeBankInfo();
			bankInfo.setAccountNumber(bankAccount.getAccountNumber());
			bankInfo.setAccountType(bankAccount.getAccountType());
			bankInfo.setBankName(bankAccount.getBankName());
			bankInfo.setDepositValue(bankAccount.getDepositValue());
			bankInfo.setDepositValueType(bankAccount.getDepositValueType());
			bankInfo.setId(bankAccount.getId());
			bankInfo.setRoutingNumber(bankAccount.getRoutingNumber());
			bankInfos.add(bankInfo);
		}
		return bankInfos;
	}

	@Override
	@Transactional
	public void saveEmployeeBankInfo(EmployeeBankInfoForm bankInfoForm, Employee employee)
			throws CommonException {
		logger.debug(" >> saveEmployeeBankInfo");
		try {
			for (EmployeeBankInfo employeeBankAccount : bankInfoForm.getEmployeeBankInfos()) {
				EmployeeBankAccount bankAccount = null;
				logger.debug("employeeBankAccount.id:" + employeeBankAccount.getId());
				if (employeeBankAccount.getId() != null) {
					bankAccount = employeeBankAccountDao.find(employeeBankAccount.getId());
				} else {
					bankAccount = new EmployeeBankAccount();
				}
				bankAccount.setEmployee(employee);
				bankAccount.setAccountNumber(employeeBankAccount.getAccountNumber());
				bankAccount.setAccountType(employeeBankAccount.getAccountType());
				bankAccount.setBankName(employeeBankAccount.getBankName());
				bankAccount.setDepositValue(employeeBankAccount.getDepositValue());
				bankAccount.setDepositValueType(employeeBankAccount.getDepositValueType());
				bankAccount.setRoutingNumber(employeeBankAccount.getRoutingNumber());

				if (employeeBankAccount.getId() == null) {
					employeeBankAccountDao.save(bankAccount);
				}

			}
		} catch (InstanceNotFoundException e) {
			logger.error("Error in saveEmployeeBankInfo", e.getLocalizedMessage());
		}
		logger.debug(" saveEmployeeBankInfo >> ");
	}

	@Override
	@Transactional
	public void deleteEmployeeBankInfo(Long bankInfoId) throws InstanceNotFoundException {
		logger.debug(" >> deleteEmployeeBankInfo");
		employeeBankAccountDao.remove(bankInfoId);
		logger.debug(" deleteEmployeeBankInfo >> ");
	}

	@Override
	public AccountType[] getAccountTypes() {
		logger.debug(" >> in getAccountTypes() ");
		return AccountType.values();
	}

	@Override
	public DepositValueType[] getDepositAllocationTypes() {
		logger.debug(" >> in getDepositAllocationTypes() ");
		return DepositValueType.values();
	}
}
