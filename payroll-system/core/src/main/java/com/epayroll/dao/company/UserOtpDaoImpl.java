package com.epayroll.dao.company;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.UserOtp;

@SuppressWarnings("unchecked")
@Repository
public class UserOtpDaoImpl extends GenericDaoImpl<UserOtp, Long> implements UserOtpDao {

	@Override
	public List<UserOtp> getOTPCodes(Long userId) {
		return getCriteria().add(Restrictions.eq("user.id", userId)).list();
	}
}