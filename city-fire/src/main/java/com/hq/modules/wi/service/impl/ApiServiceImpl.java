package com.hq.modules.wi.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.common.utils.R;
import com.hq.modules.wi.dao.WiApiDao;
import com.hq.modules.wi.dao.WiUserDao;
import com.hq.modules.wi.entity.WiApiEntity;
import com.hq.modules.wi.entity.WiUserEntity;
import com.hq.modules.wi.proxy.ApiProxy;
import com.hq.modules.wi.service.ApiService;
import com.qy.api.RequestApi;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/11/18.
 */
@Service
@DS("oracle")
public class ApiServiceImpl extends ServiceImpl<WiApiDao,WiApiEntity> implements ApiService {
    @Value(value = "${cityfire.wi.baseurl}")
    private String baseurl;
    @Autowired
    private ApiProxy proxy;
    @Autowired
    private WiUserDao userDao;
    @Autowired
    private WiApiDao wiApiDao;
    @Override
    public R requestApi(String servicename, String  data, Long userId,String type) {
        R r =  null;
        WiUserEntity userEntity = userDao.selectById(userId);
        String[] strings = userEntity.getServiceids().split(",");
        List list = Arrays.asList(strings);
        //数据库查询
        RequestApi api = null;
        WiApiEntity  wiApiEntity = wiApiDao.selectOne(new QueryWrapper<WiApiEntity>()
                                    .eq(StringUtils.isNotEmpty(servicename),"servicename",servicename));
        boolean flag = list.contains(wiApiEntity.getId());
        if(!flag) {
            r = R.error(-1,"你没有访问该接口的权限!");;
        }else if(!type.equals(wiApiEntity.getType())) {
            r = R.error(-1,"该接口是"+wiApiEntity.getType()+"接口!");;
        }else if(wiApiEntity==null) {
            r = R.error(-1,"服务接口不存在!");;
        }else if(wiApiEntity.getActive()==0) {
            r = R.error(-1,"接口未激活!");
        }
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            String buildOutput = URLDecoder.decode(path.substring(1),"UTF-8")+"com/hq/modules/wi/proxy";
            String fullClassName = wiApiEntity.getServicename().substring(0,1).toUpperCase()+wiApiEntity.getServicename().substring(1);
            api = (RequestApi) proxy.newProxyInstance(fullClassName,wiApiEntity.getPath(),"UTF-8",buildOutput);
            r = R.ok().put("data",api.run(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        String servicename = (String)params.get("servicename");
        Page<WiApiEntity> page = this.page(
                new Query<WiApiEntity>(params).getPage(),
                new QueryWrapper<WiApiEntity>().like(StringUtils.isNotEmpty(servicename),"servicename",servicename)
        );
        return new PageUtils(page);
    }

    @Override
    public WiApiEntity queryWiApiEntityById(Long id) {
        return  wiApiDao.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWi(WiApiEntity wi) {
        String url = "";
        if("get".equals(wi.getType())) {
            url = baseurl+"/"+wi.getType()+"/"+wi.getServicename()+"/params";
        }else{
            url = baseurl+"/"+wi.getType()+"/"+wi.getServicename();
        }
        wi.setUrl(url);
        wiApiDao.insert(wi);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWi(WiApiEntity wi) {
        wiApiDao.updateById(wi);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        wiApiDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] ids) {
        updateBatch(ids,0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] ids) {
        updateBatch(ids,1);
    }

    @Override
    public int updateBatch(Long[] ids,int active) {
        Map<String,Object> map = new HashMap<>();
        map.put("list",ids);
        map.put("active",active);
        return wiApiDao.updateBatch(map);
    }
}
