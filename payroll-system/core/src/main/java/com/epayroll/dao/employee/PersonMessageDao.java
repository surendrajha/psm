/**
 * 
 */
package com.epayroll.dao.employee;

import java.util.List;

import com.epayroll.dao.GenericDao;
import com.epayroll.entity.Message;

/**
 * @author Surendra Jha
 */
public interface PersonMessageDao extends GenericDao<Message, Long> {

	List<Message> getReceivedMessages(Long personId);

}
