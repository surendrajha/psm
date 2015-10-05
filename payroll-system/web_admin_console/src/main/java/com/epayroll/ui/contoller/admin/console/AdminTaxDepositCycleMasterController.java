package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.TaxDepositCycleMasterForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/taxDepositCycleMaster")
@Controller
public class AdminTaxDepositCycleMasterController {
	private Logger logger = LoggerFactory.getLogger(AdminTaxDepositCycleMasterController.class);

	@Autowired
	private AdminSystemDataService adminSystemDataService;

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showTaxDepositCycleMasterForm", method = RequestMethod.GET)
	public ModelAndView showTaxDepositCycleMasterForm() {
		logger.debug(">> showTaxDepositCycleMasterForm");
		ModelAndView modelView = new ModelAndView("taxDepositCycleMasterForm");
		List<TaxDepositCycle> taxDepositCycleList = adminSystemDataService.getTaxDepositCycles();
		modelView.addObject("taxDepositCycleList", taxDepositCycleList);
		modelView.addObject("taxDepositCycleMasterForm", new TaxDepositCycleMasterForm());
		logger.debug("showTaxDepositCycleMasterForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addTaxDepositCycle", method = RequestMethod.POST)
	public ModelAndView addTaxDepositCycle(
			@Valid @ModelAttribute TaxDepositCycleMasterForm taxDepositCycleMasterForm,
			BindingResult bindingResult) {
		logger.debug(">> addTaxDepositCycle");
		ModelAndView modelView = new ModelAndView("taxDepositCycleMasterForm");
		List<TaxDepositCycle> taxDepositCycleList = adminSystemDataService.getTaxDepositCycles();
		modelView.addObject("taxDepositCycleList", taxDepositCycleList);
		if (!bindingResult.hasErrors()) {
			try {
				@SuppressWarnings("unused")
				Long id = adminSystemDataService.addTaxDepositCycle(taxDepositCycleMasterForm);
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addTaxDepositCycle >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateTaxDepositCycleMasterForm/{taxDepositCycleId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTaxDepositCycleMasterForm(@PathVariable Long taxDepositCycleId,
			@ModelAttribute TaxDepositCycleMasterForm taxDepositCycleMasterForm) {
		logger.debug(">> showUpdateTaxDepositCycleMasterForm");
		ModelAndView modelView = new ModelAndView("taxDepositCycleMasterForm");
		List<TaxDepositCycle> taxDepositCycleList = adminSystemDataService.getTaxDepositCycles();
		modelView.addObject("taxDepositCycleList", taxDepositCycleList);
		try {
			// TODO set id in hidden variable
			modelView.addObject("taxDepositCycleMasterForm",
					adminSystemDataService.getTaxDepositCycleMasterForm(taxDepositCycleId));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateTaxDepositCycleMasterForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateTaxDepositCycle", method = RequestMethod.POST)
	public ModelAndView updateTaxDepositCycle(
			@Valid @ModelAttribute TaxDepositCycleMasterForm taxDepositCycleMasterForm,
			BindingResult bindingResult) {
		logger.debug(">> updateTaxDepositCycle");
		ModelAndView modelView = new ModelAndView("taxDepositCycleMasterForm");
		List<TaxDepositCycle> taxDepositCycleList = adminSystemDataService.getTaxDepositCycles();
		modelView.addObject("taxDepositCycleList", taxDepositCycleList);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.updateTaxDepositCycle(taxDepositCycleMasterForm);
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("updateTaxDepositCycle >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteTaxDepositCycle/{taxDepositCycleId}", method = RequestMethod.GET)
	public ModelAndView deleteTaxDepositCycle(@PathVariable Long taxDepositCycleId) {
		logger.debug(">> deleteTaxDepositCycle");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (adminSystemDataService.verifyAndDeleteTaxDepositCycle(taxDepositCycleId)) {

				result = "Record Deleted Successfully";
			} else {
				result = "Tax Deposit Cycle Records are associated with this Company Tax and Tax Authority Deposit Cycle. So you cannot delete this Tax Deposit Cycle from system. ";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		modelView.setViewName("taxDepositCycleMasterForm");
		logger.debug("deleteTaxDepositCycle >>");
		return modelView;
	}

}
