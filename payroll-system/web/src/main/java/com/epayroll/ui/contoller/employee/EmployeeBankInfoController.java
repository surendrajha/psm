package com.epayroll.ui.contoller.employee;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.entity.Employee;
import com.epayroll.exception.CommonException;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.EmployeeBankInfoForm;
import com.epayroll.service.employee.EmployeeBankInfoService;
import com.epayroll.spring.authorization.AuthorizationUtil;

@Controller
@RequestMapping("/employee/bank")
public class EmployeeBankInfoController {

	private Logger logger = LoggerFactory.getLogger(EmployeeBankInfoController.class);

	@Autowired
	private EmployeeBankInfoService bankInfoService;

	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public ModelAndView getInfo() {
		logger.debug(" >> getInfo");
		ModelAndView modelAndView = new ModelAndView("employeeBankInfo");
		EmployeeBankInfoForm bankInfoForm = new EmployeeBankInfoForm();
		try {
			modelAndView.addObject("accountTypeList", bankInfoService.getAccountTypes());
			modelAndView.addObject("depositTypeList", bankInfoService.getDepositAllocationTypes());
			bankInfoForm.setEmployeeBankInfos(bankInfoService
					.getEmployeeBankAccounts(getEmployeeFromSession()));
			bankInfoForm.setEmployeeId(getEmployeeFromSession().getId());

		} catch (InstanceNotFoundException infe) {
			logger.error("Employee Not exists", infe);
			modelAndView.addObject("employeeNotFound", "Employee Not exists!");
		}
		modelAndView.addObject("bankInfoForm", bankInfoForm);
		return modelAndView;
	}

	@RequestMapping(value = "/addInfo", method = RequestMethod.POST)
	public ModelAndView addInfo(@ModelAttribute EmployeeBankInfoForm bankInfoForm,
			BindingResult result, HttpServletRequest request) {
		logger.debug(" >> addInfo .... bankInfoForm:" + bankInfoForm);
		logger.debug("result::" + result);
		ModelAndView modelAndView = new ModelAndView("employeeBankInfo");
		try {
			if (!result.hasErrors()) {
				bankInfoService.saveEmployeeBankInfo(bankInfoForm, getEmployeeFromSession());
				modelAndView.setView(new RedirectView(request.getContextPath()
						+ "/employee/bank/getInfo"));
			}
		} catch (CommonException e) {
			modelAndView.addObject("error", e.getMessage());
			logger.error("ERROR:", e.fillInStackTrace());
		}
		modelAndView.addObject("bankInfoForm", bankInfoForm);
		modelAndView.addObject("accountTypeList", bankInfoService.getAccountTypes());
		modelAndView.addObject("depositTypeList", bankInfoService.getDepositAllocationTypes());
		return modelAndView;
	}

	@RequestMapping(value = "/deleteInfo", method = RequestMethod.POST)
	public ModelAndView deleteInfo(@ModelAttribute EmployeeBankInfoForm bankInfoForm,
			HttpServletRequest request) {
		logger.debug(" >> deleteInfo,, bankInfoForm_ID:" + bankInfoForm.getId());
		ModelAndView modelAndView = new ModelAndView();
		try {
			bankInfoService.deleteEmployeeBankInfo(bankInfoForm.getId());
		} catch (InstanceNotFoundException e) {
			logger.error("Error in deleting Id not found:", e);
		}
		modelAndView.setView(new RedirectView(request.getContextPath() + "/employee/bank/getInfo"));
		return modelAndView;
	}

	private Employee getEmployeeFromSession() {

		logger.debug(" in getEmployeeFromSession..");

		return (Employee) AuthorizationUtil.getSession().getAttribute("employee",
				RequestAttributes.SCOPE_SESSION);
	}

}
