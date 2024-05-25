package com.example.demo.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Student;

public class ExcelHelper {

    // Step 1: Check if the file is in Excel format
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.ms-excel");
    }

    // Step 2: Convert the Excel file to a list of Student objects
    public static List<Student> convertExcelTOListOfStudent(InputStream is) throws IOException {
        List<Student> list = new ArrayList<>();

        // Step 3: Create a workbook object from the input stream
        try (Workbook workbook = new XSSFWorkbook(is)) {
            // Step 4: Get the "Student" sheet from the workbook
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Student");

            // Step 5: Check if the sheet exists
            if (sheet != null) {
                // Step 6: Iterate over each row in the sheet
                Iterator<Row> rows = sheet.iterator();

                while (rows.hasNext()) {
                    Row currentRow = rows.next();

                    // Step 7: Skip the header row
                    if (currentRow.getRowNum() == 0) {
                        continue;
                    }

                    // Step 8: Create a new Student object for each row
                    Student data = new Student();

                    // Step 9: Get the cell values from specific columns and set the corresponding properties of the Student object
                    Cell cell0 = currentRow.getCell(0);
                    if (cell0 != null) {
                        if (cell0.getCellType() == CellType.STRING) {
                            data.setId(cell0.getStringCellValue());
                        } else if (cell0.getCellType() == CellType.NUMERIC) {
                            data.setId(String.valueOf((int) cell0.getNumericCellValue()));
                        }
                    }

                    Cell cell1 = currentRow.getCell(1);
                    if (cell1 != null) {
                        if (cell1.getCellType() == CellType.STRING) {
                            data.setName(cell1.getStringCellValue());
                        } else if (cell1.getCellType() == CellType.NUMERIC) {
                            data.setName(String.valueOf((int) cell1.getNumericCellValue()));
                        }
                    }

                    Cell cell2 = currentRow.getCell(2);
                    if (cell2 != null) {
                        if (cell2.getCellType() == CellType.STRING) {
                            data.setRoll_No(cell2.getStringCellValue());
                        }
                    }

                    Cell cell3 = currentRow.getCell(3);
                    if (cell3 != null) {
                        if (cell3.getCellType() == CellType.STRING) {
                            data.setBranch(cell3.getStringCellValue());
                        }
                    }

                    // Step 10: Add the Student object to the list
                    list.add(data);
                }
            }
        }

        // Step 11: Return the list of Student objects
        return list;
    }
}
