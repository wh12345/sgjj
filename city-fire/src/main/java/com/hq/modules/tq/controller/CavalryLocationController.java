package com.hq.modules.tq.controller;

import com.hq.common.utils.R;
import com.hq.modules.tq.service.CavalryLocationService;
import com.hq.modules.tq.service.CavalryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
/**
 * Created by Because of you on 2019/12/24.
 */
@RestController
@RequestMapping("/tq/location")
public class CavalryLocationController {
    @Autowired
    private CavalryService cavalryService;
    @Autowired
    private CavalryLocationService locationService;
    @GetMapping("/getlocation")
    public R getLocation() {
        List<Map<String,Object>> cavalrys =  cavalryService.queryAllCavalryInfo();
        int size = cavalrys.size();
        List<Map<String,Object>> list = locationService.getLocation(size);
        for(int i=0;i<size;i++) {
            Map<String,Object>  map = list.get(i);
            cavalrys.get(i).put("longitude",map.get("longitude"));
            cavalrys.get(i).put("latitude",map.get("latitude"));
        }
        return R.ok().put("locations",cavalrys);
    }
}
