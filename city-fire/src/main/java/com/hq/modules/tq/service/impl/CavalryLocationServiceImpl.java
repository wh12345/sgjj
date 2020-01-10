package com.hq.modules.tq.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.modules.tq.dao.CavalryLocationDao;
import com.hq.modules.tq.entity.CavalryLocationEntity;
import com.hq.modules.tq.service.CavalryLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/24.
 */
@Service
@DS("mysql")
public class CavalryLocationServiceImpl  extends ServiceImpl<CavalryLocationDao,CavalryLocationEntity> implements CavalryLocationService {
    @Autowired
    private CavalryLocationDao cavalryLocationDao;
    @Override
    public List<Map<String, Object>> getLocation(int size) {
        return cavalryLocationDao.getLocationInfo(size);
    }
}
