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
public class QueryTableEntity {
    private String tablename;
    private String comments;
}
