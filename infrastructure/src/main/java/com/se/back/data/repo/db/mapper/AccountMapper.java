package com.se.back.data.repo.db.mapper;

import static com.se.back.data.repo.db.mapper.AccountDynamicSqlSupport.*;

import com.se.back.data.repo.db.bean.Account;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
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
public interface AccountMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    BasicColumn[] selectList = BasicColumn.columnList(createdAt, updatedAt, deletedAt, id, phone, username, password, vipTime, vipType, lastLoginIp, lastLoginTime, dOpenid, wOpenid, userVer, oneWeek, appleId, company, firstMobile, wUnionid);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<Account> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AccountResult")
    Optional<Account> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AccountResult", value = {
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="deleted_at", property="deletedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="vip_time", property="vipTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="vip_type", property="vipType", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_ip", property="lastLoginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="d_openid", property="dOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="w_openid", property="wOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_ver", property="userVer", jdbcType=JdbcType.INTEGER),
        @Result(column="one_week", property="oneWeek", jdbcType=JdbcType.INTEGER),
        @Result(column="apple_id", property="appleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="company", property="company", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_mobile", property="firstMobile", jdbcType=JdbcType.INTEGER),
        @Result(column="w_unionid", property="wUnionid", jdbcType=JdbcType.VARCHAR)
    })
    List<Account> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default int insert(Account record) {
        return MyBatis3Utils.insert(this::insert, record, account, c ->
            c.map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(deletedAt).toProperty("deletedAt")
            .map(phone).toProperty("phone")
            .map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(vipTime).toProperty("vipTime")
            .map(vipType).toProperty("vipType")
            .map(lastLoginIp).toProperty("lastLoginIp")
            .map(lastLoginTime).toProperty("lastLoginTime")
            .map(dOpenid).toProperty("dOpenid")
            .map(wOpenid).toProperty("wOpenid")
            .map(userVer).toProperty("userVer")
            .map(oneWeek).toProperty("oneWeek")
            .map(appleId).toProperty("appleId")
            .map(company).toProperty("company")
            .map(firstMobile).toProperty("firstMobile")
            .map(wUnionid).toProperty("wUnionid")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default int insertSelective(Account record) {
        return MyBatis3Utils.insert(this::insert, record, account, c ->
            c.map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(deletedAt).toPropertyWhenPresent("deletedAt", record::getDeletedAt)
            .map(phone).toPropertyWhenPresent("phone", record::getPhone)
            .map(username).toPropertyWhenPresent("username", record::getUsername)
            .map(password).toPropertyWhenPresent("password", record::getPassword)
            .map(vipTime).toPropertyWhenPresent("vipTime", record::getVipTime)
            .map(vipType).toPropertyWhenPresent("vipType", record::getVipType)
            .map(lastLoginIp).toPropertyWhenPresent("lastLoginIp", record::getLastLoginIp)
            .map(lastLoginTime).toPropertyWhenPresent("lastLoginTime", record::getLastLoginTime)
            .map(dOpenid).toPropertyWhenPresent("dOpenid", record::getdOpenid)
            .map(wOpenid).toPropertyWhenPresent("wOpenid", record::getwOpenid)
            .map(userVer).toPropertyWhenPresent("userVer", record::getUserVer)
            .map(oneWeek).toPropertyWhenPresent("oneWeek", record::getOneWeek)
            .map(appleId).toPropertyWhenPresent("appleId", record::getAppleId)
            .map(company).toPropertyWhenPresent("company", record::getCompany)
            .map(firstMobile).toPropertyWhenPresent("firstMobile", record::getFirstMobile)
            .map(wUnionid).toPropertyWhenPresent("wUnionid", record::getwUnionid)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default Optional<Account> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default List<Account> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default List<Account> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, account, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source Table: account")
    static UpdateDSL<UpdateModel> updateAllColumns(Account record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(deletedAt).equalTo(record::getDeletedAt)
                .set(phone).equalTo(record::getPhone)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(vipTime).equalTo(record::getVipTime)
                .set(vipType).equalTo(record::getVipType)
                .set(lastLoginIp).equalTo(record::getLastLoginIp)
                .set(lastLoginTime).equalTo(record::getLastLoginTime)
                .set(dOpenid).equalTo(record::getdOpenid)
                .set(wOpenid).equalTo(record::getwOpenid)
                .set(userVer).equalTo(record::getUserVer)
                .set(oneWeek).equalTo(record::getOneWeek)
                .set(appleId).equalTo(record::getAppleId)
                .set(company).equalTo(record::getCompany)
                .set(firstMobile).equalTo(record::getFirstMobile)
                .set(wUnionid).equalTo(record::getwUnionid);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.823+08:00", comments="Source Table: account")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Account record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(deletedAt).equalToWhenPresent(record::getDeletedAt)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(vipTime).equalToWhenPresent(record::getVipTime)
                .set(vipType).equalToWhenPresent(record::getVipType)
                .set(lastLoginIp).equalToWhenPresent(record::getLastLoginIp)
                .set(lastLoginTime).equalToWhenPresent(record::getLastLoginTime)
                .set(dOpenid).equalToWhenPresent(record::getdOpenid)
                .set(wOpenid).equalToWhenPresent(record::getwOpenid)
                .set(userVer).equalToWhenPresent(record::getUserVer)
                .set(oneWeek).equalToWhenPresent(record::getOneWeek)
                .set(appleId).equalToWhenPresent(record::getAppleId)
                .set(company).equalToWhenPresent(record::getCompany)
                .set(firstMobile).equalToWhenPresent(record::getFirstMobile)
                .set(wUnionid).equalToWhenPresent(record::getwUnionid);
    }
}