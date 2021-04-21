package com.se.back.data.repo.db.mapper;

import static com.se.back.data.repo.db.mapper.AlembicVersionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.se.back.data.repo.db.dataclass.AlembicVersion;
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
public interface AlembicVersionMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.799+08:00", comments="Source Table: alembic_version")
    BasicColumn[] selectList = BasicColumn.columnList(versionNum);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.791+08:00", comments="Source Table: alembic_version")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.792+08:00", comments="Source Table: alembic_version")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.792+08:00", comments="Source Table: alembic_version")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<AlembicVersion> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.794+08:00", comments="Source Table: alembic_version")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("AlembicVersionResult")
    Optional<AlembicVersion> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.795+08:00", comments="Source Table: alembic_version")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="AlembicVersionResult", value = {
        @Result(column="version_num", property="versionNum", jdbcType=JdbcType.VARCHAR, id=true)
    })
    List<AlembicVersion> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.795+08:00", comments="Source Table: alembic_version")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.796+08:00", comments="Source Table: alembic_version")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.796+08:00", comments="Source Table: alembic_version")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.796+08:00", comments="Source Table: alembic_version")
    default int deleteByPrimaryKey(String versionNum_) {
        return delete(c -> 
            c.where(versionNum, isEqualTo(versionNum_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.797+08:00", comments="Source Table: alembic_version")
    default int insert(AlembicVersion record) {
        return MyBatis3Utils.insert(this::insert, record, alembicVersion, c ->
            c.map(versionNum).toProperty("versionNum")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.798+08:00", comments="Source Table: alembic_version")
    default int insertSelective(AlembicVersion record) {
        return MyBatis3Utils.insert(this::insert, record, alembicVersion, c ->
            c.map(versionNum).toPropertyWhenPresent("versionNum", record::getVersionNum)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.8+08:00", comments="Source Table: alembic_version")
    default Optional<AlembicVersion> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.8+08:00", comments="Source Table: alembic_version")
    default List<AlembicVersion> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.8+08:00", comments="Source Table: alembic_version")
    default List<AlembicVersion> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.801+08:00", comments="Source Table: alembic_version")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, alembicVersion, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.801+08:00", comments="Source Table: alembic_version")
    static UpdateDSL<UpdateModel> updateAllColumns(AlembicVersion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(versionNum).equalTo(record::getVersionNum);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-16T17:15:05.802+08:00", comments="Source Table: alembic_version")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(AlembicVersion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(versionNum).equalToWhenPresent(record::getVersionNum);
    }
}