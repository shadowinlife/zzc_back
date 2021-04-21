package com.se.back.data.repo.neo4j.dataclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.se.back.data.enums.CompanyTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:58
 * @className: RelationShip
 * @description: TODO
 * @version: 1.0
 */
@Data
public class RelationShipDTO implements Serializable {

    private static final long serialVersionUID = -7439512793662106124L;
    /**
     * 边或者关系在neo4j的id, 由neo4j自动生成
     */
    @JsonProperty(value = "e_id")
    private Long edgeId;

    /**
     * 此关系的标讯在es中的索引
     */
    @JsonProperty(value = "index")
    private String ElasticSearchIndexName;
    /**
     * 此关系的标讯在es中的docId
     */
    @JsonProperty(value = "doc_id")
    private String elasticSearchDocId;

    /**
     * 此关系的标讯在es index加密的数据
     */
    @JsonProperty(value = "sign")
    private String sign;

    /**
     * 此关系的标讯的发布时间
     */
    @JsonProperty(value = "publish_time")
    private String publishTime;

    /**
     * 起始公司
     */
    @JsonProperty(value = "from")
    private String fromCompany;
    /**
     * 起始公司的id, 由neo4j自动生成
     */
    @JsonProperty(value = "from_id")
    private Long fromCompanyId;

    /**
     * 起始公司的身份; 只有3种, 从ENUM中获取
     */
    @JsonProperty(value = "from_type")
    private CompanyTypeEnum fromCompanyType;

    /**
     * 终止公司
     */
    @JsonProperty(value = "to")
    private String toCompany;
    /**
     * 终止公司的id, 由neo4j自动生成
     */
    @JsonProperty(value = "to_id")
    private Long toCompanyId;

    /**
     * 终止公司身份
     */
    @JsonProperty(value = "to_type")
    private CompanyTypeEnum toCompanyType;

}
