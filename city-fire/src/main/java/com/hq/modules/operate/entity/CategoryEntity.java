package com.hq.modules.operate.entity;

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
 *
 *
 * @author hq
 * @date 2018-12-17 09:25:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cf_category")
@KeySequence(value="SEQ_CF_CATEGORY",clazz = Long.class)
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "category_Id",type = IdType.INPUT)
	private Long categoryId;
	/**
	 * 分类名
	 */
	private String typeName;
	/**
	 * 分类编码
	 */
	private String typeId;
	/**
	 * 父级分类ID
	 */
	private String typePid;
	/**

	 * 父级分类ID
	 */
	private String typePname;
	/**
	 * 层级
	 */
	private String typeLevel;
	/**
	 * 状态 0:正常 1：删除 2：禁用
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
}
