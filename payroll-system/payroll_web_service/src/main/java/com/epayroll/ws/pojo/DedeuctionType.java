//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.12 at 10:26:30 AM EST 
//

package com.epayroll.ws.pojo;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for DedeuctionType complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="DedeuctionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="Amount" type="{http://www.i-techsoftware.com/vjs-payroll}DecimalType"/>
 *         &lt;element name="Type" type="{http://www.i-techsoftware.com/vjs-payroll}Type"/>
 *         &lt;element name="IsExempted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DedeuctionType", propOrder = { "name", "amount", "type", "isExempted" })
public class DedeuctionType {

	@XmlElement(name = "Name", required = true, nillable = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "token")
	protected String name;
	@XmlElement(name = "Amount", required = true, defaultValue = "0.0", nillable = true)
	protected BigDecimal amount;
	@XmlElement(name = "Type", required = true, defaultValue = "% of Gross Pay")
	protected Type type;
	@XmlElement(name = "IsExempted", defaultValue = "false")
	protected boolean isExempted;

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the amount property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 */
	public void setAmount(BigDecimal value) {
		this.amount = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link Type }
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link Type }
	 */
	public void setType(Type value) {
		this.type = value;
	}

	/**
	 * Gets the value of the isExempted property.
	 */
	public boolean isIsExempted() {
		return isExempted;
	}

	/**
	 * Sets the value of the isExempted property.
	 */
	public void setIsExempted(boolean value) {
		this.isExempted = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DedeuctionType [name=");
		builder.append(name);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", type=");
		builder.append(type);
		builder.append(", isExempted=");
		builder.append(isExempted);
		builder.append("]");
		return builder.toString();
	}

}