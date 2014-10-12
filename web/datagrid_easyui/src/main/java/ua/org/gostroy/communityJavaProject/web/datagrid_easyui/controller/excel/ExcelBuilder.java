package ua.org.gostroy.communityJavaProject.web.datagrid_easyui.controller.excel;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import ua.org.gostroy.communityJavaProject.core_entity.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Panov Sergey on 10/11/2014.
 */
@Component
public class ExcelBuilder extends AbstractExcelView{
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        List<User> users = (List<User>) model.get("users");

        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Java Excel Test");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Id");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Login");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Email");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Password");
        header.getCell(3).setCellStyle(style);

        // create data rows
        int rowCount = 1;

        for (User user : users) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(user.getId());
            aRow.createCell(1).setCellValue(user.getLogin());
            aRow.createCell(2).setCellValue(user.getEmail());
            aRow.createCell(3).setCellValue(user.getPassword());
        }

    }
}
