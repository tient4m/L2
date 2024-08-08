package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.dto.MappingFulfilmentDTO;
import com.octl2.api.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/logistics")
@RequiredArgsConstructor
@Validated
@Slf4j
public class DeliveryController {
    private final DeliveryService deliveryService;
    @GetMapping("/fulfilments")
    public OctResponse<Set<MappingFulfilmentDTO>> getAllFulfilment() {
        Set<MappingFulfilmentDTO> result = deliveryService.getAllFulfilment();
        return OctResponse.build(result);
    }
}

