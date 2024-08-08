package com.octl2.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "locationId",
        "name",
        "shortname",
        "code",
        "description",
        "fulfilmentId",
        "fulfilmentName",
        "fulfilmentShortname",
        "lastmileId",
        "lastmileName",
        "lastmileShortname",
        "warehouseId",
        "warehouseName",
        "warehouseShortname"
})
public interface LocationResultSet {
    Long getLocationId();
    String getName();
    String getCode();
    Long getFulfilmentId();
    Long getLastmileId();
    Long getWarehouseId();
    String getFulfilmentName();
    String getLastmileName();
    String getWarehouseName();
    String getFulfilmentShortname();
    String getLastmileShortname();
    String getWarehouseShortname();

    Long getDistrictId();
    String getDistrictName();
    String getDistrictCode();
    Long getSubdistrictId();
    String getSubdistrictName();
    String getSubdistrictCode();
}
