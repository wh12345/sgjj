package com.hq.modules.query.service.impl;

import com.hq.modules.query.dao.QuerySynthesisDao;
import com.hq.modules.query.entity.QueryConditionEntity;
import com.hq.modules.query.entity.QueryRankEntity;
import com.hq.modules.query.entity.QueryTableEntity;
import com.hq.modules.query.service.QuerySynthesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/3.
 */
@Service
public class QuerySynthesisServiceImpl implements QuerySynthesisService {
    @Autowired
    private QuerySynthesisDao systhesisDao;
    @Override
    public List<QueryTableEntity> listTable() {
        return systhesisDao.listTable();
    }

    @Override
    public List listField(String tablename) {
        return systhesisDao.listField(tablename);
    }

    @Override
    public List<HashMap<String, Object>> multipleQuerying(Map<String, Object> map) {
        String tablename = (String) map.get("tablename");
        List<List<QueryConditionEntity>> conditionsArray = (List<List<QueryConditionEntity>>) map.get("conditionsArray");
        List<String> fields = (List<String>) map.get("fields");
        List<QueryRankEntity> ranks = (List<QueryRankEntity>) map.get("ranks");
        List<HashMap<String, Object>> list = systhesisDao.multipleQuery(tablename,conditionsArray,fields,ranks);
        System.out.println("list:"+list.toString());
        return list;
    }
}
