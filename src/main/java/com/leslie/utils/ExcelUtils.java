package com.leslie.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * alibaba easyExcel
 */
public class ExcelUtils {

    /**
     *
     * @param datas 数据
     * @param firstRow 首行标题
     * @return
     */
    public static HSSFWorkbook writeExcel(String[][] datas, String[] firstRow) throws Exception {

        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个sheet
        Sheet sheet = workbook.createSheet("mySheet");

        // 首行为标题
        Row row = sheet.createRow(0);
        if (firstRow != null && firstRow.length>0){
            for (int i = 0; i < firstRow.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(firstRow[i]);
            }
        }

        for (int i = 0; i < datas.length; i++) {
            // 除了标题后第二行应该为第一行
            Row sheetRow = sheet.createRow(i + 1);
            int cellNum = 0;
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j]!=null){
                    sheetRow.createCell(cellNum).setCellValue(datas[i][j]);
                    cellNum += 1;
                }
            }
        }

        return workbook;
    }

}
