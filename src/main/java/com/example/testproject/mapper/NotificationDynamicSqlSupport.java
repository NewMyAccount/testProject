package com.example.testproject.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class NotificationDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source Table: NOTIFICATION")
    public static final Notification notification = new Notification();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.ID")
    public static final SqlColumn<Integer> id = notification.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.NOTIFIER")
    public static final SqlColumn<Integer> notifier = notification.notifier;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.RECEIVER")
    public static final SqlColumn<Integer> receiver = notification.receiver;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source field: NOTIFICATION.OUTER_ID")
    public static final SqlColumn<Integer> outerId = notification.outerId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source field: NOTIFICATION.TYPE")
    public static final SqlColumn<Integer> type = notification.type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source field: NOTIFICATION.GMT_CREATE")
    public static final SqlColumn<Long> gmtCreate = notification.gmtCreate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source field: NOTIFICATION.STATUS")
    public static final SqlColumn<Integer> status = notification.status;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.056+08:00", comments="Source Table: NOTIFICATION")
    public static final class Notification extends SqlTable {
        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> notifier = column("NOTIFIER", JDBCType.INTEGER);

        public final SqlColumn<Integer> receiver = column("RECEIVER", JDBCType.INTEGER);

        public final SqlColumn<Integer> outerId = column("OUTER_ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> type = column("TYPE", JDBCType.INTEGER);

        public final SqlColumn<Long> gmtCreate = column("GMT_CREATE", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("STATUS", JDBCType.INTEGER);

        public Notification() {
            super("NOTIFICATION");
        }
    }
}