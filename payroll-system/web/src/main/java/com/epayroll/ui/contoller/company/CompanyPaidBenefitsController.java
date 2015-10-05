/**
 * 
 */
package com.epayroll.ui.contoller.company;

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
import com.epayroll.form.company.CompanyPaidBenefitsForm;
import com.epayroll.service.company.CompanyPaidBenefitService;

/**
 * @author Surendra Jha
 */
@Controller
public class CompanyPaidBenefitsController {

	private Logger logger = LoggerFactory.getLogger(CompanyPaidBenefitsController.class);

	@Autowired
	private CompanyPaidBenefitService paidBenefitService;

	private Company getCompany(HttpSession session) {
		return (Company) session.getAttribute("company");
	}

	// @ModelAttribute
	// public CompanyPaidBenefitsForm getForm() {
	// return new CompanyPaidBenefitsForm();
	// }

	@RequestMapping("/showPaidBenefitsForm")
	public ModelAndView showPaidBenefitsForm(HttpSession session) {
		logger.debug(" >> show PaidBenefits Form");
		ModelAndView mav = new ModelAndView("paid-benefits");
		Company company = getCompany(session);
		mav.addObject("companyEarnings", paidBenefitService.getCompanyEarnings(company));
		logger.debug("show PaidBenefits Form >> ");
		return mav;
	}

	@RequestMapping("/getPaidBenefits/{companyEarningId}/{status}")
	public ModelAndView getCompanyPaidBenefits(@PathVariable Long companyEarningId,
			@PathVariable Boolean status, HttpSession session) {
		logger.debug(" >> getCompanyPaidBenefits ");
		ModelAndView mav = new ModelAndView("paid-benefits");
		Company company = getCompany(session);
		CompanyPaidBenefitsForm benefitsForm = new CompanyPaidBenefitsForm();
		try {
			mav.addObject("accrualModes", paidBenefitService.getAccrualModes());
			mav.addObject("carryForwardHourList", paidBenefitService.getCarryForwardHours());
			mav.addObject("paidBenefits",
					paidBenefitService.getPaidBenefits(company, companyEarningId, status));
			mav.addObject("companyPaifBenefitsForm", benefitsForm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error: ", e.fillInStackTrace());
		}
		logger.debug("getCompanyPaidBenefits >> ");
		return mav;
	}

	@RequestMapping(value = "/addPaidBenefits", method = RequestMethod.POST)
	public ModelAndView addCompanyPaidBenefits(
			@Valid @ModelAttribute CompanyPaidBenefitsForm companyPaidBenefitsForm,
			BindingResult result, HttpSession session) {

		ModelAndView mav = new ModelAndView("paid-benefits");
		Company company = getCompany(session);
		try {
			System.out.println("companyPaidBenefitsForm:::" + companyPaidBenefitsForm);
			paidBenefitService.addPaidBenefit(company, companyPaidBenefitsForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
