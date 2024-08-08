package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.OctException;
import com.octl2.api.commons.exception.OctInvalidInputException;
import com.octl2.api.commons.suberror.ApiMessageError;
import com.octl2.api.converter.ConverterToDTO;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.dto.SubdistrictDTO;
import com.octl2.api.repository.SubdistrictRepository;
import com.octl2.api.service.SubdistrictService;
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
public class SubdistrictServiceImpl implements SubdistrictService {
    @Value("${spring.mapping.level}")
    private int levelMapping;

    private final SubdistrictRepository subdistrictRepo;
    private final ValidatorDelivery validatorDeli;
    private final ConverterToDTO converterToDTO;

    @Override
    public Set<SubdistrictDTO> getByLevel(long districtId) {
        Set<LocationResultSet> locationSet = getResultSet(districtId);
        if(locationSet.isEmpty()) {
            throw new OctException(NOT_FOUND_DISTRICT);
        }
        return converterToDTO.toSubdistrictSet(locationSet);
    }

    @Override
    public Set<LocationResultSet> getResultSet(long districtId) {
        switch (levelMapping) {
            case LEVEL_MAPPING_PROVINCE:
                return subdistrictRepo.getByLevel1(districtId);
            case LEVEL_MAPPING_DISTRICT:
                return subdistrictRepo.getByLevel2(districtId);
            case LEVEL_MAPPING_SUBDISTRICT:
                return subdistrictRepo.getByLevel3(districtId);
            default:
                throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }

    @Override
    public void updateByLevel(long subdistrictId, DeliveryDTO deliveryDTO) {
        validatorDeli.validateSubdistrictDelivery(subdistrictId, deliveryDTO);

        if (levelMapping == LEVEL_MAPPING_PROVINCE || levelMapping == LEVEL_MAPPING_DISTRICT) {
            throw new OctInvalidInputException(UPDATE_ERROR, new ApiMessageError(UPDATE_ERROR_BY_LEVEL));
        } else if (levelMapping == LEVEL_MAPPING_SUBDISTRICT) {
            subdistrictRepo.updateByLevel3(subdistrictId, deliveryDTO);
        } else {
            throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }
}
