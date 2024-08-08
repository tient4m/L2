package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.DistrictDTO;
import com.octl2.api.dto.LocationResultSet;

import java.util.Set;

public interface DistrictService {
    Set<DistrictDTO> getByLevel(long provinceId);

    Set<LocationResultSet> getResultSet(long provinceId);

    void updateByLevel(long districtId, DeliveryDTO deliveryDTO);
}
