package com.hq.modules.sys.service;

import com.hq.modules.sys.entity.SysTableEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/3.
 */
public interface SysTableService {
    List<SysTableEntity> listTable();

    List listField(String tablename);

    List<HashMap<String,Object>> multipleQuerying(Map<String, Object> map);
}
