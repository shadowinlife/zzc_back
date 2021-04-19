package com.se.back.controller.service.impl;

import com.se.back.controller.constant.RelationSearchConstant;
import com.se.back.controller.entity.vo.RelationShipVO;
import com.se.back.controller.enums.CompanyTypeEnum;
import com.se.back.controller.service.RelationSearchService;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author: 信长华
 * @date: 2021/4/19 15:37
 * @className: RelationSearchServiceImpl
 * @description: TODO
 * @version: 1.0
 */
@Service
public class RelationSearchServiceImpl implements RelationSearchService {
    private final Driver driver;

    public RelationSearchServiceImpl(Driver driver) {
        this.driver = driver;
    }

    @Override
    public List<List<RelationShipVO>> searchPathByCompany(String fromCompany, String toCompany, Integer pathLength) {

        String searchCql = String.format("MATCH " +
                        "(company1:Company{name:\"%s\"})," +
                        "(company2:Company{name:\"%s\"}), " +
                        "path=(company1)-[*..%d]-(company2)  " +
                        "return path, LENGTH(path) as distance ORDER BY distance ASC limit %d",
                fromCompany,
                toCompany,
                pathLength,
                RelationSearchConstant.QUERY_PATH_LIMIT
        );
        System.out.println("searchCql = " + searchCql);
        return searchPath(searchCql, fromCompany);
    }

    @Override
    public List<List<RelationShipVO>> searchPathByRegion(String fromCompany, String toCompany, Integer pathLength) {
        return null;
    }

    private List<List<RelationShipVO>> searchPath(String searchCql, String baseFromCompany) {
        List<List<RelationShipVO>> relationShipVOList = new ArrayList<>();
        Session session = driver.session();
        Result result = session.run(searchCql);
        List<Record> recordList = result.list();
        for (Record record : recordList) {

            List<RelationShipVO> relationShipVOSubList = new ArrayList<>();
            String fromCompany = baseFromCompany;
            Path path = record.get("path").asPath();
            for (Path.Segment segment : path) {
                Relationship relationship = segment.relationship();

                Node startNode = segment.start();
                String startCompany = startNode.get("name").asString();
                long startCompanyId = startNode.id();
                Integer startCompanyTypeId = Integer.valueOf(relationship.get("start").asString());
                CompanyTypeEnum startCompanyType = getCompanyTypeEnum(startCompanyTypeId);

                Node endNode = segment.end();
                String endCompany = endNode.get("name").asString();
                long endCompanyId = endNode.id();
                Integer endCompanyTypeId = Integer.valueOf(relationship.get("end").asString());
                CompanyTypeEnum endCompanyType = getCompanyTypeEnum(endCompanyTypeId);

                long relationShipId = relationship.id();
                String esIndex = relationship.get("index").asString();
                String esDocId = relationship.get("docId").asString();
                String publishTime = relationship.get("publishTime").asString();

                RelationShipVO relationShipVO = new RelationShipVO();
                relationShipVO.setEdgeId(relationShipId);
                relationShipVO.setElasticSearchIndexName(esIndex);
                relationShipVO.setElasticSearchDocId(esDocId);
                relationShipVO.setPublishTime(publishTime);
                if (Objects.equals(startCompany, fromCompany)) {
                    fromCompany = endCompany;

                    relationShipVO.setFromCompanyId(startCompanyId);
                    relationShipVO.setFromCompany(startCompany);
                    relationShipVO.setFromCompanyType(startCompanyType);

                    relationShipVO.setToCompanyId(endCompanyId);
                    relationShipVO.setToCompany(endCompany);
                    relationShipVO.setToCompanyType(endCompanyType);
                } else {
                    fromCompany = startCompany;

                    relationShipVO.setFromCompanyId(endCompanyId);
                    relationShipVO.setFromCompany(endCompany);
                    relationShipVO.setFromCompanyType(endCompanyType);

                    relationShipVO.setToCompanyId(startCompanyId);
                    relationShipVO.setToCompany(startCompany);
                    relationShipVO.setToCompanyType(startCompanyType);
                }
                relationShipVOSubList.add(relationShipVO);
            }
            relationShipVOList.add(relationShipVOSubList);
        }
        return relationShipVOList;
    }

    private CompanyTypeEnum getCompanyTypeEnum(Integer CompanyTypeId) {
        for (CompanyTypeEnum value : CompanyTypeEnum.values()) {
            if (Objects.equals(value.getCode(), CompanyTypeId)) {
                return value;
            }
        }
        return null;
    }
}
