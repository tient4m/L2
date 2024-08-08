package com.octl2.api.converter;

import com.octl2.api.dto.*;
import com.octl2.api.dto.impl.DistrictDTOImpl;
import com.octl2.api.dto.impl.ProvinceDTOImpl;
import com.octl2.api.dto.impl.SubdistrictDTOImpl;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ConverterToDTO {
    public Set<MappingFulfilmentDTO> toMappingFulfilmentSet(Set<LocationResultSet> locationResultSets) {
        Map<Long, MappingFulfilmentDTO> fulfilmentMap = new LinkedHashMap<>();

        for(LocationResultSet locationResultSet : locationResultSets) {
            Long fulfilmentId = locationResultSet.getFulfilmentId();
            if(!fulfilmentMap.containsKey(fulfilmentId)) {
                fulfilmentMap.put(fulfilmentId, new MappingFulfilmentDTO(
                        fulfilmentId,
                        locationResultSet.getLastmileName(),
                        locationResultSet.getLastmileShortname(),
                        new LinkedHashSet<>(), new LinkedHashSet<>()
                ));
            }

            MappingFulfilmentDTO mappingFulfilmentDTO = fulfilmentMap.get(fulfilmentId);
            if(locationResultSet.getLastmileId() != null) {
                mappingFulfilmentDTO.getLastmileDTOSet().add(new LastmileDTO(locationResultSet));
            }

            if(locationResultSet.getWarehouseId() != null) {
                mappingFulfilmentDTO.getWarehouseDTOSet().add(new WarehouseDTO(locationResultSet));
            }
        }
        return new LinkedHashSet<>(fulfilmentMap.values());
    }

    public Set<ProvinceDTO> toProvinceSet(Set<LocationResultSet> locations) {
        Map<Long, ProvinceDTO> provinceMap = new LinkedHashMap<>();

        locations.forEach(location -> {
            Long locationId = location.getLocationId();
            ProvinceDTO provinceDTO = provinceMap.computeIfAbsent(locationId, id -> new ProvinceDTOImpl(location));

            if (provinceDTO instanceof ProvinceDTOImpl) {
                provinceDTO.addFulfilmentDTO(location);
                provinceDTO.addLastmileDTO(location);
                provinceDTO.addWarehouseDTO(location);
            }
        });
        return new LinkedHashSet<>(provinceMap.values());
    }

    public Set<DistrictDTO> toDistrictSet(Set<LocationResultSet> locations) {
        Map<Long, DistrictDTO> districtMap = new LinkedHashMap<>();

        locations.forEach(location -> {
            Long locationId = location.getLocationId();
            DistrictDTO districtDTO = districtMap.computeIfAbsent(locationId, id -> new DistrictDTOImpl(location));
            if(districtDTO instanceof DistrictDTOImpl) {
                districtDTO.addFulfilmentDTO(location);
                districtDTO.addLastmileDTO(location);
                districtDTO.addWarehouseDTO(location);
            }
        });
        return new LinkedHashSet<>(districtMap.values());
    }

    public Set<SubdistrictDTO> toSubdistrictSet(Set<LocationResultSet> locations) {
        Map<Long, SubdistrictDTO> subdistrictMap = new LinkedHashMap<>();

        locations.forEach(location -> {
            Long locationId = location.getLocationId();
            SubdistrictDTO subdistrictDTO = subdistrictMap.computeIfAbsent(locationId, id -> new SubdistrictDTOImpl(location));
            if(subdistrictDTO instanceof SubdistrictDTOImpl) {
                subdistrictDTO.addFulfilmentDTO(location);
                subdistrictDTO.addLastmileDTO(location);
                subdistrictDTO.addWarehouseDTO(location);
            }
        });
        return new LinkedHashSet<>(subdistrictMap.values());
    }
}
