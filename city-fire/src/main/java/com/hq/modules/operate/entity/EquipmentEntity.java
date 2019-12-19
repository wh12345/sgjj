package com.hq.modules.operate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@TableName("cf_equipment")
public class EquipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "equipment_id",type = IdType.INPUT)
	private String equipmentId;
	/**
	 * 设备名称
	 */
	private String equipmentName;
	/**
	 * 所属分类id
	 */
	private String belongTypeid;
	/**
	 * 所属分类名称
	 */
	private String belongTypename;
	/**
	 * 设备价格
	 */
	private Integer equipmentPrice;
	/**
	 * 设备数量
	 */
	private Integer equipmentNum;
	/**
	 * 设备所在位置id
	 */
	private String localtionId;
	/**
	 * 设备所在位置名称
	 */
	private String locationName;
	/**
	 * 设备状态0:正常 1：报修 2：报废
	 */
	private Integer equipmentStatus;
	/**
	 * 设备状态: 0:在库 1：检修 2：在用
	 */
	private Integer locationStatus;

	/**
	 * 小区
	 */
	private String community;
	/**
	 * 单元
	 */
	private String unit;
	/**
	 * 楼层
	 */
	private String floor;
	/**
	 * 楼道
	 */
	private String corridor;
	/**
	 * 房间号
	 */
	private String roomNumber;
	/**
	 * 管理员
	 */
	private String administrator;
	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;
}
