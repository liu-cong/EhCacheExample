package com.toptal.blog.service;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.toptal.blog.service.interfaces.IDataService;

@Service
public class DataService implements IDataService{
	private ConcurrentHashMap<Integer, String> messages
	= new ConcurrentHashMap<Integer, String>();
	
//	@Override
//	public String getMessage(Integer id) {
//		System.out.println("Getting data from SOR......");
//		return messages.get(id);
//	}
//0d
//	@Override
//	public void setMessage (Integer id, String message){
//		messages.put(id, message);
//	}
	
//	@Override
//	@Cacheable(cacheName = "messageCache", selfPopulating = true)
//	public String getMessage(Integer id) {
//		System.out.println("Getting data from SOR......");
//		return messages.get(id);
//	}
//
//	@Override
//	public void setMessage (Integer id, String message){
//		messages.put(id, message);
//	}

	
@Override
@Cacheable(cacheName = "messageCache",selfPopulating = true,//use self populating cache
		 keyGenerator = @KeyGenerator (//method name is not included in cache key to work with @TriggersRemove
	                name = "HashCodeCacheKeyGenerator",
	                properties = @Property( name="includeMethod", value="false" )))	
public String getMessage(Integer id) {
	System.out.println("Getting data from SOR......");
	return messages.get(id);
}

@Override
@TriggersRemove (cacheName = "messageCache",
		 keyGenerator = @KeyGenerator (
	                name = "HashCodeCacheKeyGenerator",
	                properties = @Property( name="includeMethod", value="false" )))
public void setMessage (@PartialCacheKey Integer id, String message){
	messages.put(id, message);
}
}
