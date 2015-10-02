package com.epayroll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epayroll.entity.SecurityQuestion;

@Repository
public interface SecurityQuestionDao extends GenericDao<SecurityQuestion, Long> {

	List<SecurityQuestion> getQuestionsFromGroup1();

	List<SecurityQuestion> getQuestionsFromGroup2();

}