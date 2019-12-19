
package com.hq.modules.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.sys.dao.SysLogDao;
import com.hq.modules.sys.entity.SysLogEntity;
import com.hq.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
@DS("oracle")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        Page<SysLogEntity> page = this.page(
                new Query<SysLogEntity>(params).getPage(),
                new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
        );
        return new PageUtils(page);
    }
}
