package com.epayroll.dao.employee;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmploymentHistory;

@Repository
public class EmploymentHistoryDaoImpl extends GenericDaoImpl<EmploymentHistory, Long> implements
		EmploymentHistoryDao {
	private Logger logger = LoggerFactory.getLogger(EmploymentHistoryDaoImpl.class);

	@Override
	public EmploymentHistory getEmploymentHistory(Long employeeId) {
		logger.debug(">> getEmploymentHistory");
		String query = "select e from EmploymentHistory e where e.hireDate=(select max(eh.hireDate) from EmploymentHistory eh where eh.employee.id= ?) and e.employee.id=?";
		Query query1 = getSession().createQuery(query);
		query1.setLong(0, employeeId);
		query1.setLong(1, employeeId);
		EmploymentHistory employmentHistory = (EmploymentHistory) query1.uniqueResult();
		logger.debug("EmploymentHistory::" + employmentHistory);
		logger.debug("getEmploymentHistory >>");
		return employmentHistory;
	}

	@Override
	public List<EmploymentHistory> getEmploymentHistoryForViewHistory(Long employeeId) {
		logger.debug(">> getEmploymentHistoryForViewHistory");
		String query = "select e from EmploymentHistory e where e.employee.id=? order by e.hireDate DESC";
		Query query1 = getSession().createQuery(query);
		query1.setLong(0, employeeId);
		@SuppressWarnings("unchecked")
		List<EmploymentHistory> employmentHistoryList = query1.list();
		logger.debug("EmploymentHistoryList::" + employmentHistoryList);
		logger.debug("getEmploymentHistoryForViewHistory >>");
		return employmentHistoryList;
	}

	@Override
	public Date getHireDateForPayStubs(Long employeeId) {
		logger.debug(">> getHireDateForPayStubs");
		String hqlQuery = "select min(eh.hireDate) from EmploymentHistory eh where eh.employee.id=?";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, employeeId);
		// Criteria criteria = getCriteria();
		// criteria.add(Restrictions.eq("employee.id", employeeId));
		// Date d = (Date) criteria.uniqueResult();
		Date date = (Date) query.uniqueResult();
		logger.debug("getHireDateForPayStubs >>");
		return date;
	}

}
