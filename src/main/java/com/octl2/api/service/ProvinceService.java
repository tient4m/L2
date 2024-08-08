package com.octl2.api.service;

import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.dto.ProvinceDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public interface ProvinceService {
    Set<ProvinceDTO> getByLevel();

    void exportExcel(HttpServletResponse response) throws IOException;

    Set<LocationResultSet> getResultSet();

    void update(long provinceId, DeliveryDTO deliveryDTO);
}
