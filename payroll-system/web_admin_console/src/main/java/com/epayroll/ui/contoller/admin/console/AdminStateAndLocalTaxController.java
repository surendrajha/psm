package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.epayroll.entity.CountyTaxRate;
import com.epayroll.entity.FederalStateTaxRate;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.TaxAuthorityDepositCycle;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.CountyTaxForm;
import com.epayroll.form.adminConsole.FilingStatusForm;
import com.epayroll.form.adminConsole.FutaSutaTaxForm;
import com.epayroll.form.adminConsole.IncomeTaxSlabForm;
import com.epayroll.form.adminConsole.SutaForm;
import com.epayroll.form.adminConsole.TaxAuthorityDepositCycleForm;
import com.epayroll.form.adminConsole.UsStateForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/stateAndLocalTax")
@Controller
public class AdminStateAndLocalTaxController {
	private Logger logger = LoggerFactory.getLogger(AdminStateAndLocalTaxController.class);
	@Autowired
	private AdminSystemDataService adminSystemDataService;

	private Long getUsStateIdFromSession(HttpSession session) {
		logger.debug(">> getUsStateIdFromSession");
		UsState usState = (UsState) session.getAttribute("usStateForm");
		Long usStateId = usState.getId();
		logger.debug("getUsStateIdFromSession >>");
		return usStateId;
	}

	@RequestMapping(value = "/showStateListForm", method = RequestMethod.GET)
	public ModelAndView showStateListForm() {
		logger.debug(">> showStateListForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<UsState> usStates = adminSystemDataService.getUsStateList();
		modelView.addObject("usStates", usStates);
		modelView.addObject("usStateForm", new UsStateForm());
		logger.debug("showStateListForm >>");
		return modelView;
	}

	// when State selected (dropdown) year = currentYear

	@RequestMapping(value = "/showStateAndLocalTaxForm", method = RequestMethod.POST)
	public ModelAndView showStateAndLocalTaxForm(@ModelAttribute UsStateForm usStateForm,
			HttpSession session) {
		logger.debug(">> showStateAndLocalTaxForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		session.setAttribute("usStateForm", usStateForm);
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		modelView.addObject("countyTaxForm", new CountyTaxForm());
		modelView.addObject("filingStatusForm", new FilingStatusForm());
		logger.debug("showStateAndLocalTaxForm >>");
		return modelView;
	}

	// when filing status selected (dropdown) year = currentYear
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/showStateTaxList", method = RequestMethod.POST)
	public ModelAndView showStateTaxList(@Valid @ModelAttribute FilingStatusForm filingStatusForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> showStateTaxList");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		modelView.addObject("countyTaxForm", new CountyTaxForm());
		if (!bindingResult.hasErrors()) {
			try {
				Long sitTaxTypeId = adminSystemDataService
						.getSitTaxTypeId(getUsStateIdFromSession(session));
				List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
						.getFederalStateTaxRates(filingStatusForm.getId(), sitTaxTypeId);

				modelView.addObject("federalStateTaxRates", federalStateTaxRates);
				IncomeTaxSlabForm incomeTaxSlabForm = new IncomeTaxSlabForm();
				incomeTaxSlabForm.setFilingStatusId(filingStatusForm.getId());
				incomeTaxSlabForm.setTaxTypeId(sitTaxTypeId);
				modelView.addObject("incomeTaxSlabForm", incomeTaxSlabForm);
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("showStateTaxList >>");
		return modelView;
	}

	// TODO set taxTypeId ,federalStateTaxRateId,filingStatusId; in hidden
	// variable
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateTaxRate", method = RequestMethod.POST)
	public ModelAndView addStateTaxRate(@Valid @ModelAttribute IncomeTaxSlabForm incomeTaxSlabForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> addStateTaxRate");
		logger.debug("incomeTaxSlabForm :: " + incomeTaxSlabForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		modelView.addObject("countyTaxForm", new CountyTaxForm());

		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(incomeTaxSlabForm.getFilingStatusId(),
						incomeTaxSlabForm.getTaxTypeId());
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.addFederalStateTaxRate(incomeTaxSlabForm);
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addStateTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateStateTaxRateForm/{federalStateTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateStateTaxRateForm(@PathVariable Long federalStateTaxRateId,
			@ModelAttribute FilingStatusForm filingStatusForm, HttpSession session) {
		logger.debug(">> showUpdateStateTaxRateForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		modelView.addObject("countyTaxForm", new CountyTaxForm());

		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(filingStatusForm.getId(),
						adminSystemDataService.getSitTaxTypeId(getUsStateIdFromSession(session)));
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		try {
			IncomeTaxSlabForm incomeTaxSlabForm = adminSystemDataService
					.getIncomeTaxSlabForm(federalStateTaxRateId);
			modelView.addObject("incomeTaxSlabForm", incomeTaxSlabForm);
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateStateTaxRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStateTaxRate", method = RequestMethod.POST)
	public ModelAndView updateStateTaxRate(
			@Valid @ModelAttribute IncomeTaxSlabForm incomeTaxSlabForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> updateStateTaxRate");
		logger.debug("incomeTaxSlabForm :: " + incomeTaxSlabForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		modelView.addObject("countyTaxForm", new CountyTaxForm());

		List<FederalStateTaxRate> federalStateTaxRates = adminSystemDataService
				.getFederalStateTaxRates(incomeTaxSlabForm.getFilingStatusId(),
						incomeTaxSlabForm.getTaxTypeId());
		modelView.addObject("federalStateTaxRates", federalStateTaxRates);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.updateFederalStateTaxRate(incomeTaxSlabForm);
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("updateStateTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_INCOME_TAX_SLAB, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteStateTaxRate/{federalStateTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteStateTaxRate(@PathVariable Long federalStateTaxRateId) {
		logger.debug(">> deleteStateTaxRate");
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
		modelView.setViewName("state&LocalForm");
		logger.debug("deleteStateTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COUNTY_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addCountyTaxRate", method = RequestMethod.POST)
	public ModelAndView addCountyTaxRate(@Valid @ModelAttribute CountyTaxForm countyTaxForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> addCountyTaxRate");
		logger.debug("countyTaxForm :: " + countyTaxForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.addCountyTaxRate(countyTaxForm,
						getUsStateIdFromSession(session));
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addCountyTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COUNTY_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateCountyTaxRateForm/{countyTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateCountyTaxRateForm(@PathVariable Long countyTaxRateId,
			HttpSession session) {
		logger.debug(">> showUpdateCountyTaxRateForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		try {
			CountyTaxForm countyTaxForm = adminSystemDataService.getCountyTaxForm(countyTaxRateId);
			modelView.addObject("countyTaxForm", countyTaxForm);
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}

		logger.debug("showUpdateCountyTaxRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COUNTY_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateCountyTaxRate", method = RequestMethod.POST)
	public ModelAndView updateCountyTaxRate(@Valid @ModelAttribute CountyTaxForm countyTaxForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> updateCountyTaxRate");

		ModelAndView modelView = new ModelAndView("state&LocalForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);

		List<UsCounty> usCounties = adminSystemDataService
				.getUsCountyList(getUsStateIdFromSession(session));
		modelView.addObject("usCounties", usCounties);
		List<CountyTaxRate> countyTaxRates = adminSystemDataService
				.getCountyTaxRates(getUsStateIdFromSession(session));
		modelView.addObject("countyTaxRates", countyTaxRates);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.updateCountyTaxRate(countyTaxForm);
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("updateCountyTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COUNTY_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteCountyTaxRate/{countyTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteCountyTaxRate(@PathVariable Long countyTaxRateId) {
		logger.debug(">> deleteCountyTaxRate");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (adminSystemDataService.verifyAndDeleteCountyTaxRate(countyTaxRateId)) {

				result = "Record Deleted Successfully";
			} else {
				result = "This Records are associated with this Other Entity. So you cannot delete this County Tax Rate from system. ";
			}
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		modelView.setViewName("state&LocalForm");
		logger.debug("deleteCountyTaxRate >>");
		return modelView;
	}

	@RequestMapping(value = "/showSutaCompanyRateForm", method = RequestMethod.GET)
	public ModelAndView showSutaCompanyRateForm(HttpSession session) {
		logger.debug(">> showSutaCompanyRateForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		FutaSutaTaxForm sutaTaxForm = adminSystemDataService
				.getFutaSutaTaxForm(getUsStateIdFromSession(session));
		modelView.addObject("companyList", adminSystemDataService.getAllCompanies());
		modelView.addObject("futaTaxRates",
				adminSystemDataService.getFutaSutaTaxRates(sutaTaxForm.getTaxTypeId()));
		modelView.addObject("sutaSutaTaxForm", sutaTaxForm);
		logger.debug("showSutaCompanyRateForm >>");
		return modelView;
	}

	@RequestMapping(value = "/addSutaCompanyRate", method = RequestMethod.POST)
	public ModelAndView addSutaCompanyRate(@ModelAttribute FutaSutaTaxForm futaSutaTaxForm) {
		logger.debug(">> addSutaCompanyRate");
		logger.debug("futaSutaTaxForm :: " + futaSutaTaxForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");

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

	@RequestMapping(value = "/showUpdateSutaCompanyRateForm/{futaSutaTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateSutaCompanyRateForm(@PathVariable Long futaSutaTaxRateId) {
		logger.debug(">> showUpdateSutaCompanyRateForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		FutaSutaTaxForm futaSutaTaxForm = null;
		try {
			futaSutaTaxForm = adminSystemDataService.getFutaSutaTaxUpdateForm(futaSutaTaxRateId);
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

	@RequestMapping(value = "/updateSutaCompanyRate", method = RequestMethod.POST)
	public ModelAndView updateSutaCompanyRate(@ModelAttribute FutaSutaTaxForm futaSutaTaxForm) {
		logger.debug(">> updateSutaCompanyRate");
		logger.debug("futaSutaTaxForm :: " + futaSutaTaxForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");

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

	@RequestMapping(value = "/deleteSutaCompanyRate/{futaSutaTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteSutaCompanyRate(@PathVariable Long futaSutaTaxRateId) {
		logger.debug(">> deleteSutaCompanyRate");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
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

	@RequestMapping(value = "/showSutaForm", method = RequestMethod.GET)
	public ModelAndView showSutaForm(HttpSession session) {
		logger.debug(">> showSutaForm");
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		SutaForm sutaForm = adminSystemDataService.getSutaForm(getUsStateIdFromSession(session));
		modelView.addObject("sutaForm", sutaForm);
		logger.debug("showSutaForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).SUTA_CEILING, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addUpdateSuta", method = RequestMethod.POST)
	public ModelAndView addUpdateSuta(@ModelAttribute SutaForm sutaForm) {
		logger.debug(">> addUpdateSuta");
		logger.debug("sutaForm :: " + sutaForm);
		ModelAndView modelView = new ModelAndView("state&LocalForm");
		try {
			adminSystemDataService.addUpdateSuta(sutaForm);
			logger.debug("Updated sutaForm :: " + sutaForm);
			modelView.addObject("sutaForm", sutaForm);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			e.printStackTrace();
		}
		logger.debug("addUpdateSuta >>");
		return modelView;
	}

	@RequestMapping(value = "/showTaxAuthorityDepositCycleForm", method = RequestMethod.GET)
	public ModelAndView showTaxAuthorityDepositCycleForm(HttpSession session) {
		logger.debug(">> showTaxAuthorityDepositCycleForm");
		ModelAndView modelView = new ModelAndView("federalForm");
		Long stateId = getUsStateIdFromSession(session);
		TaxAuthorityDepositCycleForm taxAuthorityDepositCycleForm = adminSystemDataService
				.getTaxAuthorityDepositCycleForm(stateId);
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
}
