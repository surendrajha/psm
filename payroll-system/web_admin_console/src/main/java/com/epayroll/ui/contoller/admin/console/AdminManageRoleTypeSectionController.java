package com.epayroll.ui.contoller.admin.console;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.Section;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.form.RoleTypeSectionMapForm;
import com.epayroll.service.ManagePermissionService;

@Controller
@RequestMapping("/admin")
public class AdminManageRoleTypeSectionController {

	private Logger logger = LoggerFactory.getLogger(AdminManageRoleTypeSectionController.class);

	@Autowired
	private ManagePermissionService managePermissionService;

	@RequestMapping(value = "/showRoleTypes", method = RequestMethod.GET)
	public ModelAndView showRoleTypes() {
		logger.debug(">> showRoleTypes");
		ModelAndView modelAndView = new ModelAndView("manageRoleTypeSection");
		modelAndView.addObject("roleTypeList", RoleType.values());
		modelAndView.addObject("roleTypeSectionMapForm", new RoleTypeSectionMapForm());
		return modelAndView;
	}

	@RequestMapping(value = "/getSections")
	public ModelAndView getPermissions(@ModelAttribute RoleTypeSectionMapForm roleTypeSectionMapForm) {
		logger.debug("in getSections");
		ModelAndView modelAndView = new ModelAndView("manageRoleTypeSection");
		modelAndView.addObject("roleTypeList", RoleType.values());
		modelAndView.addObject("sectionList", Section.values());
		modelAndView.addObject("managePermissionForm",
				managePermissionService.getRoleTypeSectionForm(roleTypeSectionMapForm));
		return modelAndView;
	}

	@RequestMapping(value = "/manageSections", method = RequestMethod.POST)
	public ModelAndView managePermissions(
			@ModelAttribute RoleTypeSectionMapForm roleTypeSectionMapForm,
			HttpServletRequest request) {
		logger.debug("in manageSections ");
		logger.debug("roleTypeSectionMapForm ::" + roleTypeSectionMapForm);
		ModelAndView modelAndView = new ModelAndView("manageRoleTypeSection");
		modelAndView.addObject("roleTypeList", RoleType.values());
		modelAndView.addObject("sectionList", Section.values());
		modelAndView.addObject("roleTypeSectionMapForm", roleTypeSectionMapForm);
		managePermissionService.addRoleTypeSections(roleTypeSectionMapForm);
		return modelAndView;
	}
}
