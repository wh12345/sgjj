package com.hq.modules.wi.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.Query;
import com.hq.modules.wi.dao.WiApiDao;
import com.hq.modules.wi.entity.WiApiEntity;
import com.hq.modules.wi.proxy.ApiProxy;
import com.hq.modules.wi.service.ApiService;
import com.qy.api.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Because of you on 2019/11/18.
 */
@Service
public class ApiServiceImpl extends ServiceImpl<WiApiDao,WiApiEntity> implements ApiService {
    @Value(value = "${cityfire.wi.baseurl}")
    private String baseurl;
    @Autowired
    private ApiProxy proxy;
    @Autowired
    private WiApiDao wiApiDao;
    @Override
    public String requestApi(String servicename,String  data) {
        //数据库查询
        RequestApi api = null;
        WiApiEntity wiApiEntity = new WiApiEntity();
        wiApiEntity.setServicename(servicename);
        //wiApiEntity.setActive(1);
        wiApiEntity = wiApiDao.selectOne(wiApiEntity);
        if(wiApiEntity==null) {
           return "-1";
        }else if(wiApiEntity.getActive()==0) {
            return "0";
        }
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            String buildOutput = URLDecoder.decode(path.substring(1),"UTF-8")+"com/hq/modules/wi/proxy";
            String fullClassName = wiApiEntity.getServicename().substring(0,1).toUpperCase()+wiApiEntity.getServicename().substring(1);
            api = (RequestApi) proxy.newProxyInstance(fullClassName,wiApiEntity.getPath(),"UTF-8",buildOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return api.run(data);
    }

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        String servicename = (String)params.get("servicename");
        Page<WiApiEntity> page = this.selectPage(
                new Query<WiApiEntity>(params).getPage(),
                new EntityWrapper<WiApiEntity>().like(StringUtils.isNotEmpty(servicename),"servicename",servicename)
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
