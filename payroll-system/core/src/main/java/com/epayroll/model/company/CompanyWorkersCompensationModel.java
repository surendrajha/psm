package com.epayroll.model.company;

import java.util.List;

import com.epayroll.entity.UsState;
import com.epayroll.form.company.CompanyWorkersCompensationForm;

/**
 * @author Uma
 */
public class CompanyWorkersCompensationModel {
	private List<UsState> usStates;
	private CompanyWorkersCompensationForm companyWorkersCompensationForm;

	public CompanyWorkersCompensationModel() {
	}

	public List<UsState> getUsStates() {
		return usStates;
	}

	public void setUsStates(List<UsState> usStates) {
		this.usStates = usStates;
	}

	public CompanyWorkersCompensationForm getCompanyWorkersCompensationForm() {
		return companyWorkersCompensationForm;
	}

	public void setCompanyWorkersCompensationForm(
			CompanyWorkersCompensationForm companyWorkersCompensationForm) {
		this.companyWorkersCompensationForm = companyWorkersCompensationForm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyWorkersCompensationModel [companyWorkersCompensationForm=");
		builder.append(companyWorkersCompensationForm);
		builder.append("]");
		return builder.toString();
	}
}
