package com.smart.service.excel.imp;

import com.smart.entity.BaseEntity;
import com.smart.service.excel.exp.BaseExportExcel;
import com.smart.component.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public abstract class BaseImport<T extends BaseEntity> extends BaseExportExcel {

    protected Map<Integer, String> header = new HashMap<>();
    protected Map<Integer, Map<String, Cell>> data = new HashMap<>();
    protected List<T> result = new ArrayList<>();
    protected Map<String, Object> errorMap = new HashMap<>();

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    Drawing drawing;
    CreationHelper creationHelper;

    protected void setNested(T t, String fieldName, String nField, Cell cell) {
        try {
            Field f = t.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object nest = f.get(t);
            if (nest == null)
                nest = f.getType().newInstance();
            f.set(t, nest);
            Field f2 = f.getType().getDeclaredField(nField);
            f2.setAccessible(true);
            f2.set(nest, ExcelUtils.getCellValue(workbook, cell, f.getType()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.error(e.getCause().getLocalizedMessage());
        }
    }

    protected void set(T t, String fieldName, Cell cell) {
        try {
            Field f = t.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(t, ExcelUtils.getCellValue(workbook, cell, f.getType()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.error(e.getCause().getLocalizedMessage());
        }
    }

    protected void mapping(int rowNum, T t) {
        if (rowNum % 100 == 0) {
            System.out.println(rowNum);
        }
        Map<String, Cell> mapValue = data.get(rowNum);
        mapValue.forEach((k, c) -> {
            String[] keys = k.split("\\.");
            if (keys.length == 2) {
                setNested(t, keys[0], keys[1], c);
                validNested(c, t, keys[0], keys[1]);
            } else {
                set(t, k, c);
                valid(c, t, k);
            }
        });
        result.add(t);
    }

    protected void setHeader(Row row, T t) {
        row.cellIterator().forEachRemaining(c -> {
            String value = t.getAttributes().get(c.getStringCellValue());
            if (StringUtils.isNotEmpty(value)) {
                header.put(c.getColumnIndex(), value);
            }
        });
    }

    protected void setData(Row row) {
        header.forEach((key, value) -> {
            if (!data.containsKey(row.getRowNum())) {
                data.put(row.getRowNum(), new HashMap<>());
            }
            Cell cell = row.getCell(key);
            if (cell == null) {
                cell = row.createCell(key);
            }
            data.get(row.getRowNum()).put(value, cell);
        });
    }

    public void valid(Cell cell, T t, String property) {
        Set<ConstraintViolation<T>> violations = validator.validateProperty(t, property);
        if (!violations.isEmpty()) {
            violations.forEach(err -> {
                errorMap.put(cell.getAddress().toString(), err.getMessage());
                setComment(cell, err.getMessage());
            });
        }
    }

    public void validNested(Cell cell, T t, String fieldName, String property) {
        try {
            Field f = t.getClass().getDeclaredField(fieldName);
            Set<? extends ConstraintViolation<?>> violations = validator.validateProperty(f.getType().newInstance(), property);
            if (!violations.isEmpty()) {
                violations.forEach(err -> {
                    errorMap.put(cell.getAddress().toString(), err.getMessage());
                    setComment(cell, err.getMessage());
                });
            }
        } catch (Exception ignored) {
        }
    }

    protected void setHeaderComment() {
        if (drawing == null) {
            drawing = getCurrentSheet().createDrawingPatriarch();
        }
        if (creationHelper == null) {
            creationHelper = workbook.getCreationHelper();
        }
    }

    protected void setComment(Cell cell, String message) {
        // When the comment box is visible, have it show in a 1x3 space
        ClientAnchor anchor = creationHelper.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex() + 2);
        anchor.setRow1(cell.getRowIndex());
        anchor.setRow2(cell.getRowIndex() + 2);
        anchor.setDx1(500);
        anchor.setDx2(2000);

        // Create the comment and set the text + author
        Comment comment = drawing.createCellComment(anchor);
        RichTextString str = creationHelper.createRichTextString(message);
        comment.setString(str);
        cellStyle = getCellStyleComment();

        cell.setCellStyle(cellStyle);
        // Assign the comment to the cell
        cell.setCellComment(comment);
    }

    public Map<String, Object> getErrorMap() {
        return errorMap;
    }
}
