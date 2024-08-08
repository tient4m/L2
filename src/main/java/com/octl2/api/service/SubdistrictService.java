package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.dto.SubdistrictDTO;

import java.util.Set;

public interface SubdistrictService {
    Set<SubdistrictDTO> getByLevel(long districtId);

    Set<LocationResultSet> getResultSet(long districtId);

    void updateByLevel(long subdistrictId, DeliveryDTO deliveryDTO);
}
