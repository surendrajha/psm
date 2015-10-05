package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.company.AddressType;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Employee;
import com.epayroll.entity.Employee.Type;
import com.epayroll.entity.EmployeePayroll;
import com.epayroll.entity.EmployeeSection;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.ContractorSearchForm;
import com.epayroll.form.employee.EmployeeSearchForm;

@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Long> implements EmployeeDao {

	private Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Override
	public List<Object[]> getEmployeeList(Long companyId, Type employeeType) {

		logger.debug(">> getEmployeeList");
		String hqlQuery = "select e.person.firstName,e.person.ssn,empAddresses.streetAddress,empAddresses.usState.id,empAddresses.cityName,empAddresses.pinZip,e.person.phone,e.person.email, e.status,e.employeeAccess from Employee e INNER JOIN e.addresses empAddresses  where e.company.id=? and e.employeeType=:empType and empAddresses.addressType.type=:addrType";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, companyId);
		query.setParameter("empType", employeeType);
		query.setParameter("addrType", AddressType.WORK);
		@SuppressWarnings("unchecked")
		List<Object[]> employeeList = query.list();
		logger.debug("List of " + employeeType + "::" + employeeList);
		logger.debug("getEmployeeList >>");
		return employeeList;
	}

	@Override
	public List<Object[]> getSearchedEmployees(EmployeeSearchForm employeeSearchForm, Long companyId) {
		logger.debug(">> getSearchedEmployees");
		String bHQL = "select e.person.firstName,e.person.ssn,empAddresses.streetAddress,empAddresses.usState.id,empAddresses.cityName,empAddresses.pinZip,e.person.phone,e.person.email, e.status,e.employeeAccess from Employee e INNER JOIN e.addresses empAddresses  where empAddresses.addressType.type='"
				+ AddressType.WORK + "' and ";

		bHQL += " e.company.id=? and e.employeeType=? ";

		if (employeeSearchForm.getName() != null) {
			bHQL = bHQL + "and e.person.firstName=" + "'" + employeeSearchForm.getName() + "'";
		}
		if (employeeSearchForm.getSsn() != null) {
			bHQL = bHQL + "and e.person.ssn=" + "'" + employeeSearchForm.getSsn() + "'";
		}
		if (employeeSearchForm.getStatus() != null) {
			bHQL = bHQL + "and e.status=" + "'" + employeeSearchForm.getStatus() + "'";
		}
		if (employeeSearchForm.getPhone() != null) {
			bHQL = bHQL + "and e.person.phone=" + "'" + employeeSearchForm.getPhone() + "'";
		}
		if (employeeSearchForm.getAccessToEmployee() != null) {
			bHQL = bHQL + "and e.employeeAccess=" + "'" + employeeSearchForm.getAccessToEmployee()
					+ "'";
		}
		if (employeeSearchForm.getStreetAddress() != null) {
			bHQL = bHQL + "and empAddresses.streetAddress=" + "'"
					+ employeeSearchForm.getStreetAddress() + "'";
		}
		if (employeeSearchForm.getCityId() != null) {
			bHQL = bHQL + "and empAddresses.usCity.id=" + "'" + employeeSearchForm.getCityId()
					+ "'";
		}
		if (employeeSearchForm.getStateId() != null) {
			bHQL = bHQL + "and empAddresses.usState.id=" + "'" + employeeSearchForm.getStateId()
					+ "'";
		}
		if (employeeSearchForm.getZip() != null) {
			bHQL = bHQL + "and empAddresses.pinZip=" + "'" + employeeSearchForm.getZip() + "'";
		}

		logger.debug("Search HQL : " + bHQL);

		Query query = getSession().createQuery(bHQL);
		query.setLong(0, companyId);
		query.setString(1, Type.EMPLOYEE.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> employeeList = query.list();
		logger.debug("searchedEmployee::" + employeeList);
		logger.debug("getSearchedEmployees >>");
		return employeeList;
	}

	@Override
	public List<Object[]> getSearchedContractor(ContractorSearchForm contractorSearchForm,
			Long companyId) {
		logger.debug(">> getSearchedContractor");
		String bHQL = "select e.person.firstName,e.person.ssn,empAddresses.streetAddress,empAddresses.usState.id,empAddresses.cityName,empAddresses.pinZip,e.person.phone,e.person.email, e.status,e.employeeAccess from Employee e INNER JOIN e.addresses empAddresses  where empAddresses.addressType.type='"
				+ AddressType.WORK + "' and ";

		bHQL += " e.company.id=? and e.employeeType=? ";

		if (contractorSearchForm.getName() != null) {
			bHQL = bHQL + "and e.person.firstName=" + "'" + contractorSearchForm.getName() + "'";
		}
		if (contractorSearchForm.getSsn() != null) {
			bHQL = bHQL + "and e.person.ssn=" + "'" + contractorSearchForm.getSsn() + "'";
		}
		if (contractorSearchForm.getStatus() != null) {
			bHQL = bHQL + "and e.status=" + "'" + contractorSearchForm.getStatus() + "'";
		}
		if (contractorSearchForm.getPhone() != null) {
			bHQL = bHQL + "and e.person.phone=" + "'" + contractorSearchForm.getPhone() + "'";
		}
		if (contractorSearchForm.getAccessToEmployee() != null) {
			bHQL = bHQL + "and e.employeeAccess=" + "'"
					+ contractorSearchForm.getAccessToEmployee() + "'";
		}
		if (contractorSearchForm.getStreetAddress() != null) {
			bHQL = bHQL + "and empAddresses.streetAddress=" + "'"
					+ contractorSearchForm.getStreetAddress() + "'";
		}
		if (contractorSearchForm.getCityId() != null) {
			bHQL = bHQL + "and empAddresses.usCity.id=" + "'" + contractorSearchForm.getCityId()
					+ "'";
		}
		if (contractorSearchForm.getStateId() != null) {
			bHQL = bHQL + "and empAddresses.usState.id=" + "'" + contractorSearchForm.getStateId()
					+ "'";
		}
		if (contractorSearchForm.getZip() != null) {
			bHQL = bHQL + "and empAddresses.pinZip=" + "'" + contractorSearchForm.getZip() + "'";
		}

		logger.debug("Search HQL : " + bHQL);

		Query query = getSession().createQuery(bHQL);
		query.setLong(0, companyId);
		query.setString(1, Type.CONTRACTOR.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> contractorList = query.list();
		logger.debug("searchedContractor::" + contractorList);
		logger.debug("getSearchedContractor >>");
		return contractorList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getCities() {
		logger.debug(">> getCities");
		String bHQL = "select distinct empAddresses.cityName from Employee e Inner Join e.addresses empAddresses";
		Query query = getSession().createQuery(bHQL);
		List<String> cityList = query.list();
		logger.debug("cityList::" + cityList);
		logger.debug("getCities >>");
		return cityList;
	}

	@Override
	public List<EmployeeSection> getEmployeeSectionList(Long companyId) {
		String hqlQuery = "select employeeSection.sectionName from Company c Inner Join c.employeeSections employeeSection";
		Query query = getSession().createQuery(hqlQuery);
		@SuppressWarnings("unchecked")
		List<EmployeeSection> employeeSections = query.list();
		return employeeSections;
	}

	@Override
	public Employee getEmployee(String userName) throws InstanceNotFoundException {
		Employee user = (Employee) getCriteria().add(Restrictions.eq("userName", userName))
				.uniqueResult();
		logger.debug("Employee User::" + user);
		if (user == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("UserName [ ").append(userName).append(" ] ");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return user;
	}

	@Override
	public Criteria getCriteriaForEmployee(Long employeeId, String currentPassword) {
		logger.debug(">> getCriteriaForEmployee");
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq("id", employeeId));
		criteria.add(Restrictions.eq("password", currentPassword));
		logger.debug("getCriteriaForEmployee >>");
		return criteria;
	}

	@Override
	public Long getEmployeeStateId(Long employeeId) {
		logger.debug(">> getEmployeeStateId");
		String bHQL = "select empAddresses.usState.id from Employee e Inner Join e.addresses empAddresses where e.id=? AND empAddresses.addressType.type=:addrType   ";
		Query query = getSession().createQuery(bHQL);
		query.setLong(0, employeeId);
		query.setParameter("addrType", AddressType.WORK);
		Long stateId = (Long) query.uniqueResult();
		logger.debug("stateId ::" + stateId);
		logger.debug("getEmployeeStateId >>");
		return stateId;
	}

	@Override
	public Employee checkTemporaryPassword(Long companyId, String password) {
		return (Employee) getCriteria().add(Restrictions.eq("company.id", companyId))
				.add(Restrictions.eq("password", password)).uniqueResult();

	}

	@Override
	public Employee checkUserId(String userName) {
		logger.debug("userName::::::::::::::" + userName);
		return (Employee) getCriteria().add(Restrictions.eq("userName", userName)).uniqueResult();
	}

	@Override
	public Employee checkLogin(String userName, String password) {
		logger.debug("userName::::::::::::::" + userName + "password::::::::::::::" + password);
		return (Employee) getCriteria().add(Restrictions.eq("userName", userName))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	@Override
	public Employee getEmployeeForSentEmailFromEmailId(String emailId)
			throws InstanceNotFoundException {
		logger.debug("emailId::::::::::::::" + emailId);
		String queryString = "select e from Employee e where e.workEmail=?";
		Query query = getSession().createQuery(queryString);
		query.setString(0, emailId);
		logger.debug("(Employee) query.uniqueResult()" + query.uniqueResult());
		return (Employee) query.uniqueResult();
	}

	@Override
	public Employee getEmployeeForSentEmailFromUserName(String userName) {
		return checkUserId(userName);
	}

	@Override
	public List<Long> getPayrollEmployees(Long payrollId) {
		Criteria criteria = getSession().createCriteria(EmployeePayroll.class, "empPayroll");
		criteria.setProjection(Projections.distinct(Projections.property("empPayroll.employee.id")));

		return criteria.list();
	}
}
