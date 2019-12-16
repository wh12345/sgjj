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
@TableName("cf_warningrecord")
public class WarningrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long recordId;
	/**
	 * 报警设备id
	 */
	private String equipmentId;
	/**
	 * 报警设备名称
	 */
	private String equipmentName;
	/**
	 * 报警人
	 */
	private String warningOperator;
	/**
	 * 操作项
	 * 0 ：正常
	 * 1 ：报修
	 * 2 ：报废
	 */
	private int operatorStatus;
	/**
	 * 报警时间
	 */
	private Date warningTime;
	/**
	 * 报警原因
	 */
	private String warningReason;
	/**
	 * 处理人
	 */
	private String dealOperator;
	/**
	 * 处理时间
	 */
	private Date dealTime;
	/**
	 * 处理方案
	 */
	private String dealPlan;
	/**
	 * 当前状态 0：解决 1：未解决
	 */
	private Integer status;
}
