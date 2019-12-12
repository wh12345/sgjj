package com.hq.modules.wi.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Created by Because of you on 2019/11/18.
 */
@TableName("wi_api")
@KeySequence(value="SEQ_WI_API",clazz = Long.class)
public class WiApiEntity {
    @TableId(value = "ID",type = IdType.INPUT)
    private Long   id;
    private String servicename;
    private String path;
    private String url;
    private String type;
    private Integer active;
    private String remark;
    public WiApiEntity(){}

    public WiApiEntity(Long id, String servicename, String path, String url, String type, Integer active, String remark) {
        this.id = id;
        this.servicename = servicename;
        this.path = path;
        this.url = url;
        this.type = type;
        this.active = active;
        this.remark = remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public String getServicename() {
        return servicename;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    public Integer getActive() {
        return active;
    }

    public String getRemark() {
        return remark;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "WiApiEntity{" +
                "id=" + id +
                ", servicename='" + servicename + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", remark='" + remark + '\'' +
                '}';
    }
}
