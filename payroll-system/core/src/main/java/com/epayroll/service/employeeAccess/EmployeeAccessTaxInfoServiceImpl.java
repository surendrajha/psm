package com.epayroll.service.employeeAccess;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epayroll.dao.employee.EmployeeTaxDao;
import com.epayroll.entity.EmployeeTax;
import com.epayroll.model.EmployeeW4DetailModel;

/**
 * @author Rajul Tiwari
 */
@Service
public class EmployeeAccessTaxInfoServiceImpl implements EmployeeAccessTaxInfoService {
	private Logger logger = LoggerFactory.getLogger(EmployeeAccessTaxInfoServiceImpl.class);

	@Autowired
	private EmployeeTaxDao employeeTaxDao;

	@Override
	public List<EmployeeTax> getEmployeeFederalTaxList(Long employeeId) {
		logger.debug(">> getEmployeeFederalTaxList");
		List<EmployeeTax> federalTaxList = employeeTaxDao.getFederalTaxList(employeeId);
		logger.debug("getEmployeeFederalTaxList >>");
		return federalTaxList;
	}

	@Override
	public EmployeeW4DetailModel getFederalFITDetail(Long employeeId) {
		logger.debug(">> getFederalFITDetail");
		EmployeeW4DetailModel employeeW4DetailModel = employeeTaxDao.getFITRecord(employeeId);
		logger.debug("getFederalFITDetail >>");
		return employeeW4DetailModel;
	}

	@Override
	public List<EmployeeTax> getEmployeeStateTaxList(Long employeeId) {
		logger.debug(">> getEmployeeStateTaxList");
		List<EmployeeTax> stateTaxList = employeeTaxDao.getStateTaxList(employeeId);
		logger.debug("getEmployeeStateTaxList >>");
		return stateTaxList;
	}

	@Override
	public List<EmployeeTax> getEmployeeLocalTaxList(Long employeeId) {
		logger.debug(">> getEmployeeLocalTaxList");
		List<EmployeeTax> localTaxList = employeeTaxDao.getLocalTaxList(employeeId);
		logger.debug("getEmployeeLocalTaxList >>");
		return localTaxList;
	}

	@Override
	public EmployeeW4DetailModel getStateSITDetail(Long employeeId) {
		logger.debug(">> getStateSITDetail");
		EmployeeW4DetailModel employeeSITDetail = employeeTaxDao.getSITRecord(employeeId);
		logger.debug("getStateSITDetail >>");
		return employeeSITDetail;
	}

}
