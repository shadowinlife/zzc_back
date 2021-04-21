package com.se.back.data.repo.es.dataclass;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xin
 */
public class RegionDTO {
    private String region;
    private String alias;
    private String province;
    @JsonProperty(value = "province_alias")
    private String provinceAlias;
    private String city;
    @JsonProperty(value = "city_alias")
    private String cityAlias;
    private String county;


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceAlias() {
        return provinceAlias;
    }

    public void setProvinceAlias(String provinceAlias) {
        this.provinceAlias = provinceAlias;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityAlias() {
        return cityAlias;
    }

    public void setCityAlias(String cityAlias) {
        this.cityAlias = cityAlias;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "RegionDTO{" +
                "region='" + region + '\'' +
                ", alias='" + alias + '\'' +
                ", province='" + province + '\'' +
                ", provinceAlias='" + provinceAlias + '\'' +
                ", city='" + city + '\'' +
                ", cityAlias='" + cityAlias + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
