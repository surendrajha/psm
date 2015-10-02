package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.UserSecurityQuestion;

public interface UserSecurityQuestionDao extends GenericDao<UserSecurityQuestion, Long> {

	List<UserSecurityQuestion> getUserSecurityQnAs(Long userId);
}
