package com.hq.modules.tq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.tq.entity.CavalryEntity;
import com.hq.modules.tq.entity.DwEntity;
import com.hq.modules.tq.service.CavalryService;
import com.hq.modules.tq.service.DwService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/18.
 */
@RestController
@RequestMapping("/tq/supervise")
public class SuperviseController {
        @Autowired
        private CavalryService cavalryService;
        @Autowired
        private DwService dwService;
        @GetMapping("/list")
        @RequiresPermissions("tq:supervise:list")
        public R list(@RequestParam Map<String,Object> map) {
               PageUtils page =  cavalryService.queryPage(map);
               return R.ok().put("page",page);
        }
        @GetMapping("/info/{id}")
        @RequiresPermissions("tq:supervise:info")
        public R info(@PathVariable Long id) {
            CavalryEntity cavalryEntity = cavalryService.getById(id);
            return R.ok().put("cavalry",cavalryEntity);
        }
        @PostMapping("/save")
        @RequiresPermissions("tq:supervise:save")
        public R save(@RequestBody CavalryEntity cavalryEntity) {
            Long dwbh = cavalryEntity.getDwbh();
            DwEntity dw = dwService.getById(dwbh);
            cavalryEntity.setDwmc(dw.getDwmc());
            cavalryService.save(cavalryEntity);
            return R.ok();
        }
        @PostMapping("/update")
        @RequiresPermissions("tq:supervise:update")
        public R update(@RequestBody CavalryEntity cavalryEntity) {
            cavalryService.saveOrUpdate(cavalryEntity);
            return R.ok();
        }
        @GetMapping("/delete/{cavalryId}")
        @RequiresPermissions("tq:supervise:delete")
        public R delete(@PathVariable Long cavalryId) {
            cavalryService.removeById(cavalryId);
            return R.ok();
        }
        @GetMapping("/listdw")
        @RequiresPermissions("tq:supervise:info")
        public R listDw() {
            List<Map<String,Object>> dws = dwService.listMaps(new QueryWrapper<DwEntity>().select("dwbh","dwmc"));
            return R.ok().put("dws",dws);
        }
}
