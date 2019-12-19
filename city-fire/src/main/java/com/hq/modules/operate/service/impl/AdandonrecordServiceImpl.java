package com.hq.modules.operate.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.operate.dao.AdandonrecordDao;
import com.hq.modules.operate.entity.AdandonrecordEntity;
import com.hq.modules.operate.service.AdandonrecordService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("AdandonrecordService")
@DS("oracle")
public class AdandonrecordServiceImpl extends ServiceImpl<AdandonrecordDao, AdandonrecordEntity> implements AdandonrecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /*Page<AdandonrecordEntity> page = this.selectPage(
                new Query<AdandonrecordEntity>(params).getPage(),
                new EntityWrapper<AdandonrecordEntity>()
        );*/
        Page<AdandonrecordEntity> page = this.page(
                new Query<AdandonrecordEntity>(params).getPage(),
                new QueryWrapper<AdandonrecordEntity>()
        );
        return new PageUtils(page);
    }

}
