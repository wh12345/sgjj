package com.hq.modules.wi.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Because of you on 2019/12/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("wi_user")
@KeySequence(value="SEQ_WI_USER",clazz = Long.class)
public class WiUserEntity {
    @TableId(value = "user_Id",type = IdType.INPUT)
    private Long userId;
    private String username;
    private String password;
    private String serviceids;
    private Date createTime;
}
