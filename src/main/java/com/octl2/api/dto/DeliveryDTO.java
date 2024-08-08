package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryDTO {
    private Long fulfilmentId;
    private String fulfilmentName;
    private String fulfilmentShortname;
    private Long lastmileId;
    private String lastmileName;
    private String lastmileShortname;
    private Long warehouseId;
    private String warehouseName;
    private String warehouseShortname;
}
