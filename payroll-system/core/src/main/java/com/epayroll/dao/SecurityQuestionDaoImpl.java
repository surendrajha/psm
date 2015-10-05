package com.epayroll.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.entity.SecurityQuestion;
import com.epayroll.entity.SecurityQuestion.GroupNo;

@SuppressWarnings("unchecked")
@Repository
public class SecurityQuestionDaoImpl extends GenericDaoImpl<SecurityQuestion, Long> implements
		SecurityQuestionDao {

	@Override
	public List<SecurityQuestion> getQuestionsFromGroup1() {
		return getCriteria().add(Restrictions.eq("groupNo", GroupNo.ONE)).list();
	}

	@Override
	public List<SecurityQuestion> getQuestionsFromGroup2() {
		return getCriteria().add(Restrictions.eq("groupNo", GroupNo.TWO)).list();
	}

}
