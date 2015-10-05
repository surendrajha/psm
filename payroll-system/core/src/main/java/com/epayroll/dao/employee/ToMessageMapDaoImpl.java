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
import com.epayroll.entity.ToMessageMap;

/**
 * @author Surendra Jha
 */
@Repository
public class ToMessageMapDaoImpl extends GenericDaoImpl<ToMessageMap, Long> implements
		ToMessageMapDao {

	private Logger logger = LoggerFactory.getLogger(ToMessageMapDaoImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public List<ToMessageMap> getSentMessages(Long personId) {
		logger.debug("in getPersonMessages .. personId:" + personId);
		return getCriteria().createAlias("personMessage", "pm")
				.add(Restrictions.eq("pm.fromPerson.id", personId))
				.addOrder(Order.desc("pm.receivedAt")).list();
	}

}
