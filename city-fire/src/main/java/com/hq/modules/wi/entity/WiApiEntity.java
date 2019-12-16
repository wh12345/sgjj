package com.hq.modules.wi.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Because of you on 2019/11/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("wi_api")
@KeySequence(value="SEQ_WI_API",clazz = Long.class)
public class WiApiEntity {
    @TableId(value = "ID",type = IdType.INPUT)
    private Long   id;
    private String servicename;
    private String path;
    private String url;
    private String type;
    private Integer active;
    private String remark;
}
