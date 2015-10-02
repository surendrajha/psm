/**
 * 
 */
package com.epayroll.dao.company;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Role;
import com.epayroll.entity.Role.RoleType;

/**
 * @author Surendra Jha
 */
public interface RoleDao extends GenericDao<Role, Long> {

	Role getRole(RoleType roleType);

	List<Role> getCompanyRolesExceptAdmin();

	List<Role> getAdminUserRolesExceptAdmin();

	List<Role> getRoles(RoleType roleType);

	List<Role> getRoles(List<RoleType> roleTypes);

}
