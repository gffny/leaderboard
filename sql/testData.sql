INSERT INTO t_golf_society(golf_society_name) VALUES('Blood and Bandages');

INSERT INTO t_user(email_address, profile_handle, first_name, surname, location, handicap) VALUES("gaffney.ie@gmail.com", "gffny", "John", "Gaffney", "Cambridge, MA", "35");

INSERT INTO t_user(email_address, profile_handle, first_name, surname, location, handicap) VALUES("eoindeb@gmail.com", "debarra", "Eoin", "Barry", "East Cambridge, MA", "26");

INSERT INTO t_golf_club(club_name, club_location) VALUES("Fresh Pond", "Cambridge, MA");

INSERT INTO t_society_membership(golf_society_id, user_id) VALUES(0000000001, 0000000001);

INSERT INTO t_society_membership(golf_society_id, user_id) VALUES(0000000001, 0000000002);

INSERT INTO `t_club_course` (`golf_club_id`, `course_name`, `course_par`, `tee_colour`, 
 `distance_hole_1`, `index_hole_1`, `par_hole_1`, 
 `distance_hole_2`, `index_hole_2`, `par_hole_2`, 
 `distance_hole_3`, `index_hole_3`, `par_hole_3`, 
 `distance_hole_4`, `index_hole_4`, `par_hole_4`, 
 `distance_hole_5`, `index_hole_5`, `par_hole_5`,
 `distance_hole_6`, `index_hole_6`, `par_hole_6`,
 `distance_hole_7`, `index_hole_7`, `par_hole_7`,
 `distance_hole_8`, `index_hole_8`, `par_hole_8`, 
 `distance_hole_9`, `index_hole_9`, `par_hole_9`, 
 `distance_hole_10`, `index_hole_10`, `par_hole_10`, 
 `distance_hole_11`, `index_hole_11`, `par_hole_11`, 
 `distance_hole_12`, `index_hole_12`, `par_hole_12`, 
 `distance_hole_13`, `index_hole_13`, `par_hole_13`, 
 `distance_hole_14`, `index_hole_14`, `par_hole_14`, 
 `distance_hole_15`, `index_hole_15`, `par_hole_15`, 
 `distance_hole_16`, `index_hole_16`, `par_hole_16`, 
 `distance_hole_17`, `index_hole_17`, `par_hole_17`, 
 `distance_hole_18`, `index_hole_18`, `par_hole_18`)
 VALUES ("1", "FRESH POND WHITE", "70", "WHITE",
 "336", "2", "4",
 "249", "4", "4",
 "135", "8", "3",
 "350", "1", "4",
 "460", "3", "5",
 "172", "5", "3",
 "311", "7", "4",
 "112", "9", "3",
 "447", "6", "4",
 "336", "11", "4",
 "249", "13", "4",
 "135", "17", "3",
 "350", "10", "4",
 "460", "12", "5",
 "152", "14", "3",
 "311", "16", "4",
 "112", "18", "3",
 "447", "15", "4");
 
 INSERT INTO `leaderboard`.`t_scorecard` (`course_id`, `user_id`, `tee_played_off`, 
 `hole_one_shots`, `hole_two_shots`, `hole_three_shots`, `hole_four_shots`, `hole_five_shots`, 
 `hole_six_shots`, `hole_seven_shots`, `hole_eight_shots`, `hole_nine_shots`,
 `hole_ten_shots`, `hole_eleven_shots`, `hole_twelve_shots`, `hole_thirteen_shots`, 
 `hole_fourteen_shots`, `hole_fifteen_shots`, `hole_sixteen_shots`, `hole_seventeen_shots`,
 `hole_eighteen_shots`, `round_notes`, `round_date`) 
 VALUES ('1', '1', 'WHITES', '7', '4', '3', '5', '7', '6', '7', '5', '4', '7', '4', '3', '5', '7', '6', '7', '5', '4', 'Good driving, poor putting!', '2012-07-29');
 
 INSERT INTO `leaderboard`.`t_scorecard` (`course_id`, `user_id`, `tee_played_off`, 
 `hole_one_shots`, `hole_two_shots`, `hole_three_shots`, `hole_four_shots`, `hole_five_shots`, 
 `hole_six_shots`, `hole_seven_shots`, `hole_eight_shots`, `hole_nine_shots`,
 `hole_ten_shots`, `hole_eleven_shots`, `hole_twelve_shots`, `hole_thirteen_shots`, 
 `hole_fourteen_shots`, `hole_fifteen_shots`, `hole_sixteen_shots`, `hole_seventeen_shots`,
 `hole_eighteen_shots`, `round_notes`, `round_date`) 
 VALUES ('1', '1', 'WHITES', '6', '5', '5', '6', '8', '5', '9', '5', '4', '6', '5', '5', '6', '8', '5', '9', '5', '4', 'Good driving, poor putting!', '2012-07-25');
 
 INSERT INTO `leaderboard`.`t_competition` (`society_id`, `course_id`, `competition_name`, `competition_type`) VALUES ("1", "1", "Presidents Day Cup Day One", "STABLEFORD");
 
 INSERT INTO `leaderboard`.`t_competition_entry` (`competition_id`, `scorecard_id`, `society_member_id`) VALUES ("1", "1", "1");