package com.hq.modules.sys.entity;

/**
 * Created by Because of you on 2019/12/10.
 */
public class SysRankEntity {
    private String field;
    private String type;
    public SysRankEntity(){}
    public SysRankEntity(String field, String type) {
        this.field = field;
        this.type = type;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }
}
