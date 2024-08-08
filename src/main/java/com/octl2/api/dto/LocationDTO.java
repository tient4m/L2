package com.octl2.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "code",
        "logisticDTO"
})
public interface LocationDTO {
    Long getId();

    String getName();

    String getCode();

    LogisticDTO getLogisticDTO();

    void addFulfilmentDTO(LocationResultSet location);

    void addLastmileDTO(LocationResultSet location);

    void addWarehouseDTO(LocationResultSet location);
}

