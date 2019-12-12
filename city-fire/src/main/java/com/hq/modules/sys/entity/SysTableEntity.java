package com.hq.modules.sys.entity;

/**
 * Created by Because of you on 2019/12/3.
 */
public class SysTableEntity {
    private String tablename;
    private String comments;

    public SysTableEntity(String tablename, String comments) {
        this.tablename = tablename;
        this.comments = comments;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTablename() {
        return tablename;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "SysTableEntity{" +
                "tablename='" + tablename + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
