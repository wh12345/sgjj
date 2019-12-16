CREATE TABLE cf_adandonrecord (
  record_id varchar2(50) NOT NULL  PRIMARY KEY,
  equipment_id varchar2(50) default NULL ,
  equipment_name varchar2(200) DEFAULT NULL,
  operator varchar2(50) DEFAULT NULL,
  operat_time date DEFAULT sysdate,
  operat_reason varchar2(255) DEFAULT NULL 
);
comment on column cf_adandonrecord.equipment_id is '废弃设备id';
comment on column cf_adandonrecord.equipment_name is '废弃设备名称';
comment on column cf_adandonrecord.operator is '操作人';
comment on column cf_adandonrecord.operat_time is  '操作时间';
comment on column cf_adandonrecord.operat_reason is  '操作原因';


CREATE TABLE cf_area (
  id number(10) NOT NULL PRIMARY KEY,
  pid number(10)  DEFAULT NULL,
  node varchar2(64)  DEFAULT NULL,
  name varchar2(64) NOT NULL,
  a_level inetger NOT NULL,
  lat number(8,2) NOT NULL,
  lng number(8,2) NOT NULL
)
create index  area_lat_lng_index  on cf_area(lat,lng);
create index  area_pid_index on cf_area(pid);
create index  area_name_index on cf_area(name);
create index  area_level_index on cf_area(a_level);


CREATE TABLE cf_category (
  category_id number(20) NOT NULL PRIMARY KEY,
  type_name varchar2(200) DEFAULT NULL,
  type_id varchar2(50) DEFAULT NULL,
  type_pid varchar2(50) DEFAULT NULL,
  type_pname varchar2(200) DEFAULT NULL,
  type_level varchar2(20) DEFAULT NULL,
  status integer DEFAULT '0' NOT NULL ,
  gmt_create date DEFAULT NULL
);
create sequence seq_cf_category;
create index typeid_index  on cf_category(type_id,status);
create index typelevel_index on cf_category(type_level,status);
create index typepid_index on cf_category(type_pid,status);
COMMENT ON COLUMN cf_category.type_name is '分类名';
COMMENT ON COLUMN cf_category.type_id is '分类编码';
COMMENT ON COLUMN cf_category.type_pid is '父级分类ID';
COMMENT ON COLUMN cf_category.type_pname is '父级分类名称';
COMMENT ON COLUMN cf_category.type_level is '层级';
COMMENT ON COLUMN cf_category.status is '状态 0:正常 1：删除 2：禁用';
COMMENT ON COLUMN cf_category.gmt_create is '创建时间';

INSERT INTO cf_category VALUES ('1', '自动火灾报警系统', 'xf001', '0', null, null, '0', to_date('2018-12-28 14:58:32','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('2', '消防水灭火系统', 'xf002', '0', null, '0', '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('3', '气体灭火系统', 'xf003', '0', null, null, '0', to_date('2018-12-19 00:25:20','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('4', '应急照明灯与疏散指灯', 'xf004', '0', null, null, '0', to_date('2018-12-18 17:38:32','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('5', '灭火器', 'xf005', '0', null, '0', '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('6', '防排烟系统', 'xf006', '0', null, null, '0', to_date('2018-12-18 20:44:43','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('7', '烟雾感应器', 'xf0010001', 'xf001', '自动火灾报警系统', '1', '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('8', '温度感应器', 'xf0010002', 'xf001', '自动火灾报警系统', '1', '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('9', '喷淋', 'xf0020001', 'xf002', '消防水灭火系统', '1', '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('17', '七氟丙烷灭火器', 'xf005011448', 'xf005', '灭火器', null, '0', to_date('2018-12-18 01:14:49','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('19', '二氧化碳灭火系统', 'xf0031005', 'xf003', '气体灭火系统', null, '0', null);
INSERT INTO cf_category VALUES ('20', '手提式灭火器', 'xf005171230', 'xf005', '灭火器', null, '0', to_date('2018-12-18 17:12:30','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('21', 'ZY8812照明指示灯', 'xf0041256', 'xf004', '应急照明灯与疏散指灯', null, '0', to_date('2018-12-18 17:38:55','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_category VALUES ('22', '干冰灭火器', 'xf005171230205243', 'xf005171230', '手提式灭火器', null, '0', to_date('2018-12-18 20:52:44','YYYY-MM-DD HH24:MI:SS'));



CREATE TABLE cf_equipment (
  equipment_id varchar2(255) NOT NULL PRIMARY KEY ,
  equipment_name varchar2(200) DEFAULT NULL ,
  belong_typeid varchar2(50) DEFAULT NULL,
  belong_typename varchar2(200) DEFAULT NULL,
  equipment_price number(20) DEFAULT '0' ,
  equipment_num number(20)  DEFAULT '1' NOT NULL ,
  localtion_id varchar2(50) DEFAULT NULL ,
  location_name varchar2(200) DEFAULT NULL ,
  equipment_status integer DEFAULT '0' NOT NULL  ,
  location_status integer DEFAULT '0' NOT NULL ,
  unit varchar2(100) DEFAULT NULL  ,
  floor varchar2(50) DEFAULT NULL,
  corridor varchar2(20) DEFAULT NULL,
  room_number varchar2(20) DEFAULT NULL ,
  administrator varchar2(50) DEFAULT NULL,
  phone varchar2(20) DEFAULT NULL,
  gmt_create date DEFAULT NULL ,
  community varchar2(100) DEFAULT NULL
);
COMMENT ON COLUMN cf_equipment.equipment_name  IS  '设备名称';
COMMENT ON COLUMN cf_equipment.belong_typeid  IS  '所属分类id';
COMMENT ON COLUMN cf_equipment.belong_typename  IS '所属分类名称';
COMMENT ON COLUMN cf_equipment.equipment_price  IS '设备价格';
COMMENT ON COLUMN cf_equipment.equipment_num  IS '设备数量';
COMMENT ON COLUMN cf_equipment.localtion_id  IS  '设备所在位置id';
COMMENT ON COLUMN cf_equipment.location_name  IS  '设备所在位置名称';
COMMENT ON COLUMN cf_equipment.equipment_status  IS '设备状态0:正常 1：报修 2：检修 3：报废';
COMMENT ON COLUMN cf_equipment.location_status IS  '设备状态: 0:在库 1：检修 2：在用';
COMMENT ON COLUMN cf_equipment.unit  IS  '单元';
COMMENT ON COLUMN cf_equipment.floor  IS  '楼层'; 
COMMENT ON COLUMN cf_equipment.corridor  IS  '楼道';
COMMENT ON COLUMN cf_equipment.room_number  IS  '房间号';
COMMENT ON COLUMN cf_equipment.administrator  IS  '管理员';
COMMENT ON COLUMN cf_equipment.phone  IS  '电话'; 
COMMENT ON COLUMN cf_equipment.gmt_create  IS '创建时间';
COMMENT ON COLUMN cf_equipment.community  IS '小区';

INSERT INTO cf_equipment VALUES ('EQ-XF0010001-02851011630103163156-F9A94-BF9F5983', 'ST-烟雾报警器', 'xf0010001', '烟雾感应器', null, '8', '02851011630103163156', '四川省成都市双流区江安花园小区江安花园', '0', '0', '二单元', '18楼', '2', '1806', '胡琪', '18482252171', to_date('2019-01-13 01:08:18','YYYY-MM-DD HH24:MI:SS'), '江安花园');
INSERT INTO cf_equipment VALUES ('EQ-XF0010002-02851011630103163156-12B90A-7D971A14', '温控感应开关', 'xf0010002', '温度感应器', null, '3', '02851011630103163156', '四川省成都市双流区江安花园小区江安花园', '0', '0', '二单元', '15楼', '3', '1506', '胡琪', '18482252171', to_date('2019-01-10 22:47:28','YYYY-MM-DD HH24:MI:SS'), '江安花园');
INSERT INTO cf_equipment VALUES ('EQ-XF0020001-02851010430104014642-178339-D3E34CDD', '自动喷淋', 'xf0020001', '喷淋', null, '15', '02851010430104014642', '四川省成都市锦江区春熙路街道春禧公寓春熙路', '0', '0', '二单元', '6', '5', '608', '胡琪', '18482252171',to_date('2019-01-10 22:52:22','YYYY-MM-DD HH24:MI:SS'), '春天里');
INSERT INTO cf_equipment VALUES ('EQ-XF0031005-02851010530104163229-151F86-633CC814', 'CO2灭火器', 'xf0031005', '二氧化碳灭火系统', null, '5', '02851010530104163229', '四川省成都市青羊区少城街道长富新城', '0', '0', '2单元', '16', '3', '1608', '胡琪', '18482252171', to_date('2019-01-07 22:22:47','YYYY-MM-DD HH24:MI:SS'), '长富新城');
INSERT INTO cf_equipment VALUES ('EQ-XF005171230205243-02851010530104222129-18BEA9-02189DEC', '灭火器', 'xf005171230205243', '干冰灭火器', null, '10', '02851010530104222129', '四川省成都市青羊区汪家拐街道长富花园', '0', '0', '1', '18', '6', '18605', '胡琪', '18482252171',to_date('2019-01-06 19:40:23','YYYY-MM-DD HH24:MI:SS'), '春熙路公寓');
INSERT INTO cf_equipment VALUES ('EQ-XF005171230205243-02851011630103163156-12CD27-8208BC46', 'ST灭火器', 'xf005171230205243', '干冰灭火器', null, '2', '02851011630103163156', '四川省成都市双流区江安花园小区江安花园', '0', '0', '二单元', '15', '6', '1609', '胡琪', '18482252171', to_date('2019-01-11 00:25:45','YYYY-MM-DD HH24:MI:SS'), '江安花园');


CREATE TABLE cf_location (
  location_id varchar2(255) NOT NULL PRIMARY KEY,
  location_name varchar2(255) DEFAULT NULL ,
  province varchar2(50) DEFAULT NULL  ,
  city varchar2(50) DEFAULT NULL  ,
  citycode varchar2(50) DEFAULT NULL  ,
  district varchar2(50) DEFAULT NULL  ,
  adcode varchar2(50) DEFAULT NULL  ,
  street varchar2(255) DEFAULT NULL  ,
  street_number varchar2(50) DEFAULT NULL  ,
  lat NUMBER(38,10) DEFAULT '0.0000000000'  ,
  lng NUMBER(38,10) DEFAULT '0.0000000000' ,
  status integer  DEFAULT '0'  NOT NULL,
  remark varchar2(255) DEFAULT NULL  ,
  gmt_create date DEFAULT NULL    
);
COMMENT ON COLUMN CF_Location.location_name is '位置全称名';
COMMENT ON COLUMN CF_Location.province is '所属省';
COMMENT ON COLUMN CF_Location.city is '所属城市';
COMMENT ON COLUMN CF_Location.citycode is '城市编码';
COMMENT ON COLUMN CF_Location.district is '区';
COMMENT ON COLUMN CF_Location.adcode is '区级编码';
COMMENT ON COLUMN CF_Location.street is  '街道';
COMMENT ON COLUMN CF_Location.street_number is '街编号';
COMMENT ON COLUMN CF_Location.lat is '经度';
COMMENT ON COLUMN CF_Location.lng is '纬度';
COMMENT ON COLUMN CF_Location.status is '设备状态: 0:正常 1：删除 2：禁用';
COMMENT ON COLUMN CF_Location.remark is '备注';
COMMENT ON COLUMN CF_Location.gmt_create is '创建时间';

INSERT INTO cf_location VALUES ('02851010430104014642', '四川省成都市锦江区春熙路街道春禧公寓春熙路', '四川省', '成都市', '028', '锦江区', '510104', '红星路三段', '14-附1号', '30.6555110000', '104.0771930000', '1', '春熙路备注测试成功否', to_date('2018-12-24 01:46:42','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851010530104154710', '四川省成都市青羊区汪家拐街道人民公园', '四川省', '成都市', '028', '青羊区', '510105', '小南街', '20号', '30.6552330000', '104.0562440000', '0', null, to_date('2019-01-07 15:47:11','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851010530104163229', '四川省成都市青羊区少城街道长富新城', '四川省', '成都市', '028', '青羊区', '510105', '通惠门路', '5号', '30.6552330000', '104.0562440000', '0', null, to_date('2019-01-07 16:32:29','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851010530104222129', '四川省成都市青羊区汪家拐街道长富花园', '四川省', '成都市', '028', '青羊区', '510105', '中南大街', '43号', '30.6552330000', '104.0562440000', '0', null, to_date('2018-12-25 22:21:29','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851010630104163205', '四川省成都市金牛区沙河源街道水韵天府', '四川省', '成都市', '028', '金牛区', '510106', '王贾路', '19号3幢', '30.7293710000', '104.0544000000', '0', null, to_date('2019-01-07 16:32:05','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851011410430163242', '四川省成都市新都区龙桥镇瑞云社区瑞云文化广场', '四川省', '成都市', '028', '新都区', '510114', '阳光林森路', '164号', '104.0199050000', '30.8412265000', '0', null, to_date('2019-01-07 16:32:42','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851011430104163215', '四川省成都市新都区斑竹园镇忠义东路21-23号水韵兰亭', '四川省', '成都市', '028', '新都区', '510114', '忠义东路', '21-23号', '30.8184440000', '104.0697370000', '0', null, to_date('2019-01-07 16:32:15','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851011610330160259', '四川省成都市双流区锦华路四段32号成都信息工程大学(航空港校区)', '四川省', '成都市', '028', '双流区', '510116', '锦华路四段', '32号', '103.9853600000', '30.5803635000', '0', null, to_date('2019-01-07 16:02:59','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851011630103163156', '四川省成都市双流区江安花园小区江安花园', '四川省', '成都市', '028', '双流区', '510116', '川大路三段', '305号', '30.5478250000', '103.9905040000', '0', null, to_date('2019-01-07 16:31:57','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851011630104160621', '四川省成都市双流区空港·晶座', '四川省', '成都市', '028', '双流区', '510116', '临港路四段', '66号', '30.5574770000', '104.0112300000', '0', null, to_date('2019-01-07 16:06:22','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO cf_location VALUES ('02851012130104154800', '四川省成都市金堂县赵镇街道水城茗馨苑(A区)水城茗馨苑A区', '四川省', '成都市', '028', '金堂县', '510121', '北滨路', '4号', '30.8547320000', '104.4284460000', '0', null, to_date('2019-01-07 15:48:00','YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE cf_region (
  region_id varchar2(50) NOT NULL PRIMARY KEY,
  belong_id varchar2(50) DEFAULT NULL,
  belong_name varchar2(200) DEFAULT NULL,
  detaile_address varchar2(100) DEFAULT NULL,
  completes_address varchar2(200) DEFAULT NULL,
  status integer DEFAULT '0' NOT NULL ,
  gmt_create date DEFAULT NULL
);
COMMENT ON COLUMN cf_region.belong_id IS '所属地区id';
COMMENT ON COLUMN cf_region.belong_name IS '所属地区名称';
COMMENT ON COLUMN cf_region.detaile_address IS 'detaile_address  详细地址精确到楼（街道）';
COMMENT ON COLUMN cf_region.completes_address IS '地址全称';
COMMENT ON COLUMN cf_region.status IS '状态 0:正常 1：删除 2：禁用';
COMMENT ON COLUMN cf_region.gmt_create IS '创建时间';


CREATE TABLE cf_transferrecord (
  record_id varchar2(50) NOT NULL  PRIMARY KEY,
  equipment_id varchar2(50) DEFAULT NULL  ,
  equipment_name varchar2(200) DEFAULT NULL  ,
  transfer_name integer DEFAULT '0'  ,
  original_address varchar2(255) DEFAULT NULL, 
  dest_address varchar2(255) DEFAULT NULL  ,
  operator varchar2(50) DEFAULT NULL ,
  operat_time date DEFAULT NULL  ,
  operat_reason varchar2(255) DEFAULT NULL 
) ;
COMMENT ON COLUMN cf_transferrecord.equipment_id IS '流转设备id';
COMMENT ON COLUMN cf_transferrecord.equipment_name IS  '流转设备名称';
COMMENT ON COLUMN cf_transferrecord.transfer_name IS '流转类型名称 0：安装 1：更换';
COMMENT ON COLUMN cf_transferrecord.original_address IS '原地址';
COMMENT ON COLUMN cf_transferrecord.dest_address IS '目的地址';
COMMENT ON COLUMN cf_transferrecord.operator IS '操作人';
COMMENT ON COLUMN cf_transferrecord.operat_time IS '操作时间';
COMMENT ON COLUMN cf_transferrecord.operat_reason IS '操作原因';



CREATE TABLE cf_user (
  user_id NUMBER(20) NOT NULL PRIMARY KEY,
  username VARCHAR2(50) DEFAULT NULL ,
  password VARCHAR2(50) DEFAULT NULL ,
  email VARCHAR2(100) DEFAULT NULL ,
  mobile VARCHAR2(100) DEFAULT NULL ,
  status INTEGER DEFAULT '0' NOT NULL ,
  gmt_create DATE DEFAULT NULL
)
COMMENT ON  COLUMN cf_user.username IS '登陆帐户';
COMMENT ON  COLUMN cf_user.password IS '登陆密码';
COMMENT ON  COLUMN cf_user.email IS '邮箱';
COMMENT ON  COLUMN cf_user.mobile IS '手机号';
COMMENT ON  COLUMN cf_user.status IS '状态 0:正常 1：删除 2：禁用';
COMMENT ON  COLUMN cf_user.gmt_create IS '创建时间';


CREATE TABLE cf_warningrecord (
  record_id varchar2(50) NOT NULL PRIMARY KEY ,
  equipment_id varchar2(50) DEFAULT NULL ,
  equipment_name varchar2(200) DEFAULT NULL ,
  warning_operator varchar2(50) DEFAULT NULL,
  warning_time date DEFAULT NULL ,
  warning_reason varchar2(255) DEFAULT NULL ,
  deal_operator varchar2(50) DEFAULT NULL ,
  deal_time date DEFAULT NULL ,
  deal_plan varchar2(255) DEFAULT NULL ,
  status integer DEFAULT '0'  NOT NULL
);
COMMENT ON COLUMN cf_warningrecord.equipment_id IS '报警设备id';
COMMENT ON COLUMN cf_warningrecord.equipment_name IS '报警设备名称';
COMMENT ON COLUMN cf_warningrecord.warning_operator IS '报警人';
COMMENT ON COLUMN cf_warningrecord.warning_time IS '报警时间';
COMMENT ON COLUMN cf_warningrecord.warning_reason IS '报警原因';
COMMENT ON COLUMN cf_warningrecord.deal_operator IS '处理人';
COMMENT ON COLUMN cf_warningrecord.deal_time IS '处理时间';
COMMENT ON COLUMN cf_warningrecord.deal_plan IS '处理方案';
COMMENT ON COLUMN cf_warningrecord.status IS '当前状态 0：解决 1：未解决';

CREATE INDEX equipment_id_index ON cf_warningrecord(equipment_id);


CREATE TABLE qrtz_job_details (
  SCHED_NAME varchar2(120) NOT NULL,
  JOB_NAME varchar2(200) NOT NULL,
  JOB_GROUP varchar2(200) NOT NULL,
  DESCRIPTION varchar2(250) DEFAULT NULL,
  JOB_CLASS_NAME varchar2(250) NOT NULL,
  IS_DURABLE varchar2(1) NOT NULL,
  IS_NONCONCURRENT varchar2(1) NOT NULL,
  IS_UPDATE_DATA varchar2(1) NOT NULL,
  REQUESTS_RECOVERY varchar2(1) NOT NULL,
  JOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
)
CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON qrtz_job_details(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON qrtz_job_details(SCHED_NAME,JOB_GROUP);
INSERT INTO qrtz_job_details VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'com.hq.modules.job.utils.ScheduleJob', '0', '0', '0', '0',null);
INSERT INTO qrtz_job_details VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', null, 'com.hq.modules.job.utils.ScheduleJob', '0', '0', '0', '0', null);


CREATE TABLE qrtz_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  JOB_NAME varchar2(200) NOT NULL,
  JOB_GROUP varchar2(200) NOT NULL,
  DESCRIPTION varchar2(250) DEFAULT NULL,
  NEXT_FIRE_TIME number(13) DEFAULT NULL,
  PREV_FIRE_TIME number(13) DEFAULT NULL,
  PRIORITY int DEFAULT NULL,
  TRIGGER_STATE varchar2(16) NOT NULL,
  TRIGGER_TYPE varchar2(8) NOT NULL,
  START_TIME number(13) NOT NULL,
  END_TIME number(13) DEFAULT NULL,
  CALENDAR_NAME varchar2(200) DEFAULT NULL,
  MISFIRE_INSTR number(5) DEFAULT NULL,
  JOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP) REFERENCES qrtz_job_details (SCHED_NAME, JOB_NAME, JOB_GROUP)
) 


CREATE INDEX IDX_QRTZ_T_J on qrtz_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG on qrtz_triggers(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C on qrtz_triggers(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE on qrtz_triggers(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE on qrtz_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE on qrtz_triggers(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME on qrtz_triggers(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST on qrtz_triggers(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP on qrtz_triggers(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);


INSERT INTO qrtz_triggers VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1544608800000', '1544607000000', '5', 'WAITING', 'CRON', '1544499149000', '0', null, '2',null);
INSERT INTO qrtz_triggers VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1544500800000', '-1', '5', 'PAUSED', 'CRON', '1544499149000', '0', null, '2',null);


CREATE TABLE qrtz_blob_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  BLOB_DATA blob,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_blob_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers(SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)
create index SCHED_NAME on qrtz_blob_triggers(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);


CREATE TABLE qrtz_calendars (
  SCHED_NAME varchar2(120) NOT NULL,
  CALENDAR_NAME varchar2(200) NOT NULL,
  CALENDAR blob NOT NULL,
  PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
)


CREATE TABLE qrtz_cron_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  CRON_EXPRESSION varchar2(120) NOT NULL,
  TIME_ZONE_ID varchar2(80) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_cron_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)

INSERT INTO qrtz_cron_triggers VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO qrtz_cron_triggers VALUES ('RenrenScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

CREATE TABLE qrtz_fired_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  ENTRY_ID varchar2(95) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  INSTANCE_NAME varchar2(200) NOT NULL,
  FIRED_TIME number(13) NOT NULL,
  SCHED_TIME number(13) NOT NULL,
  PRIORITY number(11) NOT NULL,
  STATE varchar2(16) NOT NULL,
  JOB_NAME varchar2(200) DEFAULT NULL,
  JOB_GROUP varchar2(200) DEFAULT NULL,
  IS_NONCONCURRENT varchar2(1) DEFAULT NULL,
  REQUESTS_RECOVERY varchar2(1) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,ENTRY_ID)
)
 create index IDX_QRTZ_FT_TRIG_INST_NAME on qrtz_fired_triggers(SCHED_NAME,INSTANCE_NAME);
  create index IDX_QRTZ_FT_INST_JOB_REQ_RCVRY on qrtz_fired_triggers (SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
  create index IDX_QRTZ_FT_J_G on qrtz_fired_triggers(SCHED_NAME,JOB_NAME,JOB_GROUP);
  create index IDX_QRTZ_FT_JG on qrtz_fired_triggers(SCHED_NAME,JOB_GROUP);
  create index IDX_QRTZ_FT_T_G on qrtz_fired_triggers (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
  create index IDX_QRTZ_FT_TG on qrtz_fired_triggers (SCHED_NAME,TRIGGER_GROUP);
  
  
  CREATE TABLE qrtz_locks (
  SCHED_NAME varchar2(120) NOT NULL,
  LOCK_NAME varchar2(40) NOT NULL,
  PRIMARY KEY (SCHED_NAME,LOCK_NAME)
)

INSERT INTO qrtz_locks VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO qrtz_locks VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');


CREATE TABLE qrtz_paused_trigger_grps (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
)


CREATE TABLE qrtz_scheduler_state (
  SCHED_NAME varchar2(120) NOT NULL,
  INSTANCE_NAME varchar2(200) NOT NULL,
  LAST_CHECKIN_TIME number(13) NOT NULL,
  CHECKIN_INTERVAL number(13) NOT NULL,
  PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
)

INSERT INTO qrtz_scheduler_state VALUES ('RenrenScheduler', 'DESKTOP-VHIE3R81544586008165', '1544608585894', '15000');



CREATE TABLE qrtz_simple_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  REPEAT_COUNT number(7) NOT NULL,
  REPEAT_INTERVAL number(12) NOT NULL,
  TIMES_TRIGGERED number(10) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_simple_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)

CREATE TABLE qrtz_simprop_triggers (
  SCHED_NAME varchar2(120) NOT NULL,
  TRIGGER_NAME varchar2(200) NOT NULL,
  TRIGGER_GROUP varchar2(200) NOT NULL,
  STR_PROP_1 varchar2(512) DEFAULT NULL,
  STR_PROP_2 varchar2(512) DEFAULT NULL,
  STR_PROP_3 varchar2(512) DEFAULT NULL,
  INT_PROP_1 number(11) DEFAULT NULL,
  INT_PROP_2 number(11) DEFAULT NULL,
  LONG_PROP_1 number(20) DEFAULT NULL,
  LONG_PROP_2 number(20) DEFAULT NULL,
  DEC_PROP_1 decimal(13,4) DEFAULT NULL,
  DEC_PROP_2 decimal(13,4) DEFAULT NULL,
  BOOL_PROP_1 varchar2(1) DEFAULT NULL,
  BOOL_PROP_2 varchar2(1) DEFAULT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  CONSTRAINT qrtz_simprop_triggers_ibfk_1 FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP) REFERENCES qrtz_triggers (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
)


CREATE TABLE schedule_job (
  job_id number(20) NOT NULL,
  bean_name varchar(200) DEFAULT NULL  ,
  method_name varchar(100) DEFAULT NULL  ,
  params varchar(2000) DEFAULT NULL  ,
  cron_expression varchar(100) DEFAULT NULL  ,
  status integer DEFAULT NULL  ,
  remark varchar(255) DEFAULT NULL  ,
  create_time date DEFAULT NULL  ,
  PRIMARY KEY (job_id)
);
create sequence seq_schedule_job;
comment on table schedule_job is '定时任务';
COMMENT on column schedule_job.job_id is '任务id';
COMMENT on column schedule_job.bean_name is  'spring bean名称';
COMMENT on column schedule_job.method_name is  '方法名';
COMMENT on column schedule_job.params is '参数';
COMMENT on column schedule_job.cron_expression is  'cron表达式';
COMMENT on column schedule_job.status is '任务状态  0：正常  1：暂停';
COMMENT on column schedule_job.remark is '备注';
COMMENT on column schedule_job.create_time is '创建时间';

INSERT INTO schedule_job VALUES ('1', 'testTask', 'test', 'cityfire', '0 0/30 * * * ?', '0', '有参数测试', to_date('2016-12-01 23:16:46','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO schedule_job VALUES ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', to_date('2016-12-03 14:55:56','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO schedule_job VALUES ('4', 'hu', 'test', 'hu', '0 0/30 * * * ?', '0', null, to_date('2019-01-10 17:01:58','YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE schedule_job_log (
  log_id NUMBER(20) NOT NULL ,
  job_id NUMBER(20) NOT NULL,
  bean_name varchar(200) DEFAULT NULL  ,
  method_name varchar(100) DEFAULT NULL  ,
  params varchar(2000) DEFAULT NULL  ,
  status INTEGER NOT NULL  ,
  error varchar(2000) DEFAULT NULL  ,
  times NUMBER(11) NOT NULL  ,
  create_time date DEFAULT NULL  ,
  PRIMARY KEY (log_id)
);
create sequence seq_schedule_job_log;
CREATE INDEX  job_id ON schedule_job_log(job_id);
comment on table schedule_job_log is '定时任务日志';
COMMENT ON COLUMN schedule_job_log.log_id IS '任务日志id';
COMMENT ON COLUMN schedule_job_log.job_id IS  '任务id';
COMMENT ON COLUMN schedule_job_log.bean_name IS  'spring bean名称';
COMMENT ON COLUMN schedule_job_log.method_name IS '方法名';
COMMENT ON COLUMN schedule_job_log.params IS '参数';
COMMENT ON COLUMN schedule_job_log.status IS '任务状态    0：成功    1：失败';
COMMENT ON COLUMN schedule_job_log.error IS '失败信息';
COMMENT ON COLUMN schedule_job_log.times IS '耗时(单位：毫秒)';
COMMENT ON COLUMN schedule_job_log.create_time IS '创建时间';


CREATE TABLE sys_captcha (
  uuid varchar(36) NOT NULL,
  code varchar2(6) NOT NULL,
  expire_time date DEFAULT NULL,
  PRIMARY KEY (uuid)
);
comment on table sys_captcha is '系统验证码';
comment on  column sys_captcha.uuid is 'uuid';
comment on column sys_captcha.code is '验证码';
comment on column sys_captcha.expire_time is '过期时间';


CREATE TABLE sys_config (
  id number(20) NOT NULL,
  param_key varchar2(50) DEFAULT NULL  UNIQUE,
  param_value varchar2(2000) DEFAULT NULL ,
  status integer DEFAULT '1' ,
  remark varchar2(500) DEFAULT NULL,
  PRIMARY KEY (id)
);
create sequence SEQ_SYS_CONFIG;
comment on table sys_config is '系统配置信息表';
comment on column sys_config.param_key is 'key';
comment on column sys_config.param_value  is 'value';
comment on column sys_config.status is '状态   0：隐藏   1：显示';
comment on column sys_config.remark is  '备注';

INSERT INTO sys_config VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');


CREATE TABLE sys_log (
  id number(20) NOT NULL,
  username varchar2(50) DEFAULT NULL  ,
  operation varchar2(50) DEFAULT NULL  ,
  method varchar2(200) DEFAULT NULL  ,
  params varchar2(4000) DEFAULT NULL  ,
  time number(20) NOT NULL  ,
  ip varchar2(64) DEFAULT NULL  ,
  create_date date DEFAULT NULL  ,
  PRIMARY KEY (id)
);
create SEQUENCE SEQ_SYS_LOG;
comment on table sys_log is '系统日志';
comment on column sys_log.username is '用户名';
comment on column sys_log.operation is '用户操作';
comment on column sys_log.method is '请求方法';
comment on column sys_log.params is '请求参数';
comment on column sys_log.time is'执行时长(毫秒)';
comment on column sys_log.ip is 'IP地址';
comment on column sys_log.create_date is '创建时间';

CREATE TABLE sys_menu (
  menu_id number(20) NOT NULL,
  parent_id number(20) DEFAULT NULL  ,
  name varchar2(50) DEFAULT NULL  ,
  url varchar2(200) DEFAULT NULL  ,
  perms varchar2(500) DEFAULT NULL  ,
  type number(11) DEFAULT NULL ,
  icon varchar2(50) DEFAULT NULL  ,
  order_num number(11) DEFAULT NULL  ,
  PRIMARY KEY (menu_id)
);
create sequence SEQ_SYS_MENU;
comment on table sys_menu is '菜单管理';
comment on column sys_menu.parent_id is '父菜单ID，一级菜单为0' ;
comment on column sys_menu.name is '菜单名称' ;
comment on column sys_menu.url is '菜单URL' ;
comment on column sys_menu.perms is '授权(多个用逗号分隔，如：user:list,user:create)' ;
comment on column sys_menu.type is '类型   0：目录   1：菜单   2：按钮' ;
comment on column sys_menu.icon is  '菜单图标';
comment on column sys_menu.order_num is  '排序';

INSERT INTO sys_menu VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO sys_menu VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO sys_menu VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO sys_menu VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO sys_menu VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/city-fire/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO sys_menu VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO sys_menu VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO sys_menu VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO sys_menu VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO sys_menu VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO sys_menu VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO sys_menu VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO sys_menu VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO sys_menu VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO sys_menu VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO sys_menu VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO sys_menu VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO sys_menu VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO sys_menu VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO sys_menu VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO sys_menu VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO sys_menu VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO sys_menu VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO sys_menu VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO sys_menu VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO sys_menu VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO sys_menu VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO sys_menu VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO sys_menu VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO sys_menu VALUES ('31', '0', '地区管理', null, null, '0', 'daohang', '1');
INSERT INTO sys_menu VALUES ('32', '0', '分类管理', 'category/category', 'operate:category:list', '1', 'zonghe', '4');
INSERT INTO sys_menu VALUES ('33', '32', '删除', null, 'operate:category:delete', '2', null, '0');
INSERT INTO sys_menu VALUES ('34', '32', '修改', null, 'operate:category:update,operate:category:info', '2', null, '0');
INSERT INTO sys_menu VALUES ('35', '32', '新增', null, 'operate:category:save', '2', null, '0');
INSERT INTO sys_menu VALUES ('36', '31', '新增地区', 'location/map', 'operate:location:save', '1', 'dangdifill', '0');
INSERT INTO sys_menu VALUES ('37', '36', '新增', null, 'operate:map:save', '2', null, '0');
INSERT INTO sys_menu VALUES ('38', '31', '所有地区', 'location/location', 'operate:location:list,operate:location:info,operate:location:updateRemark,operate:location:updateStatus,operate:location:delete', '1', 'mudedi', '0');
INSERT INTO sys_menu VALUES ('40', '0', '设备管理', null, null, '0', 'sql', '5');
INSERT INTO sys_menu VALUES ('41', '40', '新增设备', 'equipment/add', 'operate:category:list,operate:equipment:save', '1', 'menu', '0');
INSERT INTO sys_menu VALUES ('42', '40', '设备列表', 'equipment/list', 'operate:equipment:list,operate:equipment:info,operate:equipment:update,operate:equipment:delete', '1', 'xiangqu', '0');
INSERT INTO sys_menu VALUES ('43', '0', '统计管理', null, null, '0', 'tubiao', '5');
INSERT INTO sys_menu VALUES ('44', '43', '位置统计', '/statistics/location', 'operate:location:list', '1', 'daohang', '0');
INSERT INTO sys_menu VALUES ('45', '43', '设备统计', '/statistics/equipment', 'operate:equipment:list', '1', 'zhedie', '1');

CREATE TABLE sys_oss (
  id number(20) NOT NULL,
  url varchar2(200) DEFAULT NULL ,
  create_date date DEFAULT NULL,
  PRIMARY KEY (id)
);
comment on table sys_oss is '文件上传';
comment on column sys_oss.url is 'URL地址';
comment on column sys_oss.create_date is '创建时间';

CREATE TABLE sys_role (
  role_id number(20) NOT NULL,
  role_name varchar2(100) DEFAULT NULL,
  remark varchar2(100) DEFAULT NULL,
  create_user_id number(20) DEFAULT NULL ,
  create_time date DEFAULT NULL,
  PRIMARY KEY (role_id)
);
create sequence SEQ_SYS_ROLE;
comment on table  sys_role is  '角色';
comment on column  sys_role.role_name   is '角色名称';
comment on column  sys_role.remark   is '备注';
comment on column  sys_role.create_user_id   is '创建者ID';
comment on column  sys_role.create_time   is '创建时间';
INSERT INTO sys_role VALUES ('1', '用户组', '不享受系统管理菜单的修改和部分查看', '1', to_date('2019-01-11 13:44:04','YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE sys_role_menu (
  id number(20) NOT NULL,
  role_id number(20) DEFAULT NULL,
  menu_id number(20) DEFAULT NULL,
  PRIMARY KEY (id)
);
create sequence SEQ_sys_role_menu;
comment on table  sys_role_menu is '角色与菜单对应关系';
comment on column sys_role_menu.role_id is '角色ID';
comment on column sys_role_menu.menu_id is '菜单ID';

INSERT INTO sys_role_menu VALUES ('32', '1', '2');
INSERT INTO sys_role_menu VALUES ('33', '1', '15');
INSERT INTO sys_role_menu VALUES ('34', '1', '16');
INSERT INTO sys_role_menu VALUES ('35', '1', '17');
INSERT INTO sys_role_menu VALUES ('36', '1', '18');
INSERT INTO sys_role_menu VALUES ('37', '1', '5');
INSERT INTO sys_role_menu VALUES ('38', '1', '6');
INSERT INTO sys_role_menu VALUES ('39', '1', '7');
INSERT INTO sys_role_menu VALUES ('40', '1', '8');
INSERT INTO sys_role_menu VALUES ('41', '1', '9');
INSERT INTO sys_role_menu VALUES ('42', '1', '10');
INSERT INTO sys_role_menu VALUES ('43', '1', '11');
INSERT INTO sys_role_menu VALUES ('44', '1', '12');
INSERT INTO sys_role_menu VALUES ('45', '1', '13');
INSERT INTO sys_role_menu VALUES ('46', '1', '14');
INSERT INTO sys_role_menu VALUES ('47', '1', '27');
INSERT INTO sys_role_menu VALUES ('48', '1', '29');
INSERT INTO sys_role_menu VALUES ('49', '1', '30');
INSERT INTO sys_role_menu VALUES ('50', '1', '31');
INSERT INTO sys_role_menu VALUES ('51', '1', '36');
INSERT INTO sys_role_menu VALUES ('52', '1', '37');
INSERT INTO sys_role_menu VALUES ('53', '1', '38');
INSERT INTO sys_role_menu VALUES ('54', '1', '32');
INSERT INTO sys_role_menu VALUES ('55', '1', '33');
INSERT INTO sys_role_menu VALUES ('56', '1', '34');
INSERT INTO sys_role_menu VALUES ('57', '1', '35');
INSERT INTO sys_role_menu VALUES ('58', '1', '40');
INSERT INTO sys_role_menu VALUES ('59', '1', '41');
INSERT INTO sys_role_menu VALUES ('60', '1', '42');
INSERT INTO sys_role_menu VALUES ('61', '1', '43');
INSERT INTO sys_role_menu VALUES ('62', '1', '44');
INSERT INTO sys_role_menu VALUES ('63', '1', '45');
INSERT INTO sys_role_menu VALUES ('64', '1', '-666666');
INSERT INTO sys_role_menu VALUES ('65', '1', '1');


CREATE TABLE sys_user (
  user_id number(20) NOT NULL,
  username varchar2(50) NOT NULL  UNIQUE,
  password varchar2(100) DEFAULT NULL  ,
  salt varchar2(20) DEFAULT NULL  ,
  email varchar2(100) DEFAULT NULL  ,
  mobile varchar2(100) DEFAULT NULL  ,
  status integer DEFAULT NULL  ,
  create_user_id number(20) DEFAULT NULL  ,
  create_time date DEFAULT NULL  ,
  PRIMARY KEY (user_id)
);
create SEQUENCE SEQ_SYS_USER;
comment on table sys_user is '系统用户';
comment on column  sys_user.username is '用户名';
comment on column  sys_user.password is '密码';
comment on column  sys_user.salt is '盐';
comment on column  sys_user.email is '邮箱';
comment on column  sys_user.mobile is '手机号';
comment on column  sys_user.status is '状态  0：禁用   1：正常';
comment on column  sys_user.create_user_id is '创建者ID';
comment on column  sys_user.create_time is '创建时间';

INSERT INTO sys_user VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', '332252453@qq.com', '18482252171', '1', '1', to_date('2018-12-10 11:11:11','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO sys_user VALUES ('2', 'huqi', '47478b6d58472988d4ec9bdca1eb19c47792641ab81aa702b50c20580835f3af', 'C6QvarMywRd4EzBV233t', '332252453@qq.com', '18482252171', '1', '1', to_date('2019-01-11 13:46:20','YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE sys_user_role (
  id NUMBER(20) NOT NULL,
  user_id NUMBER(20) DEFAULT NULL,
  role_id NUMBER(20) DEFAULT NULL,
  PRIMARY KEY (id)
);
create SEQUENCE SEQ_sys_user_role;
COMMENT ON TABLE sys_user_role IS '用户与角色对应关系';
COMMENT ON COLUMN sys_user_role.user_id IS  '用户ID';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';

INSERT INTO sys_user_role VALUES ('1', '2', '1');


CREATE TABLE sys_user_token (
  user_id NUMBER(20) NOT NULL,
  token varchar2(100) NOT NULL UNIQUE,
  expire_time date DEFAULT NULL,
  update_time date DEFAULT NULL,
  PRIMARY KEY (user_id)
);
COMMENT ON TABLE  sys_user_token IS '系统用户Token';
COMMENT ON COLUMN  sys_user_token.token IS 'token';
COMMENT ON COLUMN  sys_user_token.expire_time IS '过期时间';
COMMENT ON COLUMN  sys_user_token.update_time IS '更新时间';

INSERT INTO sys_user_token VALUES ('1', '463e1a56bbc12af142b5b72701c9d864', TO_DATE('2019-01-16 10:07:14','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-01-15 22:07:14','YYYY-MM-DD HH24:MI:SS'));
INSERT INTO sys_user_token VALUES ('2', '5c47027f5668cff7af270778729f31a9', TO_DATE('2019-01-12 02:02:22','YYYY-MM-DD HH24:MI:SS'), TO_DATE('2019-01-11 14:02:22','YYYY-MM-DD HH24:MI:SS'));


CREATE TABLE tb_user (
  user_id NUMBER(20) NOT NULL,
  username varchar2(50) NOT NULL  UNIQUE,
  mobile varchar2(20) NOT NULL,
  password varchar2(64) DEFAULT NULL,
  create_time date DEFAULT NULL,
  PRIMARY KEY (user_id)
);
COMMENT ON table tb_user IS  '用户';
COMMENT ON COLUMN tb_user.username  IS   '用户名' ;
COMMENT ON COLUMN tb_user.mobile  IS   '手机号' ;
COMMENT ON COLUMN tb_user.password  IS    '密码';
COMMENT ON COLUMN tb_user.create_time IS   '创建时间' ;

INSERT INTO tb_user VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', TO_DATE('2017-03-23 22:37:41','YYYY-MM-DD HH24:MI:SS'));




create table wi_api(
  id number(10) not null primary key,
  servicename varchar2(30)  unique not null,
  path varchar2(300) not null,
  url varchar2(300)  not null,
  type varchar2(20)  not null,
  active integer  not null,
  remark varchar2(500)
 );
comment on table wi_api is 'web接口api';
comment on column wi_api.servicename is '服务名字、Java文件名字';
comment on column wi_api.path is '文件路径';
comment on column wi_api.url is '访问地址';
comment on column wi_api.type is '请求类型 目前只支持post、get';
comment on column wi_api.active is '接口状态';
comment on column wi_api.remark is '备注';
create sequence seq_wi_api;