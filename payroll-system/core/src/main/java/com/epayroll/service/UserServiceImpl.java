package com.epayroll.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.AddressTypeDao;
import com.epayroll.dao.ContactTypeDao;
import com.epayroll.dao.SecurityQuestionDao;
import com.epayroll.dao.UsStateDao;
import com.epayroll.dao.company.AdminUserDao;
import com.epayroll.dao.company.CompanyDao;
import com.epayroll.dao.company.CompanyUserDao;
import com.epayroll.dao.company.RoleDao;
import com.epayroll.dao.company.UserDao;
import com.epayroll.dao.company.UserOtpDao;
import com.epayroll.dao.company.UserSecurityQuestionDao;
import com.epayroll.entity.Address;
import com.epayroll.entity.AdminUser;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyUser;
import com.epayroll.entity.Person;
import com.epayroll.entity.SecurityQuestion.GroupNo;
import com.epayroll.entity.UsState;
import com.epayroll.entity.User;
import com.epayroll.entity.User.Status;
import com.epayroll.entity.UserOtp;
import com.epayroll.entity.UserSecurityQuestion;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ForgotInfoStep2Form;
import com.epayroll.form.ForgotPasswordAndPinStep1Form;
import com.epayroll.form.ForgotUserIdStep1Form;
import com.epayroll.form.GenerateOTPForm;
import com.epayroll.form.PinVerficationForm;
import com.epayroll.form.ResetPasswordForm;
import com.epayroll.form.ResetPinForm;
import com.epayroll.form.company.ChangeSecurityQnAForm;
import com.epayroll.form.company.ChangeUserEmailForm;
import com.epayroll.form.company.ChangeUserIDForm;
import com.epayroll.form.company.ChangeUserPasswordForm;
import com.epayroll.form.company.ChangeUserPinForm;
import com.epayroll.form.company.UserAddForm;
import com.epayroll.form.company.UserForm;
import com.epayroll.model.SecurityQuestions;
import com.epayroll.utils.RandomNumberUtils;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Autowired
	private CompanyUserDao companyUserDao;

	@Autowired
	private AdminUserDao adminUserDao;

	@Autowired
	private UserSecurityQuestionDao companySecurityQuestionDao;

	@Autowired
	private SecurityQuestionDao securityQuestionDao;

	@Autowired
	private UsStateDao usStateDao;

	@Autowired
	private UserOtpDao userOtpDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private ContactTypeDao contactTypeDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private AddressTypeDao addressTypeDao;

	@Override
	@Transactional
	public User getUser(String userName) throws InstanceNotFoundException {
		logger.debug(">> getUser");
		User user = userDao.getUser(userName);
		logger.debug("getUser >>");
		return user;
	}

	@Override
	@Transactional
	public ForgotInfoStep2Form getForgotInfoStep2Form(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form)
			throws InstanceNotFoundException {
		logger.debug(">> getForgotInfoStep2Form");
		ForgotInfoStep2Form forgotInfoStep2Form = new ForgotInfoStep2Form();
		User user = getUser(forgotPasswordAndPinStep1Form.getUserName());
		logger.debug("companyUser" + user);
		if (user != null) {
			loadUserQuestionsIntoForm(forgotInfoStep2Form, user);
			logger.debug("forgotInfoStep2Form :: " + forgotInfoStep2Form);
		} else {
			logger.debug("getForgotInfoStep2Form >>");
			return null;
		}
		logger.debug("getForgotInfoStep2Form >>");
		return forgotInfoStep2Form;
	}

	@Override
	@Transactional
	public ForgotInfoStep2Form getForgotInfoStep2FormForUserId(
			ForgotUserIdStep1Form forgotUserIdStep1Form) throws InstanceNotFoundException {
		logger.debug(">> getSecurityQuestionsForUserId");
		ForgotInfoStep2Form forgotInfoStep2Form = new ForgotInfoStep2Form();
		User user = getUser(forgotUserIdStep1Form.getEmailId(), forgotUserIdStep1Form.getDob());
		if (user != null) {
			loadUserQuestionsIntoForm(forgotInfoStep2Form, user);
			logger.debug("forgotInfoStep2Form :: " + forgotInfoStep2Form);
		} else {
			logger.debug("getSecurityQuestionsForUserId >>");
			return null;
		}
		logger.debug("getSecurityQuestionsForUserId >>");
		return forgotInfoStep2Form;
	}

	private void loadUserQuestionsIntoForm(ForgotInfoStep2Form forgotInfoStep2Form, User user) {
		logger.debug(">> loadUserQuestionsIntoForm");
		Iterator<UserSecurityQuestion> iterator = user.getUserSecurityQuestions().iterator();
		List<UserSecurityQuestion> companyUserSecurityQuestionList = new ArrayList<UserSecurityQuestion>();
		while (iterator.hasNext()) {
			UserSecurityQuestion userSecurityQuestion = iterator.next();
			companyUserSecurityQuestionList.add(userSecurityQuestion);
		}
		forgotInfoStep2Form.setSecurityQuestion1(companyUserSecurityQuestionList.get(0)
				.getSecurityQuestion().getQuestion());
		forgotInfoStep2Form.setSecurityQuestion2(companyUserSecurityQuestionList.get(1)
				.getSecurityQuestion().getQuestion());
		forgotInfoStep2Form.setSecurityQuestionId1(companyUserSecurityQuestionList.get(0)
				.getSecurityQuestion().getId());
		forgotInfoStep2Form.setSecurityQuestionId2(companyUserSecurityQuestionList.get(1)
				.getSecurityQuestion().getId());
		logger.debug("loadUserQuestionsIntoForm >>");
	}

	@Override
	@Transactional
	public String checkSecurityAnswersAndGetEmailForPassword(
			ForgotPasswordAndPinStep1Form forgotPasswordAndPinStep1Form,
			ForgotInfoStep2Form forgotInfoStep2Form) throws InstanceNotFoundException {
		logger.debug(">> checkSecurityAnswersAndGetEmailForPassword");
		String email = null;
		User user = getUser(forgotPasswordAndPinStep1Form.getUserName());
		if (user != null) {
			if (isSecurityAnswersValidMethod(forgotInfoStep2Form, user)) {
				user.setForgetProcessInitiated(true);
				userDao.update(user);
				email = user.getPerson().getEmail();
			}
		}
		logger.debug("checkSecurityAnswersAndGetEmailForPassword >>");
		return email;
	}

	private boolean isSecurityAnswersValidMethod(ForgotInfoStep2Form forgotInfoStep2Form, User user) {
		logger.debug(">> isSecurityAnswersValidMethod");
		Iterator<UserSecurityQuestion> iterator = user.getUserSecurityQuestions().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			UserSecurityQuestion userSecurityQuestion = iterator.next();
			if (forgotInfoStep2Form.getSecurityQuestionId1().equals(
					userSecurityQuestion.getSecurityQuestion().getId())
					&& forgotInfoStep2Form.getSecurityAnswer1().equals(
							userSecurityQuestion.getAnswer())) {
				count++;
			} else if (forgotInfoStep2Form.getSecurityQuestionId2().equals(
					userSecurityQuestion.getSecurityQuestion().getId())
					&& forgotInfoStep2Form.getSecurityAnswer2().equals(
							userSecurityQuestion.getAnswer())) {
				count++;
			}
		}
		if (count == 2) {
			logger.debug("isSecurityAnswersValidMethod >>");
			return true;
		} else {
			logger.debug("isSecurityAnswersValidMethod >>");
			return false;
		}
	}

	@Override
	@Transactional
	public String checkSecurityAnswersAndGetUserIdForUserId(
			ForgotUserIdStep1Form companyUserForgotUseridStep1Form,
			ForgotInfoStep2Form forgotInfoStep2Form) throws InstanceNotFoundException {
		logger.debug(">> checkSecurityAnswersAndGetUserIdForUserId");
		String userName = null;
		User user = getUser(companyUserForgotUseridStep1Form.getEmailId(),
				companyUserForgotUseridStep1Form.getDob());
		if (user != null) {
			if (isSecurityAnswersValidMethod(forgotInfoStep2Form, user)) {
				userName = user.getUserName();
			}
		}
		logger.debug("checkSecurityAnswersAndGetUserIdForUserId >>");
		return userName;
	}

	@Override
	@Transactional
	public Boolean changePassword(User user, ResetPasswordForm resetPasswordForm) {
		logger.debug(">> changePassword");
		Boolean result = false;
		if (resetPasswordForm.getNewPassword().equals(resetPasswordForm.getConfirmPassword())) {

			// companyUser
			// .setPassword(passwordEncryption(companyUserResetPasswordForm
			// .getNewPassword()));
			user.setPassword(RandomNumberUtils.encode(resetPasswordForm.getNewPassword()));
			user.setForgetProcessInitiated(false);
			userDao.update(user);
			result = true;
		}
		logger.debug("changePassword >>");
		return result;
	}

	@Override
	@Transactional
	public boolean changePin(User user, ResetPinForm resetPinForm) {
		logger.debug(">> changePin");
		boolean result = false;
		if (resetPinForm.getNewPin().equals(resetPinForm.getConfirmPin())) {

			// user
			// .setSecurityPin(passwordEncryption(companyUserResetPasswordForm
			// .getNewPassword()));
			user.setSecurityPin(RandomNumberUtils.encode(resetPinForm.getNewPin()));
			user.setForgetProcessInitiated(false);
			userDao.update(user);
			result = true;
		}
		logger.debug("changePin >>");
		return result;
	}

	// MD5 encryption method

	// private String passwordEncryption(String pwd) {
	// logger.debug(" >> passwordEncryption");
	// MessageDigest messageDigest;
	// String hashedPass = null;
	// try {
	// messageDigest = MessageDigest.getInstance("MD5");
	// messageDigest.update(pwd.getBytes(), 0, pwd.length());
	// hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
	// if (hashedPass.length() < 32) {
	// hashedPass = "0" + hashedPass;
	// }
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// }
	// logger.debug("passwordEncryption >>");
	// return hashedPass;
	// }

	@Override
	@Transactional
	public User getUser(String email, Date dob) throws InstanceNotFoundException {
		logger.debug(">> getUser");
		User user = userDao.getUser(email, dob);
		logger.debug("getUser >>");
		return user;
	}

	@Override
	public boolean verifyPin(PinVerficationForm pinVerficationForm) {
		logger.debug(">> verifyPin");
		boolean isVerify = false;
		User user = userDao.verifyPin(pinVerficationForm.getUsername(),
				RandomNumberUtils.encode(pinVerficationForm.getPin()));
		if (!user.equals(null)) {
			isVerify = true;
		}
		logger.debug("verifyPin >>");
		return isVerify;
	}

	@Override
	@Transactional
	public UserForm getUserProfile(Long userId) throws InstanceNotFoundException {
		User user = userDao.find(userId);
		UserForm userForm = new UserForm();

		// profile data
		userForm.setId(user.getId());
		userForm.setFirstName(user.getPerson().getFirstName());
		userForm.setLastName(user.getPerson().getLastName());
		userForm.setDob(user.getPerson().getDob());
		userForm.setSsn(user.getPerson().getSsn());
		userForm.setPhone(user.getPerson().getPhone());
		userForm.setExt(user.getPerson().getExt());
		userForm.setFax(user.getPerson().getFax());
		userForm.setStreetAddress(user.getAddress().getStreetAddress());
		userForm.setCity(user.getAddress().getCityName());
		userForm.setState(user.getAddress().getUsState().getId());
		userForm.setPinZip(user.getAddress().getPinZip());
		userForm.setCounty(user.getAddress().getCountyName());
		userForm.setStatus(user.getStatus());

		// security data
		userForm.setUserName(user.getUserName());
		userForm.setPassword(user.getPassword());
		userForm.setSecurityPin(user.getSecurityPin());
		userForm.setEmail(user.getPerson().getEmail());

		// security QnA data

		List<UserSecurityQuestion> userSecurityQnAList = companySecurityQuestionDao
				.getUserSecurityQnAs(userId);
		for (UserSecurityQuestion compUserSecQn : userSecurityQnAList) {
			if (compUserSecQn.getSecurityQuestion() == null) {
				// first question should be null, bcoz it has defauld name
				userForm.setAnswer1(compUserSecQn.getAnswer());
			}
			if (compUserSecQn.getSecurityQuestion() != null
					&& compUserSecQn.getSecurityQuestion().getGroupNo() == GroupNo.ONE) {
				userForm.setSecurityQues2(compUserSecQn.getSecurityQuestion().getId());
				userForm.setAnswer2(compUserSecQn.getAnswer());
			}
			if (compUserSecQn.getSecurityQuestion() != null
					&& compUserSecQn.getSecurityQuestion().getGroupNo() == GroupNo.TWO) {
				userForm.setSecurityQues3(compUserSecQn.getSecurityQuestion().getId());
				userForm.setAnswer3(compUserSecQn.getAnswer());
			}
		}
		return userForm;
	}

	@Override
	@Transactional
	public SecurityQuestions getSecurityQuestions() {

		SecurityQuestions questionForm = new SecurityQuestions();

		questionForm.setGroup1(securityQuestionDao.getQuestionsFromGroup1());

		questionForm.setGroup2(securityQuestionDao.getQuestionsFromGroup2());

		return questionForm;
	}

	@Override
	@Transactional
	public List<UsState> getStates() {
		return usStateDao.getEntities();
	}

	@Override
	@Transactional
	public List<UserForm> getComapanyUsersExceptAdmin(Company company) {

		// users data expect Admin user
		List<UserForm> list = new ArrayList<UserForm>();

		List<CompanyUser> companyUsers = companyUserDao.getCompanyUsersExceptAdmin(company);

		for (CompanyUser companyUser : companyUsers) {
			list.add(getCompanyUser(companyUser));
		}
		return list;
	}

	private UserForm getCompanyUser(CompanyUser companyUser) {
		UserForm userForm = new UserForm();
		User user = companyUser.getUser();
		userForm.setId(user.getId());
		userForm.setCompanyId(companyUser.getCompany().getId());
		userForm.setFirstName(user.getPerson().getFirstName());
		userForm.setLastName(user.getPerson().getLastName());
		userForm.setDob(user.getPerson().getDob());
		userForm.setSsn(user.getPerson().getSsn());
		userForm.setPhone(user.getPerson().getPhone());
		userForm.setExt(user.getPerson().getExt());
		userForm.setFax(user.getPerson().getFax());
		userForm.setStreetAddress(user.getAddress().getStreetAddress());
		userForm.setCity(user.getAddress().getCityName());
		userForm.setState(user.getAddress().getUsState().getId());
		userForm.setPinZip(user.getAddress().getPinZip());
		userForm.setCounty(user.getAddress().getCountyName());
		userForm.setRole(user.getRole().getId());
		userForm.setStatus(user.getStatus());

		// security data
		// companyUserForm.setUserName(companyUser.getUserName());
		// companyUserForm.setPassword(companyUser.getPassword());
		// companyUserForm.setSecurityPin(companyUser.getSecurityPin());
		userForm.setEmail(user.getPerson().getEmail());
		return userForm;
	}

	private UserForm getAdminUser(AdminUser adminUser) {
		UserForm userForm = new UserForm();
		User user = adminUser.getUser();
		userForm.setId(user.getId());
		userForm.setFirstName(user.getPerson().getFirstName());
		userForm.setLastName(user.getPerson().getLastName());
		userForm.setDob(user.getPerson().getDob());
		userForm.setSsn(user.getPerson().getSsn());
		userForm.setPhone(user.getPerson().getPhone());
		userForm.setExt(user.getPerson().getExt());
		userForm.setFax(user.getPerson().getFax());
		userForm.setStreetAddress(user.getAddress().getStreetAddress());
		userForm.setCity(user.getAddress().getCityName());
		userForm.setState(user.getAddress().getUsState().getId());
		userForm.setPinZip(user.getAddress().getPinZip());
		userForm.setCounty(user.getAddress().getCountyName());
		userForm.setRole(user.getRole().getId());
		userForm.setStatus(user.getStatus());

		// security data
		// companyUserForm.setUserName(companyUser.getUserName());
		// companyUserForm.setPassword(companyUser.getPassword());
		// companyUserForm.setSecurityPin(companyUser.getSecurityPin());
		userForm.setEmail(user.getPerson().getEmail());
		return userForm;
	}

	@Override
	@Transactional
	public void updateAdminUserProfile(UserForm userForm) throws InstanceNotFoundException {
		System.out.println("updateAdminUserProfile....... ");
		User user = userDao.find(userForm.getId());

		System.out.println("User:::" + user);

		user.getPerson().setFirstName(userForm.getFirstName());
		user.getPerson().setLastName(userForm.getLastName());
		user.getPerson().setDob(userForm.getDob());
		user.getPerson().setSsn(userForm.getSsn());
		user.getPerson().setPhone(userForm.getPhone());
		user.getPerson().setExt(userForm.getExt());
		user.getPerson().setFax(userForm.getFax());
		user.getAddress().setStreetAddress(userForm.getStreetAddress());
		user.getAddress().setCityName(userForm.getCity());
		user.getAddress().setUsState(usStateDao.find(userForm.getState()));
		user.getAddress().setPinZip(userForm.getPinZip());
		user.getAddress().setCountyName(userForm.getCounty());

		userDao.update(user);
	}

	@Override
	@Transactional
	public GenerateOTPForm generateOtp(GenerateOTPForm otpForm) throws InstanceNotFoundException {
		UserOtp otp = new UserOtp();
		String code = RandomNumberUtils.get6digitUniqueNumber().toString();
		otpForm.setOtp(code);
		otp.setOtpCode(code);
		User user = getUser(otpForm.getUserId());
		otp.setUser(user);
		userOtpDao.save(otp);
		otpForm.setEmail(user.getPerson().getEmail());
		return otpForm;
	}

	@Override
	public User getUser(Long userId) throws InstanceNotFoundException {
		return userDao.find(userId);
	}

	@Override
	@Transactional
	public Boolean verifyOTP(GenerateOTPForm otpForm) throws InstanceNotFoundException {
		List<UserOtp> otpList = userOtpDao.getOTPCodes(otpForm.getUserId());
		Boolean isVarified = false;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		Date dateBefore1Hour = calendar.getTime();
		for (UserOtp userOtp : otpList) {
			if (userOtp.getGeneratedOn().getTime().after(dateBefore1Hour)) {
				if (userOtp.getOtpCode().equals(otpForm.getOtp())) {
					isVarified = true;
					break;
				}
			}
		}
		if (isVarified) {
			for (UserOtp userOtp : otpList) {
				userOtpDao.remove(userOtp);
			}
		}
		return isVarified;
	}

	@Override
	@Transactional
	public Boolean changeUserID(ChangeUserIDForm changeUserIDForm) throws InstanceNotFoundException {
		Boolean isChanged = false;
		User user = getUser(changeUserIDForm.getUserId());
		logger.debug("user::" + user);
		if (isPasswordMatch(changeUserIDForm.getCurrentPassword(), user.getPassword())) {
			logger.debug("password match");
			if (StringUtils.equals(changeUserIDForm.getNewUserName(),
					changeUserIDForm.getConfirmNewUserName())) {
				logger.debug(" new username match");
				user.setUserName(changeUserIDForm.getNewUserName());
				userDao.update(user);
				isChanged = true;
			}
		}
		return isChanged;
	}

	private Boolean isPasswordMatch(String pwd1, String pwd2) {
		logger.debug("RandomNumberUtils.encode(pwd1)" + RandomNumberUtils.encode(pwd1));
		return RandomNumberUtils.encode(pwd1).equals(pwd2);
	}

	@Override
	@Transactional
	public Boolean changeUserPassword(ChangeUserPasswordForm changeUserPasswordForm)
			throws InstanceNotFoundException {
		Boolean isChanged = false;
		User user = getUser(changeUserPasswordForm.getUserId());

		if (isPasswordMatch(changeUserPasswordForm.getCurrentPassword(), user.getPassword())) {
			logger.debug("password match");
			if (StringUtils.equals(changeUserPasswordForm.getNewPassword(),
					changeUserPasswordForm.getConfirmNewPassword())) {
				logger.debug(" new password match");
				user.setPassword(RandomNumberUtils.encode(changeUserPasswordForm.getNewPassword()));
				userDao.update(user);
				isChanged = true;
			}
		}
		return isChanged;
	}

	@Override
	@Transactional
	public Boolean changeUserPin(ChangeUserPinForm changeUserPinForm)
			throws InstanceNotFoundException {
		Boolean isChanged = false;
		User user = getUser(changeUserPinForm.getUserId());

		if (isPasswordMatch(changeUserPinForm.getCurrentPassword(), user.getPassword())) {

			if (StringUtils.equals(changeUserPinForm.getCurrentPin(), user.getSecurityPin())) {

				if (StringUtils.equals(changeUserPinForm.getNewPin(),
						changeUserPinForm.getConfirmNewPin())) {
					user.setSecurityPin(changeUserPinForm.getNewPin());
					userDao.update(user);
					isChanged = true;
				}
			}
		}
		return isChanged;
	}

	@Override
	@Transactional
	public Boolean changeUserEmail(ChangeUserEmailForm changeUserEmailForm)
			throws InstanceNotFoundException {
		Boolean isChanged = false;
		User user = getUser(changeUserEmailForm.getUserId());

		if (isPasswordMatch(changeUserEmailForm.getCurrentPassword(), user.getPassword())) {

			if (StringUtils.equals(changeUserEmailForm.getCurrentEmail(), user.getPerson()
					.getEmail())) {

				if (StringUtils.equals(changeUserEmailForm.getNewEmail(),
						changeUserEmailForm.getConfirmNewEmail())) {
					user.getPerson().setEmail(changeUserEmailForm.getNewEmail());
					userDao.update(user);
					isChanged = true;
				}
			}
		}
		return isChanged;
	}

	@Override
	@Transactional
	public Boolean isUserNameAvailable(String userName) throws InstanceNotFoundException {
		User user = userDao.getUser(userName);
		System.out.println("companyUser::" + user);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public List<UserSecurityQuestion> getUserSecurityQnAs(Long userId) {
		return companySecurityQuestionDao.getUserSecurityQnAs(userId);
	}

	@Override
	@Transactional
	public Boolean updateUserSequrityQnA(ChangeSecurityQnAForm changeSecurityQnAForm)
			throws InstanceNotFoundException {

		User user = userDao.find(changeSecurityQnAForm.getUserId());

		if (user.getPassword().equals(
				RandomNumberUtils.encode(changeSecurityQnAForm.getCurrentPassword()))) {

			Set<UserSecurityQuestion> securityQuestions = user.getUserSecurityQuestions();
			for (UserSecurityQuestion userSecurityQuestion : securityQuestions) {
				if (userSecurityQuestion.getSecurityQuestion() == null) {
					userSecurityQuestion.setAnswer(changeSecurityQnAForm.getAnswer1());
				}
				if (userSecurityQuestion.getSecurityQuestion() != null
						&& userSecurityQuestion.getSecurityQuestion().getGroupNo() == GroupNo.ONE) {
					logger.debug("GroupNo.ONE" + GroupNo.ONE);
					userSecurityQuestion.setSecurityQuestion(securityQuestionDao
							.find(changeSecurityQnAForm.getSecurityQues2()));
					userSecurityQuestion.setAnswer(changeSecurityQnAForm.getAnswer2());
				}
				if (userSecurityQuestion.getSecurityQuestion() != null
						&& userSecurityQuestion.getSecurityQuestion().getGroupNo() == GroupNo.TWO) {
					logger.debug("GroupNo.TWO" + GroupNo.TWO);
					userSecurityQuestion.setSecurityQuestion(securityQuestionDao
							.find(changeSecurityQnAForm.getSecurityQues3()));
					userSecurityQuestion.setAnswer(changeSecurityQnAForm.getAnswer3());
				}
			}
			userDao.update(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public Long addUser(UserAddForm userAddForm) throws Exception {

		User user = new User();
		Person person = new Person();
		person.setFirstName(userAddForm.getFirstName());
		person.setLastName(userAddForm.getLastName());
		person.setEmail(userAddForm.getEmail());
		person.setContactType(contactTypeDao
				.getContactType(com.epayroll.constant.company.ContactType.NORMAL));
		user.setPerson(person);

		user.setUserName(userAddForm.getUserName());
		user.setPassword(RandomNumberUtils.encode(userAddForm.getPassword()));
		user.setStatus(Status.INACTIVE);
		user.setRole(roleDao.find(userAddForm.getRole()));
		userDao.save(user);
		if (userAddForm.getCompanyId() != null) {
			logger.debug("add companyUser");
			CompanyUser companyUser = new CompanyUser();
			companyUser.setCompany(companyDao.find(userAddForm.getCompanyId()));
			companyUser.setUser(user);
			return companyUserDao.save(companyUser);
		} else {
			logger.debug("add adminUser");
			AdminUser adminUser = new AdminUser();
			adminUser.setUser(user);
			return adminUserDao.save(adminUser);
		}

	}

	@Override
	@Transactional
	public void registerUser(UserForm userForm) throws InstanceNotFoundException {
		User user = getUser(userForm.getId());
		user.getPerson().setDob(userForm.getDob());
		user.getPerson().setPhone(userForm.getPhone());
		Address address = new Address();
		address.setStreetAddress(userForm.getStreetAddress());
		address.setCityName(userForm.getCity());
		address.setUsState(usStateDao.find(userForm.getState()));
		address.setCountyName(userForm.getCounty());
		address.setPinZip(userForm.getPinZip());
		address.setAddressType(addressTypeDao
				.getAddressType(com.epayroll.constant.company.AddressType.NORMAL));
		user.setAddress(address);

		// add security QnA info
		UserSecurityQuestion userSecurityQuestion = null;
		for (int i = 1; i <= 3; i++) {
			userSecurityQuestion = new UserSecurityQuestion();
			userSecurityQuestion.setUser(user);
			switch (i) {
			case 1:
				// userSecurityQuestion.setSecurityQuestion(null);
				userSecurityQuestion.setAnswer(userForm.getAnswer1());
				companySecurityQuestionDao.save(userSecurityQuestion);
				break;
			case 2:
				userSecurityQuestion.setSecurityQuestion(securityQuestionDao.find(userForm
						.getSecurityQues2()));
				userSecurityQuestion.setAnswer(userForm.getAnswer2());
				companySecurityQuestionDao.save(userSecurityQuestion);
				break;
			case 3:
				userSecurityQuestion.setSecurityQuestion(securityQuestionDao.find(userForm
						.getSecurityQues3()));
				userSecurityQuestion.setAnswer(userForm.getAnswer3());
				companySecurityQuestionDao.save(userSecurityQuestion);
				break;
			default:
				break;
			}
		}
		userDao.update(user);
	}

	@Override
	@Transactional
	public UserForm getCompanyUserForm(Long companyUserId) throws InstanceNotFoundException {
		return getCompanyUser(getCompanyUser(companyUserId));
	}

	@Override
	@Transactional
	public UserForm getAdminUserForm(Long adminUserId) throws InstanceNotFoundException {
		return getAdminUser(getAdminUser(adminUserId));
	}

	@Override
	public AdminUser getAdminUser(Long adminUserId) throws InstanceNotFoundException {
		return adminUserDao.find(adminUserId);
	}

	@Override
	public CompanyUser getCompanyUser(Long companyUserId) throws InstanceNotFoundException {
		return companyUserDao.find(companyUserId);
	}

	@Override
	@Transactional
	public void updateUser(UserForm userForm) throws InstanceNotFoundException {

		User user = userDao.find(userForm.getId());

		user.setRole(roleDao.find(userForm.getRole()));
		user.getPerson().setFirstName(userForm.getFirstName());
		user.getPerson().setLastName(userForm.getLastName());
		user.getPerson().setDob(userForm.getDob());
		user.getPerson().setPhone(userForm.getPhone());
		user.getPerson().setEmail(userForm.getEmail());
		user.getAddress().setStreetAddress(userForm.getStreetAddress());
		user.getAddress().setCityName(userForm.getCity());
		user.getAddress().setUsState(usStateDao.find(userForm.getState()));
		user.getAddress().setPinZip(userForm.getPinZip());
		user.getAddress().setCountyName(userForm.getCounty());

		userDao.update(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long userId) throws InstanceNotFoundException {
		userDao.remove(userId);
	}

	@Override
	@Transactional
	public UserForm resetLoginCredential(Long userId) throws InstanceNotFoundException {

		UserForm userForm = new UserForm();
		userForm.setUserName(RandomNumberUtils.getUserName());
		userForm.setPassword(RandomNumberUtils.getTempPassword());

		User user = userDao.find(userId);

		user.setUserName(userForm.getUserName());
		user.setPassword(RandomNumberUtils.encode(userForm.getPassword()));

		userForm.setFirstName(user.getPerson().getFirstName());
		userForm.setLastName(user.getPerson().getLastName());
		userForm.setEmail(user.getPerson().getEmail());
		userDao.update(user);
		return userForm;
	}

	@Override
	public List<UserForm> getAdminUsersExceptAdmin() {
		// users data expect system user
		List<UserForm> list = new ArrayList<UserForm>();
		List<AdminUser> adminUsers = adminUserDao.getSystemUsersExceptAdmin();
		logger.debug("adminUsers:::" + adminUsers);
		for (AdminUser adminUser : adminUsers) {
			list.add(getAdminUser(adminUser));
		}
		logger.debug("list:::" + list);
		return list;
	}

}
