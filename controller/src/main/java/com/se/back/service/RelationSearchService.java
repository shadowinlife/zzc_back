package com.se.back.service;

import com.se.back.data.repo.neo4j.dataclass.RelationShipDTO;

import java.util.List;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:56
 * @className: RelationSearchService
 * @description: TODO
 * @version: 1.0
 */

public interface RelationSearchService {
    /**
     * 公司到公司的路径
     *
     * @param fromCompany 起始公司
     * @param toCompany   终止公司
     * @return
     */
    List<List<RelationShipDTO>> searchPathByCompany(String fromCompany, String toCompany, Integer pathLength);

    /**
     * 地区到公司的路径
     */
    List<List<RelationShipDTO>> searchPathByRegion(String fromProvince, String fromCity, String fromCounty, String toCompany, Integer pathLength);
}
