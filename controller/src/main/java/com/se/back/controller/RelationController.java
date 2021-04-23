package com.se.back.controller;

import com.se.back.common.ResponseEnum;
import com.se.back.common.Result;
import com.se.back.common.holder.RequestIdHolder;
import com.se.back.constant.RelationSearchConstant;
import com.se.back.entity.ao.RelationSearchAO;
import com.se.back.entity.vo.BasePageVO;
import com.se.back.data.repo.es.dataclass.RegionDTO;
import com.se.back.data.repo.neo4j.dataclass.RelationShipDTO;
import com.se.back.service.RegionService;
import com.se.back.service.RelationSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:41
 * @className: RelationController
 * @description: 公司关系查找, 通过一个公司或者某个地区所有公司查找具体的公司的路径
 * @version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("relation")
public class RelationController {
    private final RelationSearchService relationSearchService;
    private final RegionService regionService;

    public RelationController(RelationSearchService relationSearchService, RegionService regionService) {
        this.relationSearchService = relationSearchService;
        this.regionService = regionService;
    }

    /**
     * 使用neo4j, 查询从公司到公司的路径
     *
     * @param relationSearchAO
     * @return
     */
    @RequestMapping(value = "company", method = RequestMethod.POST)
    public Result<Object> searchPathByCompany(@Valid @RequestBody RelationSearchAO relationSearchAO) {
        String fromCompany = relationSearchAO.getFromCompany();
        String toCompany = relationSearchAO.getToCompany();
        String requestId = RequestIdHolder.getRequestId();
        for (int pathLength = RelationSearchConstant.QUERY_PATH_LENGTH_MIN_BY_COMPANY;
             pathLength <= RelationSearchConstant.QUERY_PATH_LENGTH_MAX_BY_COMPANY; pathLength++) {
            List<List<RelationShipDTO>> relationShipVOList = relationSearchService.searchPathByCompany(fromCompany, toCompany, pathLength);
            long length = relationShipVOList.size();
            if (length > 0) {
                BasePageVO<List<RelationShipDTO>> basePageVO = new BasePageVO<>();
                basePageVO.setRelationship(relationShipVOList);
                basePageVO.setTotalCount(length);
                return Result.successResult(requestId, basePageVO);
            }
        }

        return Result.successResult(requestId, null);
    }

    /**
     * 查询地区到公司的路径
     *
     * @param relationSearchAO
     * @return
     */
    @RequestMapping(value = "region", method = RequestMethod.POST)
    public Result<Object> searchPathByRegion(@Valid @RequestBody RelationSearchAO relationSearchAO) throws IOException {
        String fromRegion = relationSearchAO.getFromCompany();
        String toCompany = relationSearchAO.getToCompany();
        String requestId = RequestIdHolder.getRequestId();
        // 获取详细的省市县信息
        RegionDTO regionDTO = regionService.makeRegionDto(fromRegion);
        if (regionDTO == null) {
            return Result.errResult(null, ResponseEnum.RELATION_NO_REGION);
        }
        log.error("fuck-123--hot234reload---fuck");
        String province = regionDTO.getProvince();
        String city = regionDTO.getCity();
        String county = regionDTO.getCounty();
        for (int pathLength = RelationSearchConstant.QUERY_PATH_LENGTH_MIN_BY_REGION;
             pathLength <= RelationSearchConstant.QUERY_PATH_LENGTH_MAX_BY_REGION; pathLength++) {
            List<List<RelationShipDTO>> relationShipVOList = relationSearchService.searchPathByRegion(
                    province, city, county, toCompany, pathLength);
            long length = relationShipVOList.size();
            if (length > 0) {
                BasePageVO<List<RelationShipDTO>> basePageVO = new BasePageVO<>();
                basePageVO.setRelationship(relationShipVOList);
                basePageVO.setTotalCount(length);
                return Result.successResult(requestId, basePageVO);
            }
        }

        return Result.successResult(requestId, null);
    }

}
