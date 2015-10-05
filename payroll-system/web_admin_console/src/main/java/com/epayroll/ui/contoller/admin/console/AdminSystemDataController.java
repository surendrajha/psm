package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.AllowanceRate;
import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDeduction;
import com.epayroll.entity.DeductionCap;
import com.epayroll.entity.FilingStatus;
import com.epayroll.entity.StandardDeductionRate;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AllowanceRateForm;
import com.epayroll.form.adminConsole.DeductionCapForm;
import com.epayroll.form.adminConsole.StandardDeductionRateForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * 
 * @author Uma
 * 
 */
@RequestMapping("/adminConsole")
@Controller
public class AdminSystemDataController {
	private Logger logger = LoggerFactory.getLogger(AdminSystemDataController.class);
	@Autowired
	private AdminSystemDataService adminSystemDataService;

	private Long getUsStateIdFromSession(HttpSession session) {
		logger.debug(">> getUsStateIdFromSession");
		UsState usState = (UsState) session.getAttribute("usStateForm");
		Long usStateId = usState.getId();
		logger.debug("getUsStateIdFromSession >>");
		return usStateId;
	}

	@RequestMapping(value = "/showFederalAllowanceRateForm", method = RequestMethod.GET)
	public ModelAndView showFederalAllowanceRateForm() {
		logger.debug(" >> showFederalAllowanceRateForm");
		ModelAndView modelAndView = new ModelAndView("federalAllowanceRateView");
		List<AllowanceRate> allowanceRates = adminSystemDataService.getFederalAllowanceRates();
		modelAndView.addObject("allowanceRates", allowanceRates);
		List<AllowanceType> allowanceTypes = adminSystemDataService.getFederalAllowanceTypes();
		modelAndView.addObject("allowanceTypes", allowanceTypes);
		modelAndView.addObject("allowanceRateForm", new AllowanceRateForm());
		logger.debug("showFederalAllowanceRateForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showStateAllowanceRateForm", method = RequestMethod.GET)
	public ModelAndView showStateAllowanceRateForm(HttpSession httpSession) {
		logger.debug(" >> showStateAllowanceRateForm");
		ModelAndView modelAndView = new ModelAndView("stateAllowanceRateView");
		Long StateId = getUsStateIdFromSession(httpSession);
		List<AllowanceRate> allowanceRates = adminSystemDataService.getStateAllowanceRates(StateId);
		modelAndView.addObject("allowanceRates", allowanceRates);
		List<AllowanceType> allowanceTypes = adminSystemDataService.getStateAllowanceTypes(StateId);
		modelAndView.addObject("allowanceTypes", allowanceTypes);
		modelAndView.addObject("allowanceRateForm", new AllowanceRateForm());
		logger.debug("showStateAllowanceRateForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFederalAllowanceRate", method = RequestMethod.POST)
	public ModelAndView addFederalAllowanceRate(@ModelAttribute AllowanceRateForm allowanceRateForm) {
		logger.debug(" >> addFederalAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("federalAllowanceRateView");
		try {
			adminSystemDataService.addFederalAllowanceRate(allowanceRateForm);
			logger.debug("addFederalAllowanceRate >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateAllowanceRate", method = RequestMethod.POST)
	public ModelAndView addStateAllowanceRate(@ModelAttribute AllowanceRateForm allowanceRateForm) {
		logger.debug(" >> addStateAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("stateAllowanceRateView");
		try {
			adminSystemDataService.addStateAllowanceRate(allowanceRateForm);
			logger.debug("addStateAllowanceRate >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFederalAllowanceRateForm/{allowanceRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalAllowanceRateForm(@PathVariable Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFederalAllowanceRateForm");
		ModelAndView modelAndView = new ModelAndView("federalAllowanceRateView");
		AllowanceRateForm allowanceRateForm = adminSystemDataService
				.getFederalAllowanceRateForm(allowanceRateId);
		logger.debug("allowanceRateForm::" + allowanceRateForm);
		modelAndView.addObject("allowanceRateForm", allowanceRateForm);
		logger.debug("showUpdateFederalAllowanceRateForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateStateAllowanceRateForm/{allowanceRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateStateAllowanceRateForm(@PathVariable Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateStateAllowanceRateForm");
		ModelAndView modelAndView = new ModelAndView("stateAllowanceRateView");
		AllowanceRateForm allowanceRateForm = adminSystemDataService
				.getStateAllowanceRateForm(allowanceRateId);
		logger.debug("allowanceRateForm::" + allowanceRateForm);
		modelAndView.addObject("allowanceRateForm", allowanceRateForm);
		logger.debug("showUpdateStateAllowanceRateForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFederalAllowanceRate", method = RequestMethod.POST)
	public ModelAndView updateFederalAllowanceRate(
			@ModelAttribute AllowanceRateForm allowanceRateForm) throws InstanceNotFoundException {
		logger.debug(" >> updateFederalAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("federalAllowanceRateView");
		adminSystemDataService.updateFederalAllowanceRate(allowanceRateForm);
		logger.debug("updateFederalAllowanceRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStateAllowanceRate", method = RequestMethod.POST)
	public ModelAndView updateStateAllowanceRate(@ModelAttribute AllowanceRateForm allowanceRateForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateStateAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("stateAllowanceRateView");
		adminSystemDataService.updateStateAllowanceRate(allowanceRateForm);
		logger.debug("updateStateAllowanceRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFederalAllowanceRate/{allowanceRateId}", method = RequestMethod.GET)
	public ModelAndView deleteFederalAllowanceRate(@PathVariable Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteFederalAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("federalAllowanceRateView");
		adminSystemDataService.deleteFederalAllowanceRate(allowanceRateId);
		logger.debug("deleteFederalAllowanceRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_ALLOWANCE_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteStateAllowanceRate/{allowanceRateId}", method = RequestMethod.GET)
	public ModelAndView deleteStateAllowanceRate(@PathVariable Long allowanceRateId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteStateAllowanceRate");
		ModelAndView modelAndView = new ModelAndView("stateAllowanceRateView");
		adminSystemDataService.deleteStateAllowanceRate(allowanceRateId);
		logger.debug("deleteStateAllowanceRate >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showFederalStandardDeductionForm", method = RequestMethod.GET)
	public ModelAndView showFederalStandardDeductionForm() {
		logger.debug(" >> showFederalStandardDeductionForm");
		ModelAndView modelAndView = new ModelAndView("federalStandardDeductionView");
		List<StandardDeductionRate> standardDeductionRates = adminSystemDataService
				.getFederalStandardDeductions();
		modelAndView.addObject("standardDeductionRates", standardDeductionRates);
		List<FilingStatus> filingStatus = adminSystemDataService.getFilingStatusList();
		modelAndView.addObject("filingStatus", filingStatus);
		modelAndView.addObject("standardDeductionRateForm", new StandardDeductionRateForm());
		logger.debug("showFederalStandardDeductionForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showStateStandardDeductionForm", method = RequestMethod.GET)
	public ModelAndView showStateStandardDeductionForm(HttpSession session) {
		logger.debug(" >> showStateStandardDeductionForm");
		ModelAndView modelAndView = new ModelAndView("stateStandardDeductionView");
		Long stateId = getUsStateIdFromSession(session);
		List<StandardDeductionRate> standardDeductionRates = adminSystemDataService
				.getStateStandardDeductions(stateId);
		modelAndView.addObject("standardDeductionRates", standardDeductionRates);
		List<FilingStatus> filingStatus = adminSystemDataService.getFilingStatusList();
		modelAndView.addObject("filingStatus", filingStatus);
		modelAndView.addObject("standardDeductionRateForm", new StandardDeductionRateForm());
		logger.debug("showStateStandardDeductionForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFederalStandardDeductionRate", method = RequestMethod.POST)
	public ModelAndView addFederalStandardDeductionRate(
			@ModelAttribute StandardDeductionRateForm standardDeductionRateForm) {
		logger.debug(" >> addFederalStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("federalStandardDeductionView");
		try {
			adminSystemDataService.addFederalStandardDeductionRate(standardDeductionRateForm);
			logger.debug("addFederalStandardDeductionRate >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateStandardDeductionRate", method = RequestMethod.POST)
	public ModelAndView addStateStandardDeductionRate(
			@ModelAttribute StandardDeductionRateForm standardDeductionRateForm) {
		logger.debug(" >> addStateStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("stateStandardDeductionView");
		try {
			adminSystemDataService.addStateStandardDeductionRate(standardDeductionRateForm);
			logger.debug("addStateStandardDeductionRate >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFederalStandardDeductionRateForm/{federalStandardDeductionId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalStandardDeductionRateForm(
			@PathVariable Long federalStandardDeductionId) throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFederalStandardDeductionRateForm");
		ModelAndView modelAndView = new ModelAndView("federalStandardDeductionView");
		StandardDeductionRateForm standardDeductionRateForm = adminSystemDataService
				.getFederalStandardDeductionRateForm(federalStandardDeductionId);
		logger.debug("standardDeductionRateForm::" + standardDeductionRateForm);
		modelAndView.addObject("standardDeductionRateForm", standardDeductionRateForm);
		logger.debug("showUpdateFederalStandardDeductionRateForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateStateStandardDeductionRateForm/{federalStandardDeductionId}", method = RequestMethod.GET)
	public ModelAndView showUpdateStateStandardDeductionRateForm(
			@PathVariable Long stateStandardDeductionId) throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFederalStandardDeductionRateForm");
		ModelAndView modelAndView = new ModelAndView("stateStandardDeductionView");
		StandardDeductionRateForm standardDeductionRateForm = adminSystemDataService
				.getStateStandardDeductionRateForm(stateStandardDeductionId);
		logger.debug("standardDeductionRateForm::" + standardDeductionRateForm);
		modelAndView.addObject("standardDeductionRateForm", standardDeductionRateForm);
		logger.debug("showUpdateStateStandardDeductionRateForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFederalStandardDeductionRate", method = RequestMethod.POST)
	public ModelAndView updateFederalStandardDeductionRate(
			@ModelAttribute StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateFederalStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("federalStandardDeductionView");
		adminSystemDataService.updateFederalStandardDeductionRate(standardDeductionRateForm);
		logger.debug("updateFederalStandardDeductionRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStateStandardDeductionRate", method = RequestMethod.POST)
	public ModelAndView updateStateStandardDeductionRate(
			@ModelAttribute StandardDeductionRateForm standardDeductionRateForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateStateStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("stateStandardDeductionView");
		adminSystemDataService.updateStateStandardDeductionRate(standardDeductionRateForm);
		logger.debug("updateStateStandardDeductionRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFederalStandardDeductionRate/{federalStandardDeductionId}", method = RequestMethod.GET)
	public ModelAndView deleteFederalStandardDeductionRate(
			@PathVariable Long federalStandardDeductionId) throws InstanceNotFoundException {
		logger.debug(" >> deleteFederalStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("federalStandardDeductionView");
		adminSystemDataService.deleteFederalStandardDeductionRate(federalStandardDeductionId);
		logger.debug("deleteFederalStandardDeductionRate >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_STANDARD_DEDUCTION_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteStateStandardDeductionRate/{stateStandardDeductionId}", method = RequestMethod.GET)
	public ModelAndView deleteStateStandardDeductionRate(@PathVariable Long stateStandardDeductionId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteStateStandardDeductionRate");
		ModelAndView modelAndView = new ModelAndView("stateStandardDeductionView");
		adminSystemDataService.deleteStateStandardDeductionRate(stateStandardDeductionId);
		logger.debug("deleteStateStandardDeductionRate >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showFederalDeductionCapForm", method = RequestMethod.GET)
	public ModelAndView showFederalDeductionCapForm() {
		logger.debug(" >> showFederalDeductionCapForm");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		List<DeductionCap> deductionCaps = adminSystemDataService.getFederalDeductionCap();
		modelAndView.addObject("deductionCaps", deductionCaps);
		List<Company> companies = adminSystemDataService.getCompanyList();
		modelAndView.addObject("companies", companies);
		modelAndView.addObject("deductionCapForm", new DeductionCapForm());
		logger.debug("showFederalDeductionCapForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showStateDeductionCapForm", method = RequestMethod.GET)
	public ModelAndView showStateDeductionCapForm(HttpSession session) {
		logger.debug(" >> showStateDeductionCapForm");
		ModelAndView modelAndView = new ModelAndView("stateDeductionCapView");
		Long stateId = getUsStateIdFromSession(session);
		List<DeductionCap> deductionCaps = adminSystemDataService.getStateDeductionCap(stateId);
		modelAndView.addObject("deductionCaps", deductionCaps);
		List<Company> companies = adminSystemDataService.getCompanyList();
		modelAndView.addObject("companies", companies);
		modelAndView.addObject("deductionCapForm", new DeductionCapForm());
		logger.debug("showStateDeductionCapForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/getFederalCompanyDeductionList/{companyId}", method = RequestMethod.GET)
	public ModelAndView getFederalCompanyDeductionList(@PathVariable Long companyId) {
		logger.debug(" >> getFederalCompanyDeductionList");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		List<CompanyDeduction> companyDeductions = adminSystemDataService
				.getCompanyDeductionList(companyId);
		modelAndView.addObject("companyDeductions", companyDeductions);
		logger.debug("getFederalCompanyDeductionList >>");
		return modelAndView;
	}

	@RequestMapping(value = "/getStateCompanyDeductionList/{companyId}", method = RequestMethod.GET)
	public ModelAndView getStateCompanyDeductionList(@PathVariable Long companyId) {
		logger.debug(" >> getStateCompanyDeductionList");
		ModelAndView modelAndView = new ModelAndView("stateDeductionCapView");
		List<CompanyDeduction> companyDeductions = adminSystemDataService
				.getCompanyDeductionList(companyId);
		modelAndView.addObject("companyDeductions", companyDeductions);
		logger.debug("getStateCompanyDeductionList >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFederalDeductionCap", method = RequestMethod.POST)
	public ModelAndView addFederalDeductionCap(@ModelAttribute DeductionCapForm deductionCapForm) {
		logger.debug(" >> addFederalDeductionCap");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		try {
			adminSystemDataService.addFederalDeductionCap(deductionCapForm);
			logger.debug("addFederalDeductionCap >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateDeductionCap", method = RequestMethod.POST)
	public ModelAndView addStateDeductionCap(@ModelAttribute DeductionCapForm deductionCapForm) {
		logger.debug(" >> addStateDeductionCap");
		ModelAndView modelAndView = new ModelAndView("stateDeductionCapView");
		try {
			adminSystemDataService.addStateDeductionCap(deductionCapForm);
			logger.debug("addStateDeductionCap >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFederalDeductionCapForm/{deductionCapId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalDeductionCapForm(@PathVariable Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFederalDeductionCapForm");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		DeductionCapForm deductionCapForm = adminSystemDataService
				.getFederalDeductionCapForm(deductionCapId);
		logger.debug("deductionCapForm::" + deductionCapForm);
		modelAndView.addObject("deductionCapForm", deductionCapForm);
		logger.debug("showUpdateFederalDeductionCapForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateStateDeductionCapForm/{deductionCapId}", method = RequestMethod.GET)
	public ModelAndView showUpdateStateDeductionCapForm(@PathVariable Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateStateDeductionCapForm");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		DeductionCapForm deductionCapForm = adminSystemDataService
				.getStateDeductionCapForm(deductionCapId);
		logger.debug("deductionCapForm::" + deductionCapForm);
		modelAndView.addObject("deductionCapForm", deductionCapForm);
		logger.debug("showUpdateStateDeductionCapForm >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFederalDeductionCap", method = RequestMethod.POST)
	public ModelAndView updateFederalDeductionCap(@ModelAttribute DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateFederalDeductionCap");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		adminSystemDataService.updateFederalDeductionCap(deductionCapForm);
		logger.debug("updateFederalDeductionCap >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStateDeductionCap", method = RequestMethod.POST)
	public ModelAndView updateStateDeductionCap(@ModelAttribute DeductionCapForm deductionCapForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateStateDeductionCap");
		ModelAndView modelAndView = new ModelAndView("stateDeductionCapView");
		adminSystemDataService.updateStateDeductionCap(deductionCapForm);
		logger.debug("updateStateDeductionCap >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFederalDeductionCap/{deductionCapId}", method = RequestMethod.GET)
	public ModelAndView deleteFederalDeductionCap(@PathVariable Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteFederalDeductionCap");
		ModelAndView modelAndView = new ModelAndView("federalDeductionCapView");
		adminSystemDataService.deleteFederalDeductionCap(deductionCapId);
		logger.debug("deleteFederalDeductionCap >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_DEDUCTION_CAP, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteStateDeductionCap/{deductionCapId}", method = RequestMethod.GET)
	public ModelAndView deleteStateDeductionCap(@PathVariable Long deductionCapId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteStateDeductionCap");
		ModelAndView modelAndView = new ModelAndView("stateDeductionCapView");
		adminSystemDataService.deleteStateDeductionCap(deductionCapId);
		logger.debug("deleteStateDeductionCap >>");
		return modelAndView;
	}

}
