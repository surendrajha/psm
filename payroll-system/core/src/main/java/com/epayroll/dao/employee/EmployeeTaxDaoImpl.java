package com.epayroll.dao.employee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.EmployeeAllowanceRecord;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.model.EmployeeW4DetailModel;

@Repository
public class EmployeeTaxDaoImpl extends GenericDaoImpl<EmployeeTax, Long> implements EmployeeTaxDao {
	private Logger logger = LoggerFactory.getLogger(EmployeeTaxDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeTax> getFederalTaxList(Long employeeId) {
		logger.debug(">> getFederalTaxList");
		Query query = getSession()
				.createQuery(
						"select e from EmployeeTax e inner join e.taxType et inner join et.taxAuthority eta "
								+ "where eta.usState IS NULL and eta.usCounty IS NULL and e.employee.id=? and e.id not in "
								+ "(select ear.employeeTax.id from EmployeeAllowanceRecord ear where ear.employeeTax.employee.id=?)");
		query.setLong(0, employeeId);
		query.setLong(1, employeeId);
		List<EmployeeTax> federalTaxList = query.list();
		logger.debug("federalTaxList ::" + federalTaxList);
		logger.debug("getFederalTaxList >>");
		return federalTaxList;
	}

	@Override
	public EmployeeW4DetailModel getFITRecord(Long employeeId) {
		logger.debug(">> getFITRecord");
		Query query = getSession().createQuery(
				"select e, ear from EmployeeTax e inner join e.taxType et "
						+ "inner join et.taxAuthority eta, EmployeeAllowanceRecord ear "
						+ "where eta.usState IS NULL and eta.usCounty IS NULL and "
						+ "e.employee.id=? and e.id=ear.employeeTax.id");
		query.setLong(0, employeeId);
		Object[] objects = (Object[]) query.uniqueResult();

		logger.debug("objects" + objects);

		List<EmployeeAllowanceRecord> employeeAllowanceRecords = new ArrayList<>();
		employeeAllowanceRecords.add((EmployeeAllowanceRecord) objects[1]);
		EmployeeW4DetailModel employeeW4DetailModel = new EmployeeW4DetailModel(
				(EmployeeTax) objects[0], employeeAllowanceRecords);
		logger.debug("employeeFITDetail::" + employeeW4DetailModel);
		logger.debug("getFITRecord >>");
		return employeeW4DetailModel;
	}

	@Override
	public List<EmployeeTax> getStateTaxList(Long employeeId) {
		logger.debug(">> getStateTaxList");
		Query query = getSession()
				.createQuery(
						"select et from EmployeeTax et inner join et.employee e inner join e.addresses addrs "
								+ " where e.id=:empId and addrs.addressType.type=:addrType and "
								+ "et.id not in (select ear.employeeTax.id from EmployeeAllowanceRecord ear where ear.employeeTax.employee.id=:empId1) and "
								+ "et.taxType.id in (select t.id from TaxType t INNER JOIN t.taxAuthority ta "
								+ "where ta.usCounty IS NULL AND ta.usState.id=addrs.usState.id)");
		query.setParameter("empId", employeeId);
		query.setParameter("addrType", AddressType.WORK);
		query.setParameter("empId1", employeeId);
		@SuppressWarnings("unchecked")
		List<EmployeeTax> stateTaxList = query.list();
		logger.debug("stateTaxList ::" + stateTaxList);
		logger.debug("getStateTaxList >>");
		return stateTaxList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EmployeeW4DetailModel getSITRecord(Long employeeId) {
		logger.debug(">> getSITRecord");
		Query query = getSession()
				.createQuery(
						"select et, et.employeeW4Detail, ear from EmployeeTax et inner join et.employee e inner join e.addresses addrs, "
								+ "EmployeeAllowanceRecord ear "
								+ " where e.id=:empId and addrs.addressType.type=:addrType and et.id=ear.employeeTax.id and "
								+ "et.taxType.id in (select t.id from TaxType t INNER JOIN t.taxAuthority ta "
								+ "where ta.usCounty IS NULL AND ta.usState.id=addrs.usState.id)");
		query.setParameter("empId", employeeId);
		query.setParameter("addrType", AddressType.WORK);
		List<Object[]> objects = query.list();
		EmployeeW4DetailModel employeeSITDetail = new EmployeeW4DetailModel();
		employeeSITDetail.setEmployeeTax((EmployeeTax) objects.get(0)[0]);
		List<EmployeeAllowanceRecord> employeeAllowanceRecords = new ArrayList<>();
		for (Object[] objectArray : objects) {
			employeeAllowanceRecords.add((EmployeeAllowanceRecord) objectArray[1]);
		}
		employeeSITDetail.setEmployeeAllowanceRecords(employeeAllowanceRecords);
		logger.debug("employeeSITDetail ::" + employeeSITDetail);
		logger.debug("getSITRecord >>");
		return employeeSITDetail;
	}

	@Override
	public List<EmployeeTax> getLocalTaxList(Long employeeId) {
		logger.debug(">> getLocalTaxList");
		Query query = getSession()
				.createQuery(
						"select et from EmployeeTax et inner join et.employee e inner join e.addresses addrs "
								+ " where e.id=:empId and addrs.addressType.type=:addrType and "
								+ "et.taxType.id in (select t.id from TaxType t INNER JOIN t.taxAuthority ta "
								+ "where ta.usState.id=addrs.usState.id AND ta.usCounty.id=addrs.usCounty.id)");
		query.setParameter("empId", employeeId);
		query.setParameter("addrType", AddressType.WORK);
		@SuppressWarnings("unchecked")
		List<EmployeeTax> localTaxList = query.list();
		logger.debug("localTaxList::" + localTaxList);
		logger.debug("getLocalTaxList >>");
		return localTaxList;
	}

	@Override
	public List<EmployeeAllowanceRecord> getAllowanceRecords(Long employeeTaxId) {
		logger.debug(">> getEmployeeTaxListForUpdate");
		Query query = getSession().createQuery(
				"select ear from EmployeeAllowanceRecord ear where ear.employeeTax.id=:empTaxId ");
		query.setParameter("empTaxId", employeeTaxId);
		@SuppressWarnings("unchecked")
		List<EmployeeAllowanceRecord> employeeAllowanceRecords = query.list();
		logger.debug("employeeAllowanceRecord:::::" + employeeAllowanceRecords);
		logger.debug("getEmployeeTaxListForUpdate >>");
		return employeeAllowanceRecords;
	}

	// @Override
	// public EmployeeTax getEmployeeTaxListForUpdate(Long employeeTaxId) {
	// logger.debug(">> getEmployeeTaxListForUpdate");
	// Query query = getSession()
	// .createQuery(
	// "select e, ewd, ear from EmployeeTax e inner join e.employeeW4Detail ewd,EmployeeAllowanceRecord ear where e.id=:empTaxId ");
	// query.setParameter("empTaxId", employeeTaxId);
	// EmployeeTax employeeTaxListForUpdate = (EmployeeTax)
	// query.uniqueResult();
	// logger.debug("employeeTaxListForUpdate:::::" + employeeTaxListForUpdate);
	// logger.debug("getEmployeeTaxListForUpdate >>");
	// return employeeTaxListForUpdate;
	// }

	/**
	 * returns the list of employeeTax which not present in employeeW4Details
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeTax> getEmployeeTaxs(Long employeeId) {
		return getCriteria()
				.createAlias("employeeW4Details", "ew", CriteriaSpecification.LEFT_JOIN)
				.add(Restrictions.isNull("ew.id")).add(Restrictions.eq("employee.id", employeeId))
				.list();
	}

}
