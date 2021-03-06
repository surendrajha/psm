package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * UsState generated by hbm2java
 */
@Entity
@Table(name = "us_state")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@JsonIgnoreProperties({ "typedeductionCaps", "companyWorkerCompensations", "taxAuthorities",
		"usCities", "usCounties", "allowanceRates", "standardDeductionRates",
		"federalStateHolidays", "addresses" })
public class UsState implements java.io.Serializable {

	private static final long serialVersionUID = -3569914473405354928L;

	private Long id;
	@NotEmpty
	private String stateName;
	private Set<DeductionCap> typedeductionCaps = new HashSet<DeductionCap>(0);
	private Set<CompanyWorkerCompensation> companyWorkerCompensations = new HashSet<CompanyWorkerCompensation>(
			0);
	private Set<TaxAuthority> taxAuthorities = new HashSet<TaxAuthority>(0);
	private Set<UsCity> usCities = new HashSet<UsCity>(0);
	private Set<UsCounty> usCounties = new HashSet<UsCounty>(0);
	private Set<AllowanceRate> allowanceRates = new HashSet<AllowanceRate>(0);
	private Set<StandardDeductionRate> standardDeductionRates = new HashSet<StandardDeductionRate>(
			0);
	private Set<FederalStateHoliday> federalStateHolidays = new HashSet<FederalStateHoliday>(0);
	private Set<Address> addresses = new HashSet<Address>(0);

	public UsState() {
	}

	public UsState(String stateName) {
		this.stateName = stateName;
	}

	public UsState(String stateName, Set<DeductionCap> typedeductionCaps,
			Set<TaxAuthority> taxAuthorities, Set<UsCity> usCities, Set<UsCounty> usCounties,
			Set<AllowanceRate> allowanceRates, Set<StandardDeductionRate> standardDeductionRates,
			Set<Address> addresses, Set<CompanyWorkerCompensation> companyWorkerCompensations) {
		this.stateName = stateName;
		this.typedeductionCaps = typedeductionCaps;
		this.taxAuthorities = taxAuthorities;
		this.usCities = usCities;
		this.setUsCounties(usCounties);
		this.allowanceRates = allowanceRates;
		this.standardDeductionRates = standardDeductionRates;
		this.addresses = addresses;
		this.companyWorkerCompensations = companyWorkerCompensations;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "state_name", unique = true)
	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<DeductionCap> getTypedeductionCaps() {
		return this.typedeductionCaps;
	}

	public void setTypedeductionCaps(Set<DeductionCap> typedeductionCaps) {
		this.typedeductionCaps = typedeductionCaps;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<UsCity> getUsCities() {
		return usCities;
	}

	public void setUsCities(Set<UsCity> usCities) {
		this.usCities = usCities;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<UsCounty> getUsCounties() {
		return usCounties;
	}

	public void setUsCounties(Set<UsCounty> usCounties) {
		this.usCounties = usCounties;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<AllowanceRate> getAllowanceRates() {
		return this.allowanceRates;
	}

	public void setAllowanceRates(Set<AllowanceRate> allowanceRates) {
		this.allowanceRates = allowanceRates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<StandardDeductionRate> getStandardDeductionRates() {
		return this.standardDeductionRates;
	}

	public void setStandardDeductionRates(Set<StandardDeductionRate> standardDeductionRates) {
		this.standardDeductionRates = standardDeductionRates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<FederalStateHoliday> getFederalStateHolidays() {
		return federalStateHolidays;
	}

	public void setFederalStateHolidays(Set<FederalStateHoliday> federalStateHolidays) {
		this.federalStateHolidays = federalStateHolidays;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<CompanyWorkerCompensation> getCompanyWorkerCompensations() {
		return this.companyWorkerCompensations;
	}

	public void setCompanyWorkerCompensations(
			Set<CompanyWorkerCompensation> companyWorkerCompensations) {
		this.companyWorkerCompensations = companyWorkerCompensations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usState")
	public Set<TaxAuthority> getTaxAuthorities() {
		return this.taxAuthorities;
	}

	public void setTaxAuthorities(Set<TaxAuthority> taxAuthorities) {
		this.taxAuthorities = taxAuthorities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsState [id=");
		builder.append(id);
		builder.append(", stateName=");
		builder.append(stateName);
		builder.append("]");
		return builder.toString();
	}

}
