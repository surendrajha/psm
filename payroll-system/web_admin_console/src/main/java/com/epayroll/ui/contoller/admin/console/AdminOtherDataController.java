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

import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.AllowanceTypeForm;
import com.epayroll.form.adminConsole.FederalAndStateHolidayForm;
import com.epayroll.form.adminConsole.FundCategoryForm;
import com.epayroll.form.adminConsole.PaymentModeForm;
import com.epayroll.form.adminConsole.PayrollPlanForm;
import com.epayroll.form.adminConsole.PlanFeatureForm;
import com.epayroll.form.adminConsole.TransactionBodyForm;
import com.epayroll.form.adminConsole.UsCityForm;
import com.epayroll.form.adminConsole.UsCountyForm;
import com.epayroll.form.adminConsole.UsStateForm;
import com.epayroll.service.adminConsole.AdminOtherDataService;

/**
 * @author Uma
 */
@RequestMapping("/admin")
@Controller
public class AdminOtherDataController {

	private Logger logger = LoggerFactory.getLogger(AdminOtherDataController.class);

	@Autowired
	private AdminOtherDataService adminOtherDataService;

	@RequestMapping(value = "/showUsStateForm", method = RequestMethod.GET)
	public ModelAndView showUsStateForm() {
		logger.debug(" >> showUsStateForm");
		ModelAndView modelAndView = new ModelAndView("usStateView");
		modelAndView.addObject("usStateForm", new UsStateForm());
		modelAndView.addObject("usStates", adminOtherDataService.getUsStates());
		logger.debug("showUsStateForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addUsState", method = RequestMethod.POST)
	public ModelAndView addUsState(@ModelAttribute UsStateForm usStateForm) {
		logger.debug(" >> addUsState");
		ModelAndView modelAndView = new ModelAndView("usStateView");
		adminOtherDataService.addUsState(usStateForm);
		logger.debug("addUsState >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateUsStateForm/{usStateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateUsStateForm(@PathVariable Long usStateId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateUsStateForm");
		ModelAndView modelAndView = new ModelAndView("usStateView");
		UsStateForm usStateForm = adminOtherDataService.getUpdateUsStateForm(usStateId);
		logger.debug("usStateForm::" + usStateForm);
		modelAndView.addObject("usStateForm", usStateForm);
		logger.debug("showUpdateUsStateForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateUsState", method = RequestMethod.POST)
	public ModelAndView updateUsState(@ModelAttribute UsStateForm usStateForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateUsState");
		ModelAndView modelAndView = new ModelAndView("usStateView");
		adminOtherDataService.updateUsState(usStateForm);
		logger.debug("updateUsState >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteUsState/{usStateId}", method = RequestMethod.GET)
	public ModelAndView deleteUsState(@PathVariable Long usStateId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteUsState");
		ModelAndView modelAndView = new ModelAndView("usStateView");
		adminOtherDataService.verifyAnddeleteUsState(usStateId);
		logger.debug("deleteUsState >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUsCountyForm", method = RequestMethod.GET)
	public ModelAndView showUsCountyForm() {
		logger.debug(" >> showUsCountyForm");
		ModelAndView modelAndView = new ModelAndView("usCountyView");
		List<UsState> usStates = adminOtherDataService.getUsStates();
		modelAndView.addObject("usStates", usStates);
		logger.debug("usStates" + usStates);
		modelAndView.addObject("usCountyForm", new UsCountyForm());
		modelAndView.addObject("usCounties", adminOtherDataService.getUsCounty());
		logger.debug("showUsCountyForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addUsCounty", method = RequestMethod.POST)
	public ModelAndView addUsCounty(@ModelAttribute UsCountyForm usCountyForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addUsCounty");
		ModelAndView modelAndView = new ModelAndView("usCountyView");
		adminOtherDataService.addUsCounty(usCountyForm);
		logger.debug("addUsCounty >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateUsCountyForm/{usCountyId}", method = RequestMethod.GET)
	public ModelAndView showUpdateUsCountyForm(@PathVariable Long usCountyId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateUsCountyForm");
		ModelAndView modelAndView = new ModelAndView("usCountyView");
		UsCountyForm usCountyForm = adminOtherDataService.getUpdateUsCountyForm(usCountyId);
		modelAndView.addObject("usCountyForm", usCountyForm);
		logger.debug("usCountyForm::" + usCountyForm);
		logger.debug("showUpdateUsCountyForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateUsCounty", method = RequestMethod.POST)
	public ModelAndView updateUsCounty(@ModelAttribute UsCountyForm usCountyForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateUsCounty");
		ModelAndView modelAndView = new ModelAndView("usCountyView");
		adminOtherDataService.updateUsCounty(usCountyForm);
		logger.debug("updateUsCounty >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteUsCounty/{usCountyId}", method = RequestMethod.GET)
	public ModelAndView deleteUsCounty(@PathVariable Long usCountyId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteUsCounty");
		ModelAndView modelAndView = new ModelAndView("usCountyView");
		adminOtherDataService.verifyAnddeleteUsCounty(usCountyId);
		logger.debug("deleteUsCounty >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUsCityForm", method = RequestMethod.GET)
	public ModelAndView showUsCityForm() {
		logger.debug(" >> showUsCityForm");
		ModelAndView modelAndView = new ModelAndView("usCityView");
		List<UsState> usStates = adminOtherDataService.getUsStates();
		logger.debug("usStates" + usStates);
		List<UsCounty> usCounties = adminOtherDataService.getUsCounty();
		logger.debug("usCounties" + usCounties);
		modelAndView.addObject("usStates", usStates);
		modelAndView.addObject("usCounties", usCounties);
		modelAndView.addObject("usCityForm", new UsCityForm());
		modelAndView.addObject("usCities", adminOtherDataService.getUsCities());
		logger.debug("showUsCityForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addUsCity", method = RequestMethod.POST)
	public ModelAndView addUsCity(@ModelAttribute UsCityForm usCityForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addUsCity");
		ModelAndView modelAndView = new ModelAndView("usCityView");
		adminOtherDataService.addUsCity(usCityForm);
		logger.debug("addUsCity >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateUsCityForm/{usCityId}", method = RequestMethod.GET)
	public ModelAndView showUpdateUsCityForm(@PathVariable Long usCityId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateUsCityForm");
		ModelAndView modelAndView = new ModelAndView("usCityView");
		UsCityForm usCityForm = adminOtherDataService.getUpdateUsCityForm(usCityId);
		logger.debug("usCityForm" + usCityForm);
		modelAndView.addObject("usCityForm", usCityForm);
		logger.debug("showUpdateUsCityForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateUsCity", method = RequestMethod.POST)
	public ModelAndView updateUsCity(@ModelAttribute UsCityForm usCityForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateUsCity");
		ModelAndView modelAndView = new ModelAndView("usCityView");
		adminOtherDataService.updateUsCity(usCityForm);
		logger.debug("updateUsCity >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteUsCity/{usCityId}", method = RequestMethod.GET)
	public ModelAndView deleteUsCity(@PathVariable Long usCityId) throws InstanceNotFoundException {
		logger.debug(" >> deleteUsCity");
		ModelAndView modelAndView = new ModelAndView("usCityView");
		adminOtherDataService.verifyAnddeleteUsCity(usCityId);
		logger.debug("deleteUsCity >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showFederalAndStateHolidayForm", method = RequestMethod.GET)
	public ModelAndView showFederalAndStateHolidayForm() {
		logger.debug(" >> showFederalAndStateHolidayForm");
		ModelAndView modelAndView = new ModelAndView("federalAndStateHolidayView");
		List<UsState> usStates = adminOtherDataService.getUsStates();
		logger.debug("usStates" + usStates);
		modelAndView.addObject("usStates", usStates);
		modelAndView.addObject("federalAndStateHolidayForm", new FederalAndStateHolidayForm());
		modelAndView.addObject("federalAndStateHolidayForm",
				adminOtherDataService.getFederalAndStateHolidays());
		logger.debug("showFederalAndStateHolidayForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addFederalAndStateHoliday", method = RequestMethod.POST)
	public ModelAndView addFederalAndStateHoliday(
			@ModelAttribute FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException {
		logger.debug(" >> addUsState");
		ModelAndView modelAndView = new ModelAndView("federalAndStateHolidayView");
		adminOtherDataService.addFederalAndStateHoliday(federalAndStateHolidayForm);
		logger.debug("addFederalAndStateHoliday >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateFederalAndStateHolidayForm/{federalAndStateHolidayId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalAndStateHolidayForm(
			@PathVariable Long federalAndStateHolidayId) throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFederalAndStateHolidayForm");
		ModelAndView modelAndView = new ModelAndView("federalAndStateHolidayView");
		FederalAndStateHolidayForm federalAndStateHolidayForm = adminOtherDataService
				.getUpdateFederalAndStateHolidayForm(federalAndStateHolidayId);
		logger.debug("federalAndStateHolidayForm" + federalAndStateHolidayForm);
		modelAndView.addObject("federalAndStateHolidayForm", federalAndStateHolidayForm);
		logger.debug("showUpdateFederalAndStateHolidayForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateFederalAndStateHoliday", method = RequestMethod.POST)
	public ModelAndView updateFederalAndStateHoliday(
			@ModelAttribute FederalAndStateHolidayForm federalAndStateHolidayForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateFederalAndStateHoliday");
		ModelAndView modelAndView = new ModelAndView("federalAndStateHolidayView");
		adminOtherDataService.updateFederalAndStateHoliday(federalAndStateHolidayForm);
		logger.debug("updateFederalAndStateHoliday >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteFederalAndStateHoliday/{federalAndStateHolidayId}", method = RequestMethod.GET)
	public ModelAndView deleteFederalAndStateHoliday(@PathVariable Long federalAndStateHolidayId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteFederalAndStateHoliday");
		ModelAndView modelAndView = new ModelAndView("federalAndStateHolidayView");
		adminOtherDataService.deleteFederalAndStateHoliday(federalAndStateHolidayId);
		logger.debug("deleteFederalAndStateHoliday >>");
		return modelAndView;
	}

	// @RequestMapping(value = "/showPayCycleForm", method = RequestMethod.GET)
	// public ModelAndView showPayCycleForm() {
	// logger.debug(" >> showPayCycleForm");
	// ModelAndView modelAndView = new ModelAndView("payFrequencyType");
	// modelAndView.addObject("payCycleForm", new PayCycleForm());
	// logger.debug("showPayCycleForm >>");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/getPayCycles", method = RequestMethod.POST)
	// @ResponseBody
	// public FlexigridDataResponseModel getPayCycles() {
	// logger.debug(" >> getPayCycles");
	// FlexigridDataResponseModel flexigridDataResponseModel = new
	// FlexigridDataResponseModel();
	// try {
	// List<PayFrequencyType> payFrequencyTypes =
	// adminOtherDataService.getPayFrequencyTypes();
	// List<Row> rows = new ArrayList<Row>();
	// for (PayFrequencyType payFrequencyType : payFrequencyTypes) {
	// Row row = new Row();
	// row.setCell(payFrequencyType);
	// rows.add(row);
	// }
	// flexigridDataResponseModel.setPage(1);
	// flexigridDataResponseModel.setTotal(payFrequencyTypes.size());
	// flexigridDataResponseModel.setRows(rows);
	// // serverResponse.setStatus(ServerStatus.SUCCESS);
	// // serverResponse.setMessage("Records Fetched Successfully");
	// // serverResponse.setData(payFrequencyTypes);
	// } catch (Exception e) {
	// logger.error("ERROR :: " + e);
	// // serverResponse.setStatus(ServerStatus.ERROR);
	// // serverResponse.setMessage(e.getMessage());
	// }
	// logger.debug("getPayCycles >>");
	// return flexigridDataResponseModel;
	// }
	//
	// @RequestMapping(value = "/addPayCycle", method = RequestMethod.POST)
	// public ModelAndView addPayCycle(@ModelAttribute PayCycleForm
	// payCycleForm) {
	// logger.debug(" >> addPayCycle");
	// ModelAndView modelAndView = new ModelAndView("payCycleView");
	// adminOtherDataService.addPayCycle(payCycleForm);
	// logger.debug("addPayCycle >>");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/showUpdatePayCycleForm/{payFrequencyTypeId}",
	// method = RequestMethod.GET)
	// public ModelAndView showUpdatePayCycleForm(@PathVariable Long
	// payFrequencyTypeId)
	// throws InstanceNotFoundException {
	// logger.debug(" >> showUpdatePayCycleForm");
	// ModelAndView modelAndView = new ModelAndView("payCycleView");
	// PayCycleForm payCycleForm =
	// adminOtherDataService.getUpdatePayCycleForm(payFrequencyTypeId);
	// modelAndView.addObject("payCycleForm", payCycleForm);
	// logger.debug("payCycleForm" + payCycleForm);
	// logger.debug("showUpdatePayCycleForm >>");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/updatePayCycle", method = RequestMethod.POST)
	// public ModelAndView updatePayCycle(@ModelAttribute PayCycleForm
	// payCycleForm)
	// throws InstanceNotFoundException {
	// logger.debug(" >> updatePayCycle");
	// ModelAndView modelAndView = new ModelAndView("payCycleView");
	// adminOtherDataService.updatePayCycle(payCycleForm);
	// logger.debug("updatePayCycle >>");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/deletePayCycle/{payFrequencyTypeId}", method =
	// RequestMethod.GET)
	// public ModelAndView deletePayCycle(@PathVariable Long payFrequencyTypeId)
	// throws InstanceNotFoundException {
	// logger.debug(" >> deletePayCycle");
	// ModelAndView modelAndView = new ModelAndView("payCycleView");
	// adminOtherDataService.verifyAnddeletePayCycle(payFrequencyTypeId);
	// logger.debug("deletePayCycle >>");
	// return modelAndView;
	// }

	@RequestMapping(value = "/showAllowanceTypeForm", method = RequestMethod.GET)
	public ModelAndView showAllowanceTypeForm() {
		logger.debug(" >> showAllowanceTypeForm");
		ModelAndView modelAndView = new ModelAndView("allowanceTypeView");
		modelAndView.addObject("allowanceTypeForm", new AllowanceTypeForm());
		modelAndView.addObject("allowanceTypes", adminOtherDataService.getAllowancetypes());
		logger.debug("showAllowanceTypeForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addAllowanceType", method = RequestMethod.POST)
	public ModelAndView addAllowanceType(@ModelAttribute AllowanceTypeForm allowanceTypeForm) {
		logger.debug(" >> addAllowanceType");
		ModelAndView modelAndView = new ModelAndView("allowanceTypeView");
		adminOtherDataService.addAllowanceType(allowanceTypeForm);
		logger.debug("addAllowanceType >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateAllowanceTypeForm/{allowanceTypeId}", method = RequestMethod.GET)
	public ModelAndView showUpdateAllowanceTypeForm(@PathVariable Long allowanceTypeId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateAllowanceTypeForm");
		ModelAndView modelAndView = new ModelAndView("allowanceTypeView");
		AllowanceTypeForm allowanceTypeForm = adminOtherDataService
				.getUpdateAllowanceTypeForm(allowanceTypeId);
		modelAndView.addObject("allowanceTypeForm", allowanceTypeForm);
		logger.debug("allowanceTypeForm" + allowanceTypeForm);
		logger.debug("showUpdateAllowanceTypeForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateAllowanceType", method = RequestMethod.POST)
	public ModelAndView updateAllowanceType(@ModelAttribute AllowanceTypeForm allowanceTypeForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateAllowanceType");
		ModelAndView modelAndView = new ModelAndView("allowanceTypeView");
		adminOtherDataService.updateAllowanceType(allowanceTypeForm);
		logger.debug("updateAllowanceType >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteAllowanceType/{allowanceTypeId}", method = RequestMethod.GET)
	public ModelAndView deleteAllowanceType(@PathVariable Long allowanceTypeId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteAllowanceType");
		ModelAndView modelAndView = new ModelAndView("allowanceTypeView");
		adminOtherDataService.verifyAnddeleteAllowanceType(allowanceTypeId);
		logger.debug("deleteAllowanceType >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showPayrollPlanForm", method = RequestMethod.GET)
	public ModelAndView showPayrollPlanForm() {
		logger.debug(" >> showPayrollPlanForm");
		ModelAndView modelAndView = new ModelAndView("payrollPlanView");
		modelAndView.addObject("payrollPlanForm", new PayrollPlanForm());
		modelAndView.addObject("payrollPlanForm", adminOtherDataService.getPayrollPlans());
		logger.debug("showPayrollPlanForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addPayrollPlan", method = RequestMethod.POST)
	public ModelAndView addPayrollPlan(@ModelAttribute PayrollPlanForm payrollPlanForm) {
		logger.debug(" >> addAllowanceType");
		ModelAndView modelAndView = new ModelAndView("payrollPlanView");
		adminOtherDataService.addPayrollPlan(payrollPlanForm);
		logger.debug("addAllowanceType >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdatePayrollPlanForm/{payrollPlanId}", method = RequestMethod.GET)
	public ModelAndView showUpdatePayrollPlanForm(@PathVariable Long payrollPlanId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdatePayrollPlanForm");
		ModelAndView modelAndView = new ModelAndView("payrollPlanView");
		PayrollPlanForm payrollPlanForm = adminOtherDataService.getPayrollPlanForm(payrollPlanId);
		modelAndView.addObject("payrollPlanForm", payrollPlanForm);
		logger.debug("payrollPlanForm" + payrollPlanForm);
		logger.debug("showUpdatePayrollPlanForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updatePayrollPlan", method = RequestMethod.POST)
	public ModelAndView updatePayrollPlan(@ModelAttribute PayrollPlanForm payrollPlanForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updatePayrollPlan");
		ModelAndView modelAndView = new ModelAndView("payrollPlanView");
		adminOtherDataService.updatePayrollPlan(payrollPlanForm);
		logger.debug("updatePayrollPlan >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deletePayrollPlan/{payrollPlanId}", method = RequestMethod.GET)
	public ModelAndView deletePayrollPlan(@PathVariable Long payrollPlanId)
			throws InstanceNotFoundException {
		logger.debug(" >> deletePayrollPlan");
		ModelAndView modelAndView = new ModelAndView("payrollPlanView");
		adminOtherDataService.verifyAnddeletePayrollPlan(payrollPlanId);
		logger.debug("deletePayrollPlan >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showPlanFeatureForm", method = RequestMethod.GET)
	public ModelAndView showPlanFeatureForm() {
		logger.debug(" >> showPlanFeatureForm");
		ModelAndView modelAndView = new ModelAndView("planFeatureView");
		modelAndView.addObject("planFeatureForm", new PlanFeatureForm());
		modelAndView.addObject("planFeatures", adminOtherDataService.getPlanFeatures());
		logger.debug("showPlanFeatureForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addPlanFeature", method = RequestMethod.POST)
	public ModelAndView addPlanFeature(@ModelAttribute PlanFeatureForm planFeatureForm) {
		logger.debug(" >> addPlanFeature");
		ModelAndView modelAndView = new ModelAndView("planFeatureView");
		try {
			adminOtherDataService.addPlanFeature(planFeatureForm);
			logger.debug("addPlanFeature >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdatePlanFeatureForm/{planFeatureId}", method = RequestMethod.GET)
	public ModelAndView showUpdatePlanFeatureForm(@PathVariable Long planFeatureId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdatePlanFeatureForm");
		ModelAndView modelAndView = new ModelAndView("planFeatureView");
		PlanFeatureForm planFeatureForm = adminOtherDataService.getPlanFeatureForm(planFeatureId);
		modelAndView.addObject("planFeatureForm", planFeatureForm);
		logger.debug("planFeatureForm" + planFeatureForm);
		logger.debug("showUpdatePlanFeatureForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updatePlanFeature", method = RequestMethod.POST)
	public ModelAndView updatePlanFeature(@ModelAttribute PlanFeatureForm planFeatureForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updatePlanFeature");
		ModelAndView modelAndView = new ModelAndView("planFeatureView");
		adminOtherDataService.updatePlanFeature(planFeatureForm);
		logger.debug("updatePlanFeature >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deletePlanFeature/{planFeatureId}", method = RequestMethod.GET)
	public ModelAndView deletePlanFeature(@PathVariable Long planFeatureId)
			throws InstanceNotFoundException {
		logger.debug(" >> deletePlanFeature");
		ModelAndView modelAndView = new ModelAndView("planFeatureView");
		adminOtherDataService.verifyAnddeletePlanFeature(planFeatureId);
		logger.debug("deletePlanFeature >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showFundCategoryForm", method = RequestMethod.GET)
	public ModelAndView showFundCategoryForm() {
		logger.debug(" >> showFundCategoryForm");
		ModelAndView modelAndView = new ModelAndView("fundCategoryView");
		modelAndView.addObject("fundCategoryForm", new FundCategoryForm());
		modelAndView.addObject("fundCategories", adminOtherDataService.getFundCategories());
		logger.debug("showFundCategoryForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addFundCategory", method = RequestMethod.POST)
	public ModelAndView addFundCategory(@ModelAttribute FundCategoryForm fundCategoryForm) {
		logger.debug(" >> addFundCategory");
		ModelAndView modelAndView = new ModelAndView("fundCategoryView");
		try {
			adminOtherDataService.addFundCategory(fundCategoryForm);
			logger.debug("addFundCategory >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateFundCategoryForm/{fundCategoryId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFundCategoryForm(@PathVariable Long fundCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateFundCategoryForm");
		ModelAndView modelAndView = new ModelAndView("fundCategoryView");
		FundCategoryForm fundCategoryForm = adminOtherDataService
				.getFundCategoryForm(fundCategoryId);
		modelAndView.addObject("fundCategoryForm", fundCategoryForm);
		logger.debug("fundCategoryForm" + fundCategoryForm);
		logger.debug("showUpdateFundCategoryForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateFundCategory", method = RequestMethod.POST)
	public ModelAndView updateFundCategory(@ModelAttribute FundCategoryForm fundCategoryForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateFundCategory");
		ModelAndView modelAndView = new ModelAndView("fundCategoryView");
		adminOtherDataService.updateFundCategory(fundCategoryForm);
		logger.debug("updateFundCategory >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteFundCategory/{fundCategoryId}", method = RequestMethod.GET)
	public ModelAndView deleteFundCategory(@PathVariable Long fundCategoryId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteFundCategory");
		ModelAndView modelAndView = new ModelAndView("fundCategoryView");
		adminOtherDataService.verifyAnddeleteFundCategory(fundCategoryId);
		logger.debug("deleteFundCategory >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showPaymentModeForm", method = RequestMethod.GET)
	public ModelAndView showPaymentModeForm() {
		logger.debug(" >> showPaymentModeForm");
		ModelAndView modelAndView = new ModelAndView("paymentModeView");
		modelAndView.addObject("paymentModeForm", new PaymentModeForm());
		modelAndView.addObject("paymentModes", adminOtherDataService.getPaymentModes());
		logger.debug("showPaymentModeForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addPaymentMode", method = RequestMethod.POST)
	public ModelAndView addPaymentMode(@ModelAttribute PaymentModeForm paymentModeForm) {
		logger.debug(" >> addPaymentMode");
		ModelAndView modelAndView = new ModelAndView("paymentModeView");
		try {
			adminOtherDataService.addPaymentMode(paymentModeForm);
			logger.debug("addPaymentMode >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdatePaymentModeForm/{paymentModeId}", method = RequestMethod.GET)
	public ModelAndView showUpdatePaymentModeForm(@PathVariable Long paymentModeId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdatePaymentModeForm");
		ModelAndView modelAndView = new ModelAndView("paymentModeView");
		PaymentModeForm paymentModeForm = adminOtherDataService.getPaymentModeForm(paymentModeId);
		modelAndView.addObject("paymentModeForm", paymentModeForm);
		logger.debug("paymentModeForm" + paymentModeForm);
		logger.debug("showUpdatePaymentModeForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updatePaymentMode", method = RequestMethod.POST)
	public ModelAndView updatePaymentMode(@ModelAttribute PaymentModeForm paymentModeForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updatePaymentMode");
		ModelAndView modelAndView = new ModelAndView("paymentModeView");
		adminOtherDataService.updatePaymentMode(paymentModeForm);
		logger.debug("updatePaymentMode >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deletePaymentMode/{paymentModeId}", method = RequestMethod.GET)
	public ModelAndView deletePaymentMode(@PathVariable Long paymentModeId)
			throws InstanceNotFoundException {
		logger.debug(" >> deletePaymentMode");
		ModelAndView modelAndView = new ModelAndView("paymentModeView");
		adminOtherDataService.verifyAnddeletePaymentMode(paymentModeId);
		logger.debug("deletePaymentMode >>");
		return modelAndView;
	}

	@RequestMapping(value = "/showTransactionBodyForm", method = RequestMethod.GET)
	public ModelAndView showTransactionBodyForm() {
		logger.debug(" >> showTransactionBodyForm");
		ModelAndView modelAndView = new ModelAndView("transactionBodyView");
		modelAndView.addObject("transactionBodyForm", new TransactionBodyForm());
		modelAndView.addObject("transactionBody", adminOtherDataService.getTransactionBodyList());
		logger.debug("showTransactionBodyForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/addTransactionBody", method = RequestMethod.POST)
	public ModelAndView addTransactionBody(@ModelAttribute TransactionBodyForm transactionBodyForm) {
		logger.debug(" >> addTransactionBody");
		ModelAndView modelAndView = new ModelAndView("transactionBodyView");
		try {
			adminOtherDataService.addTransactionBody(transactionBodyForm);
			logger.debug("addTransactionBody >>");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showUpdateTransactionBodyForm/{transactionBodyId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTransactionBodyForm(@PathVariable Long transactionBodyId)
			throws InstanceNotFoundException {
		logger.debug(" >> showUpdateTransactionBodyForm");
		ModelAndView modelAndView = new ModelAndView("transactionBodyView");
		TransactionBodyForm transactionBodyForm = adminOtherDataService
				.getTransactionBodyForm(transactionBodyId);
		modelAndView.addObject("transactionBodyForm", transactionBodyForm);
		logger.debug("transactionBodyForm" + transactionBodyForm);
		logger.debug("showUpdateTransactionBodyForm >>");
		return modelAndView;
	}

	@RequestMapping(value = "/updateTransactionBody", method = RequestMethod.POST)
	public ModelAndView updateTransactionBody(
			@ModelAttribute TransactionBodyForm transactionBodyForm)
			throws InstanceNotFoundException {
		logger.debug(" >> updateTransactionBody");
		ModelAndView modelAndView = new ModelAndView("transactionBodyView");
		adminOtherDataService.updateTransactionBody(transactionBodyForm);
		logger.debug("updateTransactionBody >>");
		return modelAndView;
	}

	@RequestMapping(value = "/deleteTransactionBody/{transactionBodyId}", method = RequestMethod.GET)
	public ModelAndView deleteTransactionBody(@PathVariable Long transactionBodyId)
			throws InstanceNotFoundException {
		logger.debug(" >> deleteTransactionBody");
		ModelAndView modelAndView = new ModelAndView("transactionBodyView");
		adminOtherDataService.verifyAnddeletePaymentMode(transactionBodyId);
		logger.debug("deleteTransactionBody >>");
		return modelAndView;
	}

}
