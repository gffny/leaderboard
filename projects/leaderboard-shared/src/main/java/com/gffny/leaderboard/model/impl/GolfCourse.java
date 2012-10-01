/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Arrays;

import com.gffny.leaderboard.model.IGolfCourse;
import com.gffny.leaderboard.model.IGolfCourseHole;

/**
 * @author John Gaffney (john@gffny.com) Jul 31, 2012
 * 
 */
public class GolfCourse implements IGolfCourse {

	/**
	 * Used for testing to create a null course
	 */
	public GolfCourse() {

	}

	/**
	 * @param name
	 * @param par
	 * @param location
	 * @param holePar
	 * @param holeIndex
	 * @param teeDistance
	 * @param blueTeeDistance
	 * @param whiteTeeDistance
	 * @param greenTeeDistance
	 * @param redTeeDistance
	 */
	public GolfCourse(String name, int par, String location, String teeColour,
			int[] holePar, int[] holeIndex, int[] teeDistance) {
		this.name = name;
		this.par = par;
		this.location = location;
		this.teeColour = teeColour;
		this.holePar = holePar;
		this.holeIndex = holeIndex;
		this.teeDistance = teeDistance;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final int maxLen = 5;
		return "GolfCourse [name="
				+ name
				+ ", par="
				+ par
				+ ", location="
				+ location
				+ ", teeColour="
				+ teeColour
				+ ", holePar="
				+ (holePar != null ? Arrays.asList(holePar).subList(0,
						Math.min(holePar.length, maxLen)) : null)
				+ ", holeIndex="
				+ (holeIndex != null ? Arrays.asList(holeIndex).subList(0,
						Math.min(holeIndex.length, maxLen)) : null)
				+ ", teeDistance="
				+ (teeDistance != null ? Arrays.asList(teeDistance).subList(0,
						Math.min(teeDistance.length, maxLen)) : null) + "]";
	}

	private String name;
	private String location;
	private String teeColour;
	private int par;
	private int[] holePar;
	private int[] holeIndex;
	private int[] teeDistance;

	/**
	 * 
	 */
	public String getName() {
		return name;
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

	// HOLE CLASS
	/**
	 * @author John Gaffney (john@gffny.com) Jul 31, 2012
	 * 
	 */
	private class Hole implements IGolfCourseHole {

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

		/**
		 * @return the goldTeeDistance
		 */
		public int getTeeDistance() {
			return teeDistance;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "Hole [par=" + par + ", index=" + index + ", teeDistance="
					+ teeDistance + "]";
		}

		/**
		 * @param par
		 * @param index
		 * @param goldTeeDistance
		 * @param blueTeeDistance
		 * @param whiteTeeDistance
		 * @param greenTeeDistance
		 * @param redTeeDistance
		 */
		public Hole(int par, int index, int teeDistance) {
			this.par = par;
			this.index = index;
			this.teeDistance = teeDistance;
		}

		private int par;
		private int index;
		private int teeDistance;
	}
}
