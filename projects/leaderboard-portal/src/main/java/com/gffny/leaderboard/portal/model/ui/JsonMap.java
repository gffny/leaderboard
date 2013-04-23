package com.gffny.leaderboard.portal.model.ui;

import java.util.HashMap;

import com.gffny.leaderboard.model.JSONable;

@SuppressWarnings("hiding")
public class JsonMap<String, RESPONSE_ENTITY extends JSONable> extends
		HashMap<String, RESPONSE_ENTITY> implements JSONable {

	/**
   * 
   */
	private static final long serialVersionUID = 1L;

}
