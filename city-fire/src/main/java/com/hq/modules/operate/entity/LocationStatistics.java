package com.hq.modules.operate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationStatistics implements Serializable {

    private String district;

    private String num;

}
