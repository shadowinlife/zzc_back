package com.se.back.data.repo.db.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AccountDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.82+08:00", comments="Source Table: account")
    public static final Account account = new Account();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.82+08:00", comments="Source field: account.created_at")
    public static final SqlColumn<Date> createdAt = account.createdAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.updated_at")
    public static final SqlColumn<Date> updatedAt = account.updatedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.deleted_at")
    public static final SqlColumn<Date> deletedAt = account.deletedAt;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.id")
    public static final SqlColumn<Integer> id = account.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.phone")
    public static final SqlColumn<String> phone = account.phone;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.username")
    public static final SqlColumn<String> username = account.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.password")
    public static final SqlColumn<String> password = account.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.vip_time")
    public static final SqlColumn<Date> vipTime = account.vipTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.vip_type")
    public static final SqlColumn<String> vipType = account.vipType;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.last_login_ip")
    public static final SqlColumn<String> lastLoginIp = account.lastLoginIp;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.last_login_time")
    public static final SqlColumn<Date> lastLoginTime = account.lastLoginTime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.d_openid")
    public static final SqlColumn<String> dOpenid = account.dOpenid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.w_openid")
    public static final SqlColumn<String> wOpenid = account.wOpenid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.user_ver")
    public static final SqlColumn<Integer> userVer = account.userVer;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.one_week")
    public static final SqlColumn<Integer> oneWeek = account.oneWeek;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.apple_id")
    public static final SqlColumn<String> appleId = account.appleId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.821+08:00", comments="Source field: account.company")
    public static final SqlColumn<String> company = account.company;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source field: account.first_mobile")
    public static final SqlColumn<Integer> firstMobile = account.firstMobile;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.822+08:00", comments="Source field: account.w_unionid")
    public static final SqlColumn<String> wUnionid = account.wUnionid;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.82+08:00", comments="Source Table: account")
    public static final class Account extends SqlTable {
        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> deletedAt = column("deleted_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<Date> vipTime = column("vip_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> vipType = column("vip_type", JDBCType.VARCHAR);

        public final SqlColumn<String> lastLoginIp = column("last_login_ip", JDBCType.VARCHAR);

        public final SqlColumn<Date> lastLoginTime = column("last_login_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> dOpenid = column("d_openid", JDBCType.VARCHAR);

        public final SqlColumn<String> wOpenid = column("w_openid", JDBCType.VARCHAR);

        public final SqlColumn<Integer> userVer = column("user_ver", JDBCType.INTEGER);

        public final SqlColumn<Integer> oneWeek = column("one_week", JDBCType.INTEGER);

        public final SqlColumn<String> appleId = column("apple_id", JDBCType.VARCHAR);

        public final SqlColumn<String> company = column("company", JDBCType.VARCHAR);

        public final SqlColumn<Integer> firstMobile = column("first_mobile", JDBCType.INTEGER);

        public final SqlColumn<String> wUnionid = column("w_unionid", JDBCType.VARCHAR);

        public Account() {
            super("account");
        }
    }
}