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

import com.epayroll.entity.Company;
import com.epayroll.entity.FederalStateTaxRate;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.FutaSutaTaxRate;
import com.epayroll.entity.TaxAuthorityDepositCycle;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.FilingStatusForm;
import com.epayroll.form.adminConsole.FutaForm;
import com.epayroll.form.adminConsole.FutaSutaTaxForm;
import com.epayroll.form.adminConsole.IncomeTaxSlabForm;
import com.epayroll.form.adminConsole.MedicareSocialSecurityForm;
import com.epayroll.form.adminConsole.TaxAuthorityDepositCycleForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/federalTax")
@Controller
public class AdminFederalTaxController {
	private Logger logger = LoggerFactory.getLogger(AdminFederalTaxController.class);
	@Autowired
	private AdminSystemDataService adminSystemDataService;

	@RequestMapping(value = "/showFederalTaxForm", method = RequestMethod.GET)
	public ModelAndView showFederalTaxForm() {
		logger.debug(">> showFederalTaxForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		modelView.addObject("filingStatusForm", new FilingStatusForm());
		logger.debug("showFederalTaxForm >>");
		return modelView;
	}

	// when filing status selected (dropdown) year = currentYear

	@RequestMapping(value = "/showFederalTaxList", method = RequestMethod.POST)
	public ModelAndView showFederalTaxList(@ModelAttribute FilingStatusForm filingStatusForm) {
		logger.debug(">> showFederalTaxList");
		ModelAndView modelView = new ModelAndView("federalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		Long taxTypeId = adminSystemDataService.getFitTaxTypeId();
		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(filingStatusForm.getId(), taxTypeId);

		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		IncomeTaxSlabForm incomeTaxSlabForm = new IncomeTaxSlabForm();
		incomeTaxSlabForm.setFilingStatusId(filingStatusForm.getId());
		incomeTaxSlabForm.setTaxTypeId(taxTypeId);
		modelView.addObject("incomeTaxSlabForm", incomeTaxSlabForm);
		logger.debug("showFederalTaxList >>");
		return modelView;
	}

	// TODO set taxTypeId ,federalStateTaxRateId,filingStatusId; in hidden
	// variable
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFederalStateTaxRate", method = RequestMethod.POST)
	public ModelAndView addFederalStateTaxRate(@ModelAttribute IncomeTaxSlabForm incomeTaxSlabForm) {
		logger.debug(">> addFederalStateTaxRate");
		logger.debug("incomeTaxSlabForm :: " + incomeTaxSlabForm);
		ModelAndView modelView = new ModelAndView("federalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(incomeTaxSlabForm.getFilingStatusId(),
						incomeTaxSlabForm.getTaxTypeId());
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		try {
			adminSystemDataService.addFederalStateTaxRate(incomeTaxSlabForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("addFederalStateTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFederalStateTaxRateForm/{federalStateTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalStateTaxRateForm(@PathVariable Long federalStateTaxRateId,
			@ModelAttribute FilingStatusForm filingStatusForm) {
		logger.debug(">> showUpdateFederalStateTaxRateForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(filingStatusForm.getId(),
						adminSystemDataService.getFitTaxTypeId());
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		try {
			IncomeTaxSlabForm incomeTaxSlabForm = adminSystemDataService
					.getIncomeTaxSlabForm(federalStateTaxRateId);
			modelView.addObject("incomeTaxSlabForm", incomeTaxSlabForm);
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateFederalStateTaxRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFederalStateTaxRate", method = RequestMethod.POST)
	public ModelAndView updateFederalStateTaxRate(
			@ModelAttribute IncomeTaxSlabForm incomeTaxSlabForm) {
		logger.debug(">> updateFederalStateTaxRate");
		logger.debug("federalIncomeTaxSlabForm :: " + incomeTaxSlabForm);
		ModelAndView modelView = new ModelAndView("federalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(incomeTaxSlabForm.getFilingStatusId(),
						incomeTaxSlabForm.getTaxTypeId());
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		try {
			adminSystemDataService.updateFederalStateTaxRate(incomeTaxSlabForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("updateFederalStateTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFederalStateTaxRate/{federalStateTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteFilingStatus(@PathVariable Long federalStateTaxRateId) {
		logger.debug(">> deleteFederalStateTaxRate");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (adminSystemDataService.verifyAndDeleteFederalStateTaxRate(federalStateTaxRateId)) {

				result = "Record Deleted Successfully";
			} else {
				result = "This Records are associated with this Other Entity. So you cannot delete this Federal State Tax Rate from system. ";
			}
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		modelView.setViewName("federalForm");
		logger.debug("deleteFederalStateTaxRate >>");
		return modelView;
	}

	@RequestMapping(value = "/showFutaForm", method = RequestMethod.GET)
	public ModelAndView showFutaForm() {
		logger.debug(">> showFutaForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		FutaForm futaForm = adminSystemDataService.getFutaForm();
		logger.debug("futaForm :: " + futaForm);
		modelView.addObject("futaForm", futaForm);
		logger.debug("showFutaForm >>");
		return modelView;
	}

	@RequestMapping(value = "/addUpdateFuta", method = RequestMethod.POST)
	public ModelAndView addUpdateFuta(@ModelAttribute FutaForm futaForm) {
		logger.debug(">> addUpdateFuta");
		logger.debug("futaForm :: " + futaForm);
		ModelAndView modelView = new ModelAndView("federalForm");
		try {
			adminSystemDataService.addUpdateFuta(futaForm);
			logger.debug("Updated futaForm :: " + futaForm);
			modelView.addObject("futaForm", futaForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("addUpdateFuta >>");
		return modelView;
	}

	@RequestMapping(value = "/showMedicareSocialSecurityForm", method = RequestMethod.GET)
	public ModelAndView showMedicareSocialSecurityForm() {
		logger.debug(">> showMedicareSocialSecurityForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		MedicareSocialSecurityForm medicareSocialSecurityForm = adminSystemDataService
				.getMedicareSocialSecurityForm();
		modelView.addObject("medicareSocialSecurityForm", medicareSocialSecurityForm);
		logger.debug("showMedicareSocialSecurityForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_TAX_MEDICARE_SOCIAL_SECURITY_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addUpdateMedicareSocialSecurity", method = RequestMethod.POST)
	public ModelAndView addUpdateMedicareSocialSecurity(
			@ModelAttribute MedicareSocialSecurityForm medicareSocialSecurityForm) {
		logger.debug(">> addUpdateMedicareSocialSecurity");
		logger.debug("medicareSocialSecurityForm :: " + medicareSocialSecurityForm);
		System.out.println("medicareSocialSecurityForm :: " + medicareSocialSecurityForm);
		ModelAndView modelView = new ModelAndView("federalForm");
		try {
			adminSystemDataService.addUpdateMedicareSocialSecurity(medicareSocialSecurityForm);
			logger.debug("Updated medicareSocialSecurityForm :: " + medicareSocialSecurityForm);
			System.out.println("Updated medicareSocialSecurityForm :: "
					+ medicareSocialSecurityForm);
			modelView.addObject("medicareSocialSecurityForm", medicareSocialSecurityForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("addUpdateMedicareSocialSecurity >>");
		return modelView;
	}

	@RequestMapping(value = "/showTaxAuthorityDepositCycleForm", method = RequestMethod.GET)
	public ModelAndView showTaxAuthorityDepositCycleForm() {
		logger.debug(">> showTaxAuthorityDepositCycleForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
				.getTaxAuthorityDepositCycleForm();
		logger.debug("taxAuthorityDepositCycleForm:::" + taxAuthorityDepositCycleForm);
		List<TaxDepositCycle> depositCycleList = adminSystemDataService.getDepositCycles();
		modelView.addObject("depositCycleList", depositCycleList);
		logger.debug("depositCycleList:::" + depositCycleList);
		List<TaxAuthorityDepositCycle> taxAuthorityDepositCycles = adminSystemDataService
				.getTaxAuthorityDepositCycleList(taxAuthorityDepositCycleForm.getTaxAuthorityId());
		logger.debug("taxAuthorityDepositCycles:::" + taxAuthorityDepositCycles);
		modelView.addObject("taxAuthorityDepositCycles", taxAuthorityDepositCycles);
		modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);
		logger.debug("showTaxAuthorityDepositCycleForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addTaxAuthorityDepositCycle", method = RequestMethod.POST)
	public ModelAndView addTaxAuthorityDepositCycle(
			@ModelAttribute TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm) {
		logger.debug(">> addTaxAuthorityDepositCycle");
		logger.debug("taxAuthorityDepositCycleForm :: " + taxAuthorityDepositCycleForm);
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			adminSystemDataService.addTaxAuthorityDepositCycle(taxAuthorityDepositCycleForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("depositCycleList", adminSystemDataService.getDepositCycles());

		modelView.addObject("taxAuthorityDepositCycles", adminSystemDataService
				.getTaxAuthorityDepositCycleList(taxAuthorityDepositCycleForm.getTaxAuthorityId()));

		taxAuthorityDepositCycleForm.clear();
		modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);

		logger.debug("addTaxAuthorityDepositCycle >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateTaxAuthorityDepositCycleForm/{taxAuthorityDepositCycleId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTaxAuthorityDepositCycleForm(
			@PathVariable Long taxAuthorityDepositCycleId) {
		logger.debug(">> showUpdateTaxAuthorityDepositCycleForm");
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
					.getTaxAuthorityDepositCycleUpdateForm(taxAuthorityDepositCycleId);
			modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);
			logger.debug("taxAuthorityDepositCycleForm" + taxAuthorityDepositCycleForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
					.getTaxAuthorityDepositCycleForm();
			modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);
		}

		modelView.addObject("depositCycleList", adminSystemDataService.getDepositCycles());
		modelView.addObject("taxAuthorityDepositCycles",
				adminSystemDataService.getTaxAuthorityDepositCycleList(taxAuthorityDepositCycleId));

		logger.debug("showUpdateTaxAuthorityDepositCycleForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateTaxAuthorityDepositCycle", method = RequestMethod.POST)
	public ModelAndView updateTaxAuthorityDepositCycle(
			@ModelAttribute TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm) {
		logger.debug(">> updateTaxAuthorityDepositCycle");
		logger.debug("taxAuthorityDepositCycleForm :: " + taxAuthorityDepositCycleForm);
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			adminSystemDataService.updateTaxAuthorityDepositCycle(taxAuthorityDepositCycleForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("depositCycleList", adminSystemDataService.getDepositCycles());

		modelView.addObject("taxAuthorityDepositCycles", adminSystemDataService
				.getTaxAuthorityDepositCycleList(taxAuthorityDepositCycleForm.getTaxAuthorityId()));

		taxAuthorityDepositCycleForm.clear();
		modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);

		logger.debug("updateTaxAuthorityDepositCycle >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_DEPOSIT_CYCLE_MASTER, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteTaxAuthorityDepositCycle/{taxAuthorityDepositCycleId}", method = RequestMethod.GET)
	public ModelAndView updateTaxAuthorityDepositCycle(@PathVariable Long taxAuthorityDepositCycleId) {
		logger.debug(">> deleteTaxAuthorityDepositCycle");
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			adminSystemDataService.deleteTaxAuthorityDepositCycle(taxAuthorityDepositCycleId);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("depositCycleList", adminSystemDataService.getDepositCycles());
		modelView.addObject("taxAuthorityDepositCycles",
				adminSystemDataService.getTaxAuthorityDepositCycleList(taxAuthorityDepositCycleId));
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
				.getTaxAuthorityDepositCycleForm();
		modelView.addObject("taxAuthorityDepositCycleForm", taxAuthorityDepositCycleForm);

		logger.debug("deleteTaxAuthorityDepositCycle >>");
		return modelView;
	}

	@RequestMapping(value = "/showFutaCompanyRateForm", method = RequestMethod.GET)
	public ModelAndView showFutaCompanyRateForm() {
		logger.debug(">> showFutaCompanyRateForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		FutaSutaTaxForm futaSutaTaxForm = adminSystemDataService.getFutaSutaTaxForm();
		List<Company> companyList = adminSystemDataService.getAllCompanies();
		modelView.addObject("companyList", companyList);
		logger.debug("companyList:::" + companyList);
		List<FutaSutaTaxRate> futaTaxRates = adminSystemDataService
				.getFutaSutaTaxRates(futaSutaTaxForm.getTaxTypeId());
		modelView.addObject("futaTaxRates", futaTaxRates);
		modelView.addObject("futaSutaTaxForm", futaSutaTaxForm);
		logger.debug("futaSutaTaxForm::" + futaSutaTaxForm);
		logger.debug("showFutaCompanyRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FUTA_COMPANY_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFutaCompanyRate", method = RequestMethod.POST)
	public ModelAndView addFutaCompanyRate(@ModelAttribute FutaSutaTaxForm futaSutaTaxForm) {
		logger.debug(">> addFutaCompanyRate");
		logger.debug("futaSutaTaxForm :: " + futaSutaTaxForm);
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			adminSystemDataService.addFutaSutaCompanyRate(futaSutaTaxForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("companyList", adminSystemDataService.getAllCompanies());

		modelView.addObject("futaTaxRates",
				adminSystemDataService.getFutaSutaTaxRates(futaSutaTaxForm.getTaxTypeId()));

		futaSutaTaxForm.clear();
		modelView.addObject("futaSutaTaxForm", futaSutaTaxForm);

		logger.debug("addFutaCompanyRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FUTA_COMPANY_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFutaCompanyRateForm/{futaSutaTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFutaCompanyRateForm(@PathVariable Long futaSutaTaxRateId) {
		logger.debug(">> showUpdateFutaCompanyRateForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		FutaSutaTaxForm futaSutaTaxForm = null;
		try {
			futaSutaTaxForm = adminSystemDataService.getFutaSutaTaxUpdateForm(futaSutaTaxRateId);
			logger.debug("futaSutaTaxForm:::" + futaSutaTaxForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			futaSutaTaxForm = adminSystemDataService.getFutaSutaTaxForm();
		}
		modelView.addObject("futaSutaTaxForm", futaSutaTaxForm);
		modelView.addObject("companyList", adminSystemDataService.getAllCompanies());
		modelView.addObject("futaTaxRates",
				adminSystemDataService.getFutaSutaTaxRates(futaSutaTaxForm.getTaxTypeId()));

		logger.debug("showUpdateFutaCompanyRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FUTA_COMPANY_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFutaCompanyRate", method = RequestMethod.POST)
	public ModelAndView updateFutaCompanyRate(@ModelAttribute FutaSutaTaxForm futaSutaTaxForm) {
		logger.debug(">> updateFutaCompanyRate");
		logger.debug("futaSutaTaxForm :: " + futaSutaTaxForm);
		ModelAndView modelView = new ModelAndView("federalForm");

		try {
			adminSystemDataService.udateFutaSutaCompanyRate(futaSutaTaxForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("companyList", adminSystemDataService.getAllCompanies());

		modelView.addObject("futaTaxRates",
				adminSystemDataService.getFutaSutaTaxRates(futaSutaTaxForm.getTaxTypeId()));

		futaSutaTaxForm.clear();
		modelView.addObject("futaSutaTaxForm", futaSutaTaxForm);

		logger.debug("updateFutaCompanyRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FUTA_COMPANY_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFutaCompanyRate/{futaSutaTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteFutaCompanyRate(@PathVariable Long futaSutaTaxRateId) {
		logger.debug(">> deleteFutaCompanyRate");
		ModelAndView modelView = new ModelAndView("federalForm");
		try {
			adminSystemDataService.deleteFutaSutaCompanyRate(futaSutaTaxRateId);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}

		modelView.addObject("companyList", adminSystemDataService.getAllCompanies());

		FutaSutaTaxForm futaSutaTaxForm = adminSystemDataService.getFutaSutaTaxForm();
		modelView.addObject("futaSutaTaxForm", futaSutaTaxForm);
		modelView.addObject("futaTaxRates",
				adminSystemDataService.getFutaSutaTaxRates(futaSutaTaxForm.getTaxTypeId()));

		logger.debug("deleteFutaCompanyRate >>");
		return modelView;
	}
}
