/**
 * 
 */
package com.epayroll.form.company;

import java.util.List;

import com.epayroll.model.company.CompanyStateLocalTaxModel;

/**
 * 
 * @author Uma
 * 
 */
public class CompanyStateLocalTaxFrom {
	private Long companyId;
	private List<CompanyStateLocalTaxModel> companyStateLocalTaxModels;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<CompanyStateLocalTaxModel> getCompanyStateLocalTaxModels() {
		return companyStateLocalTaxModels;
	}

	public void setCompanyStateLocalTaxModels(
			List<CompanyStateLocalTaxModel> companyStateLocalTaxModels) {
		this.companyStateLocalTaxModels = companyStateLocalTaxModels;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyStateLocalTaxFrom [companyId=");
		builder.append(companyId);
		builder.append(", companyStateLocalTaxModels=");
		builder.append(companyStateLocalTaxModels);
		builder.append("]");
		return builder.toString();
	}

}
