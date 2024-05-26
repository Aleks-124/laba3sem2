package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelWriter {
    public static void excelWriter(HashMap<String, HashMap<String, Double>> data, String type) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Данные");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue(type);
        headerRow.createCell(1).setCellValue("Объем");
        headerRow.createCell(2).setCellValue("Год");

        int rowIndex = 1;
        for (Map.Entry<String, HashMap<String, Double>> entry : data.entrySet()) {
            String region = entry.getKey();
            HashMap<String, Double> consumption = entry.getValue();

            for (Map.Entry<String, Double> entry2 : consumption.entrySet()) {
                String year = entry2.getKey();
                Double volume = entry2.getValue();

                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(region);
                row.createCell(1).setCellValue(volume);
                row.createCell(2).setCellValue(year);
            }
        }

        // Сохранить книгу Excel в файл
        FileOutputStream out = new FileOutputStream(type+"data.xlsx");
        workbook.write(out);
        out.close();

        System.out.println("Файл Excel успешно создан.");
    }
}
