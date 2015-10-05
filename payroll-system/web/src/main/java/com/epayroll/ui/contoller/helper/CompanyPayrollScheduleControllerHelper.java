package com.epayroll.ui.contoller.helper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.epayroll.entity.PayrollSchedule;
import com.epayroll.model.company.NavigationInfo;
import com.epayroll.model.company.PagedPayrollScheduleView;
import com.epayroll.service.company.CompanyService;

@Component
public class CompanyPayrollScheduleControllerHelper {

	public PagedPayrollScheduleView getPagedUserView(Integer page, CompanyService companyService,
			Long companyId, HttpSession session) {
		/*
		 * Fetching total number of records as per candidate type from db.
		 */
		Long rowCount = companyService.getPayrollScheduleRowCount(companyId);
		PagedPayrollScheduleView pagedPayrollScheduleView = new PagedPayrollScheduleView();
		/*
		 * Setting NavigationInfo properties.
		 */
		NavigationInfo navigationInfo = pagedPayrollScheduleView.getNavInfo();
		pagedPayrollScheduleView.getNavInfo().setRowCount(rowCount);
		navigationInfo.setMaxIndices(6);
		navigationInfo.setPageSize(6);

		/*
		 * Setting current page.
		 */
		if (null == page) {
			pagedPayrollScheduleView.getNavInfo().setCurrentPage(0);
		} else {
			pagedPayrollScheduleView.getNavInfo().setCurrentPage(page);
		}
		/*
		 * Fetching paged records from database.
		 */
		List<PayrollSchedule> payrollSchedules = companyService.getPagedUserList(
				pagedPayrollScheduleView.getNavInfo().getCurrentPage(), pagedPayrollScheduleView
						.getNavInfo().getPageSize(), companyId);
		System.out.println("pagedPayrollScheduleView ........" + pagedPayrollScheduleView);
		System.out.println("payrollSchedules::::" + payrollSchedules);
		pagedPayrollScheduleView.setPayrollSchedules(payrollSchedules);
		return pagedPayrollScheduleView;
	}
}
