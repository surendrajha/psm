/**
 * 
 */
package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.ToMessageMap;

/**
 * @author Surendra Jha
 */
public interface ToMessageMapDao extends GenericDao<ToMessageMap, Long> {

	List<ToMessageMap> getSentMessages(Long personId);

}
