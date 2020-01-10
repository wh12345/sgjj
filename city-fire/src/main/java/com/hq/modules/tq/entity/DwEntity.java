package com.hq.modules.tq.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Because of you on 2019/12/20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tq_dw")
public class DwEntity {
    @TableId("dwbh")
    private Long dwbh;
    private String dwmc;
    private String dwdz;
    private String jd;
    private String wd;
    private String remark;
}
