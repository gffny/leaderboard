/**
 * 
 */
package com.gffny.leaderboard.model.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.gffny.leaderboard.model.ICompetition;
import com.gffny.leaderboard.model.ICompetitionType;
import com.gffny.leaderboard.model.IGolfer;

/**
 * @author John Gaffney (john@gffny.com)
 * Aug 21, 2012
 *
 */
public class Competition implements ICompetition {
	
	/**
	 * @param name
	 * @param date
	 * @param firstTeeTime
	 * @param competitionTypeName
	 * @param roundList
	 */
	public Competition(String name, String date, String firstTeeTime,
			String competitionTypeName, List<ICompetitionRound> roundList) {
		this.name = name;
		this.date = date;
		this.firstTeeTime = firstTeeTime;
		this.competitionTypeName = competitionTypeName;
		this.roundList = roundList;
	}

	private String name;
	private String date;
	private String firstTeeTime;
	private String competitionTypeName;
	private List<ICompetition.ICompetitionRound> roundList;

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionName()
	 */
	public String getCompetitionName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionDate()
	 */
	public String getCompetitionDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionFirstTeeTime()
	 */
	public String getCompetitionFirstTeeTime() {
		return firstTeeTime;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionTypeName()
	 */
	public String getCompetitionTypeName() {
		return competitionTypeName;
	}

	/* (non-Javadoc)
	 * @see com.gffny.leaderboard.model.ICompetition#getCompetitionRoundList()
	 */
	public List<ICompetitionRound> getCompetitionRoundList() {
		return roundList;
	}
	
	/**
	 * 
	 * @author John Gaffney (john@gffny.com)
	 * Aug 21, 2012
	 */
	public void addCompetitionRound(ICompetitionRound newRound) {
		this.roundList.add(newRound);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 2;
		return "Competition [name="
				+ name
				+ ", date="
				+ date
				+ ", firstTeeTime="
				+ firstTeeTime
				+ ", competitionTypeName="
				+ competitionTypeName
				+ ", roundList="
				+ (roundList != null ? roundList.subList(0,
						Math.min(roundList.size(), maxLen)) : null) + "]";
	}

	private class CompetitionRound implements ICompetition.ICompetitionRound {
		
		/**
		 * @param name
		 * @param date
		 * @param firstTeeTime
		 * @param groupList
		 * @param teeTimeMap
		 */
		public CompetitionRound(String name, String date, String firstTeeTime,
				List<IGolfGroup> groupList, Map<IGolfGroup, String> teeTimeMap) {
			this.name = name;
			this.date = date;
			this.firstTeeTime = firstTeeTime;
			this.groupList = groupList;
			this.teeTimeMap = teeTimeMap;
		}

		private String name;
		private String date;
		private String firstTeeTime;
		private List<IGolfGroup> groupList;
		private Map<IGolfGroup, String> teeTimeMap;

		public String getRoundName() {
			return name;
		}

		public String getRoundDate() {
			return date;
		}

		public String getRoundFirstTeeTime() {
			return firstTeeTime;
		}

		public List<IGolfGroup> getGroupList() {
			return groupList;
		}

		public Map<IGolfGroup, String> getTeeTimeMap() {
			return teeTimeMap;
		}
		
		public String getGroupTeeTime(IGolfGroup group) {
			return teeTimeMap.get(group);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			final int maxLen = 2;
			return "CompetitionRound [name="
					+ name
					+ ", date="
					+ date
					+ ", firstTeeTime="
					+ firstTeeTime
					+ ", groupList="
					+ (groupList != null ? toString(groupList, maxLen) : null)
					+ ", teeTimeMap="
					+ (teeTimeMap != null ? toString(teeTimeMap.entrySet(),
							maxLen) : null) + "]";
		}

		private String toString(Collection<?> collection, int maxLen) {
			StringBuilder builder = new StringBuilder();
			builder.append("[");
			int i = 0;
			for (Iterator<?> iterator = collection.iterator(); iterator
					.hasNext() && i < maxLen; i++) {
				if (i > 0)
					builder.append(", ");
				builder.append(iterator.next());
			}
			builder.append("]");
			return builder.toString();
		}
	}
	
	private class CompetitionGroup implements ICompetition.IGolfGroup {

		/**
		 * @param name
		 * @param type
		 * @param golferList
		 */
		public CompetitionGroup(String name, String type,
				List<IGolfer> golferList) {
			this.name = name;
			this.type = type;
			this.golferList = golferList;
		}

		String name;
		private String type;
		private List<IGolfer> golferList;

		public String getGroupName() {
			return name;
		}

		public String getGroupType() {
			return type;
		}

		public List<IGolfer> getGolferList() {
			return golferList;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			final int maxLen = 2;
			return "CompetitionGroup [name="
					+ name
					+ ", type="
					+ type
					+ ", golferList="
					+ (golferList != null ? golferList.subList(0,
							Math.min(golferList.size(), maxLen)) : null) + "]";
		}
	}
}
