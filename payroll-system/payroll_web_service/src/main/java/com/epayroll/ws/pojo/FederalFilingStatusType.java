//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.12 at 10:26:30 AM EST 
//

package com.epayroll.ws.pojo;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for FederalFilingStatusType.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="FederalFilingStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Single"/>
 *     &lt;enumeration value="Married"/>
 *     &lt;enumeration value="Head of Household"/>
 *     &lt;enumeration value="Supplemental"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "FederalFilingStatusType")
@XmlEnum
public enum FederalFilingStatusType {

	@XmlEnumValue("Single")
	SINGLE("Single"), @XmlEnumValue("Married")
	MARRIED("Married"), @XmlEnumValue("Head of Household")
	HEAD_OF_HOUSEHOLD("Head of Household"), @XmlEnumValue("Supplemental")
	SUPPLEMENTAL("Supplemental");
	private final String value;

	FederalFilingStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static FederalFilingStatusType fromValue(String v) {
		for (FederalFilingStatusType c : FederalFilingStatusType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}