package com.toptal.blog.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.googlecode.ehcache.annotations.CacheException;

public class CustomCacheManager extends net.sf.ehcache.CacheManager{

    public CustomCacheManager(){
    	super();
    }
    
    /*Add your own cache methods here.
     * 
     * public void myCustomCacheMethod(){
     *    blabla;
     * }
     * */
    

    public int getNumOfElements(
        final String cacheName)
        throws IllegalStateException,
        ClassCastException,
        CacheException {

        Cache cache = this.getCache(cacheName);
        return cache.getSize();
    }

    public boolean isKeyInCache(
        final String cacheName,
        final Object key)
        throws IllegalStateException,
        ClassCastException {

        Cache cache = this.getCache(cacheName);
        return cache.isKeyInCache(key);
    }

    public Object getObject(
        final String cacheName,
        final Object key)
        throws IllegalStateException,
        ClassCastException,
        CacheException {
    	
        Cache cache = this.getCache(cacheName);
        Element element = cache.get(key);
        if (element != null && !cache.isExpired(element)) {
            return element.getObjectValue();
        }
        return null;
    }

    public void putObject(
        final String cacheName,
        final Object key,
        final Object object)
        throws IllegalStateException,
        ClassCastException,
        CacheException {

        Cache cache = this.getCache(cacheName);
        Element newElement = new Element(key, object);
        cache.put(newElement);
    }

    public void putOrUpdateObject(
        final String cacheName,
        final Object key,
        final Object object)
        throws IllegalStateException,
        ClassCastException,
        CacheException,
        NullPointerException {

        Cache cache = this.getCache(cacheName);
        Element newElement = new Element(key, object);
        if (cache.isKeyInCache(key)) {
            cache.replace(newElement);
        } else {
            cache.put(newElement);
        }
    }

    public void updateObject(
        final String cacheName,
        final Object key,
        final Object object)
        throws IllegalStateException,
        ClassCastException,
        NullPointerException {

        Cache cache = this.getCache(cacheName);
        Element newElement = new Element(key, object);
        cache.replace(newElement);
    }

    public boolean removeObject(
        final String cacheName,
        final Object key)
        throws IllegalStateException,
        ClassCastException,
        CacheException {

        Cache cache = this.getCache(cacheName);
        return cache.remove(key);
    }

    public void removeAllObjectsInOneCache(
        final String cacheName) {

        Cache cache = this.getCache(cacheName);
        cache.removeAll();
    }

    public void removeAllObjects() {

        this.clearAll();
    }

    public void removeCache(
        final String cacheName) {

        this.removeCache(cacheName);
    }

}
