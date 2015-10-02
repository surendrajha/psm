package com.epayroll.entity;

// Generated Dec 12, 2012 9:09:02 PM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SecurityQuestion generated by hbm2java
 */
@Entity
@Table(name = "security_question")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SecurityQuestion implements java.io.Serializable {

	public enum GroupNo {
		ONE("One"), TWO("Two"), THREE("Three"), FOUR("Four"), FIVE("Five");
		private String name;

		private GroupNo(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	private static final long serialVersionUID = -9148770161284212040L;
	private Long id;
	private String question;
	private String groupDesc;
	private GroupNo groupNo;
	private Set<UserSecurityQuestion> userSecurityQuestions = new HashSet<UserSecurityQuestion>(0);

	public SecurityQuestion() {
	}

	public SecurityQuestion(String question, GroupNo groupNo) {
		this.question = question;
		this.groupNo = groupNo;
	}

	public SecurityQuestion(String question, GroupNo groupNo, String groupDesc,
			Set<UserSecurityQuestion> userSecurityQuestions) {
		this.question = question;
		this.groupNo = groupNo;
		this.groupDesc = groupDesc;
		this.userSecurityQuestions = userSecurityQuestions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "question", length = 100)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "group_no")
	public GroupNo getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(GroupNo groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "group_desc", length = 300)
	public String getGroupDesc() {
		return this.groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "securityQuestion")
	public Set<UserSecurityQuestion> getUserSecurityQuestions() {
		return this.userSecurityQuestions;
	}

	public void setUserSecurityQuestions(Set<UserSecurityQuestion> userSecurityQuestions) {
		this.userSecurityQuestions = userSecurityQuestions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityQuestion [id=");
		builder.append(id);
		builder.append(", question=");
		builder.append(question);
		builder.append(", groupDesc=");
		builder.append(groupDesc);
		builder.append(", groupNo=");
		builder.append(groupNo);
		builder.append("]");
		return builder.toString();
	}

}