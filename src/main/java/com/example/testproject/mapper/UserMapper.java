package com.example.testproject.mapper;

import static com.example.testproject.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.example.testproject.model.User;
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
public interface UserMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.459+08:00", comments="Source Table: USER")
    BasicColumn[] selectList = BasicColumn.columnList(id, accountId, name, token, gmtCreate, gmtModified, bio, avatarUrl);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.444+08:00", comments="Source Table: USER")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.445+08:00", comments="Source Table: USER")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.446+08:00", comments="Source Table: USER")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.447+08:00", comments="Source Table: USER")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.447+08:00", comments="Source Table: USER")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.448+08:00", comments="Source Table: USER")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ACCOUNT_ID", property="accountId", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="TOKEN", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="GMT_CREATE", property="gmtCreate", jdbcType=JdbcType.BIGINT),
        @Result(column="GMT_MODIFIED", property="gmtModified", jdbcType=JdbcType.BIGINT),
        @Result(column="BIO", property="bio", jdbcType=JdbcType.VARCHAR),
        @Result(column="AVATAR_URL", property="avatarUrl", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.45+08:00", comments="Source Table: USER")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.45+08:00", comments="Source Table: USER")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.451+08:00", comments="Source Table: USER")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.451+08:00", comments="Source Table: USER")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.452+08:00", comments="Source Table: USER")
    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toProperty("id")
            .map(accountId).toProperty("accountId")
            .map(name).toProperty("name")
            .map(token).toProperty("token")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(bio).toProperty("bio")
            .map(avatarUrl).toProperty("avatarUrl")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.454+08:00", comments="Source Table: USER")
    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
            c.map(id).toProperty("id")
            .map(accountId).toProperty("accountId")
            .map(name).toProperty("name")
            .map(token).toProperty("token")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
            .map(bio).toProperty("bio")
            .map(avatarUrl).toProperty("avatarUrl")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.455+08:00", comments="Source Table: USER")
    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(accountId).toPropertyWhenPresent("accountId", record::getAccountId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(token).toPropertyWhenPresent("token", record::getToken)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtModified).toPropertyWhenPresent("gmtModified", record::getGmtModified)
            .map(bio).toPropertyWhenPresent("bio", record::getBio)
            .map(avatarUrl).toPropertyWhenPresent("avatarUrl", record::getAvatarUrl)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.46+08:00", comments="Source Table: USER")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.46+08:00", comments="Source Table: USER")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.461+08:00", comments="Source Table: USER")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.461+08:00", comments="Source Table: USER")
    default Optional<User> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.462+08:00", comments="Source Table: USER")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.462+08:00", comments="Source Table: USER")
    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(accountId).equalTo(record::getAccountId)
                .set(name).equalTo(record::getName)
                .set(token).equalTo(record::getToken)
                .set(gmtCreate).equalTo(record::getGmtCreate)
                .set(gmtModified).equalTo(record::getGmtModified)
                .set(bio).equalTo(record::getBio)
                .set(avatarUrl).equalTo(record::getAvatarUrl);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.463+08:00", comments="Source Table: USER")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(accountId).equalToWhenPresent(record::getAccountId)
                .set(name).equalToWhenPresent(record::getName)
                .set(token).equalToWhenPresent(record::getToken)
                .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
                .set(gmtModified).equalToWhenPresent(record::getGmtModified)
                .set(bio).equalToWhenPresent(record::getBio)
                .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.464+08:00", comments="Source Table: USER")
    default int updateByPrimaryKey(User record) {
        return update(c ->
            c.set(accountId).equalTo(record::getAccountId)
            .set(name).equalTo(record::getName)
            .set(token).equalTo(record::getToken)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtModified).equalTo(record::getGmtModified)
            .set(bio).equalTo(record::getBio)
            .set(avatarUrl).equalTo(record::getAvatarUrl)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-08-25T18:56:52.465+08:00", comments="Source Table: USER")
    default int updateByPrimaryKeySelective(User record) {
        return update(c ->
            c.set(accountId).equalToWhenPresent(record::getAccountId)
            .set(name).equalToWhenPresent(record::getName)
            .set(token).equalToWhenPresent(record::getToken)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtModified).equalToWhenPresent(record::getGmtModified)
            .set(bio).equalToWhenPresent(record::getBio)
            .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl)
            .where(id, isEqualTo(record::getId))
        );
    }
}