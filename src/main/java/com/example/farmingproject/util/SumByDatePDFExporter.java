package com.example.farmingproject.util;

import com.example.farmingproject.jpql.AvgTechYearByType;
import com.example.farmingproject.jpql.SumByDate;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class SumByDatePDFExporter extends AbstractPDFExporter{

    private final Set<SumByDate> list;

    public SumByDatePDFExporter(Set<SumByDate> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sum", font));
        table.addCell(cell);

    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(String.valueOf(entity.getDate()));
            table.addCell(String.valueOf(entity.getSum()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("Sum of fertilizer cost by date", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
