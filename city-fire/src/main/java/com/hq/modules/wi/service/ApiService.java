package com.hq.modules.wi.service;

import com.hq.common.utils.PageUtils;
import com.hq.common.utils.R;
import com.hq.modules.wi.entity.WiApiEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Because of you on 2019/11/18.
 */
@Service
public interface ApiService {
    /**
     * restful风格请求处理
     * @param servicename
     * @param data
     * @return
     */
    public R requestApi(String servicename, String  data, Long userId,String type);

    /**
     * 查询整个页面
     * @param params
     * @return
     */
    public PageUtils queryPage(Map<String,Object> params);

    /**
     * 通过id查询WiApiEntity
     * @param id
     * @return
     */
    public WiApiEntity queryWiApiEntityById(Long id);
    /**
     * 添加接口
     * @param wi
     */
    public void addWi(WiApiEntity wi);

    /**
     * 修改
     * @param wi
     */
    public void updateWi(WiApiEntity wi);

    /**
     * 删除接口
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 禁用接口
     * @param ids
     */
    public void pause(Long[] ids);

    /**
     * 恢复接口
     * @param ids
     */
    public void resume(Long[] ids);

    /**
     *
     */
    public int updateBatch(Long[] ids,int active);
}
