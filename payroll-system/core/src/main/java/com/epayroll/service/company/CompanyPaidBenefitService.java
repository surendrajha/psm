/**
 * 
 */
package com.epayroll.service.company;

import java.util.List;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.CompanyPaidBenefit.AccrualMode;
import com.epayroll.entity.CompanyPaidBenefit.RollOverHours;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyPaidBenefitsForm;

/**
 * @author Surendra Jha
 */
public interface CompanyPaidBenefitService {

	List<AccrualMode> getAccrualModes();

	List<RollOverHours> getCarryForwardHours();

	List<CompanyEarning> getCompanyEarnings(Company company);

	CompanyPaidBenefitsForm getPaidBenefits(Company company, Long companyEarningId, Boolean status);

	void addPaidBenefit(Company company, CompanyPaidBenefitsForm paidBenefitsForm)
			throws InstanceNotFoundException;

}
