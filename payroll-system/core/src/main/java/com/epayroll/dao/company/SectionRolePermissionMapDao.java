/**
 * 
 */
package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.constant.PermissionType;
import com.epayroll.constant.Section;
import com.epayroll.dao.GenericDao;
import com.epayroll.entity.SectionRolePermissionMap;

/**
 * @author Surendra Jha
 */
public interface SectionRolePermissionMapDao extends GenericDao<SectionRolePermissionMap, Long> {

	List<SectionRolePermissionMap> getRolePermissions(Long companyId, Long roleId);

	PermissionType[] getPermissionTypes(Long companyId, Long roleId, Section section);

	void deletePermission(Long companyId, Long roleId);

}
