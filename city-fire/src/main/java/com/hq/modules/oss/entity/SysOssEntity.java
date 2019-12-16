
package com.hq.modules.oss.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 文件上传
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_oss")
public class SysOssEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@TableId
	private Long id;
	//URL地址
	private String url;
	//创建时间
	private Date createDate;
}
