package com.hq.modules.sys.entity;


import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role")
@KeySequence(value = "SEQ_SYS_USER_ROLE",clazz = Long.class)
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId(value = "ID",type = IdType.INPUT)
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

}
