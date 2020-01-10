package com.hq.modules.tq.service.impl;

import com.hq.modules.tq.service.CavalryLocationService;
import com.hq.modules.tq.service.DispatcherService;
import com.hq.modules.tq.utils.DispatchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/26.
 */
@Service
public class DispatcherServiceImpl implements DispatcherService {
    @Autowired
    private CavalryLocationService locationService;
    @Override
    public Map<String, Object> dispatcher(Map<String, Object> info) {
        Map<String, Object> resultMap = null;
        Double lngA = Double.parseDouble(String.valueOf(info.get("lng")));
        Double latA = Double.parseDouble(String.valueOf(info.get("lat")));
        List<Map<String,Object>> list =  locationService.getLocation(30);
        Double dd =  1000000000.0;
        for (Map<String,Object> tmp:list) {
            Double lngB = Double.parseDouble(String.valueOf(tmp.get("longitude")));
            Double latB = Double.parseDouble(String.valueOf(tmp.get("latitude")));
            double d = DispatchUtils.distanceCheck(lngA,latA,lngB,latB);
            if(d<dd) {
                dd = d;
                resultMap = tmp;
            }
        }
        return resultMap;
    }

}
