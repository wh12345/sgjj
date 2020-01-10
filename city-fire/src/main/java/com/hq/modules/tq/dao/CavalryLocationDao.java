package com.hq.modules.tq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.modules.tq.entity.CavalryLocationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Because of you on 2019/12/24.
 */
@Mapper
public interface CavalryLocationDao  extends BaseMapper<CavalryLocationEntity>{
      public List<Map<String,Object>> getLocationInfo(@Param("size") int size);
}
