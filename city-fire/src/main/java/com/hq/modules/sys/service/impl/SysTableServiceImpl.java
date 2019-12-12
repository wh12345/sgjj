package com.hq.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hq.modules.sys.dao.SysTableDao;
import com.hq.modules.sys.entity.SysConditionEntity;
import com.hq.modules.sys.entity.SysRankEntity;
import com.hq.modules.sys.entity.SysTableEntity;
import com.hq.modules.sys.service.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/3.
 */
@Service
public class SysTableServiceImpl implements SysTableService{
    @Autowired
    private SysTableDao sysTableDao;
    @Override
    public List<SysTableEntity> listTable() {
        return sysTableDao.listTable();
    }

    @Override
    public List listField(String tablename) {
        return sysTableDao.listField(tablename);
    }

    @Override
    public List<HashMap<String, Object>> multipleQuerying(Map<String, Object> map) {
        String tablename = (String) map.get("tablename");
        List<List<SysConditionEntity>> conditionsArray = (List<List<SysConditionEntity>>) map.get("conditionsArray");
        List<String> fields = (List<String>) map.get("fields");
        List<SysRankEntity> ranks = (List<SysRankEntity>) map.get("ranks");
        List<HashMap<String, Object>> list = sysTableDao.multipleQuery(tablename,conditionsArray,fields,ranks);
        System.out.println("list:"+list.toString());
        return list;
    }
}
