package com.hq.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 系统配置信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_config")
@KeySequence(value="SEQ_SYS_CONFIG",clazz = Long.class)
public class SysConfigEntity {
	@TableId(value = "ID",type = IdType.INPUT)
	private Long id;
	@NotBlank(message="参数名不能为空")
	private String paramKey;
	@NotBlank(message="参数值不能为空")
	private String paramValue;
	private String remark;
}
