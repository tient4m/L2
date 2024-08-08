package com.octl2.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class LastmileDTO implements Comparable<LastmileDTO> {
    @EqualsAndHashCode.Include
    private final Long lastmileId;
    private final String lastmileName;
    private final String lastmileShortname;

    public LastmileDTO(LocationResultSet location) {
        this.lastmileId = location.getLastmileId();
        this.lastmileName = location.getLastmileName();
        this.lastmileShortname = location.getLastmileShortname();
    }

    @Override
    public int compareTo(@NotNull LastmileDTO other) {
        if (this.getLastmileId() == null && other.getLastmileId() == null) {
            return 0;
        } else if (this.getLastmileId() == null) {
            return -1;
        } else if (other.getLastmileId() == null) {
            return 1;
        } else {
            return (int) (this.getLastmileId() - other.getLastmileId());
        }
    }
}
