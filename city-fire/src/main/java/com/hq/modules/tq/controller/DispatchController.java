package com.hq.modules.tq.controller;

import com.hq.common.utils.R;
import com.hq.modules.tq.entity.CavalryEntity;
import com.hq.modules.tq.service.CavalryService;
import com.hq.modules.tq.service.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

/**
 * Created by Because of you on 2019/12/26.
 */
@RestController
@RequestMapping("/tq/dispatch")
public class DispatchController {
    @Autowired
    private CavalryService cavalryService;
    @Autowired
    private DispatcherService dispatcherService;
    @GetMapping("/publish")
    public R getLocation(@RequestParam Map<String,Object> info) {
        CavalryEntity c = cavalryService.getById(new Random().nextInt(16));
        Map<String,Object> resultMap = dispatcherService.dispatcher(info);
        resultMap.put("hphm",c.getHphm());
        resultMap.put("jyxm",c.getJyxm());
        resultMap.put("phonenumber",c.getPhonenumber());
        return R.ok().put("cavalry",resultMap);
    }
}
