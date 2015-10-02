package com.epayroll.ui.contoller.employee;

import java.util.Iterator;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Access;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmploymentHistory;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorEmploymentInfoForm;
import com.epayroll.form.employee.ContractorPersonalInfoForm;
import com.epayroll.form.employee.ContractorSearchForm;
import com.epayroll.form.employee.EmployeeAccessForm;
import com.epayroll.form.employee.EmployeeEmploymentInfoForm;
import com.epayroll.form.employee.EmployeePersonalInfoForm;
import com.epayroll.form.employee.EmployeeSearchForm;
import com.epayroll.form.employee.RehireForm;
import com.epayroll.form.employee.TerminateForm;
import com.epayroll.service.MailService;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.ui.contoller.helper.EmployeeControllerHelper;

/**
 * @author Rajul Tiwari
 */
// @RequestMapping("/employee")
@Controller
public class EmployeeInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeeInfoController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeControllerHelper employeeControllerHelper;

	@Autowired
	private MailService mailService;

	private Company getCompanyFromSession() {
		logger.debug(">> getCompanyFromSession");
		User user = (User) AuthorizationUtil.getSessionAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("getCompanyFromSession >>");
		return company;
	}

	private Employee getEmployeeFromSession() {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = (Employee) AuthorizationUtil.getSessionAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	private Company getCompanyFromSession(HttpSession httpSession) {
		logger.debug(">> getCompanyFromSession");
		User user = (User) httpSession.getAttribute("loggedInUser");
		Company company = new Company();
		if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty()) {
			Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
			if (iterator.hasNext()) {
				company = iterator.next().getCompany();
			}
		}
		logger.debug("getCompanyFromSession >>");
		return company;
	}

	private User getCompanyUserFromSession(HttpSession httpSession) {
		logger.debug(">> getCompanyUserFromSession");
		User user = (User) httpSession.getAttribute("loggedInUser");
		logger.debug("getCompanyUserFromSession >>");
		return user;
	}

	// for employee/contractor
	// TODO EMPLOYEE_LIST Or CONTRACTOR_LIST
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public ModelAndView showList() {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		modelView.addObject("employeeSearchForm", new EmployeeSearchForm());
		modelView.addObject("constractorSearchForm", new ContractorSearchForm());
		// AuthorizationUtil for session.............
		List<UsState> usStateList = employeeService.getUsStates();
		AuthorizationUtil.setSessionAttribute("usStateList", usStateList);
		List<String> usCityList = employeeService.getUsCities();
		AuthorizationUtil.setSessionAttribute("usCityList", usCityList);
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		modelView.addObject("accessToEmployeeList", employeeControllerHelper.getAccessToEmployee());
		modelView.addObject("employeeList",
				employeeService.getEmployeeList(getCompanyFromSession().getId(), Type.EMPLOYEE));
		modelView.addObject("contractorList",
				employeeService.getEmployeeList(getCompanyFromSession().getId(), Type.CONTRACTOR));
		logger.debug("showList >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/employeeSearch", method = RequestMethod.POST)
	public ModelAndView employeeSearch(@ModelAttribute EmployeeSearchForm employeeSearchForm) {
		logger.debug(">> employeeSearch");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		modelView.addObject("usStateList", AuthorizationUtil.getSessionAttribute("usStateList"));
		modelView.addObject("usCityList", AuthorizationUtil.getSessionAttribute("usCityList"));
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		modelView.addObject("accessToEmployeeList", employeeControllerHelper.getAccessToEmployee());
		modelView.addObject("contractorList",
				employeeService.getEmployeeList(getCompanyFromSession().getId(), Type.CONTRACTOR));
		try {
			List<Object[]> employeeSearchFormsList = employeeService.getSearchedEmployee(
					employeeSearchForm, getCompanyFromSession().getId());
			modelView.addObject("employeeList", employeeSearchFormsList);
			modelView.addObject("employeeSearchForm", employeeSearchForm);
		} catch (Exception e) {
			e.printStackTrace();
			modelView
					.addObject("employeeList", employeeService.getEmployeeList(
							getCompanyFromSession().getId(), Type.EMPLOYEE));
			logger.error("ERROR::" + e.getMessage());
		}
		modelView.addObject("constractorSearchForm", new ContractorSearchForm());
		logger.debug("employeeSearch >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_LIST, "
	// + "T(com.epayroll.constant.PermissionType).VIEW)")
	@RequestMapping(value = "/contractorSearch", method = RequestMethod.POST)
	public ModelAndView contractorSearch(@ModelAttribute ContractorSearchForm constractorSearchForm) {
		logger.debug(">> contractorSearch");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		modelView.addObject("usStateList", AuthorizationUtil.getSessionAttribute("usStateList"));
		modelView.addObject("usCityList", AuthorizationUtil.getSessionAttribute("usCityList"));
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		modelView.addObject("accessToEmployeeList", employeeControllerHelper.getAccessToEmployee());
		modelView.addObject("employeeList",
				employeeService.getEmployeeList(getCompanyFromSession().getId(), Type.EMPLOYEE));
		try {
			List<Object[]> contractorSearchFormsList = employeeService.getSearchedContractor(
					constractorSearchForm, getCompanyFromSession().getId());
			modelView.addObject("contractorList", contractorSearchFormsList);
			modelView.addObject("constractorSearchForm", constractorSearchForm);
		} catch (Exception e) {
			e.printStackTrace();
			modelView.addObject("contractorList", employeeService.getEmployeeList(
					getCompanyFromSession().getId(), Type.CONTRACTOR));
			logger.error("ERROR::" + e.getMessage());
		}
		modelView.addObject("employeeSearchForm", new EmployeeSearchForm());
		logger.debug("contractorSearch >>");
		return modelView;
	}

	// EMPLOYEE_LIST Or CONTRACTOR_LIST
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showTerminateForm/{employeeId}", method = RequestMethod.GET)
	public ModelAndView showTerminateForm(@PathVariable Long employeeId) {
		logger.debug(">> showTerminateForm");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		modelView.addObject("terminateForm", new TerminateForm());
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			AuthorizationUtil.setSessionAttribute("employee", employee);
			modelView.setViewName("terminateForm");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("showTerminateForm >>");
		return modelView;
	}

	// for terminate employee/contractor
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/terminate", method = RequestMethod.POST)
	public ModelAndView terminate(@Valid @ModelAttribute TerminateForm terminateForm,
			BindingResult bindingResult) {
		logger.debug(">> terminate");
		ModelAndView modelView = new ModelAndView("terminateForm");
		if (!bindingResult.hasErrors()) {
			try {
				Employee employee = getEmployeeFromSession();
				employeeService.updateStatusTerminate(terminateForm, employee);
				modelView.setViewName("employeeListPage");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("terminate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showRehireForm/{employeeId}", method = RequestMethod.GET)
	public ModelAndView showRehireForm(@PathVariable Long employeeId) {
		logger.debug(">> showRehireForm");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		modelView.addObject("rehireForm", new RehireForm());
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			AuthorizationUtil.setSessionAttribute("employee", employee);
			modelView.setViewName("rehireForm");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("showRehireForm >>");
		return modelView;
	}

	// for rehire employee/contractor
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_LIST, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/rehire", method = RequestMethod.POST)
	public ModelAndView rehire(@Valid @ModelAttribute RehireForm rehireForm,
			BindingResult bindingResult) {
		logger.debug(">> rehire");
		ModelAndView modelView = new ModelAndView("terminateForm");
		if (!bindingResult.hasErrors()) {
			try {
				Employee employee = getEmployeeFromSession();
				employeeService.updateStatusRehire(rehireForm, employee);
				modelView.setViewName("employeeListPage");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("rehire >>");
		return modelView;
	}

	@RequestMapping(value = "/showHomePage/{employeeId}", method = RequestMethod.GET)
	public ModelAndView showHomePage(@PathVariable Long employeeId) {
		logger.debug(">> showHomePage");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			AuthorizationUtil.setSessionAttribute("employee", employee);
			if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
				modelView.setViewName("employeeHomePage");
			} else if (employee.getEmployeeType().equals(Type.CONTRACTOR)) {
				modelView.setViewName("contractorHomePage");
			}
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("showHomePage >>");
		return modelView;
	}

	// common for both section EMPLOYEE and CONTRACTOR (EMPLOYEE_PERSONAL_INFO
	// and
	// EMPLOYEE_EMPLOYMENT_INFO CONTRACTOR_PERSONAL_INFO and

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEmployeeInfoForm/{employeeId}", method = RequestMethod.GET)
	public ModelAndView updateEmployeeInfoForm(@PathVariable Long employeeId) {
		logger.debug(">> updateEmployeeInfoForm");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		try {
			Employee employee = employeeService.getEmployee(employeeId);
			AuthorizationUtil.setSessionAttribute("employee", employee);

			modelView.addObject("usStateList", employeeService.getUsStateList());
			modelView.addObject("usCityList", employeeService.getUsCityList());
			modelView.addObject("usCountyList", employeeService.getUsCountyList());
			modelView.addObject("genderList", employeeControllerHelper.getGenderList());
			modelView.addObject("statusList", employeeControllerHelper.getStatus());
			modelView.addObject("workCompCodeList",
					employeeService.getcompanyWorkerCompensationList());

			if (employee.getEmployeeType().equals(Type.EMPLOYEE)) {
				EmployeePersonalInfoForm employeePersonalInfoForm = employeeService
						.getEmployeePersonalInfoForm(employee);
				modelView.addObject("employeePersonalInfoForm", employeePersonalInfoForm);
				EmployeeEmploymentInfoForm employeeEmploymentInfoForm = employeeService
						.getEmployeeEmploymentInfoForm(employee);
				modelView.addObject("employeeEmploymentInfoForm", employeeEmploymentInfoForm);
				modelView.setViewName("employeeInfoForm");
			} else if (employee.getEmployeeType().equals(Type.CONTRACTOR)) {
				ContractorPersonalInfoForm contractorPersonalInfoForm = employeeService
						.getContractorPersonalInfoForm(employee);
				modelView.addObject("contractorPersonalInfoForm", contractorPersonalInfoForm);
				ContractorEmploymentInfoForm contractorEmploymentInfoForm = employeeService
						.getContractorEmploymentInfoForm(employee);
				modelView.addObject("contractorEmploymentInfoForm", contractorEmploymentInfoForm);
				modelView.setViewName("contractorInfoForm");
			}
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("updateEmployeeInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEmployeePersonalInfo", method = RequestMethod.POST)
	public ModelAndView updateEmployeePersonalInfo(
			@Valid @ModelAttribute EmployeePersonalInfoForm employeePersonalInfoForm,
			BindingResult bindingResult) {
		logger.debug(">> updateEmployeePersonalInfo");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		Employee employee = getEmployeeFromSession();
		EmployeeEmploymentInfoForm employeeEmploymentInfoForm = employeeService
				.getEmployeeEmploymentInfoForm(employee);
		modelView.addObject("employeeEmploymentInfoForm", employeeEmploymentInfoForm);
		if (!bindingResult.hasErrors()) {
			try {
				employeeService.updateEmployeePersonalInfo(employeePersonalInfoForm, employee);
				modelView.addObject("employeePersonalInfoForm", employeePersonalInfoForm);
			} catch (Exception e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}

		logger.debug("updateEmployeePersonalInfo >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateEmployeeEmploymentInfo", method = RequestMethod.POST)
	public ModelAndView updateEmployeeEmploymentInfo(
			@Valid @ModelAttribute EmployeeEmploymentInfoForm employeeEmploymentInfoForm,
			BindingResult bindingResult) {
		logger.debug(">> updateEmployeeEmploymentInfo");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		Employee employee = getEmployeeFromSession();
		EmployeePersonalInfoForm employeePersonalInfoForm = employeeService
				.getEmployeePersonalInfoForm(employee);
		modelView.addObject("employeePersonalInfoForm", employeePersonalInfoForm);
		if (!bindingResult.hasErrors()) {
			try {
				employeeService.updateEmployeeEmploymentInfo(employeeEmploymentInfoForm, employee);
				modelView.addObject("employeeEmploymentInfoForm", employeeEmploymentInfoForm);
			} catch (Exception e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}

		logger.debug("updateEmployeeEmploymentInfo >>");
		return modelView;
	}

	// common for both section CONTRACTOR_PERSONAL_INFO and
	// CONTRACTOR_EMPLOYMENT_INFO

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateContractorPersonalInfo", method = RequestMethod.POST)
	public ModelAndView updateContractorPersonalInfo(
			@Valid @ModelAttribute ContractorPersonalInfoForm contractorPersonalInfoForm,
			BindingResult bindingResult) {
		logger.debug(">> updateContractorPersonalInfo");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("statusList", employeeControllerHelper.getStatus());
		Employee employee = getEmployeeFromSession();
		ContractorEmploymentInfoForm contractorEmploymentInfoForm = employeeService
				.getContractorEmploymentInfoForm(employee);
		modelView.addObject("contractorEmploymentInfoForm", contractorEmploymentInfoForm);
		if (!bindingResult.hasErrors()) {
			try {
				employeeService.updateContractorPersonalInfo(contractorPersonalInfoForm, employee);
				modelView.addObject("contractorPersonalInfoForm", contractorPersonalInfoForm);
			} catch (Exception e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("updateContractorPersonalInfo >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateContractorEmploymentInfo", method = RequestMethod.POST)
	public ModelAndView updateContractorEmploymentInfo(
			@Valid @ModelAttribute ContractorEmploymentInfoForm contractorEmploymentInfoForm,
			BindingResult bindingResult) {
		logger.debug(">> updateContractorEmploymentInfo");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		if (!bindingResult.hasErrors()) {
			modelView.addObject("usStateList", employeeService.getUsStateList());
			modelView.addObject("usCityList", employeeService.getUsCityList());
			modelView.addObject("usCountyList", employeeService.getUsCountyList());
			modelView.addObject("genderList", employeeControllerHelper.getGenderList());
			modelView.addObject("statusList", employeeControllerHelper.getStatus());
			Employee employee = getEmployeeFromSession();
			ContractorPersonalInfoForm contractorPersonalInfoForm = employeeService
					.getContractorPersonalInfoForm(employee);
			modelView.addObject("contractorPersonalInfoForm", contractorPersonalInfoForm);
			try {
				employeeService.updateContractorEmploymentInfo(contractorEmploymentInfoForm,
						employee);
				modelView.addObject("contractorEmploymentInfoForm", contractorEmploymentInfoForm);
			} catch (Exception e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("updateContractorEmploymentInfo >>");
		return modelView;
	}

	// section ..? CONTRACTOR_EMPLOYMENT_INFO or EMPLOYEE_EMPLOYMENT_INFO

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showEmploymentHistory/{employeeId}", method = RequestMethod.GET)
	public ModelAndView showEmploymentHistory(@PathVariable Long employeeId) {
		logger.debug(">> showEmploymentHistory");
		ModelAndView modelView = new ModelAndView();
		Employee employee = getEmployeeFromSession();
		modelView.addObject("employee", employee);
		try {
			List<EmploymentHistory> employmentHistories = employeeService
					.getEmploymentHistory(employeeId);
			modelView.addObject("employmentHistories", employmentHistories);
			modelView.setViewName("employmentHistoryPage");
		} catch (Exception e) {
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("showEmploymentHistory >>");
		return modelView;
	}

	@RequestMapping(value = "/showManageAccessForm/{id}", method = RequestMethod.GET)
	public ModelAndView showManageAccessForm(@PathVariable Long id, HttpSession session) {
		logger.debug(">> show Manage Employee Access Form");
		ModelAndView modelView = new ModelAndView("employeeAccessView");
		try {
			Employee employee = employeeService.getEmployee(id);
			AuthorizationUtil.setSessionAttribute("employee", employee);
			Company company = getCompanyFromSession();
			Access employeeAccess = employee.getEmployeeAccess();
			EmployeeAccessForm employeeAccessForm = employeeService.getEmployeeAccessForm(
					employeeAccess, company, employee);
			logger.debug("employeeAccessForm::" + employeeAccessForm);
			modelView.addObject("employeeAccessForm", employeeAccessForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error("ERROR::" + e.getMessage());
		}
		logger.debug("show Manage Employee Access Form >>");
		return modelView;
	}

	@RequestMapping(value = "/sendMailOfEmployeeAccess", method = RequestMethod.POST)
	public ModelAndView sendMailOfEmployeeAccess(
			@Valid @ModelAttribute EmployeeAccessForm employeeAccessForm,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> Send Mail Of EmployeeAccess");
		ModelAndView modelView = new ModelAndView("employeeListPage");
		if (!bindingResult.hasErrors()) {
			try {
				String workEmailAddress = employeeAccessForm.getEmailAddress();
				logger.debug("EmailAddress:::::" + workEmailAddress);
				String employeePassword = employeeService.getEmployeePassword();
				logger.debug(" Get Employee Password >>");
				Employee employee = getEmployeeFromSession();
				if (employeeAccessForm.isAllowAccessCheckBox()) {
					mailService.send(employeeControllerHelper.prepareMail(employee.getUserName(),
							employeePassword, employeeAccessForm));
					employee.setEmployeeAccess(Access.ENABLED);
				} else {
					mailService.send(employeeControllerHelper.prepareMail(employee.getUserName(),
							employeeAccessForm));
					employee.setEmployeeAccess(Access.DISABLED);
				}
				modelView.addObject("employeeAccessForm", employeeAccessForm);
			} catch (Exception e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("Send Mail Of EmployeeAccess >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showEmployeeParsonalInfoForm", method = RequestMethod.GET)
	public ModelAndView showEmployeeParsonalInfoForm() {
		logger.debug(">> showEmployeeParsonalInfoForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("employeePersonalInfoForm", new EmployeePersonalInfoForm());
		modelView.setViewName("employeeInfoForm");
		logger.debug("showEmployeeParsonalInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeePersonalForm", method = RequestMethod.POST)
	public ModelAndView addEmployeePersonalForm(
			@Valid @ModelAttribute EmployeePersonalInfoForm employeePersonalInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addEmployeePersonalForm");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long employeeId = employeeService.addEmployeePersonalDetail(
						employeePersonalInfoForm, getCompanyFromSession(session));
				session.setAttribute("employeeId", employeeId);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showEmployeeEmploymentnfoForm";
				employeeService.createTask(employeeId, url, getCompanyUserFromSession(session));
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addEmployeePersonalForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeePersonalFormAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addEmployeePersonalFormAndCompleteLater(
			@Valid @ModelAttribute EmployeePersonalInfoForm employeePersonalInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addEmployeePersonalFormAndCompleteLater");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long employeeId = employeeService.addEmployeePersonalDetail(
						employeePersonalInfoForm, getCompanyFromSession(session));
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showEmployeeEmploymentnfoForm";
				employeeService.createTask(employeeId, url, getCompanyUserFromSession(session));
				modelView.setViewName("employeeListPage");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addEmployeePersonalFormAndCompleteLater >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showEmployeeEmploymentInfoForm/{taskId}", method = RequestMethod.GET)
	public ModelAndView showEmployeeEmploymentInfoForm(@PathVariable Long taskId,
			HttpSession session) {
		logger.debug(">> showEmployeeEmploymentInfoForm");
		ModelAndView modelView = new ModelAndView();
		session.setAttribute("taskId", taskId);
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("employeeEmploymentInfoForm", new EmployeeEmploymentInfoForm());
		modelView.setViewName("employeeInfoForm");
		logger.debug("showEmployeeEmploymentInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeEmploymentForm", method = RequestMethod.POST)
	public ModelAndView addEmployeeEmploymentForm(
			@Valid @ModelAttribute EmployeeEmploymentInfoForm employeeEmploymentInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addEmployeeEmploymentForm");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long taskId = (Long) session.getAttribute("taskId");
				Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
				session.setAttribute("employeeId", employeeId);
				employeeService
						.updateEmployeeEmploymentInfo(employeeId, employeeEmploymentInfoForm);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showEmployeePayrollInfoForm";
				employeeService.updateTask(taskId, url);
				modelView.setViewName("employeeInfoForm");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}

		logger.debug("addEmployeeEmploymentForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).EMPLOYEE_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addEmployeeEmploymentFormAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addEmployeeEmploymentFormAndCompleteLater(
			@Valid @ModelAttribute EmployeeEmploymentInfoForm employeeEmploymentInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addEmployeeEmploymentFormAndCompleteLater");
		ModelAndView modelView = new ModelAndView("employeeInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long taskId = (Long) session.getAttribute("taskId");
				Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
				session.setAttribute("employeeId", employeeId);
				employeeService
						.updateEmployeeEmploymentInfo(employeeId, employeeEmploymentInfoForm);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showEmployeePayrollInfoForm";
				employeeService.updateTask(taskId, url);
				modelView.setViewName("employeeListPage");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		modelView.setViewName("employeeInfoForm");
		logger.debug("addEmployeeEmploymentFormAndCompleteLater >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showContractorParsonalInfoForm", method = RequestMethod.GET)
	public ModelAndView showContractorParsonalInfoForm() {
		logger.debug(">> showContractorParsonalInfoForm");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("contractorPersonalInfoForm", new ContractorPersonalInfoForm());
		modelView.setViewName("contractorInfoForm");
		logger.debug("showContractorParsonalInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addContractorPersonalForm", method = RequestMethod.POST)
	public ModelAndView addContractorPersonalForm(
			@Valid @ModelAttribute ContractorPersonalInfoForm contractorPersonalInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addContractorPersonalForm");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long employeeId = employeeService.addContractorPersonalDetail(
						contractorPersonalInfoForm, getCompanyFromSession(session));
				session.setAttribute("employeeId", employeeId);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showContractorEmploymentnfoForm";
				employeeService.createTask(employeeId, url, getCompanyUserFromSession(session));
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addContractorPersonalForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_PERSONAL_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addContractorPersonalFormAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addContractorPersonalFormAndCompleteLater(
			@Valid @ModelAttribute ContractorPersonalInfoForm contractorPersonalInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addContractorPersonalFormAndCompleteLater");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long employeeId = employeeService.addContractorPersonalDetail(
						contractorPersonalInfoForm, getCompanyFromSession(session));
				session.setAttribute("employeeId", employeeId);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showContractorEmploymentnfoForm";
				employeeService.createTask(employeeId, url, getCompanyUserFromSession(session));
				modelView.setViewName("employeeListPage");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addContractorPersonalFormAndCompleteLater >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showContractorEmploymentInfoForm/{taskId}", method = RequestMethod.GET)
	public ModelAndView showContractorEmploymentInfoForm(@PathVariable Long taskId,
			HttpSession session) {
		logger.debug(">> showEmployeeEmploymentInfoForm");
		ModelAndView modelView = new ModelAndView();
		session.setAttribute("taskId", taskId);
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("genderList", employeeControllerHelper.getGenderList());
		modelView.addObject("contractorEmploymentInfoForm", new ContractorEmploymentInfoForm());
		modelView.setViewName("contractorInfoForm");
		logger.debug("showContractorEmploymentInfoForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addContractorEmploymentForm", method = RequestMethod.POST)
	public ModelAndView addContractorEmploymentForm(
			@Valid @ModelAttribute ContractorEmploymentInfoForm contractorEmploymentInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addContractorEmploymentForm");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long taskId = (Long) session.getAttribute("taskId");
				Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
				session.setAttribute("employeeId", employeeId);
				employeeService.updateContractorEmploymentInfo(employeeId,
						contractorEmploymentInfoForm);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showContractorPayrollInfoForm";
				employeeService.updateTask(taskId, url);
				modelView.setViewName("contractorInfoForm");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}

		logger.debug("addContractorEmploymentForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).CONTRACTOR_EMPLOYMENT_INFO, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addContractorEmploymentFormAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addContractorEmploymentFormAndCompleteLater(
			@Valid @ModelAttribute ContractorEmploymentInfoForm contractorEmploymentInfoForm,
			BindingResult bindingResult, HttpSession session, HttpServletRequest request) {
		logger.debug(">> addContractorEmploymentFormAndCompleteLater");
		ModelAndView modelView = new ModelAndView("contractorInfoForm");
		modelView.addObject("usStateList", employeeService.getUsStateList());
		modelView.addObject("usCityList", employeeService.getUsCityList());
		modelView.addObject("usCountyList", employeeService.getUsCountyList());
		modelView.addObject("workCompCodeList", employeeService.getcompanyWorkerCompensationList());
		if (!bindingResult.hasErrors()) {
			try {
				Long taskId = (Long) session.getAttribute("taskId");
				Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
				session.setAttribute("employeeId", employeeId);
				employeeService.updateContractorEmploymentInfo(employeeId,
						contractorEmploymentInfoForm);
				String url = "http://localhost:8080" + request.getContextPath()
						+ "/employee/showContractorPayrollInfoForm";
				employeeService.updateTask(taskId, url);
				modelView.setViewName("employeeListPage");
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR::" + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}

		logger.debug("addContractorEmploymentFormAndCompleteLater >>");
		return modelView;
	}

	@RequestMapping(value = "/verifyAndDeleteEmployee/{employeeId}", method = RequestMethod.GET)
	public ModelAndView verifyAndDeleteEmployee(@PathVariable Long employeeId)
			throws InstanceNotFoundException {
		logger.debug(">> verifyAndDeleteEmployee");
		ModelAndView modelView = new ModelAndView("employeeDeductionView");
		if (!employeeService.verifyAndDeleteEmployee(employeeId)) {
			logger.debug(" Inactive Employee");
			employeeService.setEmployeeInactivate(employeeId);
		} else {
			logger.debug("Employee Deleted");
		}
		logger.debug(" verifyAndDeleteEmployee >>");
		return modelView;

	}
}
