package com.se.back.data.repo.db.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AlembicVersionDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.79+08:00", comments="Source Table: alembic_version")
    public static final AlembicVersion alembicVersion = new AlembicVersion();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.79+08:00", comments="Source field: alembic_version.version_num")
    public static final SqlColumn<String> versionNum = alembicVersion.versionNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.79+08:00", comments="Source Table: alembic_version")
    public static final class AlembicVersion extends SqlTable {
        public final SqlColumn<String> versionNum = column("version_num", JDBCType.VARCHAR);

        public AlembicVersion() {
            super("alembic_version");
        }
    }
}