//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.12 at 10:26:30 AM EST 
//

package com.epayroll.ws.pojo;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.i_techsoftware.vjs_payroll package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.i_techsoftware.vjs_payroll
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PayrollResponse }
	 */
	public PayrollResponse createPayrollResponse() {
		return new PayrollResponse();
	}

	/**
	 * Create an instance of {@link DedeuctionType }
	 */
	public DedeuctionType createDedeuctionType() {
		return new DedeuctionType();
	}

	/**
	 * Create an instance of {@link PayrollRequest }
	 */
	public PayrollRequest createPayrollRequest() {
		return new PayrollRequest();
	}

}