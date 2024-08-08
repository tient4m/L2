package com.octl2.api.dto.impl;

import com.octl2.api.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.*;

@Getter
@AllArgsConstructor
public class LocationDTOImpl implements LocationDTO {
    private final Long id;
    private final String name;
    private final String code;
    private final LogisticDTO logisticDTO;

    public LocationDTOImpl(LocationResultSet location) {
        this.id = location.getLocationId();
        this.name = location.getName();
        this.code = location.getCode();

        Set<FulfilmentDTO> ffmSet = new TreeSet<>();
        Set<LastmileDTO> ltSet = new TreeSet<>();
        Set<WarehouseDTO> whSet = new TreeSet<>();
        ffmSet.add(new FulfilmentDTO(location));
        ltSet.add(new LastmileDTO(location));
        whSet.add(new WarehouseDTO(location));
        this.logisticDTO = new LogisticDTO(ffmSet, ltSet, whSet);
    }

    @Override
    public void addFulfilmentDTO(LocationResultSet location) {
        FulfilmentDTO fulfilmentDTO = new FulfilmentDTO(location);
        if(fulfilmentDTO.getFulfilmentId() != null) {
            this.getLogisticDTO().getFulfilmentDTOSet().add(fulfilmentDTO);
        }
    }

    @Override
    public void addLastmileDTO(LocationResultSet location) {
        LastmileDTO lastmileDTO = new LastmileDTO(location);
        if(lastmileDTO.getLastmileId() != null) {
            this.getLogisticDTO().getLastmileDTOSet().add(lastmileDTO);
        }
    }

    @Override
    public void addWarehouseDTO(LocationResultSet location) {
        WarehouseDTO warehouseDTO = new WarehouseDTO(location);
        if(warehouseDTO.getWarehouseId() != null) {
            this.getLogisticDTO().getWarehouseDTOSet().add(warehouseDTO);
        }
    }

}
