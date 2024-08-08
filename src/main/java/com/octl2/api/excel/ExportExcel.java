package com.octl2.api.excel;

import com.octl2.api.dto.LocationResultSet;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.octl2.api.consts.Const.LEVEL_MAPPING_DISTRICT;
import static com.octl2.api.consts.Const.LEVEL_MAPPING_SUBDISTRICT;
import static com.octl2.api.consts.ExcelConst.*;


@RequiredArgsConstructor
public class ExportExcel {
    private final SXSSFWorkbook workbook;
    private SXSSFSheet sheet;
    private final Set<LocationResultSet> locationSet;
    private CellStyle headerStyle;
    private CellStyle dataStyle;

    public ExportExcel(Set<LocationResultSet> locationSet) {
        this.locationSet = locationSet;
        this.workbook = new SXSSFWorkbook();
        createStyles();
    }

    private void createStyles() {
        //Create header style
        headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerStyle.setFont(headerFont);

        //Create data style
        dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
    }

    private void writeHeader(int levelMapping) {
        sheet = workbook.createSheet(TITLE_SHEET);
        SXSSFRow row = sheet.createRow(INDEX_ROW_HEADER);
        int cellIndex = INDEX_CELL_START;

        createCell(row, cellIndex++, PROVINCE_ID, headerStyle);
        createCell(row, cellIndex++, PROVINCE_NAME, headerStyle);
        createCell(row, cellIndex++, PROVINCE_CODE, headerStyle);

        if(levelMapping == LEVEL_MAPPING_DISTRICT) {
            createCell(row, cellIndex++, DISTRICT_ID, headerStyle);
            createCell(row, cellIndex++, DISTRICT_NAME, headerStyle);
            createCell(row, cellIndex++, DISTRICT_CODE, headerStyle);
        }

        if(levelMapping == LEVEL_MAPPING_SUBDISTRICT) {
            createCell(row, cellIndex++, DISTRICT_ID, headerStyle);
            createCell(row, cellIndex++, DISTRICT_NAME, headerStyle);
            createCell(row, cellIndex++, DISTRICT_CODE, headerStyle);
            createCell(row, cellIndex++, SUB_DISTRICT_ID, headerStyle);
            createCell(row, cellIndex++, SUB_DISTRICT_NAME, headerStyle);
            createCell(row, cellIndex++, SUB_DISTRICT_CODE, headerStyle);
        }

        createCell(row, cellIndex++, FULFILMENT_ID, headerStyle);
        createCell(row, cellIndex++, FULFILMENT_NAME, headerStyle);
        createCell(row, cellIndex++, FULFILMENT_SHORTNAME, headerStyle);
        createCell(row, cellIndex++, LASTMILE_ID, headerStyle);
        createCell(row, cellIndex++, LASTMILE_NAME, headerStyle);
        createCell(row, cellIndex++, LASTMILE_SHORTNAME, headerStyle);
        createCell(row, cellIndex++, WAREHOUSE_ID, headerStyle);
        createCell(row, cellIndex++, WAREHOUSE_NAME, headerStyle);
        createCell(row, cellIndex, WAREHOUSE_SHORTNAME, headerStyle);
    }

    private void createCell(@NotNull SXSSFRow row, int columnCount, Object valueOfCell, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if(valueOfCell == null) {
            cell.setCellValue("N/A");
        } else if(valueOfCell instanceof Number) {
            cell.setCellValue(((Number) valueOfCell).doubleValue());
        } else if(valueOfCell instanceof Boolean) {
            cell.setCellValue((Boolean) valueOfCell);
        } else {
            cell.setCellValue((String) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(int levelMapping) {
        int rowCount = INDEX_ROW_START;
        for(LocationResultSet location : locationSet) {
            SXSSFRow row = sheet.createRow(rowCount++);
            int columnCount = INDEX_CELL_START;
            createCell(row, columnCount++, location.getLocationId(), dataStyle);
            createCell(row, columnCount++, location.getName(), dataStyle);
            createCell(row, columnCount++, location.getCode(), dataStyle);

            if(levelMapping == LEVEL_MAPPING_DISTRICT) {
                createCell(row, columnCount++, location.getDistrictId(), dataStyle);
                createCell(row, columnCount++, location.getDistrictName(), dataStyle);
                createCell(row, columnCount++, location.getDistrictCode(), dataStyle);
            }

            if (levelMapping == LEVEL_MAPPING_SUBDISTRICT) {
                createCell(row, columnCount++, location.getDistrictId(), dataStyle);
                createCell(row, columnCount++, location.getDistrictName(), dataStyle);
                createCell(row, columnCount++, location.getDistrictCode(), dataStyle);
                createCell(row, columnCount++, location.getSubdistrictId(), dataStyle);
                createCell(row, columnCount++, location.getSubdistrictName(), dataStyle);
                createCell(row, columnCount++, location.getSubdistrictCode(), dataStyle);
            }

            createCell(row, columnCount++, location.getFulfilmentId(), dataStyle);
            createCell(row, columnCount++, location.getFulfilmentName(), dataStyle);
            createCell(row, columnCount++, location.getFulfilmentShortname(), dataStyle);
            createCell(row, columnCount++, location.getLastmileId(), dataStyle);
            createCell(row, columnCount++, location.getLastmileName(), dataStyle);
            createCell(row, columnCount++, location.getLastmileShortname(), dataStyle);
            createCell(row, columnCount++, location.getWarehouseId(), dataStyle);
            createCell(row, columnCount++, location.getWarehouseName(), dataStyle);
            createCell(row, columnCount, location.getWarehouseShortname(), dataStyle);
        }

        sheet.trackAllColumnsForAutoSizing();
        SXSSFRow headerRow = sheet.getRow(sheet.getLastRowNum());
        if(headerRow != null) {
            headerRow.forEach(item -> sheet.autoSizeColumn(item.getColumnIndex()));
        }
    }

    public void exportExcelFile(HttpServletResponse response, int levelMapping) throws IOException {
        writeHeader(levelMapping);
        write(levelMapping);

        try(ServletOutputStream ops = response.getOutputStream()) {
            workbook.write(ops);
        }
        finally {
            workbook.close();
        }
    }
}
