package com.octl2.api.service.impl;

import com.octl2.api.commons.exception.OctException;
import com.octl2.api.converter.ConverterToDTO;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.dto.ProvinceDTO;
import com.octl2.api.excel.ExportExcel;
import com.octl2.api.repository.ProvinceRepository;
import com.octl2.api.service.ProvinceService;
import com.octl2.api.validator.ValidatorDelivery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.octl2.api.commons.exception.ErrorMessages.*;
import static com.octl2.api.consts.Const.*;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {
    @Value(value = "${spring.mapping.level}")
    private int levelMapping;

    private final ProvinceRepository provinceRepo;
    private final ValidatorDelivery validatorDeli;
    private final ConverterToDTO converterToDTO;

    @Override
    public Set<ProvinceDTO> getByLevel() {
        Set<LocationResultSet> locationSet = getResultSet();
        if(locationSet.isEmpty()) {
            throw new OctException(NOT_FOUND);
        }
        return converterToDTO.toProvinceSet(locationSet);
    }

    @Override
    public Set<LocationResultSet> getResultSet() {
        switch (levelMapping) {
            case LEVEL_MAPPING_PROVINCE:
            {
                return provinceRepo.getLByLevel1();
            }
            case LEVEL_MAPPING_DISTRICT:
            {
                return provinceRepo.getByLevel2();
            }
            case LEVEL_MAPPING_SUBDISTRICT:
            {
                return provinceRepo.getByLevel3();
            }
            default:
                throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        Set<LocationResultSet> provinces = getResultSet();
        if(provinces.isEmpty()) {
            throw new OctException(NOT_FOUND);
        } else {
            ExportExcel export = new ExportExcel(provinces);
            export.exportExcelFile(response, levelMapping);
        }


    }

    public void update(long provinceId, DeliveryDTO deliveryDTO) {
        validatorDeli.validateProvinceDelivery(provinceId, deliveryDTO);
        switch (levelMapping) {
            case LEVEL_MAPPING_PROVINCE:
            {
                provinceRepo.updateByLevel1(provinceId, deliveryDTO);
                break;
            }
            case LEVEL_MAPPING_DISTRICT:
            {
                provinceRepo.updateByLevel2(provinceId, deliveryDTO);
                break;
            }
            case LEVEL_MAPPING_SUBDISTRICT:
            {
                provinceRepo.updateByLevel3(provinceId, deliveryDTO);
                break;
            }
            default:
                throw new OctException(INVALID_CONFIGURATION_LEVEL);
        }
    }
}

/*
 *  @AllArgsConstructor: tự động tạo một hàm tạo với tất cả các tham số cho các thuộc tính của lớp.
 */
