package com.octl2.api.service.impl;

import com.octl2.api.converter.ConverterToDTO;
import com.octl2.api.dto.*;
import com.octl2.api.repository.DeliveryRepository;
import com.octl2.api.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepo;
    private final ConverterToDTO converterToDTO;

    @Override
    public Set<MappingFulfilmentDTO> getAllFulfilment() {
        Set<LocationResultSet> resultSets = deliveryRepo.getAllLogistic();
        return converterToDTO.toMappingFulfilmentSet(resultSets);
    }
}
