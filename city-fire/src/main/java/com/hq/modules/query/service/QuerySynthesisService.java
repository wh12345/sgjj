package com.hq.modules.query.service;

import com.hq.modules.query.entity.QueryTableEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/3.
 */
public interface QuerySynthesisService {
    List<QueryTableEntity> listTable();

    List listField(String tablename);

    List<HashMap<String,Object>> multipleQuerying(Map<String, Object> map);
}
