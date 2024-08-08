package com.octl2.api.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class FulfilmentDTO implements Comparable<FulfilmentDTO> {
    @EqualsAndHashCode.Include
    private final Long fulfilmentId;
    private final String fulfilmentName;
    private final String fulfilmentShortname;

    public FulfilmentDTO(LocationResultSet location) {
        this.fulfilmentId = location.getFulfilmentId();
        this.fulfilmentName = location.getFulfilmentName();
        this.fulfilmentShortname = location.getFulfilmentShortname();
    }

    @Override
    public int compareTo(@NotNull FulfilmentDTO other) {
        if (this.getFulfilmentId() == null && other.getFulfilmentId() == null) {
            return 0;
        } else if (this.getFulfilmentId() == null) {
            return -1;
        } else if (other.getFulfilmentId() == null) {
            return 1;
        } else {
            return (int) (this.getFulfilmentId() - other.getFulfilmentId());
        }
    }
}