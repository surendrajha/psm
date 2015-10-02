package com.epayroll.service.adminConsole;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.company.CompanyDeductionDao;
import com.epayroll.dao.company.CompanyEarningDao;
import com.epayroll.dao.company.DeductionCategoryDao;
import com.epayroll.dao.company.DeductionDao;
import com.epayroll.dao.company.EarningCategoryDao;
import com.epayroll.dao.company.EarningDao;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.CompanyEarning;
import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.Earning;
import com.epayroll.entity.EarningCategory;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.DeductionCategoryForm;
import com.epayroll.form.adminConsole.DeductionForm;
import com.epayroll.form.adminConsole.EarningCategoryForm;
import com.epayroll.form.adminConsole.EarningForm;
import com.epayroll.service.employeeAccess.EmployeeAccessLoginServiceImpl;

/**
 * @author Uma
 */
@Service
public class AdminEarningDeductionServiceImpl implements AdminEarningDeductionService {
	@Autowired
	private EarningCategoryDao earningCategoryDao;

	@Autowired
	private EarningDao earningDao;

	@Autowired
	private DeductionCategoryDao deductionCategoryDao;

	@Autowired
	private DeductionDao deductionDao;

	@Autowired
	private CompanyEarningDao companyEarningDao;

	@Autowired
	private CompanyDeductionDao companyDeductionDao;

	private Logger logger = LoggerFactory.getLogger(EmployeeAccessLoginServiceImpl.class);

	@Override
	@Transactional
	public void addEarningCategory(EarningCategoryForm earningCategoryForm) {
		logger.debug(">>addEarningCategory");
		EarningCategory earningCategory = new EarningCategory();
		earningCategory.setCategory(earningCategoryForm.getCategory());
		earningCategory.setDescription(earningCategoryForm.getDescription());
		earningCategoryDao.save(earningCategory);
		logger.debug("addEarningCategory>>");
	}

	@Override
	public EarningCategoryForm getUpdateEarningCategoryForm(Long earningCategoryId)
			throws InstanceNotFoundException {
		logger.debug(">>getUpdateEarningCategoryForm");
		EarningCategory earningCategory = earningCategoryDao.find(earningCategoryId);
		EarningCategoryForm earningCategoryForm = new EarningCategoryForm();
		earningCategoryForm.setCategory(earningCategory.getCategory());
		earningCategoryForm.setDescription(earningCategory.getDescription());
		earningCategoryForm.setId(earningCategoryId);
		logger.debug(">>getUpdateEarningCategoryForm");
		return earningCategoryForm;
	}

	@Override
	@Transactional
	public void updateEarningCategory(EarningCategoryForm earningCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(">>upadateEarningCategory");
		EarningCategory earningCategory = earningCategoryDao.find(earningCategoryForm.getId());
		earningCategory.setCategory(earningCategoryForm.getCategory());
		earningCategory.setDescription(earningCategoryForm.getDescription());
		earningCategoryDao.update(earningCategory);
		logger.debug(">>upadateEarningCategory");
	}

	@Override
	@Transactional
	public void verifyAnddeleteEarningCategory(Long earningCategoryId)
			throws InstanceNotFoundException {
		logger.debug(">>deleteEarningCategory");
		@SuppressWarnings("unchecked")
		List<Earning> earnings = earningDao.getCriteriaForEarnings(earningCategoryId).list();
		if (earnings.isEmpty()) {
			earningCategoryDao.remove(earningCategoryId);
		}
		logger.debug(">>deleteEarningCategory");
	}

	@Override
	public List<EarningCategory> getEarningCategories() {
		return earningCategoryDao.getEntities();
	}

	@Override
	public List<DeductionCategory> getDeductionCategories() {
		return deductionCategoryDao.getEntities();
	}

	@Override
	@Transactional
	public void addEarning(EarningForm earningForm) throws InstanceNotFoundException {
		Earning earning = new Earning();
		EarningCategory earningCategory = earningCategoryDao.find(earningForm
				.getEarningCategoryId());
		earning.setDescription(earningForm.getDescription());
		earning.setEarningCategory(earningCategory);
		earning.setEarningName(earningForm.getEarning());
		earningDao.save(earning);
	}

	@Override
	public EarningForm getUpdateEarningForm(Long earningId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateEarningForm");
		Earning earning = earningDao.find(earningId);
		EarningForm earningForm = new EarningForm();
		earningForm.setDescription(earning.getDescription());
		earningForm.setEarning(earning.getEarningName());
		earningForm.setId(earningId);
		earningForm.setEarningCategoryId(earning.getEarningCategory().getId());
		logger.debug(">>getUpdateEarningForm");
		return earningForm;
	}

	@Override
	@Transactional
	public void updateEarning(EarningForm earningForm) throws InstanceNotFoundException {
		logger.debug(">>updateEarning");
		Earning earning = earningDao.find(earningForm.getId());
		earning.setDescription(earningForm.getDescription());
		earning.setEarningCategory(earningCategoryDao.find(earningForm.getEarningCategoryId()));
		earning.setEarningName(earningForm.getEarning());
		earningDao.update(earning);
		logger.debug(">>updateEarning");

	}

	@Override
	@Transactional
	public void verifyAnddeleteEarning(Long earningId) throws InstanceNotFoundException {
		logger.debug(">>deleteEarning");
		List<CompanyEarning> companyEarnings = companyEarningDao
				.getCompanyEarningsByEarningId(earningId);
		if (companyEarnings.isEmpty()) {
			earningDao.remove(earningId);
		}
		logger.debug(">>deleteEarning");

	}

	@Override
	@Transactional
	public void addDeductionCategory(DeductionCategoryForm deductionCategoryForm) {
		logger.debug(">>addDeductionCategory");
		DeductionCategory deductionCategory = new DeductionCategory();
		deductionCategory.setCategory(deductionCategoryForm.getCategory());
		deductionCategory.setDescription(deductionCategoryForm.getDescription());
		deductionCategoryDao.save(deductionCategory);
		logger.debug("addDeductionCategory>>");

	}

	@Override
	public DeductionCategoryForm getUpdateDeductionCategoryForm(Long deductionCategoryId)
			throws InstanceNotFoundException {
		logger.debug(">>getUpdateDeductionCategoryForm");
		DeductionCategory deductionCategory = deductionCategoryDao.find(deductionCategoryId);
		DeductionCategoryForm deductionCategoryForm = new DeductionCategoryForm();
		deductionCategoryForm.setCategory(deductionCategory.getCategory());
		deductionCategoryForm.setDescription(deductionCategory.getDescription());
		deductionCategoryForm.setId(deductionCategoryId);
		logger.debug(">>getUpdateDeductionCategoryForm");
		return deductionCategoryForm;
	}

	@Override
	@Transactional
	public void updateDeductionCategory(DeductionCategoryForm deductionCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(">>upadateDeductionCategory");
		DeductionCategory deductionCategory = deductionCategoryDao.find(deductionCategoryForm
				.getId());
		deductionCategory.setCategory(deductionCategoryForm.getCategory());
		deductionCategory.setDescription(deductionCategoryForm.getDescription());
		deductionCategoryDao.update(deductionCategory);
		logger.debug(">>upadateDeductionCategory");

	}

	@Override
	@Transactional
	public void verifyAnddeleteDeductionCategory(Long deductionCategoryId)
			throws InstanceNotFoundException {
		logger.debug(">>deleteDeductionCategory");
		@SuppressWarnings("unchecked")
		List<Deduction> deductions = deductionDao.getCriteriaForDeductions(deductionCategoryId)
				.list();
		if (deductions.isEmpty()) {
			deductionCategoryDao.remove(deductionCategoryId);
		}
		logger.debug(">>deleteDeductionCategory");

	}

	@Override
	@Transactional
	public void addDeduction(DeductionForm deductionForm) throws InstanceNotFoundException {
		Deduction deduction = new Deduction();
		DeductionCategory deductionCategory = deductionCategoryDao.find(deductionForm
				.getDeductionCategoryId());
		deduction.setDescription(deductionForm.getDescription());
		deduction.setDeductionCategory(deductionCategory);
		deduction.setDeductionName(deductionForm.getDeduction());
		deductionDao.save(deduction);

	}

	@Override
	public DeductionForm getUpdateDeductionForm(Long deductionId) throws InstanceNotFoundException {
		logger.debug(">>getUpdateEarningForm");
		Deduction deduction = deductionDao.find(deductionId);
		DeductionForm deductionForm = new DeductionForm();
		deductionForm.setDescription(deduction.getDescription());
		deductionForm.setDeduction(deduction.getDeductionName());
		deductionForm.setId(deductionId);
		deductionForm.setDeductionCategoryId(deduction.getDeductionCategory().getId());
		logger.debug(">>getUpdateEarningForm");
		return deductionForm;
	}

	@Override
	@Transactional
	public void updateDeduction(DeductionForm deductionForm) throws InstanceNotFoundException {
		logger.debug(">>updateDeduction");
		Deduction deduction = deductionDao.find(deductionForm.getId());
		deduction.setDescription(deductionForm.getDescription());
		deduction.setDeductionCategory(deductionCategoryDao.find(deductionForm
				.getDeductionCategoryId()));
		deduction.setDeductionName(deductionForm.getDeduction());
		deductionDao.update(deduction);
		logger.debug(">>updateDeduction");

	}

	@Override
	@Transactional
	public void verifyAnddeleteDeduction(Long deductionId) throws InstanceNotFoundException {
		logger.debug(">>deleteDeduction");
		List<CompanyDeduction> companyDeductions = companyDeductionDao
				.getCompanyDeductions(deductionId);
		if (companyDeductions.isEmpty()) {
			deductionDao.remove(deductionId);
		}
		logger.debug(">>deleteDeduction");

	}
}
