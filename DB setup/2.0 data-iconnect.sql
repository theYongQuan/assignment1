-- ----------------------------
-- Records of TBL_CODETYPE
-- ----------------------------
/*
-- Query: select * from tbl_codetype
LIMIT 0, 1000

-- Date: 2022-12-05 16:40
*/
INSERT INTO `tbl_codetype` (`ID`,`APP_ID`,`CODETYPE_ID`,`EFFECTIVE_DATE`,`EXPIRE_DATE`,`CODETYPE_DESC`,`CODETYPE_TABLE`,`COL_CODETYPE_ID`,`COL_CODE_ID`,`COL_CODE_DESC`,`COL_CODE_SEQ`,`COL_STATUS`,`COL_EFFECTIVE_DT`,`COL_EXPIRY_DT`,`COL_CODE_LOCALE`,`UPDATED_BY`,`UPDATED_DT`) VALUES ('4028819984c8910f0184c898cd300001','IConnect','wardclasstype',NULL,NULL,'wardclasstype','TBL_CODE_INT','CODETYPE_ID','CODE_ID','CODE_DESC','CODE_SEQ','STATUS','EFFECTIVE_DT','EXPIRY_DT','LOCALE','appadmin','2022-11-30 12:52:48');
INSERT INTO `tbl_codetype` (`ID`,`APP_ID`,`CODETYPE_ID`,`EFFECTIVE_DATE`,`EXPIRE_DATE`,`CODETYPE_DESC`,`CODETYPE_TABLE`,`COL_CODETYPE_ID`,`COL_CODE_ID`,`COL_CODE_DESC`,`COL_CODE_SEQ`,`COL_STATUS`,`COL_EFFECTIVE_DT`,`COL_EXPIRY_DT`,`COL_CODE_LOCALE`,`UPDATED_BY`,`UPDATED_DT`) VALUES ('4028819984cb3b450184cba051450004','IConnect','wardlocation',NULL,NULL,'wardlocation','TBL_CODE_INT','CODETYPE_ID','CODE_ID','CODE_DESC','CODE_SEQ','STATUS','EFFECTIVE_DT','EXPIRY_DT','LOCALE','appadmin','2022-12-01 02:59:52');


-- ----------------------------
-- Records of TBL_CODE_INT
-- ----------------------------
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984c8910f0184c89911f40002','4028819984c8910f0184c898cd300001','A',NULL,NULL,'Class A',1,'A',NULL,NULL,'appadmin','2022-11-30 12:53:06','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984c8910f0184c8992dde0003','4028819984c8910f0184c898cd300001','B',NULL,NULL,'Class B',2,'A',NULL,NULL,'appadmin','2022-11-30 12:53:13','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984cb3b450184cba0a01c0005','4028819984c8910f0184c898cd300001','C',NULL,NULL,'Class C',3,'A',NULL,NULL,'appadmin','2022-12-01 03:00:12','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984cbd3070184cc4b14a30007','4028819984cb3b450184cba051450004','A1','4028819984c8910f0184c898cd300001','4028819984c8910f0184c89911f40002','Block A level 1',1,'A',NULL,NULL,'appadmin','2022-12-01 06:06:23','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984cbd3070184cc4b51700008','4028819984cb3b450184cba051450004','A2','4028819984c8910f0184c898cd300001','4028819984c8910f0184c89911f40002','Block A level 2',2,'A',NULL,NULL,'appadmin','2022-12-01 06:06:39','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984cbd3070184cc63f121000d','4028819984cb3b450184cba051450004','B1',NULL,NULL,'Block B Level 1',3,'A',NULL,NULL,'appadmin','2022-12-01 06:33:33','en',0);
INSERT INTO `tbl_code_int` (`ID`,`CODETYPE_ID`,`CODE_ID`,`PARENT_CODETYPE_ID`,`PARENT_CODE_ID`,`CODE_DESC`,`CODE_SEQ`,`STATUS`,`EFFECTIVE_DT`,`EXPIRY_DT`,`UPDATED_BY`,`UPDATED_DT`,`LOCALE`,`VERSION`) VALUES ('4028819984cbd3070184cc7a6e92000f','4028819984cb3b450184cba051450004','B2','4028819984c8910f0184c898cd300001','4028819984c8910f0184c8992dde0003','Block B Level 2',4,'A',NULL,NULL,'appadmin','2022-12-01 06:58:07','en',0);
