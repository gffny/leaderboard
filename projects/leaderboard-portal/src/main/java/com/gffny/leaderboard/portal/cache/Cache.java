/**
 * 
 */
package com.gffny.leaderboard.portal.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class Cache {

	private static Logger logger = Logger.getLogger(Cache.class);

	protected String name;

	final private Map<String, Object> cache;

	final Object NULL_OBJECT = new Object();

	public Cache() {
		this(new HashMap<String, Object>());
	}

	protected Cache(Map<String, Object> cacheImpl) {
		this.cache = cacheImpl;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(CacheFetcher<T> fetcher) {
		if (!getCache().containsKey(fetcher.getKey())) {
			Object o = fetcher.fetch();
			// Certain fetchers may treat a null value as valid
			if (o == null && !fetcher.cacheNullValue()) {
				return null;
			}

			getCache().put(fetcher.getKey(), (o == null) ? NULL_OBJECT : o);
		}

		Object value = getCache().get(fetcher.getKey());
		if (value == NULL_OBJECT) {
			return null;
		}

		return (T) value;
	}

	public void clear() {
		cache.clear();
	}

	public int size() {
		return cache.size();
	}

	protected Map<String, Object> getCache() {
		return cache;
	}

	public void put(String key, Object o) {
		if (key != null) {
			cache.put(key, o);
		}
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public String getName() {
		return name;
	}
}
