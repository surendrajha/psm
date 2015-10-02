package com.epayroll.ui.contoller.admin.console;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.ManagePermissionForm;
import com.epayroll.form.adminConsole.AdminRoleForm;
import com.epayroll.service.ManagePermissionService;
import com.epayroll.service.adminConsole.AdminUserManagePermissionService;
import com.epayroll.service.company.CompanyManagePermissionService;
import com.epayroll.spring.authorization.AuthorizationUtil;

@Controller
@RequestMapping("/admin")
public class AdminManagePermissionController {

	private Logger logger = LoggerFactory.getLogger(AdminManagePermissionController.class);

	@Autowired
	private CompanyManagePermissionService companyManagePermissionService;
	@Autowired
	private AdminUserManagePermissionService adminUserManagePermissionService;
	@Autowired
	private ManagePermissionService managePermissionService;

	@RequestMapping(value = "/showManagePermission", method = RequestMethod.GET)
	public ModelAndView showManagePermission() {
		logger.debug(">> showManagePermission");
		ModelAndView modelAndView = new ModelAndView("managePermission");
		try {
			modelAndView.addObject(
					"roleList",
					managePermissionService.getRoles(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
			modelAndView.addObject(
					"roleTypeList",
					managePermissionService.getRoleTypes(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}

		modelAndView.addObject("roleForm", new AdminRoleForm());
		modelAndView.addObject("managePermissionForm", new ManagePermissionForm());
		logger.debug("showManagePermission >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).ADMIN_MANAGE_ROLE_PERMISSION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addAdminUserRole", method = RequestMethod.POST)
	public ModelAndView addAdminUserRole(@ModelAttribute AdminRoleForm adminRoleForm) {
		logger.debug(">> addAdminUserRole");
		ModelAndView modelAndView = new ModelAndView("managePermission");

		try {
			adminUserManagePermissionService.addAdminUser(adminRoleForm);
			modelAndView.addObject(
					"roleList",
					managePermissionService.getRoles(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}
		modelAndView.addObject("roleForm", new AdminRoleForm());
		modelAndView.addObject("adminUserManagePermissionForm", new ManagePermissionForm());
		logger.debug(" addAdminUserRole >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).ADMIN_MANAGE_ROLE_PERMISSION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateAdminUserRoleForm/{roleId}", method = RequestMethod.POST)
	public ModelAndView showUpdateAdminUserRoleForm(@PathVariable Long roleId)
			throws InstanceNotFoundException {
		logger.debug(">> showUpdateAdminUserRoleForm");
		ModelAndView modelAndView = new ModelAndView("managePermission");
		AdminRoleForm adminRoleForm = adminUserManagePermissionService.getAdminRoleForm(roleId);
		modelAndView.addObject("roleForm", adminRoleForm);
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).ADMIN_MANAGE_ROLE_PERMISSION, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateAdminUserRole", method = RequestMethod.POST)
	public ModelAndView updateAdminUserRole(@ModelAttribute AdminRoleForm adminRoleForm)
			throws InstanceNotFoundException {
		logger.debug(">> updateAdminUserRole");
		ModelAndView modelAndView = new ModelAndView("managePermission");
		try {
			adminUserManagePermissionService.updateAdminUser(adminRoleForm);
			modelAndView.addObject(
					"roleList",
					managePermissionService.getRoles(AuthorizationUtil.getLoginUser().getRole()
							.getId()));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}
		modelAndView.addObject("roleForm", new AdminRoleForm());
		modelAndView.addObject("adminUserManagePermissionForm", new ManagePermissionForm());
		logger.debug("updateAdminUserRole >>");
		return modelAndView;
	}

	@RequestMapping(value = "/getPermissions", method = RequestMethod.POST)
	public ModelAndView getPermissions(@ModelAttribute ManagePermissionForm managePermissionForm) {
		logger.debug(">> getPermissions");
		ModelAndView modelAndView = new ModelAndView("managePermission");
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
			modelAndView.addObject("managePermissionForm",
					managePermissionService.getRolePermissionsForm(null, managePermissionForm));
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e);
		}
		logger.debug("getPermissions >>");
		return modelAndView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).ADMIN_MANAGE_ROLE_PERMISSION, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/managePermissions", method = RequestMethod.POST)
	public ModelAndView managePermissions(
			@ModelAttribute ManagePermissionForm managePermissionForm, HttpServletRequest request) {
		logger.debug(">> managePermissions ");
		logger.debug("managePermissionForm::" + managePermissionForm);
		ModelAndView modelAndView = new ModelAndView(new RedirectView(request.getContextPath()
				+ "/admin/showManagePermission"));
		managePermissionService.addRolePermissions(managePermissionForm, null);
		return modelAndView;
	}

}
