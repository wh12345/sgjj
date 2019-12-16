package com.hq.modules.operate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentStatistics implements Serializable {
    //小区
    private String community;

    //时间
    private String date1;

    //新增设备数量
    private String addEqunum;

    //设备总数量
    private  String sumEqunum;
}
