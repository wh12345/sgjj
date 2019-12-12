package com.hq.modules.wi.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.modules.wi.entity.WiApiEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by Because of you on 2019/11/28.
 */
@Mapper
public interface WiApiDao  extends BaseMapper<WiApiEntity>{
        int updateBatch(Map<String,Object> map);
}
