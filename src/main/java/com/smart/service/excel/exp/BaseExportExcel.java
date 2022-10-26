package com.smart.service.excel.exp;

import com.smart.component.util.ExcelUtils;
import org.apache.poi.ss.usermodel.*;

public abstract class BaseExportExcel {

    protected CellStyle cellStyle;
    protected Workbook workbook;
    protected String sheetName;

    protected short fontSize = 11;
    protected String fontName = "Times New Roman";

    public BaseExportExcel() {
        cellStyle = null;
    }

    protected CellStyle getCellStyle() {
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
        }
        return cellStyle;
    }

    protected CellStyle getCellStyleNoneBorder() {
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
            cellStyle.setFont(setFont(fontSize, false, false, Font.U_NONE, IndexedColors.BLACK.getIndex()));
        }
        return cellStyle;
    }

    protected CellStyle getCellStyleComment() {
        if (cellStyle == null) {
            cellStyle = workbook.createCellStyle();
            cellStyle.setFont(setFont(fontSize, false, false, Font.U_NONE, IndexedColors.BLACK.getIndex()));
            cellStyle.setFillBackgroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return cellStyle;
    }

    protected Font setFont(short size, boolean bold, boolean italic, byte underline, short color) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        font.setColor(color);
        font.setBold(bold);
        font.setItalic(italic);
        font.setUnderline(underline);

        return font;
    }

    protected Sheet getCurrentSheet() {
        return workbook.getSheet(sheetName);
    }

    protected Row createRow(int index) {
        Row row = getCurrentSheet().getRow(index);
        return row != null ? row : getCurrentSheet().createRow(index);
    }

    protected Cell createCell(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) {
            cell = row.createCell(index);
        }
        cell.setCellStyle(getCellStyle());
        cell.getCellStyle().setFont(setFont(fontSize, false, false, Font.U_NONE, IndexedColors.BLACK.getIndex()));
        cell.getCellStyle().setWrapText(true);

        return cell;
    }

    protected Cell createCellNoneBorder(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) {
            cell = row.createCell(index);
        }
        cell.setCellStyle(getCellStyleNoneBorder());
        cell.getCellStyle().setFont(setFont(fontSize, false, false, Font.U_NONE, IndexedColors.BLACK.getIndex()));

        return cell;
    }

    protected void setRowData(int rowNum, Object... content) {
        Row row = createRow(rowNum);
        Cell cell;
        int cellIndex = 0;
        for (Object col : content) {
            cell = createCell(row, cellIndex);
            if (col != null && (col.getClass() == Integer.class || col.getClass() == Float.class || col.getClass() == Double.class)) {
                cell.setCellValue(Double.parseDouble(String.valueOf(col)));
            } else {
                cell.setCellValue(col == null ? "" : String.valueOf(col));
            }
            cell.setCellStyle(getCellStyle());
            cellIndex++;
        }
    }

    protected String getCellStringValue(Cell cell) {
        return ExcelUtils.getCellStringValue(workbook,cell);
    }
}
