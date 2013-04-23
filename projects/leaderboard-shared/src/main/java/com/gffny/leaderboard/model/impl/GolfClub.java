/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import com.gffny.leaderboard.model.IGolfEquipment;
import com.gffny.leaderboard.model.abst.SQLEntity;

/**
 * @author John Gaffney (john@gffny.com) Apr 16, 2013
 * 
 */
public class GolfClub extends SQLEntity implements IGolfEquipment {

	private String manufacturer;
	private String clubType;
	private String clubCategory;
	private String clubLoft;
	private boolean isDefault;
	private String notes;

	/**
	 * 
	 * @param manufacturer
	 * @param clubName
	 * @param clubType
	 * @param clubCategory
	 * @param clubLoft
	 * @param isDefault
	 * @param notes
	 */
	public GolfClub(int id, String manufacturer, String clubName,
			String clubType, String clubCategory, String clubLoft,
			boolean isDefault, String notes) {
		super(clubName, id);
		this.manufacturer = manufacturer;
		this.clubType = clubType;
		this.clubCategory = clubCategory;
		this.clubLoft = clubLoft;
		this.isDefault = isDefault;
		this.notes = notes;
	}

	/**
	 * 
	 * @param manufacturer
	 * @param clubName
	 * @param clubType
	 * @param clubCategory
	 * @param clubLoft
	 * @param isDefault
	 * @param notes
	 */
	public GolfClub(String manufacturer, String clubName, String clubType,
			String clubCategory, String clubLoft, boolean isDefault,
			String notes) {
		super(clubName, 0);
		this.manufacturer = manufacturer;
		this.clubType = clubType;
		this.clubCategory = clubCategory;
		this.clubLoft = clubLoft;
		this.isDefault = isDefault;
		this.notes = notes;
	}

	/**
	 * @return the manufacturer
	 */
	@Override
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	@Override
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the clubType
	 */
	@Override
	public String getClubName() {
		return this.getName();
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	@Override
	public void setClubName(String clubName) {
		this.setName(clubName);
	}

	/**
	 * @return the clubType
	 */
	@Override
	public String getClubType() {
		return clubType;
	}

	/**
	 * @param clubType
	 *            the clubType to set
	 */
	@Override
	public void setClubType(String clubType) {
		this.clubType = clubType;
	}

	/**
	 * @return the clubCategory
	 */
	@Override
	public String getClubCategory() {
		return clubCategory;
	}

	/**
	 * @param clubCategory
	 *            the clubCategory to set
	 */
	@Override
	public void setClubCategory(String clubCategory) {
		this.clubCategory = clubCategory;
	}

	/**
	 * @return the clubLoft
	 */
	@Override
	public String getClubLoft() {
		return clubLoft;
	}

	/**
	 * @param clubLoft
	 *            the clubLoft to set
	 */
	@Override
	public void setClubLoft(String clubLoft) {
		this.clubLoft = clubLoft;
	}

	/**
	 * @return the isDefault
	 */
	@Override
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	@Override
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the notes
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
