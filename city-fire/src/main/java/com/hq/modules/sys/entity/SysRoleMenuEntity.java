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
 * 角色与菜单对应关系
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_menu")
@KeySequence(value="SEQ_SYS_ROLE_MENU",clazz = Long.class)
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "ID",type = IdType.INPUT)
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;
	
}
