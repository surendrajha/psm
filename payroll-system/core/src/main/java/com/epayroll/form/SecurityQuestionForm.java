package com.epayroll.form;

import java.util.List;

import com.epayroll.entity.SecurityQuestion;

public class SecurityQuestionForm {

	private Long id;
	private List<SecurityQuestion> group1;
	private List<SecurityQuestion> group2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SecurityQuestion> getGroup1() {
		return group1;
	}

	public void setGroup1(List<SecurityQuestion> group1) {
		this.group1 = group1;
	}

	public List<SecurityQuestion> getGroup2() {
		return group2;
	}

	public void setGroup2(List<SecurityQuestion> group2) {
		this.group2 = group2;
	}

	@Override
	public String toString() {
		return "SecurityQuestionForm [id=" + id + ", group1=" + group1 + ", group2=" + group2 + "]";
	}

}
