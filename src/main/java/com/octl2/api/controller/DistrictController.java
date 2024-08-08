package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.DistrictDTO;
import com.octl2.api.service.DistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.octl2.api.consts.MessageConst.UPDATE_SUCCESS;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = "/api/v1/districts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping()
    public OctResponse<Set<DistrictDTO>> getDistrictByLevel(@RequestParam long provinceId) {
        Set<DistrictDTO> result = districtService.getByLevel(provinceId);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    public OctResponse<String> updateLogisticByLevel(@PathVariable("id") long id, @RequestBody DeliveryDTO deliveryDTO) {
        districtService.updateByLevel(id, deliveryDTO);
        return OctResponse.build(UPDATE_SUCCESS);
    }
}
