package com.hq.modules.wi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hq.common.exception.RRException;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.wi.entity.WiApiEntity;
import com.hq.modules.wi.proxy.ApiProxy;
import com.hq.modules.wi.service.ApiService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import java.io.*;
import java.util.Map;

/**
 * Created by Because of you on 2019/11/15.
 */
@RestController
@RequestMapping("/wi/api")
public class ApiController {
    @Value(value = "${cityfire.wi.basepath}")
    private String basepath;
    @Autowired
    private ApiProxy apiProxy;
    @Autowired
    private ApiService apiService;
    @GetMapping("/get/{servicename}/{data}")
    public R getApi(@PathVariable String servicename,@PathVariable String  data) {
        R r= null;
        try {
            if("params".equals(data)) {
                return R.error("参数params为base64加密的json字符串");
            }
            byte[] bytes = new BASE64Decoder().decodeBuffer(data);
            data = new String(bytes,"UTF-8");
            System.out.println("servicename"+servicename+"data"+data);
            String resultData = apiService.requestApi(servicename,data);
            if("-1".equals(resultData)) {
                return R.error(-1,"服务接口不存在");
            }else if("0".equals(resultData)) {
                return R.error(-1,"接口未激活");
            }
            r = R.ok().put("data",resultData);
        } catch (IOException e) {
            r = R.error();
            e.printStackTrace();
        }
        return r;

    }
    @PostMapping("/post/{servicename}")
    public R postApi(@PathVariable String servicename,@RequestBody String data) {
        JSONObject json = JSON.parseObject(data);
        String resultData =apiService.requestApi(servicename,json.getString("name"));
        if("-1".equals(resultData)) {
            return R.error(-1,"服务接口不存在");
        }else if("0".equals(resultData)) {
            return R.error(0,"接口未激活");
        }
        return R.ok().put("data",resultData);
    }
    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @RequiresPermissions("wi:api:upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String filename = file.getOriginalFilename();
        String filepath = basepath+File.separator+filename;
        InputStream is = file.getInputStream();
        byte[] data = new byte[1024*1024];
        int len = is.read(data);
        OutputStream os = new FileOutputStream(new File(filepath));
        os.write(data,0,len);
        os.flush();
        os.close();
        is.close();
        return R.ok().put("path",filepath);
    }
    @GetMapping("/info/{id}")
    @RequiresPermissions("wi:api:info")
    public R info(@PathVariable Long id) {
        WiApiEntity wi = apiService.queryWiApiEntityById(id);
        return  R.ok().put("wi",wi);
    }
    @GetMapping("/list")
    @RequiresPermissions("wi:api:list")
    public R listWi(@RequestParam Map<String,Object> params) {
        System.out.println(params.toString());
        PageUtils page = apiService.queryPage(params);
        return  R.ok().put("page",page);
    }
    @PostMapping("/save")
    @RequiresPermissions("wi:api:save")
    public R  save(@RequestBody WiApiEntity wi) {
        System.out.println(wi);
        apiService.addWi(wi);
        return  R.ok();
    }
    @PostMapping("/update")
    @RequiresPermissions("wi:api:update")
    public R  update(@RequestBody WiApiEntity wi) {
        System.out.println(wi);
        apiService.updateWi(wi);
        return  R.ok();
    }
    @PostMapping("/delete")
    @RequiresPermissions("wi:api:delete")
    public R  delete(@RequestBody Long[] ids) {
        apiService.delete(ids);
        return  R.ok();
    }
    @PostMapping("/pause")
    @RequiresPermissions("wi:api:pause")
    public R  pause(@RequestBody Long[] ids) {
        apiService.pause(ids);
        return  R.ok();
    }
    @PostMapping("/resume")
    @RequiresPermissions("wi:api:resume")
    public R  resume(@RequestBody Long[] ids) {
        apiService.resume(ids);
        return  R.ok();
    }
}
