package com.se.back.service.impl;

import com.se.back.common.util.JsonUtil;
import com.se.back.data.repo.es.dataclass.RegionDTO;
import com.se.back.service.RegionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RegionServiceImplTest {
    @Autowired
    RegionService regionService;

    @Test
    public void regionServiceTest() throws IOException {
        RegionDTO regionDTO = regionService.makeRegionDto("商河县");
        System.out.println("regionDTO = " + regionDTO);
        String regionDTOJson = JsonUtil.makeJson(regionDTO);
        String shangheDTOJson = JsonUtil.makeJson(new RegionDTO("商河县", "商河",
                "山东省", "山东", "济南市", "济南", "商河县"));
        Assertions.assertEquals(regionDTOJson, shangheDTOJson);


        RegionDTO regionDTO1 = regionService.makeRegionDto("聊城");
        String regionDTO1Json = JsonUtil.makeJson(regionDTO1);
        String liaochDTOJson = JsonUtil.makeJson(new RegionDTO("聊城市", "聊城",
                "山东省", "山东", "聊城市", null, null));
        System.out.println("regionDTO1 = " + regionDTO1);
        Assertions.assertEquals(regionDTO1Json, liaochDTOJson);

        RegionDTO regionDTO2 = regionService.makeRegionDto("朝阳区");
        System.out.println("regionDTO2 = " + regionDTO2);
        Assertions.assertNull(regionDTO2);

        RegionDTO regionDTO3 = regionService.makeRegionDto("北京朝阳区");
        System.out.println("regionDTO3 = " + regionDTO3);

    }

}