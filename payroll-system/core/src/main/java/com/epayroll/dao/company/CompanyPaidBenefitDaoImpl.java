/**
 * 
 */
package com.epayroll.dao.company;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyPaidBenefit;

/**
 * @author Surendra Jha
 */
@Repository
public class CompanyPaidBenefitDaoImpl extends GenericDaoImpl<CompanyPaidBenefit, Long> implements
		CompanyPaidBenefitDao {

	@Override
	public CompanyPaidBenefit getCompanyPaidBenefit(Company company, Long companyEarningId) {
		return (CompanyPaidBenefit) getCriteria()
				.add(Restrictions.eq("company.id", company.getId()))
				.add(Restrictions.eq("companyEarning.id", companyEarningId)).uniqueResult();
	}
}
