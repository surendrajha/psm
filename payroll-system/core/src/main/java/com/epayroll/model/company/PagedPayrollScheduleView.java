package com.epayroll.model.company;

import java.util.List;

import com.epayroll.entity.PayrollSchedule;

public class PagedPayrollScheduleView {

	private NavigationInfo navInfo = new NavigationInfo();

	private List<PayrollSchedule> payrollSchedules;

	public NavigationInfo getNavInfo() {
		return navInfo;
	}

	public void setNavInfo(NavigationInfo navInfo) {
		this.navInfo = navInfo;
	}

	public List<PayrollSchedule> getPayrollSchedules() {
		return payrollSchedules;
	}

	public void setPayrollSchedules(List<PayrollSchedule> payrollSchedules) {
		this.payrollSchedules = payrollSchedules;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PagedPayrollScheduleView [navInfo=");
		builder.append(navInfo);
		builder.append(", payrollSchedules=");
		builder.append(payrollSchedules);
		builder.append("]");
		return builder.toString();
	}

}
