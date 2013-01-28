/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.model.ICompetition.IGolfGroup;
import com.gffny.leaderboard.model.IGolfer;
import com.gffny.leaderboard.model.abst.SQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Sep 26, 2012
 * 
 */
public class GolfGroup extends SQLEntity implements IGolfGroup {

	private int groupSize;
	private List<IGolfer> groupList;
	private String groupType;

	/**
	 * 
	 * @param groupSize
	 */
	public GolfGroup(String groupName, int groupSize) {
		super(groupName, 0);
		this.groupList = new ArrayList<IGolfer>(groupSize);
		this.groupSize = groupSize;
		switch (groupSize) {
		case 2:
			groupType = TWOBALL;
			break;
		case 3:
			groupType = THREEBALL;
			break;
		case 4:
			groupType = FOURBALL;
			break;
		default:
			groupType = FOURBALL;
			break;
		}
	}

	/**
	 * 
	 * @param groupSize
	 */
	public GolfGroup(int groupId, String groupName, int groupSize) {
		super(groupName, 0);
		this.setId(groupId);
		this.groupList = new ArrayList<IGolfer>(groupSize);
		this.groupSize = groupSize;
		switch (groupSize) {
		case 2:
			groupType = TWOBALL;
			break;
		case 3:
			groupType = THREEBALL;
			break;
		case 4:
			groupType = FOURBALL;
			break;
		default:
			groupType = FOURBALL;
			break;
		}
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.IGolfGroup#getGroupName()
	 */
	@Override
	public String getGroupName() {
		return this.getName();
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.ICompetition.IGolfGroup#getGroupSize()
	 */
	@Override
	public int getGroupSize() {
		return groupSize;
	};

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.IGolfGroup#getGroupType()
	 */
	@Override
	public String getGroupType() {
		return groupType;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.IGolfGroup#getGolferList()
	 */
	@Override
	public List<IGolfer> getGolferList() {
		return groupList;
	}

	/**
	 * @see com.gffny.leaderboard.model.ICompetition.IGolfGroup#addGolfer(com.gffny.leaderboard.model.IGolfer)
	 */
	@Override
	public void addGolfer(IGolfer golfer) {
		groupList.add(golfer);
	}
}