package com.se.back.data.repo.db.bean;

import javax.annotation.Generated;

public class AlembicVersion {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.783+08:00", comments="Source field: alembic_version.version_num")
    private String versionNum;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.786+08:00", comments="Source field: alembic_version.version_num")
    public String getVersionNum() {
        return versionNum;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.786+08:00", comments="Source field: alembic_version.version_num")
    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }
}