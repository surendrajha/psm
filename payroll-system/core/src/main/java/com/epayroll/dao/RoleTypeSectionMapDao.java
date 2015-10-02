package com.epayroll.dao;

import java.util.List;

import com.epayroll.constant.Section;
import com.epayroll.entity.Role.RoleType;
import com.epayroll.entity.RoleTypeSectionMap;

public interface RoleTypeSectionMapDao extends GenericDao<RoleTypeSectionMap, Long> {

	List<Section> getSections(RoleType roleType);

	void deleteSections(RoleType roleType);

}
