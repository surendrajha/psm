/**
 * 
 */
package com.epayroll.dao.company;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyPaidBenefit;

/**
 * @author Surendra Jha
 */
public interface CompanyPaidBenefitDao extends GenericDao<CompanyPaidBenefit, Long> {

	CompanyPaidBenefit getCompanyPaidBenefit(Company company, Long companyEarningId);

}
