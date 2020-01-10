package com.hq.modules.operate.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.operate.dao.TransferrecordDao;
import com.hq.modules.operate.entity.TransferrecordEntity;
import com.hq.modules.operate.service.TransferrecordService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("TransferrecordService")
@DS("oracle")
public class TransferrecordServiceImpl extends ServiceImpl<TransferrecordDao, TransferrecordEntity> implements TransferrecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TransferrecordEntity> page = this.page(
                new Query<TransferrecordEntity>(params).getPage(),
                new QueryWrapper<TransferrecordEntity>()
        );
        return new PageUtils(page);
    }

}
