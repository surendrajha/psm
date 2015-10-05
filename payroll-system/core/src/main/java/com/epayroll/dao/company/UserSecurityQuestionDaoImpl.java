package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.UserSecurityQuestion;

@SuppressWarnings("unchecked")
@Repository
public class UserSecurityQuestionDaoImpl extends GenericDaoImpl<UserSecurityQuestion, Long>
		implements UserSecurityQuestionDao {

	@Override
	public List<UserSecurityQuestion> getUserSecurityQnAs(Long userId) {
		return getCriteria().add(Restrictions.eq("user.id", userId)).list();
	}

}