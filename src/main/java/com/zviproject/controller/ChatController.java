package com.zviproject.controller;

import java.util.Collection;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zviproject.component.entity.Message;
import com.zviproject.component.entity.MessageToDisplay;
import com.zviproject.component.entity.User;
import com.zviproject.service.ChatService;

@Configuration
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatService chatService;
	

	/**
	 * Request for return history of message
	 * 
	 * @param name1
	 * @param name2
	 * @return DetachedCriteria
	 */
	public DetachedCriteria createDetachedCriteria(int name1, int name2) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class)
				.addOrder(Order.asc("time"))
				.add(Restrictions.or(
						Restrictions.and(
								Restrictions.eq("sender", name1), 
								Restrictions.eq("receiver", name2)), 
						Restrictions.and(
								Restrictions.eq("sender", name2), 
								Restrictions.eq("receiver", name1))));
		return detachedCriteria;
	}

	/**
	 * Method for sending messages between users
	 * 
	 * @param sender
	 * @param reciver
	 * @return int
	 */
	@RequestMapping(value = "/{sender}/{reciver}", method = RequestMethod.POST)
	public int sendMessage(@PathVariable("sender") int sender, @PathVariable("reciver") int reciver, @RequestBody String text) {
		return chatService.sendMessage(sender, reciver, text);
	}

	/**
	 * Register new user in chat<br>
	 * Return information for user.
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	public int registerUser(@RequestBody User user) {

		return chatService.registerUser(user);

	}

	/**
	 * Get information about correspondence between users in pages
	 * 
	 * @param page
	 * @return Collection<MessageToDisplay>
	 */
	@RequestMapping(value = "/{sender}/{reciver}/information", method = RequestMethod.GET)
	public Collection<MessageToDisplay> getInformation(@PathVariable("sender") int sender, @PathVariable("reciver") int reciver) {
		
		return chatService.getInformation(sender, reciver);
	}
	
	/**
	 * Get all information about correspondence between users
	 * 
	 * @param dc
	 * @param sender
	 * @param reciver
	 * @return Collection<Message>
	 */
	@RequestMapping(value = "/{sender}/{reciver}/informationF", method = RequestMethod.GET)
	public Collection<Message> getFullInformation(@PathVariable("sender") int sender, @PathVariable("reciver")int reciver) {
		DetachedCriteria dc = createDetachedCriteria(sender, reciver);
		return chatService.getFullInformation(sender, reciver, dc);
	}
	
	@RequestMapping(value = "/{sender_id}/{message_id}/update_message", method = RequestMethod.PUT)
	public void updateTextOfMessageById(@PathVariable("sender_id") int sender_id, 
			@PathVariable("message_id") int message_id, 
			@RequestBody Message message) {
		chatService.updateTextOfMessageById(sender_id, message_id, message);
	}

}
