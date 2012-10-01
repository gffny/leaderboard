/**
 * 
 */
package com.gffny.leaderboard.portal.util;

import java.util.HashMap;
import java.util.Map;

import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com)
 * Jul 26, 2012
 *
 */
public class ViewUtil {
	
	public static Map<String, Object> getView(IGolfer model) {
		//TODO put something here that will manage the conversion of a IGolfer to a usable ftl map
		return new HashMap<String, Object>();
	}

}
