package com.epayroll.ui.contoller.company;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.UsCity;
import com.epayroll.entity.UsCounty;
import com.epayroll.model.ServerResponse;
import com.epayroll.service.CommonService;

/**
 * @author Basit
 */
@Controller
@RequestMapping("/common")
public class CommonDataController {
	private Logger logger = LoggerFactory.getLogger(CommonDataController.class);

	@Autowired
	private CommonService commonService;

	@RequestMapping(value = "/getCounty", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getCounty(@RequestParam Long usStateId) {
		logger.debug(">> getCounty");

		ServerResponse serverResponse = new ServerResponse();
		// Delegate to service to do the actual fetching
		try {
			List<UsCounty> counties = commonService.getCounties(usStateId);
			logger.debug("Counties : " + counties);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(counties);
		} catch (Exception e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("getCounty >>");
		return serverResponse;
	}

	@RequestMapping(value = "/getCity", method = RequestMethod.POST)
	public @ResponseBody
	ServerResponse getCity(@RequestParam Long usStateId) {
		logger.debug(">> getCity");
		ServerResponse serverResponse = new ServerResponse();
		// Delegate to service to do the actual fetching
		try {
			List<UsCity> cities = commonService.getCities(usStateId);
			logger.debug("cities : " + cities);
			serverResponse.setStatus(ServerStatus.SUCCESS);
			serverResponse.setData(cities);
		} catch (Exception e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("getCity >>");
		return serverResponse;
	}
}