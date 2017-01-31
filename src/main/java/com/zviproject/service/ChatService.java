package com.zviproject.service;

import java.util.Collection;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zviproject.component.entity.Message;
import com.zviproject.component.entity.MessageToDisplay;
import com.zviproject.component.entity.User;
import com.zviproject.component.interfacee.IChat;

@Service
public class ChatService {
	@Autowired
	IChat iChat;

	/**
	 * Method for sending message between users
	 * 
	 * @param sender
	 * @param reciver
	 * @param text
	 * @return int
	 */
	public int sendMessage(int sender, int reciver, String text) {

		return iChat.sendMessage(sender, reciver, text);
	}

	/**
	 * Get information about correspondence between users in pages Every page
	 * have 10 message
	 * 
	 * @param dc
	 * @param sender
	 * @param reciver
	 * @return Collection<Message>
	 */
	public Collection<MessageToDisplay> getInformation(int sender, int reciver) {
		return iChat.getInformation(sender, reciver);

	}

	/**
	 * Register new user in chat<br>
	 * Return information for user and <b>ID</b>.
	 * 
	 * @param user
	 * @return String
	 */
	public int registerUser(User user) {
		return iChat.registerUser(user);
	}

	/**
	 * Get all information about correspondence between users
	 * 
	 * @param dc
	 * @param sender
	 * @param reciver
	 * @return Collection<Message>
	 */
	public Collection<Message> getFullInformation(int sender, int reciver, DetachedCriteria dc) {
		return iChat.getFullInformation(sender, reciver, dc);
	}
	
	public void updateTextOfMessageById(int sender_id, int message_id, Message message){
		iChat.updateTextOfMessageById(sender_id, message_id, message);
	}

}
