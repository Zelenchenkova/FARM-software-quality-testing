package com.example.farmingproject.util;

import com.example.farmingproject.jpql.CustomersAndProviders;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class PartnersPDFExporter extends AbstractPDFExporter{

    private final Set<CustomersAndProviders> list;

    public PartnersPDFExporter(Set<CustomersAndProviders> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Partner Name", font));
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
        return new Paragraph("Partners for the farming", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
