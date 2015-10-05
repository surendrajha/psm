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
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employeeAccess.CompanyForm;
import com.epayroll.service.company.CompanyService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/manageCompanies")
@Controller
public class AdminManageCompaniesController {
	private Logger logger = LoggerFactory.getLogger(AdminManageCompaniesController.class);
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/showCompanyForm", method = RequestMethod.GET)
	public ModelAndView showCompanyForm() {
		logger.debug(">> showCompanyForm");
		ModelAndView modelView = new ModelAndView("companyForm");
		List<UsState> usStateList = companyService.getUsStates();
		modelView.addObject("usStateList", usStateList);
		List<UsCity> usCityList = companyService.getUsCities();
		modelView.addObject("usCityList", usCityList);
		List<Company> companyList = companyService.getCompanyList();
		modelView.addObject("companyList", companyList);
		modelView.addObject("companyForm", new CompanyForm());
		logger.debug("showCompanyForm >>");
		return modelView;
	}

	@RequestMapping(value = "/searchCompany", method = RequestMethod.POST)
	public ModelAndView searchCompany(@ModelAttribute CompanyForm companyForm) {
		logger.debug(">> searchCompany");
		logger.debug("CompanyForm::" + companyForm);
		ModelAndView modelView = new ModelAndView("companyForm");
		List<UsState> usStateList = companyService.getUsStates();
		modelView.addObject("usStateList", usStateList);
		List<UsCity> usCityList = companyService.getUsCities();
		modelView.addObject("usCityList", usCityList);
		List<Object[]> searchedCompanyList = companyService.getSearchedCompanyList(companyForm);
		modelView.addObject("companyList", searchedCompanyList);
		logger.debug("searchCompany >>");
		return modelView;
	}

	@RequestMapping(value = "/verifyDeleteCompany/{companyId}", method = RequestMethod.GET)
	public ModelAndView verifyDeleteCompany(@PathVariable Long companyId) {
		logger.debug(">> verifyDeleteCompany");
		ModelAndView modelView = new ModelAndView("companyForm");
		String result = "";
		try {
			if (companyService.verificationForDeleteCompany(companyId)) {
				modelView.setViewName("delete-form");
				result = "Are You sure you want to delete this Company";
				logger.debug("result " + result);

			} else {
				result = "Company Records are associated with this Payroll. So you cannot delete this Company from system. ";
				logger.debug("result " + result);
				modelView.setViewName("InActive-form");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("verifyDeleteCompany >>");
		return modelView;
	}

	@RequestMapping(value = "/deleteCompany/{companyId}", method = RequestMethod.GET)
	public ModelAndView deleteCompany(@PathVariable Long companyId) {
		logger.debug(">> deleteCompany");
		ModelAndView modelView = new ModelAndView("companyForm");
		String result = "";
		try {
			companyService.deleteCompany(companyId);
		} catch (InstanceNotFoundException e) {
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("deleteCompany >>");
		return modelView;
	}

	@RequestMapping(value = "/deactivateCompany/{companyId}", method = RequestMethod.GET)
	public ModelAndView deactivateCompany(@PathVariable Long companyId) {
		logger.debug(">> deactivateCompany");
		ModelAndView modelView = new ModelAndView("companyForm");
		String result = "";
		try {
			companyService.deactivateCompany(companyId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "Some error occurred while Deactivate the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("deactivateCompany >>");
		return modelView;
	}

	@RequestMapping(value = "/reactivateCompany/{companyId}", method = RequestMethod.GET)
	public ModelAndView reactivateCompany(@PathVariable Long companyId) {
		logger.debug(">> reactivateCompany");
		ModelAndView modelView = new ModelAndView("companyForm");
		String result = "";
		try {
			companyService.reactivateCompany(companyId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "Some error occurred while Reactivate the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		logger.debug("reactivateCompany >>");
		return modelView;
	}

}
