package com.epayroll.ui.contoller.company;

import java.util.ArrayList;
import java.util.List;

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

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyWorkerCompensation;
import com.epayroll.entity.UsState;
import com.epayroll.entity.WorkersCompensationTaxRate;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.company.CompanyWorkersCompensationForm;
import com.epayroll.model.ServerResponse;
import com.epayroll.model.company.CompanyWorkersCompensationModel;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;

/**
 * @author Uma
 */
@Controller
@RequestMapping("/company")
public class CompanyWorkersCompensationController {
	private Logger logger = LoggerFactory.getLogger(CompanyWorkersCompensationController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

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

	@RequestMapping(value = "/showWorkersCompensationList", method = RequestMethod.GET)
	public ModelAndView showWorkersCompensationList() throws InstanceNotFoundException {
		logger.debug(">>Show Workers Compensation List");
		Company company = getCompanyFromSession();
		ModelAndView modelView = new ModelAndView("companyWorkersCompensation");
		// List<WorkersCompensationTaxRateView> compensationTaxRateViewList =
		// companyService
		// .getWorkersCompensationRate(company.getId());
		List<CompanyWorkerCompensation> companyWorkerCompensations = companyService
				.getWorkersCompensationList(company.getId());
		List<WorkersCompensationTaxRate> workersCompensationTaxRates = new ArrayList<>();
		for (CompanyWorkerCompensation companyWorkerCompensation : companyWorkerCompensations) {
			WorkersCompensationTaxRate workersCompensationTaxRate = companyService
					.getWorkersCompensationRateList(companyWorkerCompensation.getId());
			workersCompensationTaxRates.add(workersCompensationTaxRate);
		}
		modelView.addObject("workersCompensationTaxRates", workersCompensationTaxRates);
		logger.debug("workersCompensationTaxRates:::" + workersCompensationTaxRates);
		logger.debug("companyWorkerCompensations:::" + companyWorkerCompensations);
		modelView.addObject("companyWorkerCompensations", companyWorkerCompensations);
		modelView.addObject("companyWorkersCompensationForm", new CompanyWorkersCompensationForm());
		logger.debug("Show Workers Compensation List>>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_WORKERS_COMPENSATION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showWorkersCompensationAddForm", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse showWorkersCompensationAddForm() {
		logger.debug(">>Show Workers Compensation Add Form");
		// ModelAndView modelView = new
		// ModelAndView("companyWorkersCompensationForm");
		List<UsState> usStates = companyService.getUsStates();
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setData(usStates);
		logger.debug("Show Workers Compensation Add Form >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_WORKERS_COMPENSATION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addWorkersCompensation", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse addWorkersCompensation(
			@Valid @ModelAttribute CompanyWorkersCompensationForm companyWorkersCompensationForm,
			BindingResult result) throws InstanceNotFoundException {
		logger.debug(">>Add Workers Compensation");
		ServerResponse serverResponse = new ServerResponse();
		logger.debug("result.hasErrors()" + result.hasErrors());
		if (!result.hasErrors()) {
			Company company = getCompanyFromSession();
			Long companyWorkersCompensationId = companyService.addWorkersCompensation(company,
					companyWorkersCompensationForm);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(companyWorkersCompensationId);
		}
		logger.debug("Add Workers Compensation>>");
		return serverResponse;
	}

	@RequestMapping(value = "/showWorkersCompensationForm", method = RequestMethod.GET)
	public @ResponseBody
	ServerResponse showWorkersCompensationForm(@RequestParam Long companyWorkersCompensationId) {
		logger.debug(">>Show Workers Compensation Form");
		Company company = getCompanyFromSession();
		ServerResponse serverResponse = new ServerResponse();
		List<UsState> usStates = companyService.getUsStates();
		CompanyWorkersCompensationModel companyWorkersCompensationModel = new CompanyWorkersCompensationModel();
		CompanyWorkersCompensationForm companyWorkersCompensationForm = companyService
				.getWorkersCompensationForm(company, companyWorkersCompensationId);
		companyWorkersCompensationForm.setCompanyId(company.getId());
		companyWorkersCompensationModel.setUsStates(usStates);
		companyWorkersCompensationModel
				.setCompanyWorkersCompensationForm(companyWorkersCompensationForm);
		serverResponse.setData(companyWorkersCompensationModel);
		logger.debug("Show Workers Compensation Form>>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_WORKERS_COMPENSATION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateWorkersCompensation", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse updateWorkersCompensation(
			@ModelAttribute @Valid CompanyWorkersCompensationForm companyWorkersCompensationForm,
			BindingResult result) throws InstanceNotFoundException {
		logger.debug(">>Update Workers Compensation");
		ServerResponse serverResponse = new ServerResponse();
		CompanyWorkerCompensation companyWorkerCompensation = new CompanyWorkerCompensation();
		if (!result.hasErrors()) {
			Company company = getCompanyFromSession();
			companyWorkerCompensation = companyService.updateWorkersCompensation(company,
					companyWorkersCompensationForm);
			serverResponse.setData(companyWorkerCompensation);
		}
		logger.debug("Update Workers Compensation>>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_WORKERS_COMPENSATION, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteWorkersCompensation", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse deleteWorkersCompensation(@RequestParam Long id) {
		logger.debug(">>Delete Workers Compensation");
		ServerResponse serverResponse = new ServerResponse();
		try {
			if (companyService.deleteWorkersCompensation(id)) {
				serverResponse.setStatus(ServerStatus.SUCCESS);
			} else {
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} catch (Exception e) {
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("Delete Workers Compensation>>");
		return serverResponse;
	}

}