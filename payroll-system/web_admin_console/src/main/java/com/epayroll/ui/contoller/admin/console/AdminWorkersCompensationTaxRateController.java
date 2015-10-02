package com.epayroll.ui.contoller.admin.console;

import java.util.List;

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
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.WorkersCompensationTaxRate;
import com.epayroll.form.adminConsole.WorkersCompensationTaxRateForm;
import com.epayroll.form.adminConsole.YearForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/workersCompensationTaxRate")
@Controller
public class AdminWorkersCompensationTaxRateController {
	private Logger logger = LoggerFactory
			.getLogger(AdminWorkersCompensationTaxRateController.class);

	@Autowired
	private AdminSystemDataService adminSystemDataService;

	@RequestMapping(value = "/showForm", method = RequestMethod.GET)
	public ModelAndView showForm() {
		logger.debug(">> showForm");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		modelView.addObject("yearForm", new YearForm());
		logger.debug("showForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showWorkersCompensationTaxRateForm", method = RequestMethod.GET)
	public ModelAndView showWorkersCompensationTaxRateForm(@ModelAttribute YearForm yearForm) {
		logger.debug(">> showWorkersCompensationTaxRateForm");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		try {
			List<WorkersCompensationTaxRate> workersCompensationTaxRateList = adminSystemDataService
					.getWorkersCompensationTaxRateList(yearForm.getYear());
			modelView.addObject("workersCompensationTaxRateList", workersCompensationTaxRateList);
			List<Company> companies = adminSystemDataService.getCompanies();
			modelView.addObject("companies", companies);
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		modelView.addObject("workersCompensationTaxRateForm", new WorkersCompensationTaxRateForm());
		logger.debug("showWorkersCompensationTaxRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/getWorkersCompensationCode/{companyId}", method = RequestMethod.POST)
	public ModelAndView getWorkersCompensationCode(@PathVariable Long companyId,
			@ModelAttribute WorkersCompensationTaxRateForm workersCompensationTaxRateForm,
			@ModelAttribute YearForm yearForm) {
		logger.debug(">> getWorkersCompensationCode");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		List<CompanyWorkerCompensation> companyWorkerCompensations = adminSystemDataService
				.getcompanyWorkerCompensations(companyId);
		modelView.addObject("companyWorkerCompensations", companyWorkerCompensations);
		logger.debug("getWorkersCompensationCode >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addWorkersCompensationTaxRate", method = RequestMethod.POST)
	public ModelAndView addWorkersCompensationTaxRate(
			@Valid @ModelAttribute WorkersCompensationTaxRateForm workersCompensationTaxRateForm,
			@ModelAttribute YearForm yearForm, BindingResult bindingResult) {
		logger.debug(">> addWorkersCompensationTaxRate");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		if (!bindingResult.hasErrors()) {
			try {
				List<WorkersCompensationTaxRate> workersCompensationTaxRateList = adminSystemDataService
						.getWorkersCompensationTaxRateList(yearForm.getYear());
				modelView.addObject("workersCompensationTaxRateList",
						workersCompensationTaxRateList);
				List<Company> companies = adminSystemDataService.getCompanies();
				modelView.addObject("companies", companies);
				@SuppressWarnings("unused")
				Long id = adminSystemDataService
						.addWorkersCompensationTaxRate(workersCompensationTaxRateForm);
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error ::: " + bindingResult.toString());
		}
		logger.debug("addWorkersCompensationTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateWorkersCompensationTaxRateForm/{workComTaxRateId}", method = RequestMethod.GET)
	public ModelAndView showUpdateWorkersCompensationTaxRateForm(
			@PathVariable Long workComTaxRateId,
			@ModelAttribute WorkersCompensationTaxRateForm workersCompensationTaxRateForm,
			@ModelAttribute YearForm yearForm) {
		logger.debug(">> showUpdateWorkersCompensationTaxRateForm");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		try {
			List<WorkersCompensationTaxRate> workersCompensationTaxRateList = adminSystemDataService
					.getWorkersCompensationTaxRateList(yearForm.getYear());
			modelView.addObject("workersCompensationTaxRateList", workersCompensationTaxRateList);
			List<Company> companies = adminSystemDataService.getCompanies();
			modelView.addObject("companies", companies);
			// TODO set id in hidden variable
			modelView.addObject("workersCompensationTaxRateForm",
					adminSystemDataService.getWorkersCompensationTaxRateForm(workComTaxRateId));
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateWorkersCompensationTaxRateForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateWorkersCompensationTaxRate", method = RequestMethod.POST)
	public ModelAndView updateWorkersCompensationTaxRate(
			@Valid @ModelAttribute WorkersCompensationTaxRateForm workersCompensationTaxRateForm,
			@ModelAttribute YearForm yearForm, BindingResult bindingResult) {
		logger.debug(">> updateWorkersCompensationTaxRate");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		if (!bindingResult.hasErrors()) {
			try {
				List<WorkersCompensationTaxRate> workersCompensationTaxRateList = adminSystemDataService
						.getWorkersCompensationTaxRateList(yearForm.getYear());
				modelView.addObject("workersCompensationTaxRateList",
						workersCompensationTaxRateList);
				List<Company> companies = adminSystemDataService.getCompanies();
				modelView.addObject("companies", companies);
				adminSystemDataService
						.updateWorkersCompensationTaxRate(workersCompensationTaxRateForm);
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error ::: " + bindingResult.toString());
		}
		logger.debug("updateWorkersCompensationTaxRate >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).WORKERS_COMPENSATION_TAX_RATE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteWorkersCompensationTaxRate/{workComTaxRateId}", method = RequestMethod.GET)
	public ModelAndView deleteWorkersCompensationTaxRate(@PathVariable Long workComTaxRateId,
			@ModelAttribute WorkersCompensationTaxRateForm workersCompensationTaxRateForm,
			@ModelAttribute YearForm yearForm) {
		logger.debug(">> deleteWorkersCompensationTaxRate");
		ModelAndView modelView = new ModelAndView("workersCompensationTaxRateForm");
		modelView.addObject("yearList",
				adminSystemDataService.getYearListForWorkersCompensationTaxRate());
		try {
			List<WorkersCompensationTaxRate> workersCompensationTaxRateList = adminSystemDataService
					.getWorkersCompensationTaxRateList(yearForm.getYear());
			modelView.addObject("workersCompensationTaxRateList", workersCompensationTaxRateList);
			List<Company> companies = adminSystemDataService.getCompanies();
			modelView.addObject("companies", companies);
			adminSystemDataService.verifyAndDeleteWorkersCompensationTaxRate(workComTaxRateId);
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("deleteWorkersCompensationTaxRate >>");
		return modelView;
	}

}
