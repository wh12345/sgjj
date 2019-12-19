package com.hq.modules.operate.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.operate.dao.LocationDao;
import com.hq.modules.operate.entity.LocationEntity;
import com.hq.modules.operate.service.LocationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("LocationService")
@DS("oracle")
public class LocationServiceImpl extends ServiceImpl<LocationDao, LocationEntity> implements LocationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String district = (String) params.get("district");
        String locationName = (String) params.get("locationName");
        /*Page<LocationEntity> page = this.selectPage(
                new Query<LocationEntity>(params).getPage(),
                new EntityWrapper<LocationEntity>()
                        .where(StringUtils.isNotBlank(district),"district = {0}",district)
                        .like(StringUtils.isNotBlank(locationName),"location_name",locationName)
        );*/
        Page<LocationEntity> page = this.page(
                new Query<LocationEntity>(params).getPage(),
                new QueryWrapper<LocationEntity>()
                        .apply(StringUtils.isNotBlank(district),"district = {0}",district)
                        .like(StringUtils.isNotBlank(locationName),"location_name",locationName)
        );
        return new PageUtils(page);
    }

    @Override
    public void updateRemarkByid(String locationid, String remark) {
        baseMapper.updateRemarkByid(locationid,remark);
    }

    @Override
    public void updateStatusByid(String locationid, String status) {
        baseMapper.updateStatusByid(locationid,status);
    }

    @Override
    public List<String> selectDistrictBycityCode(String citycode) {
        List<String> list = baseMapper.selectDistrictBycityCode(citycode);
        return  list;
    }

    @Override
    public List<LocationEntity> selectBydistrict(String district) {
        List<LocationEntity> list = baseMapper.selectBydistrict(district);
        return  list;
    }


}
