package com.epayroll.ui.contoller.company;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.constant.ServerStatus;
import com.epayroll.entity.Company;
import com.epayroll.entity.CompanyDepartment;
import com.epayroll.entity.CompanyDepartment.Status;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.model.ServerResponse;
import com.epayroll.service.CommonService;
import com.epayroll.service.company.CompanyService;
import com.epayroll.spring.authorization.AuthorizationUtil;
import com.epayroll.utils.ErrorUtils;

/**
 * @author Rajul Tiwari
 */
@Controller
@RequestMapping("/company/department")
public class CompanyDepartmentController {
	private Logger logger = LoggerFactory.getLogger(CompanyDepartmentController.class);
	@Autowired
	private CompanyService companyService;

	@Autowired
	private CommonService commonService;

	private Company getCompanyFromSession() {
		Company company = commonService.getCompany(AuthorizationUtil.getLoginUser().getId());
		return company;
	}

	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	public ModelAndView showList(HttpSession session) {
		logger.debug(">> showList");
		ModelAndView modelView = new ModelAndView("companyDepartment");
		try {
			modelView.addObject("companyDepartmentList",
					companyService.getCompanyDepartments(getCompanyFromSession()));
			modelView.addObject("companyDepartment", new CompanyDepartment());
			modelView.addObject("departmentStatusList", Status.values());
		} catch (Exception e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showList >>");
		return modelView;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/showAddForm", method = RequestMethod.GET)
	@ResponseBody
	public ServerResponse showAddForm() {
		logger.debug(">> showAddForm");
		ServerResponse serverResponse = new ServerResponse();
		try {
			CompanyDepartment companyDepartment = new CompanyDepartment();
			serverResponse.setData(companyDepartment);
			serverResponse.setStatus(ServerStatus.SUCCESS);
		} catch (Exception e) {
			logger.error("ERROR :: " + e);
			serverResponse.setStatus(ServerStatus.ERROR);
			serverResponse.setMessage(e.getMessage());
		}
		logger.debug("showAddForm >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).INSERT)")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse add(@Valid CompanyDepartment companyDepartment,
			BindingResult bindingResult, HttpSession session) {
		logger.debug(">> add");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				companyDepartment.setCompany(getCompanyFromSession());
				companyDepartment = companyService.addDepartment(companyDepartment);
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setMessage("Department Added Successfully");
				serverResponse.setData(companyDepartment.getId());
			} catch (Exception e) {
				logger.error("ERROR :: " + e.getMessage());
				serverResponse.setStatus(ServerStatus.ERROR);
				serverResponse.setMessage("Some error occurred while adding the record : "
						+ e.getMessage());
			}
		} else {
			logger.error("Validation Error" + bindingResult.toString());
			serverResponse.setMessage("Validation Error :: "
					+ ErrorUtils.getValidationErrorMessage(bindingResult.getAllErrors()));
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("add >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).DELETE)")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse delete(@RequestParam Long id) {
		logger.debug(">> delete");
		ModelAndView modelView = new ModelAndView();
		ServerResponse serverResponse = new ServerResponse();
		try {
			if (companyService.verifyAndDeleteDepartment(id)) {
				serverResponse.setMessage("Department Deleted Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
			} else {
				serverResponse
						.setMessage("Payroll Records are associated with this Department. So you cannot delete this Department from system.");
				serverResponse.setStatus(ServerStatus.ERROR);

			}
			modelView.setViewName("companyDepartment");
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
			serverResponse.setMessage("Some error occurred while deleting the record : "
					+ e.getMessage());
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("delete >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/showUpdateForm", method = RequestMethod.GET)
	@ResponseBody
	public CompanyDepartment showUpdateForm(@RequestParam Long id) {
		// TODO Make hidden variable in JSP of name "status"
		logger.debug(">> showUpdateForm");
		CompanyDepartment companyDepartment = null;
		try {
			companyDepartment = companyService.findDepartment(id);
		} catch (InstanceNotFoundException e) {
			logger.error("ERROR :: " + e.getMessage());
		}
		logger.debug("showUpdateForm >>");
		return companyDepartment;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServerResponse update(@Valid CompanyDepartment companyDepartment,
			BindingResult bindingResult) {
		logger.debug(">> update");
		ServerResponse serverResponse = new ServerResponse();
		if (!bindingResult.hasErrors()) {
			try {
				companyDepartment.setCompany(getCompanyFromSession());
				companyDepartment = companyService.updateDepartment(companyDepartment);
				serverResponse.setMessage("Department Updated Successfully");
				serverResponse.setStatus(ServerStatus.SUCCESS);
				serverResponse.setData(companyDepartment);
				logger.debug("serverResponse.getdata()" + serverResponse.getData());
			} catch (Exception e) {
				logger.error(e.getMessage());
				serverResponse.setMessage("Some error occurred while updating the record : "
						+ e.getMessage());
				serverResponse.setStatus(ServerStatus.ERROR);
			}
		} else {
			logger.error("Validation Error" + bindingResult.toString());
			serverResponse.setMessage("Validation Error :: "
					+ ErrorUtils.getValidationErrorMessage(bindingResult.getAllErrors()));
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("update >>");
		return serverResponse;
	}

	// @PreAuthorize("hasPermission(T(com.epayroll.constant.Section).COMPANY_DEPARTMENT, "
	// + "T(com.epayroll.constant.PermissionType).UPDATE)")
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse updateStatus(@RequestParam Long id, @RequestParam String status) {
		logger.debug(">> updateStatus");
		ServerResponse serverResponse = new ServerResponse();
		logger.debug("test Status :::" + status + " , " + id);
		try {
			// Status deptStatus = (Status) status;
			if (status.equals(Status.ACTIVE.getName())) {
				companyService.updateDepartmentStatus(id, Status.ACTIVE);
			} else {
				companyService.updateDepartmentStatus(id, Status.INACTIVE);
			}
			// companyService.updateDepartmentStatus(id, deptStatus);
			serverResponse.setMessage("Department Status Updated Successfully");
			serverResponse.setStatus(ServerStatus.SUCCESS);
		} catch (InstanceNotFoundException e) {
			logger.error(e.getMessage());
			serverResponse.setMessage("Some error occurred while updating the record : "
					+ e.getMessage());
			serverResponse.setStatus(ServerStatus.ERROR);
		}
		logger.debug("updateStatus >>");
		return serverResponse;
	}
}