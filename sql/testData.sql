INSERT INTO t_golf_society(golf_society_name) VALUES('Blood and Bandages');

INSERT INTO t_user(email_address, profile_handle, first_name, surname, location, handicap) VALUES("gaffney.ie@gmail.com", "gffny", "John", "Gaffney", "Cambridge, MA", "35");

INSERT INTO t_user(email_address, profile_handle, first_name, surname, location, handicap) VALUES("eoindeb@gmail.com", "debarra", "Eoin", "Barry", "East Cambridge, MA", "26");

INSERT INTO t_golf_course(name, par, location) VALUES("Fresh Pond", 70, "Cambridge, MA");

INSERT INTO t_society_membership(golf_society_id, user_id) VALUES(0000000001, 0000000001);

INSERT INTO t_society_membership(golf_society_id, user_id) VALUES(0000000001, 0000000002);

INSERT INTO `leaderboard`.`t_golf_course` (`name`, `par`, `location`, 
`hole_one_blue`, `hole_one_white`, `hole_one_green`, `hole_one_red`, `hole_one_par`, `hole_one_index`, 
`hole_two_blue`, `hole_two_white`, `hole_two_green`, `hole_two_red`, `hole_two_par`, `hole_two_index`, 
`hole_three_blue`, `hole_three_white`, `hole_three_green`, `hole_three_red`, `hole_three_par`, `hole_three_index`, 
`hole_four_blue`, `hole_four_white`, `hole_four_green`, `hole_four_red`, `hole_four_par`, `hole_four_index`, 
`hole_five_blue`, `hole_five_white`, `hole_five_green`, `hole_five_red`, `hole_five_par`, `hole_five_index`, 
`hole_six_blue`, `hole_six_white`, `hole_six_green`, `hole_six_red`, `hole_six_par`, `hole_six_index`, 
`hole_seven_blue`, `hole_seven_white`, `hole_seven_green`, `hole_seven_red`, `hole_seven_par`, `hole_seven_index`, 
`hole_eight_blue`, `hole_eight_white`, `hole_eight_green`, `hole_eight_red`, `hole_eight_par`, `hole_eight_index`, 
`hole_nine_blue`, `hole_nine_white`, `hole_nine_green`, `hole_nine_red`, `hole_nine_par`, `hole_nine_index`, 
`hole_ten_blue`, `hole_ten_white`, `hole_ten_green`, `hole_ten_red`, `hole_ten_par`, `hole_ten_index`, 
`hole_eleven_blue`, `hole_eleven_white`, `hole_eleven_green`, `hole_eleven_red`, `hole_eleven_par`, `hole_eleven_index`, 
`hole_twelve_blue`, `hole_twelve_white`, `hole_twelve_green`, `hole_twelve_red`, `hole_twelve_par`, `hole_twelve_index`, 
`hole_thirteen_blue`, `hole_thirteen_white`, `hole_thirteen_green`, `hole_thirteen_red`, `hole_thirteen_par`, `hole_thirteen_index`, 
`hole_fourteen_blue`, `hole_fourteen_white`, `hole_fourteen_green`, `hole_fourteen_red`, `hole_fourteen_par`, `hole_fourteen_index`, 
`hole_fifteen_blue`, `hole_fifteen_white`, `hole_fifteen_green`, `hole_fifteen_red`, `hole_fifteen_par`, `hole_fifteen_index`, 
`hole_sixteen_blue`, `hole_sixteen_white`, `hole_sixteen_green`, `hole_sixteen_red`, `hole_sixteen_par`, `hole_sixteen_index`, 
`hole_seventeen_blue`, `hole_seventeen_white`, `hole_seventeen_green`, `hole_seventeen_red`, `hole_seventeen_par`, `hole_seventeen_index`,
 `hole_eighteen_blue`, `hole_eighteen_white`, `hole_eighteen_green`, `hole_eighteen_red`, `hole_eighteen_par`, `hole_eighteen_index`) 
 VALUES ("Fresh Pond Golf Course", "70", "Cambridge, MA", 
 "376", "356", "336", "326", "4", "2",
 "289", "269", "249", "239", "4", "4",
 "175", "155", "135", "125", "3", "8",
 "390", "370", "350", "340", "4", "1",
 "500", "480", "460", "450", "5", "3",
 "192", "172", "152", "142", "3", "5",
 "351", "331", "311", "301", "4", "7",
 "152", "132", "112", "102", "3", "9",
 "487", "467", "447", "437", "4", "6",
 "376", "356", "336", "326", "4", "11",
 "289", "269", "249", "239", "4", "13",
 "175", "155", "135", "125", "3", "17",
 "390", "370", "350", "340", "4", "10",
 "500", "480", "460", "450", "5", "12",
 "192", "172", "152", "142", "3", "14",
 "351", "331", "311", "301", "4", "16",
 "152", "132", "112", "102", "3", "18",
 "487", "467", "447", "437", "4", "15");
 
  INSERT INTO `leaderboard`.`t_scorecard` (`golf_course_id`, `user_id`, `tee_played_off`, 
 `hole_one_shots`, `hole_two_shots`, `hole_three_shots`, `hole_four_shots`, `hole_five_shots`, 
 `hole_six_shots`, `hole_seven_shots`, `hole_eight_shots`, `hole_nine_shots`,
 `hole_ten_shots`, `hole_eleven_shots`, `hole_twelve_shots`, `hole_thirteen_shots`, 
 `hole_fourteen_shots`, `hole_fifteen_shots`, `hole_sixteen_shots`, `hole_seventeen_shots`,
 `hole_eighteen_shots`, `round_notes`) 
 VALUES ('2', '1', 'WHITES', '6', '5', '5', '6', '8', '5', '9', '5', '4', '6', '5', '5', '6', '8', '5', '9', '5', '4', 'Good driving, poor putting!');