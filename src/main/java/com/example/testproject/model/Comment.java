package com.example.testproject.model;

import javax.annotation.Generated;

public class Comment {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.ID")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.PARENT_ID")
    private Integer parentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.TYPE")
    private Integer type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.COMMENTER")
    private Integer commenter;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_CREATE")
    private Long gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_MODIFIED")
    private Long gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.LIKE_COUNT")
    private Integer likeCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.CONTENT")
    private String content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.ID")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.ID")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.PARENT_ID")
    public Integer getParentId() {
        return parentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.PARENT_ID")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.TYPE")
    public Integer getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.TYPE")
    public void setType(Integer type) {
        this.type = type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.COMMENTER")
    public Integer getCommenter() {
        return commenter;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.479+08:00", comments="Source field: COMMENT.COMMENTER")
    public void setCommenter(Integer commenter) {
        this.commenter = commenter;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_CREATE")
    public Long getGmtCreate() {
        return gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_CREATE")
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_MODIFIED")
    public Long getGmtModified() {
        return gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.GMT_MODIFIED")
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.LIKE_COUNT")
    public Integer getLikeCount() {
        return likeCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.LIKE_COUNT")
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.48+08:00", comments="Source field: COMMENT.CONTENT")
    public String getContent() {
        return content;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source field: COMMENT.CONTENT")
    public void setContent(String content) {
        this.content = content;
    }
}