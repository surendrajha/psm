package com.epayroll.dao.employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.Payroll;
import com.epayroll.form.employee.PaymentCheckInfoForm;

@Repository
@SuppressWarnings("unchecked")
public class EmployeePayrollDaoImpl extends GenericDaoImpl<EmployeePayroll, Long> implements
		EmployeePayrollDao {
	private Logger logger = LoggerFactory.getLogger(EmployeePayrollDaoImpl.class);

	@Override
	public List<Date> getpayDates(Long employeeId) {
		logger.debug(">> getpayDates");
		String hqlQuery = "select empPay.payroll.checkDate from EmployeePayroll empPay where empPay.employee.id=? order by empPay.payroll.checkDate DESC";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, employeeId);
		List<Date> payDateList = query.list();
		logger.debug("payDateList ::" + payDateList);
		logger.debug("getpayDates >>");
		return payDateList;
	}

	@Override
	public List<EmployeePayroll> getPaymentes(Long employeeId) {
		logger.debug(">> getPaymentes");
		String hqlQuery = "select empPay from EmployeePayroll empPay where empPay.employee.id=?";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, employeeId);
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<EmployeePayroll> employeePayrollList = query.list();
		logger.debug("EmployeePayrollList ::" + employeePayrollList);
		logger.debug("getPaymentes >>");
		return employeePayrollList;
	}

	@Override
	public List<EmployeePayroll> getSearchedPaymentes(Long employeeId,
			PaymentCheckInfoForm paymentCheckInfoForm) {
		logger.debug(">> getSearchedPaymentes");

		String hqlQuery = "select empPay from EmployeePayroll empPay where empPay.employee.id=?";
		Query query = null;
		if (paymentCheckInfoForm.getFromPayDate().equals(paymentCheckInfoForm.getToPayDate())) {
			hqlQuery += " and empPay.payroll.checkDate=:fromDate";
			query = getSession().createQuery(hqlQuery);
			query.setLong(0, employeeId);
			query.setParameter("fromDate", paymentCheckInfoForm.getFromPayDate());
		} else if (paymentCheckInfoForm.getFromPayDate()
				.before(paymentCheckInfoForm.getToPayDate())) {
			logger.debug("before ::::");
			hqlQuery += " and empPay.payroll.checkDate between :fromDate and :toDate order by empPay.payroll.checkDate DESC";
			query = getSession().createQuery(hqlQuery);
			query.setLong(0, employeeId);
			query.setParameter("fromDate", paymentCheckInfoForm.getFromPayDate());
			query.setParameter("toDate", paymentCheckInfoForm.getToPayDate());
		} else if (paymentCheckInfoForm.getFromPayDate().after(paymentCheckInfoForm.getToPayDate())) {
			logger.debug("after ::::");
			hqlQuery += " and empPay.payroll.checkDate between :toDate and :fromDate order by empPay.payroll.checkDate DESC";
			query = getSession().createQuery(hqlQuery);
			query.setLong(0, employeeId);
			query.setParameter("toDate", paymentCheckInfoForm.getToPayDate());
			query.setParameter("fromDate", paymentCheckInfoForm.getFromPayDate());
		} else {
			query = getSession().createQuery(hqlQuery);
			query.setLong(0, employeeId);
		}
		List<EmployeePayroll> employeePayrollList = query.list();
		logger.debug("SearchedPaymentList ::" + employeePayrollList);
		logger.debug("getSearchedPaymentes >>");
		return employeePayrollList;
	}

	@Override
	public List<Payroll> getPayrollCheckDateList(Long employeeId, Integer year) {
		logger.debug(">> getCheckDateList");
		String hqlQuery = "select new Payroll(empPay.payroll.id, empPay.payroll.checkDate,empPay.payroll.startDate,empPay.payroll.endDate) "
				+ " from EmployeePayroll "
				+ "empPay where empPay.employee.id=? and "
				+ "year(empPay.payroll.checkDate) = ? order by empPay.payroll.checkDate DESC ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, employeeId);
		query.setInteger(1, year);
		List<Payroll> payrollCheckDateList = query.list();
		logger.debug("payrollCheckDateList ::" + payrollCheckDateList);
		logger.debug("getCheckDateList >>");
		return payrollCheckDateList;
	}

	@Override
	public BigDecimal getGrossSalaryPaidY2D(Long employeeId) {
		Criteria criteria = getSession().createCriteria(EmployeePayroll.class, "empPayroll");
		criteria.add(Restrictions.eq("employee.id", employeeId));
		criteria.setProjection(Projections.sum("grossPay"));
		return (BigDecimal) criteria.uniqueResult();
	}

	@Override
	public List<EmployeePayroll> getEmployeePayroll(Long payrollId) {
		logger.debug(">> getEmployeePayroll");
		return getCriteria().add(Restrictions.eq("payroll.id", payrollId)).list();
	}

	@Override
	public List<EmployeePayroll> getEmployeePayrollValues(Long employeeId, Long payrollId) {
		logger.debug(">> getEmployeePayrollvalues");

		Query query = getSession()
				.createQuery(
						"select new EmployeePayroll( ep.salary, ep.hourlyRate, ep.regularHours, ep.otherHours, ep.grossPay, ep.netDirectDeposit, ep.netCheckAmount, ep.totalDeduction, ep.totalTax, ep.rowType ) "
								+ "from EmployeePayroll ep "
								+ "where ep.employee.id=? AND ep.payroll.id=? ");
		query.setLong(0, employeeId);
		query.setLong(1, payrollId);
		List<EmployeePayroll> employeePayrollValues = query.list();
		logger.debug("employeePayrollValues :: " + employeePayrollValues);

		logger.debug("getEmployeePayrollvalues >>");
		return employeePayrollValues;
	}

}
