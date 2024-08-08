package com.octl2.api.validator;

import com.octl2.api.commons.exception.OctInvalidInputException;
import com.octl2.api.commons.suberror.ApiSubError;
import com.octl2.api.commons.suberror.ApiValidatorError;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.octl2.api.commons.exception.ErrorMessages.INVALID_VALUE;
import static com.octl2.api.consts.MessageConst.*;

@Component
@RequiredArgsConstructor
public class ValidatorDelivery {
    private final ProvinceRepository provinceRepo;
    private final DistrictRepository districtRepo;
    private final SubdistrictRepository subdistrictRepo;
    private final PartnerRepository partnerRepo;
    private final WarehouseRepository warehouseRepo;

    public void validateProvinceDelivery(long provinceId, DeliveryDTO deliveryDTO) {
        List<ApiSubError> errors = checkExistsDelivery(deliveryDTO);
        if(!provinceRepo.existsById(provinceId)) {
            errors.add(new ApiValidatorError(FIELD_PROVINCE_ID, provinceId, PROVINCE_NOT_FOUND));
        }
        if(!errors.isEmpty()) {
            throw new OctInvalidInputException(INVALID_VALUE, errors);
        }
    }

    public void validateDistrictDelivery(long districtId, DeliveryDTO deliveryDTO) {
        List<ApiSubError> errors = checkExistsDelivery(deliveryDTO);
        if(!districtRepo.existsById(districtId)) {
            errors.add(new ApiValidatorError(FIELD_DISTRICT_ID, districtId, DISTRICT_NOT_FOUND));
        }
        if(!errors.isEmpty()) {
            throw new OctInvalidInputException(INVALID_VALUE, errors);
        }
    }

    public void validateSubdistrictDelivery(long subdistrictId, DeliveryDTO deliveryDTO) {
        List<ApiSubError> errors = checkExistsDelivery(deliveryDTO);
        if(!subdistrictRepo.existsById(subdistrictId)) {
            errors.add(new ApiValidatorError(FIELD_SUBDISTRICT_ID, subdistrictId, SUBDISTRICT_NOT_FOUND));
        }
    }

    public void checkNullDelivery(DeliveryDTO deliveryDTO) {
        List<ApiSubError> errors = new ArrayList<>();
        if(deliveryDTO.getFulfilmentId() == null) {
            errors.add(new ApiValidatorError(FIELD_FULFILMENT_ID, null, FULFILMENT_NOT_FOUND));
        }
        if(deliveryDTO.getLastmileId() == null) {
            errors.add(new ApiValidatorError(FIELD_LASTMILE_ID, null, LASTMILE_NOT_FOUND));
        }
        if(deliveryDTO.getWarehouseId() == null) {
            errors.add(new ApiValidatorError(FIELD_WAREHOUSE_ID, null, WAREHOUSE_NOT_FOUND));
        }
        if(!errors.isEmpty()) {
            throw new OctInvalidInputException(INVALID_VALUE, errors);
        }
    }

    public List<ApiSubError> checkExistsDelivery(DeliveryDTO deliveryDTO) {
        checkNullDelivery(deliveryDTO);
        List<ApiSubError> errors = new ArrayList<>();
        Long ffmId = deliveryDTO.getFulfilmentId();
        Long lmId = deliveryDTO.getLastmileId();
        Long whId = deliveryDTO.getWarehouseId();
        if(!partnerRepo.existsByFulfilmentId(ffmId)) {
            errors.add(new ApiValidatorError(FIELD_FULFILMENT_ID, ffmId, FULFILMENT_NOT_FOUND));
        }
        if(!partnerRepo.existsByLastmileId(lmId)) {
            errors.add(new ApiValidatorError(FIELD_LASTMILE_ID, lmId, LASTMILE_NOT_FOUND));
        }
        if(!warehouseRepo.existsById(whId)) {
            errors.add(new ApiValidatorError(FIELD_WAREHOUSE_ID, whId, WAREHOUSE_NOT_FOUND));
        }
        return errors;
    }
}
