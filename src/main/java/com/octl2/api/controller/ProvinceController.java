package com.octl2.api.controller;

import com.octl2.api.commons.OctResponse;
import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.ProvinceDTO;
import com.octl2.api.service.ProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static com.octl2.api.consts.MessageConst.UPDATE_SUCCESS;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1/provinces")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProvinceController {
    private final ProvinceService provinceService;

    @GetMapping()
    public OctResponse<Set<ProvinceDTO>> getAll() {
        Set<ProvinceDTO> result = provinceService.getByLevel();
        return OctResponse.build(result);
    }

    @GetMapping("/export-to-excel")
    public void exportToExcelFile(@NotNull HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=logistics_province_" + formattedDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        provinceService.exportExcel(response);
    }

    @PutMapping("/{id}")
    public OctResponse<String> updateLogisticByLevel(@PathVariable("id") long id, @RequestBody DeliveryDTO deliveryDTO) {
        provinceService.update(id, deliveryDTO);
        return OctResponse.build(UPDATE_SUCCESS);
    }
}

/*
 *  -   @RequiredArgsConstructor: tự động tạo contructor cho tất cả các trường final và không null.
 *  -   @Validated: kích hoạt trình kiểm tra hợp lệ của spring AOP và kiểm tra các tham số phương thức xem chúng có bất kỳ
 *      chú thích kiểm tra hợp lệ trên chúng hay không
 *  -   @Slf4j tự động tạo ra một trình ghi nhật ký (logger) cho lớp được chú thích. Chú thích này sẽ tự động tạo ra một
 *      trình ghi nhật ký với tên lớp hiện tại. Vì vậy không cần khai báo logger trong lớp của mình.
 *  -   response.setContentType("application/octet-stream"): đặt kiểu nội dung của response là "application/octet-stream",
 *      chỉ định rằng dữ liệu sẽ được trả về dưới dạng nhị phân:
 *  + text/html: phổ biến cho các trang web html
 *  + text/plain: sử dụng cho văn bản thuần túy, không có định dạng html
 *  + application/json: để trả về dữ liệu JSON
 *  + application/xml: dạng dữ liệu xml
 *  -   String headerKey = "Content-Disposition": Khai báo tên header cho phần header của response.
 *  -   String headerValue = "attachment; filename=logistics_province_" + formattedDateTime + ".xlsx": tạo giá trị cho
 *      header của response, bao gồm tên file sẽ được tạo dựa trên thời gian hiện tại.
 *
 */
