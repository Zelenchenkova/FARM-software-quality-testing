package com.example.farmingproject.util;

import com.example.farmingproject.jpql.AvgTechYearByType;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class AvgTechYearPDFExporter extends AbstractPDFExporter {

    private final Set<AvgTechYearByType> list;

    public AvgTechYearPDFExporter(Set<AvgTechYearByType> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Tech Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Year", font));
        table.addCell(cell);

    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getName());
            table.addCell(String.valueOf(entity.getYear()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("Average Tech Year By Type", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
