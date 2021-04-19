package com.se.back.controller.controller;

import com.se.back.controller.Result;
import com.se.back.controller.constant.RelationSearchConstant;
import com.se.back.controller.entity.ao.RelationSearchAO;
import com.se.back.controller.entity.vo.RelationShipVO;
import com.se.back.controller.service.RelationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:41
 * @className: RelationController
 * @description: 公司关系查找, 通过一个公司或者某个地区所有公司查找具体的公司的路径
 * @version: 1.0
 */

@RestController
@RequestMapping("relation")
public class RelationController {
    @Autowired
    private RelationSearchService relationSearchService;

    /**
     * 使用neo4j, 查询从公司到公司的路径
     *
     * @param relationSearchAO
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Result<Object> search(@Valid @RequestBody RelationSearchAO relationSearchAO) {
        String fromCompany = relationSearchAO.getFromCompany();
        String toCompany = relationSearchAO.getToCompany();
        List<List<RelationShipVO>> relationShipVOList = new ArrayList<>();
        for (int pathLength = RelationSearchConstant.QUERY_PATH_LENGTH_MIN_BY_COMPANY;
             pathLength < RelationSearchConstant.QUERY_PATH_LENGTH_MAX_BY_COMPANY + 1; pathLength++) {
            relationShipVOList = relationSearchService.searchPathByCompany(fromCompany, toCompany, pathLength);
            if (relationShipVOList.size() > 1) {
                return Result.succResult(null, relationShipVOList);
            }
        }

        return Result.succResult(null, null);
    }
}
