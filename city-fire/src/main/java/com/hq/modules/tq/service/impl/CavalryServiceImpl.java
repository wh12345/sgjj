package com.hq.modules.tq.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.tq.dao.CavalryDao;
import com.hq.modules.tq.entity.CavalryEntity;
import com.hq.modules.tq.service.CavalryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/19.
 */
@Service
@DS("oracle")
public class CavalryServiceImpl extends ServiceImpl<CavalryDao,CavalryEntity> implements CavalryService {

    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        String searchkey = (String)map.get("searchkey");
        String content = (String)map.get("content");
        Page<CavalryEntity> page = this.page(
                new Query<CavalryEntity>(map).getPage(),
                new QueryWrapper<CavalryEntity>().like(StringUtils.isNotEmpty(content),searchkey,content)
                .orderByAsc("cavalry_Id")
        );
        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> queryAllCavalryInfo() {
        List<Map<String,Object>> list = this.listMaps(
                new QueryWrapper<CavalryEntity>().select("jyxm","jybh","hphm","phonenumber")
        );
        return list;
    }
}
