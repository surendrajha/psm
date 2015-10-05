/**
 * 
 */
package com.epayroll.dao.employee;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.epayroll.dao.GenericDaoImpl;
import com.epayroll.entity.Message;

/**
 * @author Surendra Jha
 */
@SuppressWarnings("unchecked")
@Repository
public class PersonMessageDaoImpl extends GenericDaoImpl<Message, Long> implements PersonMessageDao {

	private Logger logger = LoggerFactory.getLogger(PersonMessageDaoImpl.class);

	@Override
	public List<Message> getReceivedMessages(Long personId) {
		logger.debug("in getPersonMessages .. personId:" + personId);
		return getCriteria().createAlias("toMessageMaps", "tm")
				.add(Restrictions.eq("tm.toPerson.id", personId))
				.addOrder(Order.desc("receivedAt")).list();
	}

}
