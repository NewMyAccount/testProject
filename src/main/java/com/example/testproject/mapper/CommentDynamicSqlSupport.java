package com.example.testproject.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CommentDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.164+08:00", comments="Source Table: COMMENT")
    public static final Comment comment = new Comment();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.ID")
    public static final SqlColumn<Long> id = comment.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.PARENT_ID")
    public static final SqlColumn<Long> parentId = comment.parentId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.TYPE")
    public static final SqlColumn<Integer> type = comment.type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.COMMENTER")
    public static final SqlColumn<Integer> commenter = comment.commenter;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.GMT_CREATE")
    public static final SqlColumn<Long> gmtCreate = comment.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.GMT_MODIFIED")
    public static final SqlColumn<Long> gmtModified = comment.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.LIKE_COUNT")
    public static final SqlColumn<Long> likeCount = comment.likeCount;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source field: COMMENT.CONTENT")
    public static final SqlColumn<String> content = comment.content;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-24T15:40:47.165+08:00", comments="Source Table: COMMENT")
    public static final class Comment extends SqlTable {
        public final SqlColumn<Long> id = column("ID", JDBCType.BIGINT);

        public final SqlColumn<Long> parentId = column("PARENT_ID", JDBCType.BIGINT);

        public final SqlColumn<Integer> type = column("TYPE", JDBCType.INTEGER);

        public final SqlColumn<Integer> commenter = column("COMMENTER", JDBCType.INTEGER);

        public final SqlColumn<Long> gmtCreate = column("GMT_CREATE", JDBCType.BIGINT);

        public final SqlColumn<Long> gmtModified = column("GMT_MODIFIED", JDBCType.BIGINT);

        public final SqlColumn<Long> likeCount = column("LIKE_COUNT", JDBCType.BIGINT);

        public final SqlColumn<String> content = column("CONTENT", JDBCType.VARCHAR);

        public Comment() {
            super("COMMENT");
        }
    }
}