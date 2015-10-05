package com.epayroll.form.adminConsole;

import java.util.Date;
import java.util.List;

import com.epayroll.model.adminConsole.MedicareSocialSecurityModel;

/**
 * @author Rajul Tiwari
 */
public class MedicareSocialSecurityForm {

	private Date wef;
	private List<MedicareSocialSecurityModel> medicareSocialSecurityModels;

	public MedicareSocialSecurityForm() {
	}

	public MedicareSocialSecurityForm(Date wef,
			List<MedicareSocialSecurityModel> medicareSocialSecurityModels) {
		super();
		this.wef = wef;
		this.medicareSocialSecurityModels = medicareSocialSecurityModels;
	}

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	public List<MedicareSocialSecurityModel> getMedicareSocialSecurityModels() {
		return medicareSocialSecurityModels;
	}

	public void setMedicareSocialSecurityModels(
			List<MedicareSocialSecurityModel> medicareSocialSecurityModels) {
		this.medicareSocialSecurityModels = medicareSocialSecurityModels;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedicareSocialSecurityForm [wef=");
		builder.append(wef);
		builder.append(", medicareSocialSecurityModels=");
		builder.append(medicareSocialSecurityModels);
		builder.append("]");
		return builder.toString();
	}

}