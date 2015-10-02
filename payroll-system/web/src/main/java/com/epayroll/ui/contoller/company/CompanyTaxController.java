package com.epayroll.ui.contoller.company;

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

import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyTax;
import com.epayroll.entity.TaxDepositCycle;
import com.epayroll.entity.TaxType;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyStateLocalTaxFrom;
import com.epayroll.form.company.CompanyTaxForm;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;

/**
 * @author Rajul Tiwari
 */

@Controller
@RequestMapping("/company/tax")
public class CompanyTaxController {

	private Logger logger = LoggerFactory.getLogger(CompanyTaxController.class);
	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

	// private Company getCompanyFromSession(HttpSession session) {
	// logger.debug(">> getCompanyFromSession");
	// User user = (User) session.getAttribute("loggedInUser");
	// Company company = new Company();
	// if (user.getCompanyUsers() != null && !user.getCompanyUsers().isEmpty())
	// {
	// Iterator<CompanyUser> iterator = user.getCompanyUsers().iterator();
	// if (iterator.hasNext()) {
	// company = iterator.next().getCompany();
	// }
	// }
	// logger.debug("Company " + company);
	// logger.debug("getCompanyFromSession >>");
	// return company;
	// }
	private Company getCompanyFromSession() {
		Company company = commonService.getCompany(AuthorizationUtil.getLoginUser().getId());
		return company;
	}

	// TODO section ..?
	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public ModelAndView showList(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView();
		try {
			// modelView.addObject("companyTaxList",
			// companyService.getCompanyTaxes(getCompanyFromSession(session)));
			Long companyId = getCompanyFromSession().getId();
			List<CompanyTax> federalTaxList = companyService.getCompanyFederalTaxList(companyId);
			modelView.addObject("federalTaxList", federalTaxList);
			List<CompanyTax> stateTaxList = companyService.getCompanyStateTaxList(companyId);
			modelView.addObject("stateTaxList", stateTaxList);
			List<CompanyTax> localTaxList = companyService.getCompanyLocalTaxList(companyId);
			modelView.addObject("localTaxList", localTaxList);
			modelView.setViewName("companyTaxListPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("showList >>");
		return modelView;
	}

	@RequestMapping(value = "/showUpdateForm/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateForm(@PathVariable Long id) {
		logger.debug(">> showUpdateForm");
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("companyTaxForm", companyService.getCompanyTaxForm(id));
			modelView.setViewName("companyTaxForm");
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("showUpdateForm >>");
		return modelView;
	}

	@RequestMapping(value = "/updateDepositCycle", method = RequestMethod.POST)
	public ModelAndView updateDepositCycle(@ModelAttribute CompanyTaxForm companyTaxForm) {
		logger.debug(">> updateDepositCycle");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			companyService.updateTaxDepositCycle(companyTaxForm);
			result = "Deposit Cycle Updated Successfully";
			modelView.setViewName("companyTaxListPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while updating the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("updateDepositCycle >>");
		return modelView;
	}

	@RequestMapping(value = "/updateCompanyTax", method = RequestMethod.POST)
	public ModelAndView updateCompanyTax(@ModelAttribute CompanyTaxForm companyTaxForm,
			HttpSession session) {
		logger.debug(">> updateCompanyTax");
		ModelAndView modelView = new ModelAndView("companyTaxListPage");
		String result = "";
		try {
			companyService.updateCompanyTax(companyTaxForm, getCompanyFromSession());
			result = "Updated Successfully";
			modelView.setViewName("companyTaxListPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while updating the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("updateCompanyTax >>");
		return modelView;
	}

	@RequestMapping(value = "/showAddTaxForm", method = RequestMethod.GET)
	public ModelAndView showAddTaxForm(HttpSession session) {
		logger.debug(">> showAddTaxForm");
		ModelAndView modelView = new ModelAndView("companyTaxListPage");
		modelView.addObject("companyTaxForm", new CompanyTaxForm());
		List<TaxDepositCycle> taxDepositCycles = companyService.getDepositCycle();
		modelView.addObject("taxDepositCycles", taxDepositCycles);
		logger.debug("taxDepositCycles:::" + taxDepositCycles);
		// add federal tax form
		Company company = getCompanyFromSession();
		TaxType taxType = companyService.getFutaTaxType(company.getId());
		modelView.addObject("futaTaxType", taxType);
		logger.debug("futaTaxType:::" + taxType);
		CompanyTaxForm companyTaxForm = companyService.getFederalTaxForm(company.getId());
		modelView.addObject("companyTaxForm", companyTaxForm);
		logger.debug("companyTaxForm:::" + companyTaxForm);
		// add State tax form
		CompanyStateLocalTaxFrom companyStateTaxFrom = companyService.getStateTaxForm(company
				.getId());
		modelView.addObject("companyStateLocalTaxFrom", companyStateTaxFrom);
		logger.debug("companyStateTaxFrom:::" + companyStateTaxFrom);
		// add Local tax form
		CompanyStateLocalTaxFrom companyLocalTaxFrom = companyService.getLocalTaxForm(company
				.getId());
		logger.debug("companyLocalTaxFrom:::" + companyLocalTaxFrom);
		modelView.addObject("companyLocalTaxFrom", companyLocalTaxFrom);
		logger.debug("showAddTaxForm >>");
		return modelView;
	}

	@RequestMapping(value = "/addFederalTax", method = RequestMethod.POST)
	public ModelAndView addFederalTax(@ModelAttribute CompanyTaxForm companyTaxForm,
			HttpSession session) {
		logger.debug(">> addFederalTax");
		ModelAndView modelView = new ModelAndView("federalTaxView");
		String result = "";
		try {
			companyService.addFederalTax(companyTaxForm);
			result = "Added Successfully";
			modelView.setViewName("federalTaxView");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while adding the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("addFederalTax >>");
		return modelView;
	}

	@RequestMapping(value = "/addStateTax", method = RequestMethod.POST)
	public ModelAndView addStateTax(
			@ModelAttribute CompanyStateLocalTaxFrom companyStateLocalTaxFrom, HttpSession session) {
		logger.debug(">> addStateTax");
		ModelAndView modelView = new ModelAndView("stateTaxView");
		String result = "";
		try {
			companyService.addStateTax(companyStateLocalTaxFrom);
			result = "Added Successfully";
			modelView.setViewName("stateTaxView");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while adding the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("addStateTax >>");
		return modelView;
	}

	@RequestMapping(value = "/addLocalTax", method = RequestMethod.POST)
	public ModelAndView addLocalTax(
			@ModelAttribute CompanyStateLocalTaxFrom companyStateLocalTaxFrom, HttpSession session) {
		logger.debug(">> addLocalTax");
		ModelAndView modelView = new ModelAndView("localTaxView");
		String result = "";
		try {
			companyService.addLocalTax(companyStateLocalTaxFrom);
			result = "Added Successfully";
			modelView.setViewName("localTaxView");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "Some error occurred while adding the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("addLocalTax >>");
		return modelView;
	}
}