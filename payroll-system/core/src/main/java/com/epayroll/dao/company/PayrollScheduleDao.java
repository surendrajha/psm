package com.epayroll.dao.company;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.PayrollFrequency.PayFrequencyType;
import com.epayroll.entity.PayrollSchedule;
import com.epayroll.entity.PayrollSchedule.Status;
import com.epayroll.exception.InstanceNotFoundException;

public interface PayrollScheduleDao extends GenericDao<PayrollSchedule, Long> {

	Criteria getCriteriaForPayrollSchedule(String payrollScheduleStatus, Long payrollFrequencyId);

	PayrollSchedule getCurrentPayrollSchedule(Long payrollFrequencyId);

	Long getRemainingPayrollCount(Long payrollFrequencyId);

	List<PayrollSchedule> getPayrollScheduleList(Long payrollFrequencyId);

	void setPayrollScheduleStatus(Long payrollScheduleId, Status status);

	PayrollSchedule getNextFuturePayrollSchedule(Long payrollFrequencyId);

	// List<PayrollSchedule> getPayrollSchedules(Long companyId);

	List<PayrollSchedule> getPreviousPayrollScheduleList(Date perioStartDate, Date periodEndDate,
			Long companyId);

	Long getPayrollScheduleRowCount(Long companyId);

	DetachedCriteria getCriteriaForPayrollSchedules(Long companyId);

	void deletePayrollSchedules(List<Long> payrollSchedulesUpdatedId,
			PayFrequencyType payFrequencyType, Long payrollFrequencyId)
			throws InstanceNotFoundException;

}