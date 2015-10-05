package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeAllowanceRecord;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeAllowanceRecordDaoImpl extends GenericDaoImpl<EmployeeAllowanceRecord, Long>
		implements EmployeeAllowanceRecordDao {
	private Logger logger = LoggerFactory.getLogger(EmployeeAllowanceRecordDaoImpl.class);

	@Override
	public EmployeeAllowanceRecord getemployeeAllowance(Long employeeTaxId, Long allowanceTypeId) {

		return (EmployeeAllowanceRecord) getCriteria()
				.add(Restrictions.eq("employeeTax.id", employeeTaxId))
				.add(Restrictions.eq("allowanceType.id", allowanceTypeId)).uniqueResult();
	}

	@Override
	public List<EmployeeAllowanceRecord> getemployeeAllowanceRecords(Long employeeId) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(EmployeeAllowanceRecord.class, "ear");
		criteria.createAlias("employeeW4Detail", "ewd").createAlias("ewd.employeeTax", "et")
				.createAlias("et.employee", "ee").add(Restrictions.eq("ee.id", employeeId));
		return criteria.list();
	}

	@Override
	public List<EmployeeAllowanceRecord> getEmployeeAllowanceList(Long allowanceTypeId) {
		return getCriteria().add(Restrictions.eq("allowanceType.id", allowanceTypeId)).list();
	}

}
