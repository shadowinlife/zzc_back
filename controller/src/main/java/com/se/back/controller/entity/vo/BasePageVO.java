package com.se.back.controller.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 信长华
 * @date: 2021/4/20 9:57
 * @className: BasePageVO
 * @description: TODO
 * @version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePageVO<E> implements Serializable {
    private static final long serialVersionUID = -4015567037999686062L;

    // 总数据条数
    @JsonProperty(value = "total")
    private Long totalCount;
    // 最大页数
    private Long totalPage;
    // 当前页数
    private Integer currentPage;
    // 当前页数据数
    private Integer pageSize;
    @JsonProperty(value = "result")
    private List<E> result;
    @JsonProperty(value = "marked")
    private List<E> marked;
    @JsonProperty(value = "unmarked")
    private List<E> unmarked;
    @JsonProperty(value = "relationships")
    private List<E> relationship;


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public List<E> getMarked() {
        return marked;
    }

    public void setMarked(List<E> marked) {
        this.marked = marked;
    }

    public List<E> getUnmarked() {
        return unmarked;
    }

    public void setUnmarked(List<E> unmarked) {
        this.unmarked = unmarked;
    }

    public List<E> getRelationship() {
        return relationship;
    }

    public void setRelationship(List<E> relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "BasePageVO{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", result=" + result +
                ", marked=" + marked +
                ", unmarked=" + unmarked +
                ", relationship=" + relationship +
                '}';
    }
}
