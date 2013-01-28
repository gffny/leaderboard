/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfCourseHole;
import com.gffny.leaderboard.model.abst.NoSQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Jul 31, 2012
 * 
 */
public class GolfCourse extends NoSQLEntity implements IGolfCourse {

	private String location;
	private String teeColour;
	private int par;
	private int[] holePar;
	private int[] holeIndex;
	private int[] teeDistance;
	private List<IGolfCourseHole> holeList;

	/**
	 * 
	 * @param name
	 * @param par
	 * @param location
	 * @param teeColour
	 * @param holeParArray
	 * @param holeIndexArray
	 * @param teeDistanceArray
	 */
	public GolfCourse(String name, int par, String location, String teeColour,
			int[] holeParArray, int[] holeIndexArray, int[] teeDistanceArray) {
		super(name);
		this.par = par;
		this.location = location;
		this.teeColour = teeColour;
		this.holePar = holeParArray;
		this.holeIndex = holeIndexArray;
		this.teeDistance = teeDistanceArray;
		this.holeList = populateHoleList(holeParArray, holeIndexArray,
				teeDistanceArray);
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param par
	 * @param location
	 * @param teeColour
	 * @param holeParArray
	 * @param holeIndexArray
	 * @param teeDistanceArray
	 */
	public GolfCourse(String id, String name, int par, String location,
			String teeColour, int[] holeParArray, int[] holeIndexArray,
			int[] teeDistanceArray) {
		super(name);
		this.setId(id);
		this.par = par;
		this.location = location;
		this.teeColour = teeColour;
		this.holePar = holeParArray;
		this.holeIndex = holeIndexArray;
		this.teeDistance = teeDistanceArray;
		this.holeList = populateHoleList(holeParArray, holeIndexArray,
				teeDistanceArray);
	}

	/**
	 * 
	 * @return
	 */
	public String getCourseId() {
		return this.getId();
	}

	/**
	 * 
	 */
	public int getPar() {
		return par;
	}

	/**
	 * 
	 */
	public int[] getHoleParArray() {
		return holePar;
	}

	/**
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public IGolfCourseHole getHole(int holeNumber) {
		return new GolfCourse.Hole(holePar[holeNumber - 1],
				holeIndex[holeNumber - 1], teeDistance[holeNumber - 1]);
	}

	/**
	 * 
	 */
	public int[] getTeeDistanceArray() {
		return teeDistance;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public int getHolePar(int holeNumber) {
		return holePar[holeNumber - 1];
	}

	/**
	 * 
	 */
	public int[] getHoleIndexArray() {
		return holeIndex;
	}

	/**
	 * 
	 * @param holeNumber
	 */
	public int getHoleIndex(int holeNumber) {
		return holeIndex[holeNumber - 1];
	}

	/**
	 * 
	 */
	public String getTeeColour() {
		return teeColour;
	}

	/**
	 * 
	 * @see com.gffny.leaderboard.model.IGolfCourse#getHoleList()
	 */
	public List<IGolfCourseHole> getHoleList() {
		return holeList;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 18;
		return "GolfCourse [holePar="
				+ (holePar != null ? Arrays.toString(Arrays.copyOf(holePar,
						Math.min(holePar.length, maxLen))) : null)
				+ ", holeIndex="
				+ (holeIndex != null ? Arrays.toString(Arrays.copyOf(holeIndex,
						Math.min(holeIndex.length, maxLen))) : null) + "]";
	}

	/**
	 * @param holeParArray
	 * @param holeIndexArray
	 * @param teeDistanceArray
	 */
	private List<IGolfCourseHole> populateHoleList(int[] holeParArray,
			int[] holeIndexArray, int[] teeDistanceArray) {
		List<IGolfCourseHole> holeList = new ArrayList<IGolfCourseHole>();
		for (int i = 0; i < holeParArray.length; i++) {
			holeList.add(new Hole("Hole " + (i + 1), holeParArray[i],
					holeIndexArray[i], teeDistanceArray[i]));
		}
		return holeList;
	}

	// HOLE CLASS
	/**
	 * @author John Gaffney (john@gffny.com) Jul 31, 2012
	 * 
	 */
	private class Hole extends NoSQLEntity implements IGolfCourseHole {

		/**
		 * @return the par
		 */
		public int getPar() {
			return par;
		}

		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		public String getIndexAsString() {
			return Integer.toString(getIndex());
		}

		/**
		 * @return the goldTeeDistance
		 */
		public int getTeeDistance() {
			return teeDistance;
		}

		/**
		 * 
		 * @see com.gffny.leaderboard.model.IGolfCourseHole#getTeeDistanceAsString()
		 */
		public String getTeeDistanceAsString() {
			return Integer.toString(getTeeDistance());
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "Hole [par=" + par + ", index=" + index + ", teeDistance="
					+ teeDistance + "]";
		}

		/**
		 * 
		 * @param par
		 * @param index
		 * @param teeDistance
		 */
		public Hole(int par, int index, int teeDistance) {
			super("");
			this.par = par;
			this.index = index;
			this.teeDistance = teeDistance;
		}

		/**
		 * 
		 * @param holeName
		 * @param par
		 * @param index
		 * @param teeDistance
		 */
		public Hole(String holeName, int par, int index, int teeDistance) {
			super(holeName);
			this.par = par;
			this.index = index;
			this.teeDistance = teeDistance;
		}

		private int par;
		private int index;
		private int teeDistance;
	}
}
