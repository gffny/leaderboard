/**
 * 
 */
package com.gffny.leaderboard.model;

/**
 * @author John Gaffney (john@gffny.com) Apr 15, 2013
 * 
 */
public interface IGolfEquipment extends ISQLEntity {

	/**
	 * @see com.gffny.leaderboard.model.IEntity#getId()
	 */
	@Override
	public Integer getId();

	public void setNotes(String notes);

	public String getNotes();

	public void setDefault(boolean isDefault);

	public boolean isDefault();

	public void setClubLoft(String clubLoft);

	public String getClubLoft();

	public void setClubCategory(String clubCategory);

	public String getClubCategory();

	public void setClubType(String clubType);

	public String getClubType();

	public void setClubName(String clubName);

	public String getClubName();

	public void setManufacturer(String manufacturer);

	public String getManufacturer();
}
