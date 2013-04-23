/**
 * 
 */
package com.gffny.leaderboard.model;

import java.util.List;

/**
 * @author John Gaffney (john@gffny.com) Apr 15, 2013
 * 
 */
public interface IGolfBag extends ISQLEntity {

	/**
	 * 
	 * @return
	 */
	public Integer getGolferId();

	/**
	 * 
	 * @return
	 */
	public List<IGolfEquipment> getGolfClubList();

	/**
	 * 
	 * @param golfClubId
	 * @return
	 */
	public IGolfEquipment getGolfClub(int golfClubId);

	/**
	 * 
	 * @param golfClub
	 */
	public void addClubToBag(IGolfEquipment golfClub);

}
