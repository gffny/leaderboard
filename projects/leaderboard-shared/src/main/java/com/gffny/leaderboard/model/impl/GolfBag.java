/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gffny.leaderboard.model.IGolfBag;
import com.gffny.leaderboard.model.IGolfEquipment;
import com.gffny.leaderboard.model.abst.SQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Apr 15, 2013
 * 
 */
public class GolfBag extends SQLEntity implements IGolfBag {

	private Map<Integer, IGolfEquipment> golfClubMap = new HashMap<Integer, IGolfEquipment>();
	private Logger log = Logger.getLogger(GolfBag.class);

	/**
	 * 
	 * @param golferId
	 */
	public GolfBag(int golferId) {
		super(golferId + " Golf Bag", golferId);
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfBag#getGolferId()
	 */
	@Override
	public Integer getGolferId() {
		return this.getId();
	}

	/**
	 * 
	 * @param golfClub
	 */
	@Override
	public void addClubToBag(IGolfEquipment golfClub) {
		if (golfClubMap != null) {
			golfClubMap.put(golfClub.getId(), golfClub);
		} else {
			log.error("GolfBag.addClubToBag() - golfClubMap is null");
		}
	}

	/**
	 * 
	 * @param golfClubId
	 * @return
	 */
	@Override
	public IGolfEquipment getGolfClub(int golfClubId) {
		if (golfClubMap != null) {
			return golfClubMap.get(Integer.valueOf(golfClubId));
		} else {
			log.error("GolfBag.addClubToBag() - golfClubMap is null");
			return null; // TODO return an empty GolfEquipment instance instead
		}
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<IGolfEquipment> getGolfClubList() {
		if (golfClubMap != null) {
			return new ArrayList<IGolfEquipment>(golfClubMap.values());
		} else {
			log.error("GolfBag.addClubToBag() - golfClubMap is null");
			return new ArrayList<IGolfEquipment>();
		}
	}
}
