package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeTax.Status;
import com.epayroll.entity.EmployeeW4Detail;

@Repository
public class EmployeeW4DetailsDaoImpl extends GenericDaoImpl<EmployeeW4Detail, Long> implements
		EmployeeW4DetailsDao {
	private Logger logger = LoggerFactory.getLogger(EmployeeW4DetailsDaoImpl.class);

	@Override
	public EmployeeW4Detail getEmployeeW4Detail(Long employeeTaxId) {
		logger.debug(">> getEmployeeW4Detail");

		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("employeeTax.id", employeeTaxId));
		EmployeeW4Detail employeeW4Detail = (EmployeeW4Detail) criteria.uniqueResult();
		logger.debug("employeeW4Detail :: " + employeeW4Detail);

		logger.debug("getEmployeeW4Detail >>");
		return employeeW4Detail;
	}

	// Payroll & Employee Pay Stub
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeW4Detail> getEmployeeW4Details(Long employeeId) {
		logger.debug(" in getEmployeeW4Details.,employeeId:" + employeeId);
		return getCriteria().createAlias("employeeTax", "et")
				.add(Restrictions.eq("et.employee.id", employeeId))
				.add(Restrictions.eq("et.status", Status.ACTIVE)).list();
	}

}
