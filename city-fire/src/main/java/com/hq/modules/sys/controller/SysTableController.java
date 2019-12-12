package com.hq.modules.sys.controller;

import com.hq.common.utils.R;
import com.hq.modules.sys.service.SysTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/3.
 */
@RestController
@RequestMapping("/sys/table")
public class SysTableController {
    @Autowired
    private SysTableService sysTableService;
    @GetMapping("/listtable")
/*    @RequiresPermissions("sys:table:listtable")*/
    public R listTable(){
        List tables = sysTableService.listTable();
        if(tables.size()==0) {
            return R.error("查不到记录");
        }
        return R.ok().put("tables", tables);
    }

    @GetMapping("/listfield/{tablename}")
/*    @RequiresPermissions("sys:table:listfield")*/
    public R listField(@PathVariable String tablename){
        List fields = sysTableService.listField(tablename);
        if(fields.size()==0) {
            return R.error("查不到记录");
        }
        return R.ok().put("fields", fields);
    }
    @PostMapping("/multiplequerying")
    public R multipleQuerying(@RequestBody Map<String,Object> map) {
        List<HashMap<String,Object>> results = sysTableService.multipleQuerying(map);
        return R.ok().put("results",results);
    }
}
