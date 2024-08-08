package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class WarehouseDTO implements Comparable<WarehouseDTO> {
    @EqualsAndHashCode.Include
    private final Long warehouseId;
    private final String warehouseName;
    private final String warehouseShortname;

    public WarehouseDTO(LocationResultSet location) {
        this.warehouseId = location.getWarehouseId();
        this.warehouseName = location.getWarehouseName();
        this.warehouseShortname = location.getWarehouseShortname();
    }

    @Override
    public int compareTo(@NotNull WarehouseDTO other) {
        if (this.getWarehouseId() == null && other.getWarehouseId() == null) {
            return 0;
        } else if (this.getWarehouseId() == null) {
            return -1;
        } else if (other.getWarehouseId() == null) {
            return 1;
        } else {
            return (int) (this.getWarehouseId() - other.getWarehouseId());
        }
    }
}
