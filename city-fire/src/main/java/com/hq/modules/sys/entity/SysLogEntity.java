package com.hq.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_log")
@KeySequence(value="SEQ_SYS_LOG",clazz = Long.class)
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(value = "ID",type = IdType.INPUT)
	private Long id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createDate;
}
