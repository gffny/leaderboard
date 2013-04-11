/**
 * 
 */
package com.gffny.leaderboard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author John Gaffney (john@gffny.com) Jan 26, 2013
 * 
 */
public class AbstractService {

	/**
	 * @param <T>
	 * @param ex
	 * @param class1
	 * @param log2
	 * @return
	 */
	protected <T> List<T> logErrorReturnEmptyList(Exception ex, Logger log,
			Class<T> listType) {
		log.error(ex.getMessage());
		return new ArrayList<T>();
	}

	/**
	 * @param ex
	 * @param log
	 * @param class
	 * @return
	 * @return
	 */
	protected <T> T logErrorReturnEmptyClass(Exception ex, Logger log,
			Class<T> classToReturn) {
		log.error(ex.getMessage());
		final Class<T> classType = classToReturn;
		T objectToReturn = null;
		try {
			objectToReturn = classType.newInstance();
		} catch (Exception e) {
			log.error(classToReturn.getName() + " is not instantiable");
			return null;
		}
		return objectToReturn;
	}
}
