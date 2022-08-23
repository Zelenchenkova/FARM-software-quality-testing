package com.example.farmingproject.util;

import com.example.farmingproject.jpql.MostExpensiveFertilizerForTypes;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;

import java.util.Set;

public class MostExpensiveForTypesPDFExporter extends AbstractPDFExporter{

    private final Set<MostExpensiveFertilizerForTypes> list;

    public MostExpensiveForTypesPDFExporter(Set<MostExpensiveFertilizerForTypes> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Fertilizer type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Fertilizer", font));
        table.addCell(cell);

    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getType());
            table.addCell(entity.getName());
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("The most expensive fert for each type", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
