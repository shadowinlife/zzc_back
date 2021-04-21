package com.se.back.controller.service.impl;

import com.se.back.controller.constant.ElasticSearchConstant;
import com.se.back.controller.service.RegionService;
import com.se.back.data.repo.es.dataclass.RegionDTO;
import com.se.back.data.repo.es.repo.RegionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: 信长华
 * @date: 2021/4/20 13:35
 * @className: RegionServiceImpl
 * @description: TODO
 * @version: 1.0
 */
@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public RegionDTO makeRegionDto(String originRegion) throws IOException {
        // 判定县区
        List<RegionDTO> regionDTOListByCounty = regionRepository.searchStringQuery(
                ElasticSearchConstant.SNIFFER_COUNTY_INDEX_NAME, originRegion, RegionDTO.class);
        List<RegionDTO> sameCountyAliasRegionDTO = new ArrayList<>();
        for (RegionDTO regionDTO : regionDTOListByCounty) {

            String regionName = regionDTO.getRegion();

            // 类似于 商河县, 具有唯一性, 直接返回
            if (StringUtils.contains(originRegion, regionName)) {
                return regionDTO;
            }
            String countyAlias = regionDTO.getAlias();
            if (StringUtils.isEmpty(countyAlias)) {
                continue;
            }
            String cityAlias = regionDTO.getCityAlias();
            String provinceAlias = regionDTO.getProvinceAlias();

            // 类似于 济南商河, 山东商河, 山东省 商河, 济南市商河, 也具有唯一性, 直接返回
            if (StringUtils.contains(originRegion, countyAlias)) {
                if (StringUtils.contains(originRegion, provinceAlias)) {
                    return regionDTO;
                }
                if (StringUtils.isNotBlank(cityAlias) && StringUtils.contains(originRegion, cityAlias)) {
                    return regionDTO;
                }
                sameCountyAliasRegionDTO.add(regionDTO);
            }
        }
        // 当县区简称只有一个的时候认为是这个县区
        if (sameCountyAliasRegionDTO.size() == 1) {
            return sameCountyAliasRegionDTO.get(0);
        }

        // 判定地级市
        List<RegionDTO> regionDTOListByCity = regionRepository.searchStringQuery(
                ElasticSearchConstant.SNIFFER_CITY_INDEX_NAME, originRegion, RegionDTO.class);
        for (RegionDTO regionDTO : regionDTOListByCity) {
            System.out.println("regionDTO = " + regionDTO);
            String region = regionDTO.getRegion();
            if (StringUtils.isEmpty(region)) {
                continue;
            }
            if (StringUtils.contains(originRegion, region)) {
                return regionDTO;
            }
            String cityAlias = regionDTO.getAlias();
            System.out.println("cityAlias = " + cityAlias);
            if (StringUtils.isEmpty(cityAlias)) {
                continue;
            }
            System.out.println("cityAlias = " + cityAlias);
            if (StringUtils.contains(originRegion, cityAlias)) {
                regionDTO = filterNoiseCity(regionDTO, originRegion);
                if (regionDTO != null) {
                    return regionDTO;
                }
            }
        }

        // 判断省
        List<RegionDTO> regionDTOListByProvince = regionRepository.searchStringQuery(
                ElasticSearchConstant.SNIFFER_PROVINCE_INDEX_NAME, originRegion, RegionDTO.class);
        for (RegionDTO regionDTO : regionDTOListByProvince) {
            String provinceAlias = regionDTO.getAlias();
            if (StringUtils.isEmpty(provinceAlias)) {
                continue;
            }
            if (StringUtils.contains(originRegion, provinceAlias)) {
                return regionDTO;
            }

        }

        return null;
    }

    /**
     * 过滤掉 大同区商业公司, 可能隶属于 山西大同市 也可能隶属于 黑龙江大庆市 大同区的情况
     *
     * @param regionDTO
     * @param regionName
     * @return
     */
    private RegionDTO filterNoiseCity(RegionDTO regionDTO, String regionName) {
        RegionDTO resultRegionDTO = new RegionDTO();
        String cityName = regionDTO.getCity();
        if (Objects.equals(cityName, "大同市") && StringUtils.contains(regionName, "大同区")) {
            return null;
        }
        if (Objects.equals(cityName, "大连市") && StringUtils.contains(regionName, "五大连池")) {
            return null;
        }
        if (Objects.equals(cityName, "眉山市") && StringUtils.contains(regionName, "峨眉山")) {
            return null;
        }
        if (Objects.equals(cityName, "鞍山市") &&
                (StringUtils.contains(regionName, "宿州马鞍山") ||
                        StringUtils.contains(regionName, "宿州市马鞍山"))) {
            resultRegionDTO.setCity("宿州市");
            resultRegionDTO.setProvince("安徽省");
            return resultRegionDTO;
        }
        if (Objects.equals(cityName, "马鞍山市") &&
                (StringUtils.contains(regionName, "宿州马鞍山") ||
                        StringUtils.contains(regionName, "宿州市马鞍山"))) {
            resultRegionDTO.setCity("宿州市");
            resultRegionDTO.setProvince("安徽省");
            return resultRegionDTO;
        }
        if (Objects.equals("合肥市", cityName) && (StringUtils.contains(regionName, "阜阳合肥") || StringUtils.contains(regionName, "阜阳市合肥"))) {
            resultRegionDTO.setProvince("安徽省");
            resultRegionDTO.setCity("阜阳市");
        }
        if (Objects.equals("中山市", cityName) && StringUtils.contains(regionName, "中山区")) {
            return null;
        }
        if (Objects.equals("甘南藏族自治州", cityName) && StringUtils.contains(regionName, "甘南县")) {
            return null;
        }
        return regionDTO;
    }
}
