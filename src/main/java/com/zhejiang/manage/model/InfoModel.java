package com.zhejiang.manage.model;


import com.zhejiang.manage.util.DateUtils;

import java.io.Serializable;

public class InfoModel implements Serializable {


    private String title;

    private String company;

    private String connUser;

    private String connPhone;

    private String btype;

    private String type;

    private String updateTime;

    public InfoModel(Information information){
        super();
        title = information.getTitle();
        company = information.getCompany();
        connUser = information.getConnUser();
        connPhone = information.getConnPhone();
        btype = information.getbType();
        type = information.getType();
        updateTime = DateUtils.dateToString(information.getUpdateTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getConnUser() {
        return connUser;
    }

    public void setConnUser(String connUser) {
        this.connUser = connUser;
    }

    public String getConnPhone() {
        return connPhone;
    }

    public void setConnPhone(String connPhone) {
        this.connPhone = connPhone;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}