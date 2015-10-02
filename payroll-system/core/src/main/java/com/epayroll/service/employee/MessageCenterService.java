/**
 * 
 */
package com.epayroll.service.employee;

import java.util.List;

import com.epayroll.entity.Message;
import com.epayroll.entity.ToMessageMap;
import com.epayroll.exception.CommonException;
import com.epayroll.form.employee.MessageCenterForm;
import com.epayroll.model.employee.MessageCenter;

/**
 * @author Surendra Jha
 */
public interface MessageCenterService {

	List<MessageCenter> getPersons(Long companyId);

	void addMessage(MessageCenterForm messageCenterForm, Long personId) throws CommonException;

	List<Message> getReceivedMessages(Long personId);

	List<ToMessageMap> getSentMessages(Long personId);

	Message getPersonMessage(Long messageId) throws CommonException;

}
