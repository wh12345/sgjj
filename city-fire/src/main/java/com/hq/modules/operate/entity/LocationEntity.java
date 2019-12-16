package com.hq.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author hq
 * @date 2018-12-17 09:25:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("cf_location")
public class LocationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "location_id",type = IdType.INPUT)
	private String locationId;
	/**
	 * 位置全称名
	 */
	private String locationName;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 市级编码
	 */
	private String citycode;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 区级编码
	 */
	private String adcode;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 街道编号
	 */
	private String streetNumber;
	/**
	 * 经度
	 */
	private Double lat;
	/**
	 * 纬度
	 */
	private Double lng;

	/**
	 * 设备状态: 0:正常 1：删除 2：禁用
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;

}
