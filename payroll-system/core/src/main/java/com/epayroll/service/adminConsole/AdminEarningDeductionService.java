package com.epayroll.service.adminConsole;

import java.util.List;

import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.EarningCategory;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.DeductionCategoryForm;
import com.epayroll.form.adminConsole.DeductionForm;
import com.epayroll.form.adminConsole.EarningCategoryForm;
import com.epayroll.form.adminConsole.EarningForm;

/**
 * 
 * @author Uma
 * 
 */
public interface AdminEarningDeductionService {

	void addEarningCategory(EarningCategoryForm earningCategoryForm);

	EarningCategoryForm getUpdateEarningCategoryForm(Long earningCategoryId)
			throws InstanceNotFoundException;

	void updateEarningCategory(EarningCategoryForm earningCategoryForm)
			throws InstanceNotFoundException;

	void verifyAnddeleteEarningCategory(Long earningCategoryId) throws InstanceNotFoundException;

	List<EarningCategory> getEarningCategories();

	List<DeductionCategory> getDeductionCategories();

	void addEarning(EarningForm earningForm) throws InstanceNotFoundException;

	EarningForm getUpdateEarningForm(Long earningId) throws InstanceNotFoundException;

	void updateEarning(EarningForm earningForm) throws InstanceNotFoundException;

	void verifyAnddeleteEarning(Long earningId) throws InstanceNotFoundException;

	void addDeductionCategory(DeductionCategoryForm deductionCategoryForm);

	DeductionCategoryForm getUpdateDeductionCategoryForm(Long deductionCategoryId)
			throws InstanceNotFoundException;

	void updateDeductionCategory(DeductionCategoryForm deductionCategoryForm)
			throws InstanceNotFoundException;

	void verifyAnddeleteDeductionCategory(Long deductionCategoryId)
			throws InstanceNotFoundException;

	void addDeduction(DeductionForm deductionForm) throws InstanceNotFoundException;

	DeductionForm getUpdateDeductionForm(Long deductionId) throws InstanceNotFoundException;

	void updateDeduction(DeductionForm deductionForm) throws InstanceNotFoundException;

	void verifyAnddeleteDeduction(Long deductionId) throws InstanceNotFoundException;

}
