/**
 * 
 */
package com.epayroll.service.employee;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epayroll.dao.PersonDao;
import com.epayroll.dao.employee.PersonMessageDao;
import com.epayroll.dao.employee.ToMessageMapDao;
import com.epayroll.entity.Message;
import com.epayroll.entity.Person;
import com.epayroll.entity.ToMessageMap;
import com.epayroll.entity.ToMessageMap.Status;
import com.epayroll.exception.CommonException;
import com.epayroll.exception.InstanceNotFoundException;
import com.epayroll.form.employee.MessageCenterForm;
import com.epayroll.model.employee.MessageCenter;

/**
 * @author Surendra Jha
 */
@Service
public class MessageCenterServiceImpl implements MessageCenterService {

	private Logger logger = LoggerFactory.getLogger(MessageCenterServiceImpl.class);

	@Autowired
	private PersonDao personDao;

	@Autowired
	private PersonMessageDao personMessageDao;

	@Autowired
	private ToMessageMapDao toMessageMapDao;

	@Override
	public List<MessageCenter> getPersons(Long companyId) {
		logger.debug(" in getPersons ..");
		MessageCenter messageCenter = null;
		List<MessageCenter> messageCenters = new ArrayList<>();
		List<Person> persons = personDao.getPersons(companyId);
		for (Person person : persons) {
			messageCenter = new MessageCenter();
			messageCenter.setPersionId(person.getId());
			messageCenter.setName(new StringBuilder(person.getFirstName()).append(" ")
					.append(person.getLastName()).append("[").append(person.getEmail()).append("]")
					.toString());
			messageCenters.add(messageCenter);
		}
		return messageCenters;
	}

	@Override
	@Transactional
	public void addMessage(MessageCenterForm messageCenterForm, Long personId)
			throws CommonException {
		logger.debug("messageCenterForm::" + messageCenterForm + ", person:" + personId);
		try {
			ToMessageMap toMessageMap = null;
			Message message = null;

			for (Long toPrsnId : messageCenterForm.getToPersionId()) {
				logger.debug("toPrsnId::" + toPrsnId);
				message = new Message();
				message.setFromPerson(personDao.find(personId));
				message.setSubject(messageCenterForm.getSubject());
				message.setMessage(messageCenterForm.getMessageText());

				toMessageMap = new ToMessageMap();
				toMessageMap.setToPerson(personDao.find(toPrsnId));
				toMessageMap.setMessage(message);
				toMessageMap.setStatus(Status.UNREAD);
				message.getToMessageMaps().add(toMessageMap);
				personMessageDao.save(message);
			}

		} catch (InstanceNotFoundException e) {
			logger.error("Error in addMessage::", e.getLocalizedMessage());
			throw new CommonException("Sorry: Message not Sent... Please Try Again!");
		} catch (Exception e) {
			logger.error("Error in addMessage::", e);
		}
	}

	@Override
	public List<Message> getReceivedMessages(Long personId) {
		logger.debug("in getReceivedMessage..");
		return personMessageDao.getReceivedMessages(personId);
	}

	@Override
	public List<ToMessageMap> getSentMessages(Long personId) {
		logger.debug("in getSentMessages..");
		return toMessageMapDao.getSentMessages(personId);
	}

	@Override
	public Message getPersonMessage(Long messageId) throws CommonException {
		logger.debug("in getPersonMessage..");
		try {
			return personMessageDao.find(messageId);
		} catch (InstanceNotFoundException e) {
			logger.error(" error in  getPersonMessage..", e.getMessage());
			throw new CommonException("Server Error.. Please try again!");
		}
	}

}
