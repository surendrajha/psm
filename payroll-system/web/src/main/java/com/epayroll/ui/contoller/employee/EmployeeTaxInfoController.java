package com.epayroll.ui.contoller.employee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.epayroll.entity.AllowanceType;
import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.entity.FilingStatus;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeFederalTaxForm;
import com.epayroll.form.employee.EmployeeLocalTaxForm;
import com.epayroll.form.employee.EmployeeStateTaxForm;
import com.epayroll.model.EmployeeW4DetailModel;
import com.epayroll.model.employee.TaxTypeList;
import com.epayroll.service.employee.EmployeeService;
import com.epayroll.service.employeeAccess.EmployeeAccessTaxInfoService;

/**
 * @author Uma
 */
// @RequestMapping("/employee")
@Controller
public class EmployeeTaxInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeeTaxInfoController.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeAccessTaxInfoService employeeAccessTaxInfoService;

	private Employee getEmployeeFromSession(HttpSession session) {
		logger.debug(">> getEmployeeFromSession");
		// Employee employee = (Employee) AuthorizationUtil
		// .getSessionAttribute("employee");
		Employee employee = (Employee) session.getAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	@RequestMapping(value = "/showEmployeeTaxList", method = RequestMethod.GET)
	public ModelAndView showEmployeeTaxList(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView("employeeTaxListPage");
		Employee employee = getEmployeeFromSession(session);
		List<EmployeeTax> federalTaxList = employeeAccessTaxInfoService
				.getEmployeeFederalTaxList(employee.getId());
		modelView.addObject("federalTaxList", federalTaxList);
		EmployeeW4DetailModel employeeW4DetailModel = employeeAccessTaxInfoService
				.getFederalFITDetail(employee.getId());
		modelView.addObject("employeeFITDetail", employeeW4DetailModel);

		EmployeeW4DetailModel employeeSITDetail = employeeAccessTaxInfoService
				.getStateSITDetail(employee.getId());
		modelView.addObject("employeeSITDetail", employeeSITDetail);
		List<EmployeeTax> stateTaxList = employeeAccessTaxInfoService
				.getEmployeeStateTaxList(employee.getId());
		modelView.addObject("stateTaxList", stateTaxList);
		List<EmployeeTax> localTaxList = employeeAccessTaxInfoService
				.getEmployeeLocalTaxList(employee.getId());
		modelView.addObject("localTaxList", localTaxList);
		logger.debug("showList >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFederalTaxForm/{employeeTaxId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFederalTaxForm(@PathVariable Long employeeTaxId,
			HttpSession session) throws InstanceNotFoundException {
		logger.debug(">> showUpdateFederalTaxForm");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		Employee employee = getEmployeeFromSession(session);

		// Set FIT Details into Form
		EmployeeFederalTaxForm employeeFederalTaxForm = employeeService
				.getFitForm(employee.getId());

		// Set Other Federal Tax Details
		List<EmployeeTax> employeeTaxs = employeeAccessTaxInfoService
				.getEmployeeFederalTaxList(employee.getId());
		List<TaxTypeList> taxTypes = employeeFederalTaxForm.getExemptedList();
		for (EmployeeTax employeeTax : employeeTaxs) {
			taxTypes.add(new TaxTypeList(employeeTax.getTaxType().getId(), employeeTax.getTaxType()
					.getTaxName(), employeeTax.getExempted(), false));
		}
		employeeFederalTaxForm.setExemptedList(taxTypes);

		// Set Employee ID
		employeeFederalTaxForm.setEmployeeId(employee.getId());

		logger.debug("employeeFedralTaxForm" + employeeFederalTaxForm);
		modelView.addObject("employeeFedralTaxForm", employeeFederalTaxForm);

		logger.debug("showUpdateFederalTaxForm >>");
		return modelView;

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateStateTaxForm/{employeeTaxId}", method = RequestMethod.GET)
	public ModelAndView showUpdateStateTaxForm(@PathVariable Long employeeTaxId, HttpSession session)
			throws InstanceNotFoundException {
		logger.debug(">>showUpdateStateTaxForm");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		Employee employee = getEmployeeFromSession(session);
		EmployeeStateTaxForm employeeStateTaxForm = employeeService.getStateTaxForm(employee
				.getId());
		logger.debug("employeeStateTaxForm" + employeeStateTaxForm);
		modelView.addObject("employeeStateTaxForm", employeeStateTaxForm);
		logger.debug("showUpdateStateTaxForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LOCAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateLocalTaxForm/{employeeTaxId}", method = RequestMethod.GET)
	public ModelAndView showUpdateLocalTaxForm(@PathVariable Long employeeTaxId, HttpSession session)
			throws InstanceNotFoundException {
		logger.debug(">>showUpdateLocalTaxForm");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		Employee employee = getEmployeeFromSession(session);
		EmployeeLocalTaxForm employeeLocalTaxForm = employeeService.getLocalForm(employeeTaxId);
		employeeLocalTaxForm.setEmployeeId(employee.getId());
		logger.debug("employeeLocalTaxForm" + employeeLocalTaxForm);
		modelView.addObject("employeeLocalTaxForm", employeeLocalTaxForm);
		logger.debug("showUpdateLocalTaxForm >>");
		return modelView;
	}

	@RequestMapping(value = "/showAddEmployeeTaxForm/{taskId}", method = RequestMethod.GET)
	public ModelAndView showAddEmployeeTaxList(@PathVariable Long taskId, HttpSession session) {
		logger.debug(">>show Add  Employee Fedral Tax List");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		session.setAttribute("taskId", taskId);
		List<FilingStatus> filingStatusList = employeeService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		List<AllowanceType> allowanceTypes = employeeService.getallowanceTypes();
		modelView.addObject("allowanceTypes", allowanceTypes);
		modelView.addObject("taxOverrideMethodList", employeeService.getTaxOverrideType());
		modelView.addObject("exemptedList", employeeService.getExemptedList());

		Long employeeId = ((Employee) session.getAttribute("employee")).getId();
		// For Add Fed Tax
		EmployeeFederalTaxForm employeeFederalTaxForm = new EmployeeFederalTaxForm();
		employeeFederalTaxForm.setEmployeeId(employeeId);
		logger.debug("employeeFederalTaxForm" + employeeFederalTaxForm);

		// For Add State Tax
		EmployeeStateTaxForm employeeStateTaxForm = new EmployeeStateTaxForm();
		employeeStateTaxForm.setEmployeeId(employeeId);
		logger.debug("employeeStateTaxForm" + employeeStateTaxForm);

		// For Add Local Tax
		EmployeeLocalTaxForm employeeLocalTaxForm = new EmployeeLocalTaxForm();
		employeeLocalTaxForm.setEmployeeId(employeeId);
		logger.debug("employeeLocalTaxForm" + employeeStateTaxForm);

		logger.debug("show Add  Employee Fedral Tax List >>");
		return modelView;

	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFedralEmployeeTax", method = RequestMethod.POST)
	public ModelAndView addFedralEmployeeTax(
			@ModelAttribute EmployeeFederalTaxForm employeeFederalTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add Fedral Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService.addEmployeeFederalTax(
					employeeFederalTaxForm, employeeId);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
			logger.debug("add Fedral Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFedralEmployeeTaxAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addFedralEmployeeTaxAndCompleteLater(
			@ModelAttribute EmployeeFederalTaxForm employeeFederalTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add Fedral Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService.addEmployeeFederalTax(
					employeeFederalTaxForm, employeeId);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
			logger.debug("add Fedral Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateEmployeeTax", method = RequestMethod.POST)
	public ModelAndView addStateEmployeeTax(
			@ModelAttribute EmployeeStateTaxForm employeeStateTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add State Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService
					.addEmployeeStateTax(employeeStateTaxForm);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
			logger.debug("add State Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addStateEmployeeTaxAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addStateEmployeeTaxAndCompleteLater(
			@ModelAttribute EmployeeStateTaxForm employeeStateTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add State Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService
					.addEmployeeStateTax(employeeStateTaxForm);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/showAddEmployeeTaxForm";
			employeeService.updateTask(taskId, url);
			logger.debug("add State Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LOCAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addLocalEmployeeTax", method = RequestMethod.POST)
	public ModelAndView addLocalEmployeeTax(
			@ModelAttribute EmployeeLocalTaxForm employeeLocalTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add Local Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService.addEmployeeLocalTax(
					employeeLocalTaxForm, employeeId);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/departmentAllocation";
			employeeService.updateTask(taskId, url);
			logger.debug("add Local Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LOCAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addLocalEmployeeTaxAndCompleteLater", method = RequestMethod.POST)
	public ModelAndView addLocalEmployeeTaxAndCompleteLater(
			@ModelAttribute EmployeeLocalTaxForm employeeLocalTaxForm, BindingResult result,
			HttpSession session, HttpServletRequest request) {
		logger.debug(">>add Local Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		try {
			Long taskId = (Long) session.getAttribute("taskId");
			Long employeeId = employeeService.getEmployeeIdFromSystemTask(taskId);
			session.setAttribute("employeeId", employeeId);
			List<EmployeeTax> employeeTaxes = employeeService.addEmployeeLocalTax(
					employeeLocalTaxForm, employeeId);
			modelView.addObject("employeeTaxes", employeeTaxes);
			String url = "http://localhost:8080" + request.getContextPath()
					+ "/employee/departmentAllocation";
			employeeService.updateTask(taskId, url);
			logger.debug("add Local Employee Tax>>");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR::" + e.getMessage());
		}
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FEDERAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFedralEmployeeTax", method = RequestMethod.POST)
	public ModelAndView updateFedralEmployeeTax(
			@ModelAttribute EmployeeFederalTaxForm employeeFederalTaxForm, BindingResult result,
			HttpSession session) throws InstanceNotFoundException {
		logger.debug(">>update Fedral Employee Tax");
		logger.debug("employeeFedralTaxForm" + employeeFederalTaxForm);
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		employeeService.updateEmployeeFederalTax(employeeFederalTaxForm);
		logger.debug("update Fedral Employee Tax>>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).STATE_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStateEmployeeTax", method = RequestMethod.POST)
	public ModelAndView updateStateEmployeeTax(
			@ModelAttribute EmployeeStateTaxForm employeeStateTaxForm, BindingResult result,
			HttpSession session) throws InstanceNotFoundException {
		logger.debug(">>Update State Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		employeeService.updateEmployeeStateTax(employeeStateTaxForm);
		logger.debug("Update State Employee Tax>>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).LOCAL_TAX, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateLocalEmployeeTax", method = RequestMethod.POST)
	public ModelAndView updateLocalEmployeeTax(
			@ModelAttribute EmployeeLocalTaxForm employeeLocalTaxForm, BindingResult result,
			HttpSession session) throws InstanceNotFoundException {
		logger.debug(">>update Local Employee Tax");
		ModelAndView modelView = new ModelAndView("employeeTaxView");
		employeeService.updateEmployeeLocalTax(employeeLocalTaxForm);
		logger.debug("update Local Employee Tax>>");
		return modelView;
	}
}
