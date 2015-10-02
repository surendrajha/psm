package com.epayroll.ws.service;

/**
 * @author Surendra Jha
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.service.payroll.PayrollService;

@Service
public class PayrollWebServiceImpl implements PayrollWebService {

	private Logger logger = LoggerFactory.getLogger(PayrollWebServiceImpl.class);

	@Autowired
	private PayrollService payrollService;

	@Override
	public String testMethod(Long long1) {
		logger.debug("in testMethod .. " + long1);

		return "SUCCESS";
	}
}
