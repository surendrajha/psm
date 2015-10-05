package com.epayroll.ui.contoller;

import java.util.Map;

import javax.validation.Valid;

import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyUserService;

@Controller
@RequestMapping("/state")
public class StateContoller {

	private Logger logger = LoggerFactory.getLogger(StateContoller.class);

	@Autowired
	private CompanyUserService companyUserService;

	@Autowired
	private CommonService commonService;

	@RequestMapping("/getStates")
	public String listStates(Map<String, Object> map) {
		logger.debug(" >> getStates");
		map.put("state", new UsState());
		map.put("stateList", commonService.findStates());
		logger.debug(" getStates >> ");
		return "state";
	}

	@PreAuthorize("hasRole('US_STATE-has-INSERT')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addstate(@ModelAttribute @Valid UsState state, BindingResult result) {
		logger.debug("adding state ..." + state);
		commonService.addState(state);
		logger.info("US state added!");
		return "stateReditect";
	}

	@PreAuthorize("hasRole('US_STATE-has-DELETE')")
	@RequestMapping("/delete/{stateId}")
	public String deleteState(@PathVariable("stateId") Long stateId)
			throws InstanceNotFoundException {
		logger.debug("try to deleting State of stateID:" + stateId);
		commonService.removeState(stateId);
		logger.debug("deleted ! ");
		return "stateReditect";
	}

	@RequestMapping(value = "/showCityReportForm", method = RequestMethod.GET)
	public String showCityReportForm() {
		return "cityReport";
	}

	@RequestMapping(value = "/cityReport", method = RequestMethod.GET)
	public ModelAndView cityReport(@RequestParam("type") String type, ModelMap model) {
		logger.debug("Received request to download city report");

		JRDataSource datasource = commonService.getCityReport();

		// Add our datasource parameter
		model.addAttribute("datasource", datasource);
		// Add the report format
		model.addAttribute("format", type);

		// multiReport is the View of our application
		// This is declared inside the /WEB-INF/jasper-views.xml
		ModelAndView modelAndView = new ModelAndView("multiReport", model);

		// Return the View and the Model combined
		return modelAndView;
	}

}
