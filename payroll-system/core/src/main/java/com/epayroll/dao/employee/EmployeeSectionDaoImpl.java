package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeSection;

@Repository
public class EmployeeSectionDaoImpl extends GenericDaoImpl<EmployeeSection, Long> implements
		EmployeeSectionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeSection> getEmployeeSections(Long companyId) {
		return getCriteria().createAlias("companies", "c").add(Restrictions.eq("c.id", companyId))
				.list();
	}
}
