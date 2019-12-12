package com.hq.modules.sys.entity;

/**
 * Created by Because of you on 2019/12/3.
 */
public class SysFieldEntity {
    private String field;
    private String type;
    private String comments;
    public SysFieldEntity(){}

    public SysFieldEntity(String field, String type, String comments) {
        this.field = field;
        this.type = type;
        this.comments = comments;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }

    public String getComments() {
        return comments;
    }
}
