package com.hq.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
