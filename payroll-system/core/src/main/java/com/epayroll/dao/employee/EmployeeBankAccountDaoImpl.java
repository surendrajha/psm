package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeBankAccount;
import com.epayroll.exception.InstanceNotFoundException;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeBankAccountDaoImpl extends GenericDaoImpl<EmployeeBankAccount, Long> implements
		EmployeeBankAccountDao {

	private Long empId = 0L;

	private Logger logger = LoggerFactory.getLogger(EmployeeBankAccountDaoImpl.class);

	@Override
	public List<EmployeeBankAccount> getEmployeeBankAccounts(Employee employee)
			throws InstanceNotFoundException {
		empId = employee.getId();
		logger.debug(" >> getEmployeeBankAccounts,,, employeeID:" + empId);

		List<EmployeeBankAccount> bankAccounts = getCriteria().add(
				Restrictions.eq("employee.id", empId)).list();

		if (bankAccounts == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("employeeID [ ").append(empId).append(" ] ");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return bankAccounts;
	}

	@Override
	public Criteria getCriteriaForEmployeeBankAccounts(Long employeeId) {
		logger.debug(">> getCriteriaForEmployeeBankAccounts");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employee.id", employeeId));
		logger.debug("getCriteriaForEmployeeBankAccounts >>");
		return criteria;
	}

	// public List<EmployeeBankAccount>
	// updateEmployeeBankAccount(EmployeeBankAccount bankAccount)
	// throws InstanceNotFoundException {
	// logger.debug(" >> updateEmployeeBankAccount ");
	// return getEmployeeBankAccounts(update(bankAccount).getEmployee());
	// }

}
