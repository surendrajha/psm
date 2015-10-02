package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeEarning;
import com.epayroll.entity.EmployeeEarning.EarningValueType;

@Repository
public class EmployeeEarningDaoImpl extends GenericDaoImpl<EmployeeEarning, Long> implements
		EmployeeEarningDao {
	private Logger logger = LoggerFactory.getLogger(EmployeeEarningDaoImpl.class);

	@Override
	public List<EmployeeEarning> getEmployeeEarnings(EmployeeEarning employeeEarning) {
		String query = "select e from EmployeeEarning e where e.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setLong(0, employeeEarning.getId());
		@SuppressWarnings("unchecked")
		List<EmployeeEarning> employeeEarningList = query1.list();
		logger.debug("employeeEarningList::" + employeeEarningList);
		return employeeEarningList;
	}

	@Override
	public EmployeeEarning getEmployeeEarnings(Long employeeEarningId,
			EarningValueType earningValueType) {
		return (EmployeeEarning) getCriteria().add(Restrictions.eq("id", employeeEarningId))
				.add(Restrictions.eq("earningValueType", earningValueType)).uniqueResult();
	}

	// PAyroll //SK
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeEarning> getEmployeeEarnings(Long employeeId) {
		return getCriteria().add(Restrictions.eq("employee.id", employeeId))
				.addOrder(Order.asc("companyEarning.id")).list();
	}
}
