package com.hq.modules.sys.entity;

/**
 * Created by Because of you on 2019/12/3.
 */
public class SysConditionEntity {
    private String field;
    private String type;//类型
    private String symbol;
    private String textvalue;
    private String numvalue1;//数字值
    private String numvalue2;//数字值
    private String dateformat;//日期格式
    private String datevalue1;
    private String datevalue2;
    public SysConditionEntity(){}

    public SysConditionEntity(String field, String type, String symbol, String textvalue, String numvalue1, String numvalue2, String dateformat, String datevalue1, String datevalue2) {
        this.field = field;
        this.type = type;
        this.symbol = symbol;
        this.textvalue = textvalue;
        this.numvalue1 = numvalue1;
        this.numvalue2 = numvalue2;
        this.dateformat = dateformat;
        this.datevalue1 = datevalue1;
        this.datevalue2 = datevalue2;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTextvalue(String textvalue) {
        this.textvalue = textvalue;
    }

    public void setNumvalue1(String numvalue1) {
        this.numvalue1 = numvalue1;
    }

    public void setNumvalue2(String numvalue2) {
        this.numvalue2 = numvalue2;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public void setDatevalue1(String datevalue1) {
        this.datevalue1 = datevalue1;
    }

    public void setDatevalue2(String datevalue2) {
        this.datevalue2 = datevalue2;
    }

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTextvalue() {
        return textvalue;
    }

    public String getNumvalue1() {
        return numvalue1;
    }

    public String getNumvalue2() {
        return numvalue2;
    }

    public String getDateformat() {
        return dateformat;
    }

    public String getDatevalue1() {
        return datevalue1;
    }

    public String getDatevalue2() {
        return datevalue2;
    }
}
