package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.CompanyWorkerCompensation;

public interface CompanyWorkersCompensationDao extends GenericDao<CompanyWorkerCompensation, Long> {

	List<CompanyWorkerCompensation> getWorkersCompensations(Long companyId);

	Long getWorkersCompensationCount(Long id);

	List<CompanyWorkerCompensation> getCompanyWorkersCompensation(Long usStateId);

}