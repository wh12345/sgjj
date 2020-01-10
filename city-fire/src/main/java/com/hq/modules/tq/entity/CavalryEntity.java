package com.hq.modules.tq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Because of you on 2019/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tq_cavalry")
@ToString
@KeySequence(value="SEQ_TQ_CAVALRY",clazz = Long.class)
public class CavalryEntity {
    @TableId(value = "CAVALRY_ID",type = IdType.INPUT)
    private Long cavalryId;
    private Long dwbh;
    private String dwmc;
    private String  jybh;
    private String jyxm;
    private String jyzw;
    private String hphm;
    private String phonenumber;
    private String remark;
}
