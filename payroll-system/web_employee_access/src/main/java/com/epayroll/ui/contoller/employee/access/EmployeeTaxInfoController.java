package com.epayroll.ui.contoller.employee.access;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.Employee;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.model.EmployeeW4DetailModel;
import com.epayroll.service.employeeAccess.EmployeeAccessTaxInfoService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/employeeAccess/taxInfo")
@Controller
public class EmployeeTaxInfoController {
	private Logger logger = LoggerFactory.getLogger(EmployeeTaxInfoController.class);

	@Autowired
	private EmployeeAccessTaxInfoService employeeAccessTaxInfoService;

	private Employee getEmployeeFromSession(HttpSession session) {
		logger.debug(">> getEmployeeFromSession");
		Employee employee = (Employee) session.getAttribute("employee");
		logger.debug("getEmployeeFromSession >>");
		return employee;
	}

	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public ModelAndView showList(HttpSession session) {
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

}
