/**
 * 
 */
package com.gffny.leaderboard.portal.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.gffny.leaderboard.model.ICompetitionType;

/**
 * @author John Gaffney (john@gffny.com) Apr 22, 2013
 * 
 */
public class CompetitionOptionsDto extends BaseDto {

	private List<ICompetitionType> competitionTypeList = new ArrayList<ICompetitionType>();
	private List<String> competitionVisibilityChoiceList = new ArrayList<String>();

	/**
	 * 
	 * @param competitionTypeList
	 * @param competitionVisibilityChoiceList
	 */
	public CompetitionOptionsDto(List<ICompetitionType> competitionTypeList,
			List<String> competitionVisibilityChoiceList) {
		this.competitionTypeList = competitionTypeList;
		this.competitionVisibilityChoiceList = competitionVisibilityChoiceList;
	}

	/**
	 * @return the competitionTypeList
	 */
	public List<ICompetitionType> getCompetitionTypeList() {
		return competitionTypeList;
	}

	/**
	 * @return the competitionVisibilityChoiceList
	 */
	public List<String> getCompetitionVisibilityChoiceList() {
		return competitionVisibilityChoiceList;
	}
}
