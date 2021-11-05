package com.example.testproject.model;

import javax.annotation.Generated;

public class Notification {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.055+08:00", comments="Source field: NOTIFICATION.ID")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.055+08:00", comments="Source field: NOTIFICATION.NOTIFIER")
    private Integer notifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.RECEIVER")
    private Integer receiver;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.OUTER_ID")
    private Integer outerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.TYPE")
    private Integer type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.GMT_CREATE")
    private Long gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.STATUS")
    private Integer status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.055+08:00", comments="Source field: NOTIFICATION.ID")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.055+08:00", comments="Source field: NOTIFICATION.ID")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.NOTIFIER")
    public Integer getNotifier() {
        return notifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.NOTIFIER")
    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.RECEIVER")
    public Integer getReceiver() {
        return receiver;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.RECEIVER")
    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.OUTER_ID")
    public Integer getOuterId() {
        return outerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.OUTER_ID")
    public void setOuterId(Integer outerId) {
        this.outerId = outerId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.TYPE")
    public Integer getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.TYPE")
    public void setType(Integer type) {
        this.type = type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.GMT_CREATE")
    public Long getGmtCreate() {
        return gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.GMT_CREATE")
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.STATUS")
    public Integer getStatus() {
        return status;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.STATUS")
    public void setStatus(Integer status) {
        this.status = status;
    }
}