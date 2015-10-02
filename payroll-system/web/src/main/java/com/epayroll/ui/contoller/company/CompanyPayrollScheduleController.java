package com.epayroll.ui.contoller.company;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.Company;
import com.epayroll.entity.PayrollFrequency;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyPayrollFrequencyForm;
import com.epayroll.model.PayrollScheduleModel;
import com.epayroll.model.ServerResponse;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.ui.contoller.helper.CompanyPayrollScheduleControllerHelper;
import com.epayroll.utils.DateUtil;

/**
 * @author Uma
 */
@Controller
@RequestMapping("/company")
public class CompanyPayrollScheduleController {
	private Logger logger = LoggerFactory.getLogger(CompanyPayrollScheduleController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CompanyPayrollScheduleControllerHelper companyPayrollScheduleControllerHelper;

	// private Company getCompanyFromSession(HttpSession session) {
	// User user = (User) session.getAttribute("loggedInUser");
	// Company company = new Company();
	// if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty())
	// {
	// Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
	// if (iterator.hasNext()) {
	// company = iterator.next().getCompany();
	// }
	// }
	// return company;
	// // return AuthorizationUtil.getLoginCompany();
	// }
	private Company getCompanyFromSession() {
		Company company = commonService.getCompany(AuthorizationUtil.getLoginUser().getId());
		return company;
	}

	@RequestMapping(value = "/showPayrollFrequencyList", method = RequestMethod.GET)
	public ModelAndView showPayrollFrequencyList(HttpSession session)
			throws InstanceNotFoundException {
		logger.debug(">>Show Payroll Frequency List");
		ModelAndView modelView = new ModelAndView("companyPayrollSchedule");
		Company company = getCompanyFromSession();
		modelView.addObject("PayrollFrequencyList",
				companyService.getPayrollFrequencies(company.getId()));
		List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
				.getId());
		modelView.addObject("payFrequencyTypeList", payFrequencyTypes);
		modelView.addObject("holidayCheckDateOptionList",
				companyService.getholidayCheckDateOption());
		modelView.addObject("payrollFrequency", new PayrollFrequency());
		modelView.addObject("companyPayrollFrequencyForm", new CompanyPayrollFrequencyForm());
		logger.debug("Show PayrollFrequency List>>");
		return modelView;
	}

	@RequestMapping(value = "/showPayrollFrequencyNormalList", method = RequestMethod.GET)
	public ModelAndView showPayrollFrequencyNormalList(HttpSession session)
			throws InstanceNotFoundException {
		logger.debug(">>Show Payroll Frequency List");
		ModelAndView modelView = new ModelAndView("companyPayrollScheduleNormal");
		Company company = getCompanyFromSession();
		modelView.addObject("PayrollFrequencyList",
				companyService.getPayrollFrequencies(company.getId()));
		List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
				.getId());
		modelView.addObject("payFrequencyTypeList", payFrequencyTypes);
		modelView.addObject("holidayCheckDateOptionList",
				companyService.getholidayCheckDateOption());
		modelView.addObject("payrollFrequency", new PayrollFrequency());
		modelView.addObject("companyPayrollFrequencyForm", new CompanyPayrollFrequencyForm());
		logger.debug("Show PayrollFrequency List>>");
		return modelView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_PAYROLL_FREQUENCY, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addPayrollFrequency", method = RequestMethod.POST)
	public ModelAndView addPayrollFrequency(
			@ModelAttribute @Valid PayrollFrequency payrollFrequency, BindingResult result,
			HttpSession session) throws InstanceNotFoundException {
		ModelAndView modelView = new ModelAndView("companyPayrollSchedule");
		modelView.addObject("companyPayrollFrequencyForm", new CompanyPayrollFrequencyForm());
		logger.debug(">>Add Payroll Frequency");
		logger.debug("payrollFrequency::" + payrollFrequency);
		logger.debug("result.hasErrors()..." + result.hasErrors());
		if (!result.hasErrors()) {
			Company company = getCompanyFromSession();
			Long id = companyService.addPayrollFrequency(company, payrollFrequency);
			if (id != 0) {
				modelView.addObject("success", "data inserted Sucessfully");
			} else {
				modelView.addObject("error", "error in insertion");
			}
			// List<PayrollSchedule> payrollSchedules =
			// companyService.getPayrollSchedules(id);
			// modelView.addObject("payrollScheduleList", payrollSchedules);
			modelView.addObject("PayrollFrequencyList",
					companyService.getPayrollFrequencies(company.getId()));
			modelView.addObject("holidayCheckDateOptionList",
					companyService.getholidayCheckDateOption());
			List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
					.getId());
			modelView.addObject("payFrequencyTypeList", payFrequencyTypes);
			modelView.addObject("payFrequencyTypeName", payrollFrequency.getPayFrequencyType());
			modelView.addObject("payrollFrequency", payrollFrequency);
		}
		logger.debug("Add Payroll Frequency>>");
		return modelView;
	}

	@RequestMapping(value = "/addPayrollFrequencyNormal", method = RequestMethod.POST)
	public ModelAndView addPayrollFrequencyNormal(
			@ModelAttribute @Valid PayrollFrequency payrollFrequency, BindingResult result,
			HttpSession session) throws InstanceNotFoundException {
		ModelAndView modelView = new ModelAndView("companyPayrollScheduleNormal");
		logger.debug(">>Add Payroll Frequency");
		logger.debug("payrollFrequency::" + payrollFrequency);
		logger.debug("result.hasErrors()..." + result.hasErrors());
		if (!result.hasErrors()) {
			Company company = getCompanyFromSession();
			Long id = companyService.addPayrollFrequency(company, payrollFrequency);
			if (id != 0) {
				modelView.addObject("success", "data inserted Sucessfully");
			} else {
				modelView.addObject("error", "error in insertion");
			}
			List<PayrollSchedule> payrollSchedules = companyService.getPayrollSchedules(id);
			modelView.addObject("payrollScheduleList", payrollSchedules);
			modelView.addObject("PayrollFrequencyList",
					companyService.getPayrollFrequencies(company.getId()));
			modelView.addObject("holidayCheckDateOptionList",
					companyService.getholidayCheckDateOption());
			List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
					.getId());
			modelView.addObject("payFrequencyTypeList", payFrequencyTypes);
			modelView.addObject("payFrequencyTypeName", payrollFrequency.getPayFrequencyType());
			modelView.addObject("frequencyTypeId", payrollFrequency.getPayFrequencyType());
			modelView.addObject("companyPayrollFrequencyForm", companyService
					.getCompanyPayrollFrequencyForm(companyService.getPayrollFrequency(id)));
		}

		modelView.addObject("payrollFrequency", payrollFrequency);
		logger.debug("Add Payroll Frequency>>");
		return modelView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_PAYROLL_FREQUENCY, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showPayrollFrequencyForm", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse showPayrollFrequencyForm(@RequestParam Long payrollFrequencyId,
			HttpSession session) throws InstanceNotFoundException {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">>Show Payroll Frequency Form");
		PayrollFrequency payrollFrequency = companyService.getPayrollFrequency(payrollFrequencyId);
		logger.debug("payrollfrequency..." + payrollFrequency);
		PayrollScheduleModel payrollScheduleModel = new PayrollScheduleModel();
		payrollScheduleModel.setPayrollFrequency(payrollFrequency);
		payrollScheduleModel.setPayrollSchedules(companyService
				.getPayrollSchedules(payrollFrequencyId));
		payrollScheduleModel.setHolidayOptions(companyService.getholidayCheckDateOption());
		serverResponse.setData(payrollScheduleModel);
		logger.debug("Show Payroll Frequency Form>>");
		return serverResponse;
	}

	@RequestMapping(value = "/showPayrollFrequencyFormNormal", method = RequestMethod.GET)
	public ModelAndView showPayrollFrequencyFormNormal(@RequestParam Long payrollFrequencyId,
			HttpSession session) throws InstanceNotFoundException {
		ModelAndView modelAndView = new ModelAndView("companyPayrollScheduleNormal");
		logger.debug(">>Show Payroll Frequency Form Normal");
		PayrollFrequency payrollFrequency = companyService.getPayrollFrequency(payrollFrequencyId);
		logger.debug("payrollfrequency..." + payrollFrequency);
		CompanyPayrollFrequencyForm companyPayrollFrequencyForm = companyService
				.getCompanyPayrollFrequencyForm(payrollFrequency);
		List<PayrollSchedule> payrollSchedules = companyService
				.getPayrollSchedules(payrollFrequencyId);
		modelAndView.addObject("payrollScheduleList", payrollSchedules);
		Company company = getCompanyFromSession();
		modelAndView.addObject("PayrollFrequencyList",
				companyService.getPayrollFrequencies(company.getId()));
		modelAndView.addObject("holidayCheckDateOptionList",
				companyService.getholidayCheckDateOption());
		List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
				.getId());
		modelAndView.addObject("payFrequencyTypeList", payFrequencyTypes);
		modelAndView.addObject("companyPayrollFrequencyForm", companyPayrollFrequencyForm);
		modelAndView.addObject("payFrequencyTypeName", payrollFrequency.getPayFrequencyType());
		modelAndView.addObject("frequencyTypeId", payrollFrequency.getPayFrequencyType());
		modelAndView.addObject("payrollFrequency", payrollFrequency);
		logger.debug("Show Payroll Frequency Form Normal>>");
		return modelAndView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_PAYROLL_FREQUENCY, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updatePayrollFrequency", method = RequestMethod.POST)
	public ModelAndView updatePayrollFrequency(
			@Valid CompanyPayrollFrequencyForm companyPayrollFrequencyForm, BindingResult result,
			HttpSession session, HttpServletRequest request) throws InstanceNotFoundException {
		logger.debug(">>Update Payroll Frequency");
		logger.debug("payrollFrequency::" + companyPayrollFrequencyForm);
		logger.debug("result:::" + result);
		ModelAndView modelAndView = new ModelAndView(new RedirectView(request.getContextPath()
				+ "/company/showPayrollFrequencyList"));
		PayrollFrequency payrollFrequency = null;
		if (!result.hasErrors()) {
			payrollFrequency = companyService.updatePayrollFrequency(companyPayrollFrequencyForm);
			modelAndView.addObject("payrollFrequency", payrollFrequency);
		}
		logger.debug("Update Payroll Frequency>>");
		return modelAndView;
	}

	@RequestMapping(value = "/updatePayrollFrequencyNormal", method = RequestMethod.POST)
	public ModelAndView updatePayrollFrequencyNormal(
			@Valid CompanyPayrollFrequencyForm companyPayrollFrequencyForm, BindingResult result,
			HttpSession session, HttpServletRequest request) throws InstanceNotFoundException {
		logger.debug(">>Update Payroll Frequency");
		logger.debug("payrollFrequency::" + companyPayrollFrequencyForm);
		logger.debug("result:::" + result);
		ModelAndView modelAndView = new ModelAndView("companyPayrollScheduleNormal");
		Company company = getCompanyFromSession();
		if (!result.hasErrors()) {
			PayrollFrequency payrollFrequency = companyService
					.updatePayrollFrequency(companyPayrollFrequencyForm);
			List<PayrollSchedule> payrollSchedules = companyService
					.getPayrollSchedules(companyPayrollFrequencyForm.getId());
			modelAndView.addObject("payrollScheduleList", payrollSchedules);

			modelAndView.addObject("PayrollFrequencyList",
					companyService.getPayrollFrequencies(company.getId()));
			modelAndView.addObject("holidayCheckDateOptionList",
					companyService.getholidayCheckDateOption());
			List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
					.getId());
			modelAndView.addObject("payFrequencyTypes", payFrequencyTypes);
			modelAndView.addObject("payFrequencyTypeName", payrollFrequency.getPayFrequencyType());

		}
		modelAndView.addObject("payrollFrequency", new PayrollFrequency());
		modelAndView.addObject("companyPayrollFrequencyForm", companyPayrollFrequencyForm);
		logger.debug("Update Payroll Frequency>>");
		return modelAndView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_PAYROLL_FREQUENCY, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deletePayRollFrequency", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse deletePayRollFrequency(@RequestParam Long payrollFrequencyId,
			HttpServletRequest request) {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">>delete PayRoll Frequency");
		try {
			if (companyService.deletePayrollFrequency(payrollFrequencyId)) {
				serverResponse.setMessage("Record Deleted Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);

			} else {
				serverResponse
						.setMessage("Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.");
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Delete PayRoll Frequency>>");
		return serverResponse;
	}

	@RequestMapping(value = "/deletePayRollFrequencyNormal", method = RequestMethod.GET)
	public ModelAndView deletePayRollFrequencyNormal(@RequestParam Long payrollFrequencyId,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("companyPayrollScheduleNormal");
		logger.debug(">>delete PayRoll Frequency");
		try {
			if (companyService.deletePayrollFrequency(payrollFrequencyId)) {
				modelAndView.addObject("message", ServerStatus.SUCCESS);
			} else {
				modelAndView
						.addObject(
								"message",
								"Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.");
			}
			Company company = getCompanyFromSession();
			modelAndView.addObject("PayrollFrequencyList",
					companyService.getPayrollFrequencies(company.getId()));
			List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
					.getId());
			modelAndView.addObject("payFrequencyTypeList", payFrequencyTypes);
			modelAndView.addObject("holidayCheckDateOptionList",
					companyService.getholidayCheckDateOption());
			modelAndView.addObject("payrollFrequency", new PayrollFrequency());
			modelAndView
					.addObject("companyPayrollFrequencyForm", new CompanyPayrollFrequencyForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Delete PayRoll Frequency>>");
		return modelAndView;
	}

	// //
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_PAYROLL_SCHEDULE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deletePayRollschedule", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse deletePayRollschedule(@RequestParam Long payrollScheduleId) {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">>Delete PayRollschedule");
		try {
			PayrollSchedule payrollSchedule = companyService
					.deletePayrollSchedule(payrollScheduleId);
			if (payrollSchedule != null) {
				serverResponse.setMessage("Record Deleted Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setData(payrollSchedule);
			} else {
				serverResponse
						.setMessage("Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.");
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Delete PayRollschedule>>");
		return serverResponse;
	}

	@RequestMapping(value = "/restore", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse restore(@RequestParam Long payrollScheduleId) {
		ServerResponse serverResponse = new ServerResponse();
		logger.debug(">>Delete PayRollschedule");
		try {
			PayrollSchedule payrollSchedule = companyService
					.restorePayrollSchedule(payrollScheduleId);
			if (payrollSchedule != null) {
				serverResponse.setMessage("Record Deleted Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setData(payrollSchedule);
			} else {
				serverResponse
						.setMessage("Payroll Records are associated with this Deduction. So you cannot delete this Deduction from system.");
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Delete PayRollschedule>>");
		return serverResponse;
	}

	@RequestMapping(value = "/getEndDate", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse getEndDate(@RequestParam Date periodStartDate,
			@RequestParam PayFrequencyType payFrequencyType) {
		ServerResponse serverResponse = new ServerResponse();
		try {
			Date endDate = companyService.getEndDate(periodStartDate, payFrequencyType);
			String stringEndDate = DateUtil.getStringFromDate(endDate, "MM/dd/yyyy");
			serverResponse.setData(stringEndDate);
			logger.debug("stringEndDate::" + stringEndDate);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}
		return serverResponse;
	}

	@RequestMapping(value = "/viewPreviousPayrollScheduleList", method = RequestMethod.GET)
	public ModelAndView viewPreviousPayrollScheduleList(@RequestParam Integer page,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("companyPayrollScheduleNormal");
		logger.debug(">>viewPreviousPayrollScheduleList");
		try {
			Company company = getCompanyFromSession();
			// List<PayrollSchedule> payrollSchedules =
			// companyService.getPayrollScheduleList(company
			// .getId());
			modelAndView.addObject("payrollFrequency", new PayrollFrequency());
			// modelAndView.addObject("previousPayrollScheduleList",
			// payrollSchedules);
			modelAndView.addObject("previousPayrollScheduleList",
					companyPayrollScheduleControllerHelper.getPagedUserView(page, companyService,
							company.getId(), session));
			modelAndView.addObject("PayrollFrequencyList",
					companyService.getPayrollFrequencies(company.getId()));
			List<PayFrequencyType> payFrequencyTypes = companyService.getPayFrequencyType(company
					.getId());
			modelAndView.addObject("payFrequencyTypeList", payFrequencyTypes);
			modelAndView.addObject("holidayCheckDateOptionList",
					companyService.getholidayCheckDateOption());
			modelAndView
					.addObject("companyPayrollFrequencyForm", new CompanyPayrollFrequencyForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Delete PayRollschedule>>");
		return modelAndView;
	}

	@RequestMapping(value = "/getPreviousPayrollScheduleList", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse getPreviousPayrollScheduleList(@RequestParam String perioStartDate,
			@RequestParam String periodEndDate, HttpSession session) {
		ServerResponse serverResponse = new ServerResponse();
		// ModelAndView modelAndView = new
		// ModelAndView("companyPayrollScheduleNormal");
		logger.debug(">>getPreviousPayrollScheduleList");
		try {
			Company company = getCompanyFromSession();
			// modelAndView.addObject("payrollFrequency", new
			// PayrollFrequency());
			Date startDate = DateUtil.getDateFromString(perioStartDate, "yyyy-MM-dd");
			Date endDate = DateUtil.getDateFromString(periodEndDate, "yyyy-MM-dd");
			List<PayrollSchedule> payrollSchedules = companyService.getPreviousPayrollScheduleList(
					startDate, endDate, company.getId());
			serverResponse.setData(payrollSchedules);
			// modelAndView.addObject("scheduleList", payrollSchedules);
			// List<PayrollSchedule> payrollScheduleList = companyService
			// .getPayrollScheduleList(company.getId());
			// modelAndView.addObject("previousPayrollScheduleList",
			// companyPayrollScheduleControllerHelper.getPagedUserView(page,
			// companyService,
			// company.getId(), session));
			// modelAndView.addObject("PayrollFrequencyList",
			// companyService.getPayrollFrequencies(company.getId()));
			// modelAndView
			// .addObject("companyPayrollFrequencyForm", new
			// CompanyPayrollFrequencyForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("getPreviousPayrollScheduleList>>");
		return serverResponse;
	}
}