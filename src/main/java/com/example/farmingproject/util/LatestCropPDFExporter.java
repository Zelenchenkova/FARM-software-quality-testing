package com.example.farmingproject.util;

import com.example.farmingproject.jpql.LatestCropDateForCultures;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class LatestCropPDFExporter extends AbstractPDFExporter{

    private final Set<LatestCropDateForCultures> list;

    public LatestCropPDFExporter(Set<LatestCropDateForCultures> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Culture Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Crop Date", font));
        table.addCell(cell);

    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getName());
            table.addCell(String.valueOf(entity.getDate()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("The Latest crop date for each culture", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
