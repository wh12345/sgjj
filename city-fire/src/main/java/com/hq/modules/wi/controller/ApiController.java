package com.hq.modules.wi.controller;

import com.hq.common.exception.RRException;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.wi.entity.WiApiEntity;
import com.hq.modules.wi.service.ApiService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    private ApiService apiService;

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
