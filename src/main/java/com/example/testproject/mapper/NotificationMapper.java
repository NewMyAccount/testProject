package com.example.testproject.mapper;

import static com.example.testproject.mapper.NotificationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.example.testproject.model.Notification;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface NotificationMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    BasicColumn[] selectList = BasicColumn.columnList(id, notifier, receiver, outerId, type, gmtCreate, status);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Notification> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Notification> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NotificationResult")
    Optional<Notification> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NotificationResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="NOTIFIER", property="notifier", jdbcType=JdbcType.INTEGER),
        @Result(column="RECEIVER", property="receiver", jdbcType=JdbcType.INTEGER),
        @Result(column="OUTER_ID", property="outerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="GMT_CREATE", property="gmtCreate", jdbcType=JdbcType.BIGINT),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Notification> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default int insert(Notification record) {
        return MyBatis3Utils.insert(this::insert, record, notification, c ->
            c.map(id).toProperty("id")
            .map(notifier).toProperty("notifier")
            .map(receiver).toProperty("receiver")
            .map(outerId).toProperty("outerId")
            .map(type).toProperty("type")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(status).toProperty("status")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default int insertMultiple(Collection<Notification> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, notification, c ->
            c.map(id).toProperty("id")
            .map(notifier).toProperty("notifier")
            .map(receiver).toProperty("receiver")
            .map(outerId).toProperty("outerId")
            .map(type).toProperty("type")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(status).toProperty("status")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default int insertSelective(Notification record) {
        return MyBatis3Utils.insert(this::insert, record, notification, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(notifier).toPropertyWhenPresent("notifier", record::getNotifier)
            .map(receiver).toPropertyWhenPresent("receiver", record::getReceiver)
            .map(outerId).toPropertyWhenPresent("outerId", record::getOuterId)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.057+08:00", comments="Source Table: NOTIFICATION")
    default Optional<Notification> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default List<Notification> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default List<Notification> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default Optional<Notification> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, notification, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    static UpdateDSL<UpdateModel> updateAllColumns(Notification record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(notifier).equalTo(record::getNotifier)
                .set(receiver).equalTo(record::getReceiver)
                .set(outerId).equalTo(record::getOuterId)
                .set(type).equalTo(record::getType)
                .set(gmtCreate).equalTo(record::getGmtCreate)
                .set(status).equalTo(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Notification record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(notifier).equalToWhenPresent(record::getNotifier)
                .set(receiver).equalToWhenPresent(record::getReceiver)
                .set(outerId).equalToWhenPresent(record::getOuterId)
                .set(type).equalToWhenPresent(record::getType)
                .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default int updateByPrimaryKey(Notification record) {
        return update(c ->
            c.set(notifier).equalTo(record::getNotifier)
            .set(receiver).equalTo(record::getReceiver)
            .set(outerId).equalTo(record::getOuterId)
            .set(type).equalTo(record::getType)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(status).equalTo(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-11-02T10:53:46.058+08:00", comments="Source Table: NOTIFICATION")
    default int updateByPrimaryKeySelective(Notification record) {
        return update(c ->
            c.set(notifier).equalToWhenPresent(record::getNotifier)
            .set(receiver).equalToWhenPresent(record::getReceiver)
            .set(outerId).equalToWhenPresent(record::getOuterId)
            .set(type).equalToWhenPresent(record::getType)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(status).equalToWhenPresent(record::getStatus)
            .where(id, isEqualTo(record::getId))
        );
    }
}