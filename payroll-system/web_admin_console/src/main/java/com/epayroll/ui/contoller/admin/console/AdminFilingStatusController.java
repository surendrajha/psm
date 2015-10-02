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

import com.epayroll.entity.FilingStatus;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.FilingStatusForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@Controller
@RequestMapping("/adminConsole/filingStatus")
public class AdminFilingStatusController {
	private Logger logger = LoggerFactory.getLogger(AdminFilingStatusController.class);
	@Autowired
	private AdminSystemDataService adminSystemDataService;

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FILING_STATUS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showFilingStatusForm", method = RequestMethod.GET)
	public ModelAndView showFilingStatusForm() {
		logger.debug(">> showFilingStatusForm");
		ModelAndView modelView = new ModelAndView("filingStatusForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		modelView.addObject("filingStatusForm", new FilingStatusForm());
		logger.debug("showFilingStatusForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FILING_STATUS, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addFilingStatus", method = RequestMethod.POST)
	public ModelAndView addFilingStatus(@Valid @ModelAttribute FilingStatusForm filingStatusForm,
			BindingResult bindingResult) {
		logger.debug(">> addFilingStatus");
		ModelAndView modelView = new ModelAndView("filingStatusForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		if (!bindingResult.hasErrors()) {
			try {
				@SuppressWarnings("unused")
				Long id = adminSystemDataService.addFilingStatus(filingStatusForm);
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error:::" + bindingResult.toString());
		}
		logger.debug("addFilingStatus >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FILING_STATUS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateFilingStatusForm/{filingStatusId}", method = RequestMethod.GET)
	public ModelAndView showUpdateFilingStatusForm(@PathVariable Long filingStatusId,
			@ModelAttribute FilingStatusForm filingStatusForm) {
		logger.debug(">> showUpdateFilingStatusForm");
		ModelAndView modelView = new ModelAndView("filingStatusForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		try {
			// TODO set id in hidden variable
			modelView.addObject("filingStatusForm",
					adminSystemDataService.getFilingStatusForm(filingStatusId));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateFilingStatusForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FILING_STATUS, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateFilingStatus", method = RequestMethod.POST)
	public ModelAndView updateFilingStatus(
			@Valid @ModelAttribute FilingStatusForm filingStatusForm, BindingResult bindingResult) {
		logger.debug(">> updateFilingStatus");
		ModelAndView modelView = new ModelAndView("filingStatusForm");
		List<FilingStatus> filingStatusList = adminSystemDataService.getFilingStatusList();
		modelView.addObject("filingStatusList", filingStatusList);
		if (!bindingResult.hasErrors()) {
			try {
				adminSystemDataService.updateFilingStatus(filingStatusForm);
			} catch (InstanceNotFoundException e) {
				logger.error("ERROR :: " + e.getMessage());
			}
		} else {
			logger.error("Validation Error :::" + bindingResult.toString());
		}
		logger.debug("updateFilingStatus >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).FILING_STATUS, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteFilingStatus/{filingStatusId}", method = RequestMethod.GET)
	public ModelAndView deleteFilingStatus(@PathVariable Long filingStatusId) {
		logger.debug(">> deleteFilingStatus");
		ModelAndView modelView = new ModelAndView();
		String result = "";
		try {
			if (adminSystemDataService.verifyAndDeleteFilingStatus(filingStatusId)) {

				result = "Record Deleted Successfully";
			} else {
				result = "Filing Status Records are associated with this Other Entity. So you cannot delete this Filing Status from system. ";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = "Some error occurred while deleting the record : " + e.getMessage();
		}
		modelView.addObject("result", result);
		modelView.setViewName("filingStatusForm");
		logger.debug("deleteFilingStatus >>");
		return modelView;
	}

}
