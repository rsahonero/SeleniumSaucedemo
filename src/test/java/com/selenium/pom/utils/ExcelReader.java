package com.selenium.pom.utils;

import com.selenium.pom.model.LoginData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

// CLASE QUE OBTIENE DATOS DEL EXCEL
public class ExcelReader {
    public static List<LoginData> readUsers(String filePath) {
        List<LoginData> users = new ArrayList<>();
        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fileInputStream);
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();

                users.add(new LoginData(username, password));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // RETORNA UN LISTADO CON TODOS LOS DATOS DEL EXCEL
        return users;
    }
}
