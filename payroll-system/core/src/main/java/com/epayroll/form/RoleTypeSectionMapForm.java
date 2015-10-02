package com.epayroll.form;

import java.util.Arrays;

import com.epayroll.constant.Section;
import com.epayroll.entity.Role.RoleType;

public class RoleTypeSectionMapForm {

	private RoleType roleType;
	private Section[] sections;

	public RoleTypeSectionMapForm() {
	}

	public RoleTypeSectionMapForm(RoleType roleType, Section[] sections) {
		super();
		this.roleType = roleType;
		this.sections = sections;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Section[] getSections() {
		return sections;
	}

	public void setSections(Section[] sections) {
		this.sections = sections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleTypeSectionMapForm [roleType=");
		builder.append(roleType);
		builder.append(", sections=");
		builder.append(Arrays.toString(sections));
		builder.append("]");
		return builder.toString();
	}

}
