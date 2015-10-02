package com.epayroll.ui.contoller.company;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.entity.Company;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ManagePermissionForm;
import com.epayroll.service.ManagePermissionService;
import com.epayroll.service.company.CompanyManagePermissionService;
import com.epayroll.spring.authorization.AuthorizationUtil;

@Controller
@RequestMapping("/company")
public class CompanyManagePermissionController {

	private Logger logger = LoggerFactory.getLogger(CompanyManagePermissionController.class);

	@Autowired
	private CompanyManagePermissionService companyManagePermissionService;

	@Autowired
	private ManagePermissionService managePermissionService;

	@RequestMapping(value = "/showManagePermission", method = RequestMethod.GET)
	public ModelAndView showManagePermission() {
		logger.debug("in showManagePermission");
		ModelAndView modelAndView = new ModelAndView("managerPermission");
		try {
			modelAndView.addObject(
					"roleList",
					managePermissionService.getRoles(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
			modelAndView.addObject("managePermissionForm", new ManagePermissionForm());
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/getPermissions", method = RequestMethod.POST)
	public ModelAndView getPermissions(@ModelAttribute ManagePermissionForm managePermissionForm) {
		logger.debug("in getPermissions");
		ModelAndView modelAndView = new ModelAndView("managerPermission");
		try {
			modelAndView.addObject(
					"roleList",
					managePermissionService.getRoles(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
			modelAndView.addObject("permissionTypeList",
					companyManagePermissionService.getPermissionTypes());
			modelAndView.addObject("menuList",
					companyManagePermissionService.getMenus(managePermissionForm.getRoleId()));
			modelAndView.addObject("subMenuList",
					companyManagePermissionService.getSubMenus(managePermissionForm.getRoleId()));
			modelAndView.addObject("managePermissionForm", managePermissionService
					.getRolePermissionsForm(getCompanyFromSession(), managePermissionForm));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/managePermissions", method = RequestMethod.POST)
	public ModelAndView managePermissions(
			@ModelAttribute ManagePermissionForm managePermissionForm, HttpServletRequest request) {
		logger.debug("in managePermissions ");
		logger.debug("managePermissionForm::" + managePermissionForm);
		ModelAndView modelAndView = new ModelAndView(new RedirectView(request.getContextPath()
				+ "/company/showManagePermission"));
		managePermissionService.addRolePermissions(managePermissionForm, getCompanyFromSession());
		return modelAndView;
	}

	private Company getCompanyFromSession() {
		Company company = companyManagePermissionService.getCompany(AuthorizationUtil
				.getLoginUser().getId());
		return company;
	}

}
