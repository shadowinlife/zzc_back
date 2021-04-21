package com.se.back.data.repo.neo4j.mapper;

import com.se.back.data.enums.CompanyTypeEnum;
import com.se.back.data.repo.neo4j.dataclass.RelationShipDTO;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: 信长华
 * @date: 2021/4/20 12:36
 * @className: Neo4jRepository
 * @description: TODO
 * @version: 1.0
 */
@Slf4j
@Repository
public class Neo4jRepository {
    private final Driver driver;

    public Neo4jRepository(Driver driver) {
        this.driver = driver;
    }

    /**
     * 查找路径, 用原生CQL语句
     * https://dzone.com/articles/mapping-a-path-query-in-spring-data-neo4j
     * spring-boot-neo4j-starter-data 不支持多关系路径的查询.
     * <p>
     * 官方文档 https://neo4j.com/developer/java-driver-spring-boot-starter/
     *
     * @param searchCql
     * @return
     */
//    @Retryable(value = Exception.class)
    public List<List<RelationShipDTO>> searchPath(String searchCql) {
        log.info("op=RecordSearchCql, searchCql=[{}]", searchCql);
        // 拼接的返回值
        List<List<RelationShipDTO>> relationShipDTOList = new ArrayList<>();
        // 执行具体的cql语句
        Session session = driver.session();
        Result result = session.run(searchCql);
        // 转成recordList
        List<Record> recordList = result.list();
        // 遍历recordList
        for (Record record : recordList) {

            List<RelationShipDTO> relationShipDTOSubList = new ArrayList<>();
            // 获取neo4j的path
            Path path = record.get("path").asPath();
            // 遍历path, 获取每一条边
            for (Path.Segment segment : path) {
                // 每个segment 由 开始节点 结束节点 和关系组成
                // 关系的startNodeId() 跟 开始节点的neo4jId未必相等, 因为关系是有方向的.

                // 关系, 也就是边
                Relationship relationship = segment.relationship();
                long relationShipStartNodeId = relationship.startNodeId();
                long relationShipId = relationship.id();
                String esIndex = relationship.get("index").asString();
                String esDocId = relationship.get("docId").asString();
                String publishTime = relationship.get("publishTime").asString();

                // 开始节点
                Node startNode = segment.start();
                String startCompany = startNode.get("name").asString();
                long startCompanyId = startNode.id();


                // 结束节点
                Node endNode = segment.end();
                String endCompany = endNode.get("name").asString();
                long endCompanyId = endNode.id();

                // relationShip的StartNodeId 跟 startCompanyId是否相等判断公司的身份
                Integer startCompanyTypeId = Integer.valueOf(relationship.get("start").asString());
                Integer endCompanyTypeId = Integer.valueOf(relationship.get("end").asString());
                CompanyTypeEnum endCompanyType;
                CompanyTypeEnum startCompanyType;
                if (Objects.equals(relationShipStartNodeId, startCompanyId)) {
                    startCompanyType = getCompanyTypeEnum(startCompanyTypeId);
                    endCompanyType = getCompanyTypeEnum(endCompanyTypeId);
                } else {
                    startCompanyType = getCompanyTypeEnum(endCompanyTypeId);
                    endCompanyType = getCompanyTypeEnum(startCompanyTypeId);
                }

                // 拼接RelationShipDTO
                RelationShipDTO relationShipDTO = new RelationShipDTO();
                relationShipDTO.setEdgeId(relationShipId);
                relationShipDTO.setElasticSearchIndexName(esIndex);
                relationShipDTO.setElasticSearchDocId(esDocId);
                relationShipDTO.setPublishTime(publishTime);

                relationShipDTO.setFromCompanyId(startCompanyId);
                relationShipDTO.setFromCompany(startCompany);
                relationShipDTO.setFromCompanyType(startCompanyType);

                relationShipDTO.setToCompanyId(endCompanyId);
                relationShipDTO.setToCompany(endCompany);
                relationShipDTO.setToCompanyType(endCompanyType);

                relationShipDTOSubList.add(relationShipDTO);
            }
            relationShipDTOList.add(relationShipDTOSubList);
        }
        return relationShipDTOList;
    }

    /**
     * 获取公司身份的枚举
     *
     * @param CompanyTypeId
     * @return
     */
    private CompanyTypeEnum getCompanyTypeEnum(Integer CompanyTypeId) {
        for (CompanyTypeEnum value : CompanyTypeEnum.values()) {
            if (Objects.equals(value.getCode(), CompanyTypeId)) {
                return value;
            }
        }
        return null;
    }
}
