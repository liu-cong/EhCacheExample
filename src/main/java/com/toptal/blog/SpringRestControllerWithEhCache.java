package com.toptal.blog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.toptal.blog.service.MessageService;

@RestController  
@RequestMapping("/")  
public class SpringRestControllerWithEhCache {
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)  
	public String getMessage(@PathVariable Integer id) {  
		String message = messageService.getMessage(id);
		System.out.println("get message ["+message+"] at "+new Date());
		return message;  
	}
	
	@RequestMapping(value = "/message/set/{id}/{message}", method = RequestMethod.POST)  
	public String setMessage(@PathVariable Integer id, @PathVariable String message) {  
		System.out.println("set message ["+message+"] at "+new Date());
		messageService.setMessage(id, message);
		return message;  
	}
}
