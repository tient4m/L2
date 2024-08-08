package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.OctException;
import com.octl2.api.commons.exception.OctInvalidInputException;
import com.octl2.api.commons.suberror.ApiMessageError;
import com.octl2.api.converter.ConverterToDTO;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.DistrictDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.repository.DistrictRepository;
import com.octl2.api.service.DistrictService;
import com.octl2.api.validator.ValidatorDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.octl2.api.commons.exception.ErrorMessages.*;
import static com.octl2.api.consts.Const.*;
import static com.octl2.api.consts.MessageConst.UPDATE_ERROR_BY_LEVEL;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    @Value("${spring.mapping.level}")
    private int levelMapping;

    private final DistrictRepository districtRepo;
    private final ValidatorDelivery validatorDeli;
    private final ConverterToDTO converterToDTO;

    @Override
    public Set<DistrictDTO> getByLevel(long provinceId) {
        Set<LocationResultSet> locationSet = getResultSet(provinceId);
        if(locationSet.isEmpty()) {
            throw new OctException(NOT_FOUND_PROVINCE);
        }
        return converterToDTO.toDistrictSet(locationSet);
    }

    @Override
    public Set<LocationResultSet> getResultSet(long provinceId) {
        switch (levelMapping) {
            case LEVEL_MAPPING_PROVINCE:
                return districtRepo.getByLevel1(provinceId);
            case LEVEL_MAPPING_DISTRICT:
                return districtRepo.getByLevel2(provinceId);
            case LEVEL_MAPPING_SUBDISTRICT:
                return districtRepo.getByLevel3(provinceId);
            default:
                throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }

    public void updateByLevel(long districtId, DeliveryDTO deliveryDTO) {
        validatorDeli.validateDistrictDelivery(districtId, deliveryDTO);
        switch (levelMapping) {
            case LEVEL_MAPPING_PROVINCE:
            {
                throw new OctInvalidInputException(UPDATE_ERROR, new ApiMessageError(UPDATE_ERROR_BY_LEVEL));
            }
            case LEVEL_MAPPING_DISTRICT:
            {
                districtRepo.updateByLevel2(districtId, deliveryDTO);
                break;
            }
            case LEVEL_MAPPING_SUBDISTRICT:
            {
                districtRepo.updateByLevel3(districtId, deliveryDTO);
                break;
            }
            default:
                throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }
}
