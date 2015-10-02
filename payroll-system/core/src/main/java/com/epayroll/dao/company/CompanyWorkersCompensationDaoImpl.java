package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.CompanyWorkerCompensation;

@Repository
public class CompanyWorkersCompensationDaoImpl extends
		GenericDaoImpl<CompanyWorkerCompensation, Long> implements CompanyWorkersCompensationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyWorkerCompensation> getWorkersCompensations(Long companyId) {
		return getCriteria().add(Restrictions.eq("company.id", companyId)).list();
	}

	@Override
	public Long getWorkersCompensationCount(Long id) {
		Query query = getSession()
				.createQuery(
						"select count(*) from CompanyPayrollWorkerComp epwp inner join epwp.companyWorkerCompensation cwc where cwc.id=?");
		query.setParameter(0, id);
		Long count = (Long) query.uniqueResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyWorkerCompensation> getCompanyWorkersCompensation(Long usStateId) {
		return getCriteria().add(Restrictions.eq("usState.id", usStateId)).list();
	}
}