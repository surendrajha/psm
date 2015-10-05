package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Deduction;
import com.epayroll.entity.DeductionCategory;
import com.epayroll.entity.EarningCategory;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.DeductionCategoryForm;
import com.epayroll.form.adminConsole.DeductionForm;
import com.epayroll.form.adminConsole.EarningCategoryForm;
import com.epayroll.form.adminConsole.EarningForm;
import com.epayroll.service.adminConsole.AdminEarningDeductionService;

/**
 * @author Uma
 */
@RequestMapping("/employeeAccess")
@Controller
public class AdminEarningDeductionController {

	private Logger logger = LoggerFactory.getLogger(AdminEarningDeductionController.class);

	@Autowired
	private AdminEarningDeductionService adminEarningDeductionService;

	@RequestMapping(value = "/showEarningDeductionForm", method = RequestMethod.GET)
	public ModelAndView showEarningCategoryForm() {
		logger.debug(" >> showEarningDeductionForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		modelAndView.addObject("earningCategoryForm", new EarningCategoryForm());
		List<EarningCategory> earningCategories = adminEarningDeductionService
				.getEarningCategories();
		modelAndView.addObject("earningCategories", earningCategories);
		logger.debug("earningCategories" + earningCategories);
		modelAndView.addObject("earningForm", new EarningForm());
		modelAndView.addObject("DeductionCategoryForm", new DeductionCategoryForm());
		List<DeductionCategory> deductionCategories = adminEarningDeductionService
				.getDeductionCategories();
		modelAndView.addObject("deductionCategories", deductionCategories);
		logger.debug("deductionCategories" + deductionCategories);
		modelAndView.addObject("deductionForm", new Deduction());
		logger.debug("showEarningDeductionForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNING_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEarningCategory", method = RequestMethod.POST)
	public ModelAndView addEarningCategory(@ModelAttribute EarningCategoryForm earningCategoryForm) {
		logger.debug(" >> addEarningCategoryForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.addEarningCategory(earningCategoryForm);
		logger.debug("addEarningCategoryForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNING_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateEarningCategoryForm/{earningCategoryId}", method = RequestMethod.GET)
	public ModelAndView showUpdateEarningCategoryForm(@PathVariable Long earningCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateEarningCategoryForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		EarningCategoryForm earningCategoryForm = adminEarningDeductionService
				.getUpdateEarningCategoryForm(earningCategoryId);
		logger.debug("earningCategoryForm" + earningCategoryForm);
		modelAndView.addObject("earningCategoryForm", earningCategoryForm);
		logger.debug("showUpdateEarningCategoryForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNING_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEarningCategory", method = RequestMethod.POST)
	public ModelAndView updateEarningCategory(
			@ModelAttribute EarningCategoryForm earningCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateEarningCategory");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.updateEarningCategory(earningCategoryForm);
		logger.debug("updateEarningCategory >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNING_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteEarningCategory/{earningCategoryId}", method = RequestMethod.GET)
	public ModelAndView deleteEarningCategory(@PathVariable Long earningCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteEarningCategory");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.verifyAnddeleteEarningCategory(earningCategoryId);
		logger.debug("deleteEarningCategory >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEarning", method = RequestMethod.POST)
	public ModelAndView addEarning(@ModelAttribute EarningForm earningForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addEarning");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.addEarning(earningForm);
		logger.debug("addEarning >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateEarningForm/{earningId}", method = RequestMethod.GET)
	public ModelAndView showUpdateEarningForm(@PathVariable Long earningId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateEarningForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		EarningForm earningForm = adminEarningDeductionService.getUpdateEarningForm(earningId);
		logger.debug("earningForm" + earningForm);
		modelAndView.addObject("earningForm", earningForm);
		logger.debug("showUpdateEarningForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEarning", method = RequestMethod.POST)
	public ModelAndView updateEarning(@ModelAttribute EarningForm earningForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateEarning");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.updateEarning(earningForm);
		logger.debug("updateEarning >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EARNINGS, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteEarning/{earningId}", method = RequestMethod.GET)
	public ModelAndView deleteEarning(@PathVariable Long earningId)
			throws InstanceNotFoundException {
		logger.debug(" >> earningId");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.verifyAnddeleteEarning(earningId);
		logger.debug("earningId >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTION_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addDeductionCategory", method = RequestMethod.POST)
	public ModelAndView addDeductionCategory(
			@ModelAttribute DeductionCategoryForm deductionCategoryForm) {
		logger.debug(" >> addDeductionCategory");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.addDeductionCategory(deductionCategoryForm);
		logger.debug("addDeductionCategory >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTION_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateDeductionCategoryForm/{deductionCategoryId}", method = RequestMethod.GET)
	public ModelAndView showUpdateDeductionCategoryForm(@PathVariable Long deductionCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateDeductionCategoryForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		DeductionCategoryForm deductionCategoryForm = adminEarningDeductionService
				.getUpdateDeductionCategoryForm(deductionCategoryId);
		logger.debug("deductionCategoryForm::" + deductionCategoryForm);
		modelAndView.addObject("deductionCategoryForm", deductionCategoryForm);
		logger.debug("showUpdateDeductionCategoryForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTION_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateDeductionCategory", method = RequestMethod.POST)
	public ModelAndView updateDeductionCategory(
			@ModelAttribute DeductionCategoryForm deductionCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateDeductionCategory");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.updateDeductionCategory(deductionCategoryForm);
		logger.debug("updateDeductionCategory >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTION_CATEGORIES, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteDeductionCategory/{deductionCategoryId}", method = RequestMethod.GET)
	public ModelAndView deleteDeductionCategory(@PathVariable Long deductionCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteDeductionCategory");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.verifyAnddeleteDeductionCategory(deductionCategoryId);
		logger.debug("deleteDeductionCategory >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addDeduction", method = RequestMethod.POST)
	public ModelAndView addDeduction(@ModelAttribute DeductionForm deductionForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addDeduction");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.addDeduction(deductionForm);
		logger.debug("addDeduction >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateDeductionForm/{deductionId}", method = RequestMethod.GET)
	public ModelAndView showUpdateDeductionForm(@PathVariable Long deductionId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateDeductionForm");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		DeductionForm deductionForm = adminEarningDeductionService
				.getUpdateDeductionForm(deductionId);
		logger.debug("deductionForm::" + deductionForm);
		modelAndView.addObject("deductionForm", deductionForm);
		logger.debug("showUpdateDeductionForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateDeduction", method = RequestMethod.POST)
	public ModelAndView updateDeduction(@ModelAttribute DeductionForm deductionForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateDeduction");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.updateDeduction(deductionForm);
		logger.debug("updateDeduction >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).DEDUCTIONS, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteDeduction/{deductionId}", method = RequestMethod.GET)
	public ModelAndView deleteDeduction(@PathVariable Long deductionId)
			throws InstanceNotFoundException {
		logger.debug(" >> deductionId");
		ModelAndView modelAndView = new ModelAndView("earningDeductionView");
		adminEarningDeductionService.verifyAnddeleteDeduction(deductionId);
		logger.debug("deductionId >>");
		return modelAndView;
	}

}
