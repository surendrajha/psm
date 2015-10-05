package com.epayroll.ui.contoller.admin.console;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.entity.TaxAuthority;
import com.epayroll.entity.TaxType;
import com.epayroll.entity.UsCounty;
import com.epayroll.entity.UsState;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.adminConsole.TaxAuthorityForm;
import com.epayroll.form.adminConsole.TaxTypeForm;
import com.epayroll.service.adminConsole.AdminSystemDataService;

/**
 * @author Rajul Tiwari
 */

@RequestMapping("/adminConsole/taxTypeAndAuthority")
@Controller
public class AdminTaxTypeAndAuthorityController {
	private Logger logger = LoggerFactory.getLogger(AdminTaxTypeAndAuthorityController.class);

	@Autowired
	private AdminSystemDataService adminSystemDataService;

	// same for TAX_AUTHORITY and TAX_TYPE

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_AUTHORITY, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showTaxTypeAndAuthorityForm", method = RequestMethod.GET)
	public ModelAndView showTaxTypeAndAuthorityForm() {
		logger.debug(">> showForm");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		modelView.addObject("taxAuthorityForm", new TaxAuthorityForm());
		modelView.addObject("taxTypeForm", new TaxTypeForm());
		logger.debug("showForm >>");
		return modelView;
	}

	// for addTaxAuthority testing

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_AUTHORITY, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addTaxAuthority", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute TaxAuthorityForm taxAuthorityForm,
			@ModelAttribute TaxTypeForm taxTypeForm) {
		logger.debug(">> addTaxAuthority");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			@SuppressWarnings("unused")
			Long taxAuthorityId = adminSystemDataService.addTaxAuthority(taxAuthorityForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		logger.debug("addTaxAuthority >>");
		return modelView;
	}

	// testing for getTaxAuthorityForm and getTaxTypeForm
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_AUTHORITY, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateTaxAuthorityForm/{taxAuthorityId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTaxAuthorityForm(@PathVariable Long taxAuthorityId) {
		logger.debug(">> showUpdateTaxAuthorityForm");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			// id=taxAuthorityId
			TaxAuthorityForm taxAuthorityForm = adminSystemDataService
					.getTaxAuthorityForm(taxAuthorityId);
			modelView.addObject("taxAuthorityForm", taxAuthorityForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		logger.debug("showUpdateTaxAuthorityForm >>");
		return modelView;
	}

	// testing for updateTaxAuthority and updateTaxType
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_AUTHORITY, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateTaxAuthority", method = RequestMethod.POST)
	public ModelAndView updateTaxAuthority(@ModelAttribute TaxAuthorityForm taxAuthorityForm,
			@ModelAttribute TaxTypeForm taxTypeForm) {
		logger.debug(">> updateTaxAuthority");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			adminSystemDataService.updateTaxAuthority(taxAuthorityForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("updateTaxAuthority >>");
		return modelView;
	}

	// testing for getTaxAuthorityForm and getTaxTypeForm
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_AUTHORITY, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteTaxAuthority/{taxAuthorityId}", method = RequestMethod.GET)
	public ModelAndView deleteTaxAuthority(@PathVariable Long taxAuthorityId) {
		logger.debug(">> deleteTaxAuthority");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			adminSystemDataService.verifyAndDeleteTaxAuthority(taxAuthorityId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("deleteTaxAuthority >>");
		return modelView;
	}

	// for addTaxType testing
	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_TYPE, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/addTaxType", method = RequestMethod.POST)
	public ModelAndView addTaxType(@ModelAttribute TaxAuthorityForm taxAuthorityForm,
			@ModelAttribute TaxTypeForm taxTypeForm) {
		logger.debug(">> addTaxType");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			@SuppressWarnings("unused")
			Long taxTypeId = adminSystemDataService.addTaxType(taxTypeForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		logger.debug("addTaxType >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_TYPE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateTaxTypeForm/{taxTypeId}", method = RequestMethod.GET)
	public ModelAndView showUpdateTaxTypeForm(@PathVariable Long taxTypeId) {
		logger.debug(">> showUpdateTaxTypeForm");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {

			// id=taxTypeId
			TaxTypeForm taxTypeForm = adminSystemDataService.getTaxTypeForm(taxTypeId);
			modelView.addObject("taxTypeForm", taxTypeForm);
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		}

		logger.debug("showUpdateTaxTypeForm >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_TYPE, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateTaxType", method = RequestMethod.POST)
	public ModelAndView updateTaxType(@ModelAttribute TaxAuthorityForm taxAuthorityForm,
			@ModelAttribute TaxTypeForm taxTypeForm) {
		logger.debug(">> updateTaxType");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {
			adminSystemDataService.updateTaxType(taxTypeForm);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("updateTaxType >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).TAX_TYPE, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/deleteTaxType/{taxTypeId}", method = RequestMethod.GET)
	public ModelAndView deleteTaxType(@PathVariable Long taxTypeId) {
		logger.debug(">> deleteTaxType");
		ModelAndView modelView = new ModelAndView("form");
		List<TaxAuthority> taxAuthorityList = adminSystemDataService.getTaxAuthorityList();
		modelView.addObject("taxAuthorityList", taxAuthorityList);
		List<TaxType> taxTypeList = adminSystemDataService.getTaxTypeList();
		modelView.addObject("taxTypeList", taxTypeList);
		List<UsState> usStateList = adminSystemDataService.getUsStateList();
		modelView.addObject("usStateList", usStateList);
		List<UsCounty> usCountyList = adminSystemDataService.getUsCountyList();
		modelView.addObject("usCountyList", usCountyList);
		try {

			adminSystemDataService.verifyAndDeleteTaxType(taxTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("deleteTaxType >>");
		return modelView;
	}

}
