package com.se.back.controller.service.impl;

import com.se.back.controller.constant.RelationSearchConstant;
import com.se.back.controller.service.RelationSearchService;
import com.se.back.data.repo.neo4j.dataclass.RelationShipDTO;
import com.se.back.data.repo.neo4j.mapper.Neo4jRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * @author: 信长华
 * @date: 2021/4/19 15:37
 * @className: RelationSearchServiceImpl
 * @description: TODO
 * @version: 1.0
 */
@Slf4j
@Service
public class RelationSearchServiceImpl implements RelationSearchService {
    @Autowired
    private Neo4jRepository neo4jRepository;

    /**
     * @param fromCompany 起始公司
     * @param toCompany   终止公司
     * @param pathLength
     * @return
     */
    @Override
    public List<List<RelationShipDTO>> searchPathByCompany(String fromCompany, String toCompany, Integer pathLength) {

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
        return neo4jRepository.searchPath(searchCql);
    }

    @Override
    public List<List<RelationShipDTO>> searchPathByRegion(
            String fromProvince, String fromCity, String fromCounty, String toCompany, Integer pathLength) {
        String searchCql = null;
        if (StringUtils.isNotBlank(fromCounty)) {
            searchCql = String.format("MATCH " +
                            "(company1:Company{county:\"%s\"})," +
                            "(company2:Company{name:\"%s\"}), " +
                            "path=(company1)-[*..%d]-(company2)  " +
                            "return path, LENGTH(path) as distance ORDER BY distance ASC limit %d",
                    fromCounty,
                    toCompany,
                    pathLength,
                    RelationSearchConstant.QUERY_PATH_LIMIT
            );
        } else if (StringUtils.isNotBlank(fromCity)) {
            searchCql = String.format("MATCH " +
                            "(company1:Company{city:\"%s\"})," +
                            "(company2:Company{name:\"%s\"}), " +
                            "path=(company1)-[*..%d]-(company2)  " +
                            "return path, LENGTH(path) as distance ORDER BY distance ASC limit %d",
                    fromCity,
                    toCompany,
                    pathLength,
                    RelationSearchConstant.QUERY_PATH_LIMIT
            );
        } else if (StringUtils.isNotBlank(fromProvince)) {
            searchCql = String.format("MATCH " +
                            "(company1:Company{province:\"%s\"})," +
                            "(company2:Company{name:\"%s\"}), " +
                            "path=(company1)-[*..%d]-(company2)  " +
                            "return path, LENGTH(path) as distance ORDER BY distance ASC limit %d",
                    fromProvince,
                    toCompany,
                    pathLength,
                    RelationSearchConstant.QUERY_PATH_LIMIT
            );
        }

        if (searchCql == null) {
            return Collections.emptyList();
        }
        return neo4jRepository.searchPath(searchCql);
    }


}
