package com.hq.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hq.modules.sys.entity.SysConditionEntity;
import com.hq.modules.sys.entity.SysFieldEntity;
import com.hq.modules.sys.entity.SysRankEntity;
import com.hq.modules.sys.entity.SysTableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Because of you on 2019/12/3.
 */
@Mapper
public interface SysTableDao  extends BaseMapper{
    List<SysTableEntity> listTable();

    List<SysFieldEntity> listField(String tablename);

    List<HashMap<String,Object>> multipleQuery(@Param("tablename") String tablename, @Param("conditionsArray") List<List<SysConditionEntity>> conditionsArray, @Param("fields") List<String> fields, @Param("ranks")List<SysRankEntity> ranks);

    List<HashMap<String,Object>> multipleQueryByGroup();
}
