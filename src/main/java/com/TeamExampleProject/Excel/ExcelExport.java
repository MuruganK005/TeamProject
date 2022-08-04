package com.TeamExampleProject.Excel;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.Series;
import com.TeamExampleProject.dao.Tag;
import com.TeamExampleProject.dao.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExcelExport {
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet sheet;

    private List<Play> listPlay;
    public ExcelExport(List<Play> listPlay) {
        this.listPlay=listPlay;
        xssfWorkbook = new XSSFWorkbook();
    }


    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell= row.createCell(columnCount);
        if (value instanceof Long){
            cell.setCellValue((Long) value);
        }else if (value instanceof Integer){
            cell.setCellValue((Long) value);
        }else
        {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine(){
        sheet=xssfWorkbook.createSheet("PLay");
        Row row = sheet.createRow(0);
        CellStyle style = xssfWorkbook.createCellStyle();
        XSSFFont font=xssfWorkbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"Play Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));

        row=sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Play Id", style);
        createCell(row, 1, "Play Name", style);
        createCell(row, 2, "Team Name", style);
        createCell(row, 3, "Series Name", style);
        createCell(row, 4, "Tag Name", style);

    }
    private void writeDataLines() throws JsonProcessingException {
        int rowCount=2;

        ObjectMapper objectMapper = new ObjectMapper();
        CellStyle style=xssfWorkbook.createCellStyle();
        XSSFFont font=xssfWorkbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Play ply:listPlay) {
            List<String> tags=new ArrayList<>();
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++,ply.getId(), style);
            createCell(row, columnCount++,ply.getPlayName(), style);
            createCell(row, columnCount++,ply.getTeam().getTeamName(), style);
            createCell(row, columnCount++,ply.getSeries().getSeriesName(), style);
            for (Tag tag : ply.getTags()) {
                tags.add(tag.getTagName());
            }
            createCell(row, columnCount++,objectMapper.writeValueAsString(tags), style);
        }
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream=response.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();
    }
}





