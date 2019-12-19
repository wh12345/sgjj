package com.hq.modules.operate.entity;

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
@TableName("cf_adandonrecord")
public class AdandonrecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String recordId;
	/**
	 * 废弃设备id
	 */
	private String equipmentId;
	/**
	 * 废弃设备名称
	 */
	private String equipmentName;
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
