/**
 * 
 */
package com.epayroll.ws.endpoint;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epayroll.ws.pojo.ObjectFactory;
import com.epayroll.ws.pojo.PayrollRequest;
import com.epayroll.ws.pojo.PayrollResponse;
import com.epayroll.ws.service.PayrollWebService;

/**
 * @author Surendra Jha
 */
@Endpoint
public class PayrollWSEndpoint {

	private Logger logger = LoggerFactory.getLogger(PayrollWSEndpoint.class);

	@Autowired
	private PayrollWebService payrollWebService;

	@PayloadRoot(localPart = "PayrollRequest", namespace = "http://www.i-techsoftware.com/vjs-payroll")
	@ResponsePayload
	public PayrollResponse getPayroll(@RequestPayload PayrollRequest request) {
		logger.info("Try to calculate payroll ... ");
		PayrollResponse response = null;
		logger.debug("request::" + request);
		response = createResponse(request);
		return response;
	}

	private PayrollResponse createResponse(PayrollRequest request) {
		logger.debug("in createResponse");
		PayrollResponse response = new ObjectFactory().createPayrollResponse();

		payrollWebService.testMethod(request.getGrossPay().longValue());

		response.setMonthlyGrossPay(request.getGrossPay());
		response.setFIT(new BigDecimal("12.525"));
		response.setMedicare(new BigDecimal("123.21"));
		response.setNetPay(new BigDecimal("12.321"));
		response.setSIT(new BigDecimal("21.252"));
		response.setSocialSecurity(new BigDecimal("054.25"));

		return response;
	}
}
