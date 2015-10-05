package com.epayroll.dao.company;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.constant.RoleName;
import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.User;
import com.epayroll.exception.InstanceNotFoundException;

@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User getUser(String email, Date dob) throws InstanceNotFoundException {
		String queryString = "select user from User user where user.person.email=? and user.person.dob=?";
		Query query = getSession().createQuery(queryString);
		query.setString(0, email);
		query.setDate(1, dob);
		return (User) query.uniqueResult();
	}

	@Override
	public User getUser(String userName) throws InstanceNotFoundException {
		Criteria criteria = getCriteria().add(Restrictions.eq("userName", userName));
		User user = (User) criteria.uniqueResult();
		logger.debug("user::" + user);
		if (user == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("UserName [ ").append(userName).append(" ] ");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return user;

	}

	@Override
	public User verifyPin(String userName, String pin) {
		logger.debug(">> verifyPin");
		String queryString = "select user from User user where user.userName=? and user.securityPin=?";
		Query query = getSession().createQuery(queryString);
		query.setString(0, userName);
		query.setString(1, pin);
		User user = (User) query.uniqueResult();
		logger.debug("User ::" + user);
		logger.debug("verifyPin >>");
		return user;
	}

	@Override
	public List<String> getApprovers(Long companyId) {

		return getCriteria().createAlias("companyUsers", "cu").createAlias("role", "role")
				.createAlias("person", "p").add(Restrictions.eq("cu.company.id", companyId))
				.add(Restrictions.eq("role.roleType", RoleType.COMPANY_USER))
				.add(Restrictions.eq("role.roleName", RoleName.APPROVER))
				.setProjection(Projections.property("p.email")).list();
	}

	// surendra
	@Override
	public User checkUserPassword(String userName, String password)
			throws InstanceNotFoundException {
		Criteria criteria = getCriteria().add(Restrictions.eq("userName", userName)).add(
				Restrictions.eq("password", password));
		User user = (User) criteria.uniqueResult();
		logger.debug(" in checkUserPassword :: user::" + user);
		if (user == null) {
			StringBuilder builder = new StringBuilder();
			builder.append("UserName [ ").append(userName).append(" ] ");
			throw new InstanceNotFoundException(builder.toString(), getEntityClass().getName());
		}
		return user;
	}
}