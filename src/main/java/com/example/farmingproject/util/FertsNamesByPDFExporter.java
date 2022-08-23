package com.example.farmingproject.util;

import com.example.farmingproject.jpql.FertsNamesByProvider;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class FertsNamesByPDFExporter extends AbstractPDFExporter{

    private final Set<FertsNamesByProvider> list;

    public FertsNamesByPDFExporter(Set<FertsNamesByProvider> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Fertilizer Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Comment", font));
        table.addCell(cell);

    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getName());
            table.addCell(entity.getComment());
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("Goods ordered from the specified provider", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
