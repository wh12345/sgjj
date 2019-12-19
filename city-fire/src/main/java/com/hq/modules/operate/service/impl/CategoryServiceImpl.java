package com.hq.modules.operate.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.operate.dao.CategoryDao;
import com.hq.modules.operate.entity.CategoryEntity;
import com.hq.modules.operate.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("CategoryService")
@DS("oracle")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.page(
                new Query<CategoryEntity>(params).getPage(),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void changPnameBypid(String Pid, String Pname) {
        baseMapper.changPnameBypid(Pid,Pname);
    }

}
