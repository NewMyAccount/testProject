package com.example.testproject.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CommentDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source Table: COMMENT")
    public static final Comment comment = new Comment();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source field: COMMENT.ID")
    public static final SqlColumn<Integer> id = comment.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source field: COMMENT.PARENT_ID")
    public static final SqlColumn<Integer> parentId = comment.parentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source field: COMMENT.TYPE")
    public static final SqlColumn<Integer> type = comment.type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.482+08:00", comments="Source field: COMMENT.COMMENTER")
    public static final SqlColumn<Integer> commenter = comment.commenter;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.482+08:00", comments="Source field: COMMENT.GMT_CREATE")
    public static final SqlColumn<Long> gmtCreate = comment.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.482+08:00", comments="Source field: COMMENT.GMT_MODIFIED")
    public static final SqlColumn<Long> gmtModified = comment.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.482+08:00", comments="Source field: COMMENT.LIKE_COUNT")
    public static final SqlColumn<Integer> likeCount = comment.likeCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.482+08:00", comments="Source field: COMMENT.CONTENT")
    public static final SqlColumn<String> content = comment.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.481+08:00", comments="Source Table: COMMENT")
    public static final class Comment extends SqlTable {
        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> parentId = column("PARENT_ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> type = column("TYPE", JDBCType.INTEGER);

        public final SqlColumn<Integer> commenter = column("COMMENTER", JDBCType.INTEGER);

        public final SqlColumn<Long> gmtCreate = column("GMT_CREATE", JDBCType.BIGINT);

        public final SqlColumn<Long> gmtModified = column("GMT_MODIFIED", JDBCType.BIGINT);

        public final SqlColumn<Integer> likeCount = column("LIKE_COUNT", JDBCType.INTEGER);

        public final SqlColumn<String> content = column("CONTENT", JDBCType.VARCHAR);

        public Comment() {
            super("COMMENT");
        }
    }
}