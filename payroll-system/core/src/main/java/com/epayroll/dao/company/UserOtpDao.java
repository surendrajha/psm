package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.UserOtp;

public interface UserOtpDao extends GenericDao<UserOtp, Long> {

	List<UserOtp> getOTPCodes(Long userId);
}