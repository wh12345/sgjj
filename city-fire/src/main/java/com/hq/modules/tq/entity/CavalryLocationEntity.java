package com.hq.modules.tq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Because of you on 2019/12/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("inter_phone")
public class CavalryLocationEntity {
    private String id;
    private Integer devbtype;
    private String  devstype;
    private String  devid;
    private String  city;
    private Double  longitude;
    private Double  latitude;
    private Double  altitude;
    private Double  speed;
    private Double  direction;
    private String  locationtime;
    private Integer workstate;
    private String  clzl;
    private String  hphm;
    private Integer jzlx;
    private String  jybh;
    private String  jymc;
    private String  lxdh;
    private String  ssdwdm;
    private String  ssdwmc;
    private String  teamno;
    private String  dth;
    private String  reserve1;
    private String  reserve2;
    private String  reserve3;
    private String  issendmq;
}
