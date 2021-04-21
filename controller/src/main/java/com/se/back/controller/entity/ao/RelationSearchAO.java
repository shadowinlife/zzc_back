package com.se.back.controller.entity.ao;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author: 信长华
 * @date: 2021/4/19 14:46
 * @className: RelationSearchAO
 * @description: 关系查找的AO
 * @version: 1.0
 */

public class RelationSearchAO implements Serializable {

    private static final long serialVersionUID = 2840135232665084758L;

    @NotNull(message = "源公司或者地区不能为空")
    @JsonProperty(value = "from")
    private String fromCompany;

    @NotNull(message = "目标公司不能为空")
    @JsonProperty(value = "to")
    private String toCompany;

    public String getFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(String fromCompany) {
        this.fromCompany = fromCompany;
    }

    public String getToCompany() {
        return toCompany;
    }

    public void setToCompany(String toCompany) {
        this.toCompany = toCompany;
    }

    @Override
    public String toString() {
        return "RelationSearchAO{" +
                "fromCompany='" + fromCompany + '\'' +
                ", toCompany='" + toCompany + '\'' +
                '}';
    }
}
