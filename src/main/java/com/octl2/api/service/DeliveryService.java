package com.octl2.api.service;

import com.octl2.api.dto.MappingFulfilmentDTO;

import java.util.Set;

public interface DeliveryService {
    Set<MappingFulfilmentDTO> getAllFulfilment();
}
