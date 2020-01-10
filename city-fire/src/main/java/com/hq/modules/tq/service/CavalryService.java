package com.hq.modules.tq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hq.common.utils.PageUtils;
import com.hq.modules.tq.entity.CavalryEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/19.
 */
public interface CavalryService extends IService<CavalryEntity>{
    public PageUtils queryPage(Map<String,Object> map);

    public List<Map<String,Object>>  queryAllCavalryInfo();
}
