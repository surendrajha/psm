package com.epayroll.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

import com.epayroll.service.company.CompanyService;
import com.epayroll.ui.contoller.company.CompanyDepartmentController;

/**
 * 
 * @author Rajul Tiwari
 * 
 */

public class CompanyDepartmentControllerTest extends TestRoot {

	@Autowired
	private CompanyDepartmentController companyDepartmentController;

	@Autowired
	private CompanyService companyService;

	// @Test
	public void showDepartmentList() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/department/showList");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(2L));
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentListPage");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyDepartmentList");
		System.out.println(mav.getModelMap().get("companyDepartmentList"));
	}

	// @Test
	public void addDepartment() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/department/add");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("deptId", "Admin11");
		request.setParameter("deptName", "Admin11");
		request.setParameter("description", "Admin11 description");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentListPage");
		// when validation failed
		// ModelAndViewAssert.assertViewName(mav, "companyDepartmentForm");

	}

	@Test
	public void deleteDepartment() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/department/delete/2");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentListPage");
	}

	// @Test
	public void showUpdateForm() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/department/showUpdateForm/1");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentForm");
		ModelAndViewAssert.assertModelAttributeAvailable(mav, "companyDepartment");
	}

	// @Test
	public void updateDepartment() throws Exception {
		request.setMethod("POST");
		request.setRequestURI("/company/department/update");
		request.getSession().setAttribute("loggedInUser", companyService.getCompanyUser(1L));
		// set parameters value of requested object
		request.setParameter("id", "1");
		request.setParameter("deptId", "Admin1");
		request.setParameter("deptName", "Admin2");
		request.setParameter("description", "Admin3 description");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentListPage");
		// when validation failed
		// ModelAndViewAssert.assertViewName(mav, "companyDepartmentForm");
	}

	// @Test
	public void updateDepartmentStatus() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/company/department/updateStatus/3/Active");
		ModelAndView mav = handlerAdapter.handle(request, response, companyDepartmentController);
		ModelAndViewAssert.assertViewName(mav, "companyDepartmentListPage");
	}
}