package com.TeamExampleProject.Export;

import com.TeamExampleProject.dao.Play;
import com.TeamExampleProject.dao.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfExport {
    private List<Play> listPlay;

    public PdfExport(List<Play> listPlay) {
        this.listPlay = listPlay;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Play ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("PlayName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SeriesName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TeamName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("TagsName", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) throws JsonProcessingException {
        List<String> tags=new ArrayList<>();
        ObjectMapper mapper=new ObjectMapper();
        for (Play play : listPlay) {
            table.addCell(String.valueOf(play.getId()));
            table.addCell(play.getPlayName());
            table.addCell(play.getSeries().getSeriesName());
            table.addCell(play.getTeam().getTeamName());
            for (Tag tag : play.getTags()) {
                tags.add(tag.getTagName());
            }
            table.addCell(mapper.writeValueAsString(tags));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
