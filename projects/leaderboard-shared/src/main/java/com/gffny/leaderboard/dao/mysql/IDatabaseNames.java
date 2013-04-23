/**
 * 
 */
package com.gffny.leaderboard.dao.mysql;

/**
 * @author John Gaffney (john@gffny.com) Apr 17, 2013
 * 
 */
public interface IDatabaseNames {

	/* TABLES */
	public static final String USER_TABLE = "t_usr";
	public static final String SCORECARD_TABLE = "t_scrcrd";
	public static final String COMPETITION_TABLE = "t_cmpttn";
	public static final String COMPETITION_ROUND_TABLE = "t_cmpttn_rnd";
	public static final String USER_GOLF_BAG_TABLE = "t_usr_glf_bg";
	public static final String EQUIPMENT_TABLE = "t_eqpmnt";
	public static final String COMPETITION_TY_TABLE = "t_cmpttn_ty";
	public static final String COMPETITION_ENTRY_TABLE = "t_cmpttn_entry";

	/* ID Columns */
	public static final String BAG_ID_COL = "bg_id";
	public static final String USER_ID_COL = "usr_id";
	public static final String CLUB_ID_COL = "clb_id";
	public static final String EQUIPMENT_ID_COL = "eqpmnt_id";
	public static final String MARKER_ID_COL = "mrkr_id";
	public static final String SCORECARD_ID_COL = "scrcrd_id";
	public static final String COMPETITION_ROUND_ID_COL = "cmpttn_rnd_id";
	public static final String COMPETITION_ID_COL = "cmpttn_id";
	public static final String GOLFER_ID_COL = "glfr_id";
	public static final String TYPE_ID_COL = "ty_id";
	public static final String COURSE_ID_COL = "crs_id";

	/* NAME Columns */
	public static final String MANUFACTURER_NM_COL = "mnfctrr_nm";
	public static final String CLUB_NM_COL = "clb_nm";
	public static final String COMPETITION_NM_COL = "cmpttn_nm";
	public static final String TYPE_NM_COL = "ty_nm";
	public static final String ROUND_NM_COL = "rnd_nm";

	/* OTHER Columns */
	public static final String CLUB_TY_COL = "clb_typ";
	public static final String CLUB_CTGRY_COL = "clb_ctgry";
	public static final String CLUB_LFT_COL = "clb_lft";
	public static final String DEFAULT_COL = "dflt";
	public static final String TEE_CLR_CD_COL = "tee_clr_cd";
	public static final String NOTES_COL = "nts";
	public static final String HANDICAP_COL = "hndcp";
	public static final String SCORECARD_DATE_COL = "scrcrd_d";
	public static final String COMPETITION_TY_ID_COL = "cmpttn_ty_id";
	public static final String COMPETITION_VISIBILITY_COL = "cmpttn_vsblty";
	public static final String HOLE_QUANTITY_COL = "hl_qntty";
	public static final String TYPE_SCHEDULER_COL = "ty_schdlr";
	public static final String TYPE_SCORER_COL = "ty_scrr";
	public static final String TYPE_TEAM_COL = "ty_tm";
	public static final String TYPE_PAIR_COL = "ty_pr";
	public static final String TYPE_INDIVIDUAL_COL = "ty_indvdl";
	public static final String ROUND_NMBR_COL = "rnd_nmbr";
	public static final String ROUND_D_COL = "rnd_d";
}