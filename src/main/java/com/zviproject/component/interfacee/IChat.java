package com.zviproject.component.interfacee;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;

import com.zviproject.component.entity.Message;
import com.zviproject.component.entity.MessageToDisplay;
import com.zviproject.component.entity.User;

public interface IChat {
	/**
	 * Method for sending message between users
	 * 
	 * @param name1
	 * @param reciver
	 * @return int
	 */
	public int sendMessage(int sender, int reciver, String text);

	/**
	 * Get all information about correspondence between users
	 * 
	 * @param dc
	 * @param sender
	 * @param reciver
	 * @return Collection<Message>
	 */
	public Collection<Message> getFullInformation(int sender, int reciver, DetachedCriteria dc);

	/**
	 * Get information about correspondence between users
	 * 
	 * @param dc
	 * @param sender
	 * @param reciver
	 * @return Collection<Message>
	 */
	public Collection<MessageToDisplay> getInformation(int sender, int reciver);

	/**
	 * Register new user in DB
	 * 
	 * @param user
	 * @return int
	 */
	public int registerUser(User user);

	/**
	 * Update text of message by in DB
	 * 
	 * @param id
	 * @param message_id
	 * @param message - object of class Message
	 * @return 
	 */
	public void updateTextOfMessageById(int sender_id, int message_id, Message message);	
	
}

