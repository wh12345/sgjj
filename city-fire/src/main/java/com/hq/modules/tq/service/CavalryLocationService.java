package com.hq.modules.tq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hq.modules.tq.entity.CavalryLocationEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/24.
 */
public interface CavalryLocationService extends IService<CavalryLocationEntity> {
    public List<Map<String,Object>> getLocation(int size);
}
