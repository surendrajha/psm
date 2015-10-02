/**
 * 
 */
package com.epayroll.service.company;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.constant.company.EarningCategoryType;
import com.epayroll.dao.company.CompanyEarningDao;
import com.epayroll.dao.company.CompanyPaidBenefitDao;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.CompanyPaidBenefit;
import com.epayroll.entity.CompanyPaidBenefit.AccrualMode;
import com.epayroll.entity.CompanyPaidBenefit.RollOverHours;
import com.epayroll.entity.CompanyPaidBenefit.Status;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyPaidBenefitsForm;

/**
 * @author Surendra Jha
 */
@Service
public class CompanyPaidBenefitServiceImp implements CompanyPaidBenefitService {

	private Logger logger = LoggerFactory.getLogger(CompanyPaidBenefitServiceImp.class);

	@Autowired
	private CompanyEarningDao companyEarningDao;

	@Autowired
	private CompanyPaidBenefitDao companyPaidBenefitDao;

	@Override
	@Transactional
	public CompanyPaidBenefitsForm getPaidBenefits(Company company, Long companyEarningId,
			Boolean status) {
		logger.debug(" >> in getPaidBenefits, companyEarningId:" + companyEarningId + ", status:"
				+ status);

		CompanyPaidBenefitsForm paidBenefitsForm = new CompanyPaidBenefitsForm();

		CompanyPaidBenefit companyPaidBenefit = companyPaidBenefitDao.getCompanyPaidBenefit(
				company, companyEarningId);
		logger.debug("companyPaidBenefit::" + companyPaidBenefit);
		if (companyPaidBenefit != null) {
			paidBenefitsForm.setId(companyPaidBenefit.getId());
			paidBenefitsForm.setAccrualMode(companyPaidBenefit.getAccrualMode());
			paidBenefitsForm.setRate(companyPaidBenefit.getAccrualRate().toString());
			paidBenefitsForm.setCarryForwardHours(companyPaidBenefit.getRolloverHours());
			paidBenefitsForm.setCompanyEarningId(companyPaidBenefit.getCompanyEarning().getId());
			paidBenefitsForm.setIsHoursAccrueOnLeave(companyPaidBenefit.getAccrueHoursOnLeave());
			paidBenefitsForm.setNoOfHours(companyPaidBenefit.getNoOfHours().toString());
			paidBenefitsForm.setStatus(companyPaidBenefit.getStatus());
		}

		if (!status) {
			logger.debug("in updating status :" + status);
			companyPaidBenefit.setStatus(Status.INACTIVE);
			companyPaidBenefitDao.update(companyPaidBenefit);
		}
		logger.debug("in getPaidBenefits >> ");
		return paidBenefitsForm;
	}

	@Override
	@Transactional
	public void addPaidBenefit(Company company, CompanyPaidBenefitsForm paidBenefitsForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addPaidBenefit ");
		CompanyPaidBenefit paidBenefit = new CompanyPaidBenefit();
		paidBenefit.setAccrualMode(paidBenefitsForm.getAccrualMode());
		paidBenefit.setAccrualRate(Float.valueOf(paidBenefitsForm.getRate()));
		paidBenefit.setAccrueHoursOnLeave(paidBenefitsForm.getIsHoursAccrueOnLeave());
		paidBenefit.setNoOfHours(Float.valueOf(paidBenefitsForm.getNoOfHours()));
		paidBenefit.setRolloverHours(paidBenefitsForm.getCarryForwardHours());
		paidBenefit.setStatus(Status.ACTIVE);
		paidBenefit.setCompany(company);
		paidBenefit
				.setCompanyEarning(companyEarningDao.find(paidBenefitsForm.getCompanyEarningId()));

		if (paidBenefitsForm.getId() != null && paidBenefitsForm.getId() > 0) {
			paidBenefit.setId(paidBenefitsForm.getId());
		}
		companyPaidBenefitDao.saveOrUpdate(paidBenefit);
		logger.debug(" >> addPaidBenefit ");
	}

	@Override
	@Transactional
	public List<CompanyEarning> getCompanyEarnings(Company company) {
		return companyEarningDao.getCompanyEarnings(company, EarningCategoryType.PTO);
	}

	@Override
	public List<AccrualMode> getAccrualModes() {
		List<AccrualMode> list = new ArrayList<>();
		list.add(AccrualMode.PAY_HOURLY);
		list.add(AccrualMode.FIXED);
		return list;
	}

	@Override
	public List<RollOverHours> getCarryForwardHours() {
		List<RollOverHours> list = new ArrayList<>();
		list.add(RollOverHours.ALL_HOURS);
		list.add(RollOverHours.SOME_HOURS);
		return list;
	}

}
