package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.SubdistrictDTO;
import com.octl2.api.service.SubdistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.octl2.api.consts.MessageConst.UPDATE_SUCCESS;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/subdistricts")
@RequiredArgsConstructor
@Validated
@Slf4j
public class SubdistrictController {
    private final SubdistrictService subdistrictService;

    @GetMapping()
    OctResponse<Set<SubdistrictDTO>> getByAll(@RequestParam long districtId) {
        Set<SubdistrictDTO> result = subdistrictService.getByLevel(districtId);
        return OctResponse.build(result);
    }

    @PutMapping("/{id}")
    OctResponse<String> updateLogisticByLevel(@PathVariable("id") long id, @RequestBody DeliveryDTO deliveryDTO) {
        subdistrictService.updateByLevel(id, deliveryDTO);
        return OctResponse.build(UPDATE_SUCCESS);
    }


}
