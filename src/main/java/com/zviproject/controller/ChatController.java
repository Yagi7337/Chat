package com.zviproject.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zviproject.component.entity.Message;
import com.zviproject.service.ChatService;

@Configuration
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	ChatService chatService;
	
	
	public DetachedCriteria createDetachedCriteria(String name1, String name2) {
		DetachedCriteria detachedCriteria= DetachedCriteria.forClass(Message.class)
				.createAlias("sender", "send")
				.createAlias("receiver", "reciv")
				//.addOrder(Order.asc("time"))
				.setProjection(Projections.groupProperty("text"))
				.add(Restrictions.eq("send.name", name1))
				.add(Restrictions.eq("reciv.name", name2));
		return detachedCriteria;
	}

	/**
	 * Method for sending message between users
	 * 
	 * @param name1
	 * @param name2
	 * @return Collection<Message>
	 */
	@RequestMapping(value = "/{name1}/{name2}", method = RequestMethod.GET)
	public Collection<Message> sendMessage(@PathVariable("name1") String name1, @PathVariable("name2") String name2) {
		DetachedCriteria dc=createDetachedCriteria(name1, name2);
		return chatService.sendMessage(name1, name2, dc);
	}

	/**
	 * Get information about correspondence between users in pages Every page
	 * have 10 message
	 * 
	 * @param page
	 * @return Collection<Message>
	 */
	@RequestMapping(value = "/{name1}/{name2}/information/{page}", method = RequestMethod.GET)
	public Collection<Message> getInformation(@PathVariable("name1") String name1, @PathVariable("name2") String name2, @PathVariable("page") int page) {
		//DetachedCriteria dc=createDetachedCriteria(name1, name2);
		return chatService.getInformation(name1, name2, page);
	}

}
