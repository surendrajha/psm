/**
 * 
 */
package com.epayroll.service.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DispatchInvoice {

	private Logger logger = LoggerFactory.getLogger(DispatchInvoice.class);

	// @Scheduled()
	public void generateInvoice() {
		logger.debug("in generateInvoice..");
		// GenerateInvoice.printPdf("Invoice2013.pdf");
	}
}
