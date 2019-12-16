package com.hq.modules.operate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("cf_transferrecord")
public class TransferrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private String recordId;
	/**
	 * 流转设备id
	 */
	private String equipmentId;
	/**
	 * 流转设备名称
	 */
	private String equipmentName;
	/**
	 * 流转类型名称 0：安装 1：更换
	 */
	private Integer transferName;
	/**
	 * 原地址
	 */
	private String originalAddress;
	/**
	 * 目的地址
	 */
	private String destAddress;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private Date operatTime;
	/**
	 * 操作原因
	 */
	private String operatReason;
}