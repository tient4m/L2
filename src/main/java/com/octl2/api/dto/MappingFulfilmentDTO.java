package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class MappingFulfilmentDTO {
    private final Long fulfilmentId;
    private final String fulfilmentName;
    private final String fulfilmentShortname;
    private final Set<LastmileDTO> lastmileDTOSet;
    private final Set<WarehouseDTO> warehouseDTOSet;
}
