package com.se.back.controller.service;

import com.se.back.data.repo.es.dataclass.RegionDTO;

import java.io.IOException;

/**
 * @author: 信长华
 * @date: 2021/4/20 13:33
 * @className: RegionService
 * @description: TODO
 * @version: 1.0
 */
public interface RegionService {
    /**
     * 根据地区名获取详细的省市县
     *
     * @param region
     * @return
     */
     RegionDTO makeRegionDto(String region) throws IOException;
}
