package com.hq.modules.wi.controller;

import com.hq.common.utils.R;
import com.hq.modules.wi.annotation.Login;
import com.hq.modules.wi.proxy.ApiProxy;
import com.hq.modules.wi.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by Because of you on 2019/12/17.
 */
@RestController
@RequestMapping("/wi/services")
public class ServicesController {
    @Autowired
    private ApiProxy apiProxy;
    @Autowired
    private ApiService apiService;
    @Login
    @GetMapping("/get/{servicename}/{data}")
    public R getApi(@PathVariable String servicename, @PathVariable String  data, ServletRequest request) {
        Long userId = Long.valueOf(request.getAttribute("userId").toString());
        R r= null;
        try {
            byte[] bytes = new BASE64Decoder().decodeBuffer(data);
            data = new String(bytes,"UTF-8");
            System.out.println("servicename"+servicename+"data"+data);
            r = apiService.requestApi(servicename,data,userId,"get");
        } catch (IOException e) {
            r = R.error("参数params为base64加密的json字符串");
            //e.printStackTrace();
        }
        return r;

    }
    @Login
    @PostMapping("/post/{servicename}")
    public R postApi(@PathVariable String servicename, @RequestBody String data,@RequestAttribute("userId") Long userId) {
        return apiService.requestApi(servicename,data,userId,"post");
    }
}
