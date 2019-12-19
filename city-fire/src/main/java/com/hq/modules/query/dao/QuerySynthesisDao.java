package com.hq.modules.query.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.modules.query.entity.QueryConditionEntity;
import com.hq.modules.query.entity.QueryFieldEntity;
import com.hq.modules.query.entity.QueryRankEntity;
import com.hq.modules.query.entity.QueryTableEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Because of you on 2019/12/3.
 */
@Mapper
public interface QuerySynthesisDao  extends BaseMapper {
    List<QueryTableEntity> listTable();

    List<QueryFieldEntity> listField(String tablename);

    List<HashMap<String,Object>> multipleQuery(@Param("tablename") String tablename, @Param("conditionsArray") List<List<QueryConditionEntity>> conditionsArray, @Param("fields") List<String> fields, @Param("ranks")List<QueryRankEntity> ranks);

    List<HashMap<String,Object>> multipleQueryByGroup();
}
