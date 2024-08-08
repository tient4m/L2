package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class LogisticDTO {
    private final Set<FulfilmentDTO> fulfilmentDTOSet;
    private final Set<LastmileDTO> lastmileDTOSet;
    private final Set<WarehouseDTO> warehouseDTOSet;

}
