package com.hq.modules.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Because of you on 2019/12/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryConditionEntity {
    private String field;
    private String type;//类型
    private String symbol;
    private String textvalue;
    private String numvalue1;//数字值
    private String numvalue2;//数字值
    private String dateformat;//日期格式
    private String datevalue1;
    private String datevalue2;
}
