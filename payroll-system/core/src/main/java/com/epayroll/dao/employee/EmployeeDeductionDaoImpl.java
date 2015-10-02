package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeDeduction;
import com.epayroll.entity.EmployeeDeduction.DeductionValueType;

@Repository
@SuppressWarnings("unchecked")
public class EmployeeDeductionDaoImpl extends GenericDaoImpl<EmployeeDeduction, Long> implements
		EmployeeDeductionDao {

	private Logger logger = LoggerFactory.getLogger(EmployeeDeductionDaoImpl.class);

	@Override
	public List<EmployeeDeduction> getEmployeeDeductions(Long employeeId) {
		return getCriteria().add(Restrictions.eq("employee.id", employeeId)).list();
	}

	@Override
	public List<EmployeeDeduction> getEmployeeDeductions(EmployeeDeduction employeeDeduction) {
		String query = "select d from EmployeeDeduction d where d.id=?";

		Query query1 = getSession().createQuery(query);
		query1.setLong(0, employeeDeduction.getId());
		List<EmployeeDeduction> employeeDeductionList = query1.list();
		logger.debug("employeeDeductionList::" + employeeDeductionList);
		return employeeDeductionList;
	}

	@Override
	public EmployeeDeduction getEmployeeDeductions(Long employeeDeductionId,
			DeductionValueType deductionValueType) {
		return (EmployeeDeduction) getCriteria().add(Restrictions.eq("id", employeeDeductionId))
				.add(Restrictions.eq("deductionValueType", deductionValueType)).uniqueResult();
	}

}
