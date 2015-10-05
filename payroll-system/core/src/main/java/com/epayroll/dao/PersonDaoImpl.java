package com.epayroll.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.Person;
import com.epayroll.exception.InstanceNotFoundException;

@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {

	private Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

	@Override
	public Person findPerson(String email, String mobileNumber) throws InstanceNotFoundException {
		Criteria personCriteria = getCriteria();
		if (StringUtils.isNotEmpty(email)) {
			personCriteria.add(Restrictions.like("email", email));
		}
		if (StringUtils.isNotEmpty(mobileNumber)) {
			personCriteria.add(Restrictions.like("mobileNumber", mobileNumber));
		}
		Person person = (Person) personCriteria.uniqueResult();
		if (person == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("Email [ ").append(email).append(" ] ");
			builder.append("MobileNumber [ ").append(mobileNumber).append(" ] ");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return person;
	}

	@Override
	public Person getCompanyContact(Long companyId, String contactType) {
		logger.debug(">> getContacts");
		String hqlQuery = "select companyContacts from Company c Inner Join c.contacts companyContacts where c.id=? and companyContacts.contactType.type=? ";
		Query query = getSession().createQuery(hqlQuery);
		query.setLong(0, companyId);
		query.setString(1, contactType);
		Person contacts = (Person) query.uniqueResult();
		logger.debug("contacts::" + contacts);
		logger.debug("getContacts >>");
		return contacts;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getPersons(Long companyId) {
		logger.debug("in getPersons..companyId:" + companyId);

		String HQL_EMP = "select new Person(p.id, p.firstName, p.lastName, p.email) "
				+ "from Person p inner join p.employees e where e.company.id=:compId";
		String HQL_CU = "select new Person(p.id, p.firstName, p.lastName, p.email) "
				+ "from Person p inner join p.users u inner join u.companyUsers cu where cu.company.id=:compId";

		Query query_emp = getSession().createQuery(HQL_EMP);
		Query query_cu = getSession().createQuery(HQL_CU);

		query_emp.setParameter("compId", companyId);
		query_cu.setParameter("compId", companyId);

		List<Person> list_emp = query_emp.list();
		List<Person> list_cu = query_cu.list();

		list_emp.addAll(list_cu);

		return list_emp;
	}
}