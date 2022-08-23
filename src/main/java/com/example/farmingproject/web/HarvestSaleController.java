package com.example.farmingproject.web;

import com.example.farmingproject.domain.Harvest;
import com.example.farmingproject.domain.HarvestSale;
import com.example.farmingproject.domain.Sale;
import com.example.farmingproject.jpql.HarvestDate;
import com.example.farmingproject.service.HarvestSaleService;
import com.example.farmingproject.service.HarvestService;
import com.example.farmingproject.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class HarvestSaleController {

    private final HarvestSaleService harvestSaleService;
    private final HarvestService harvestService;
    private final SaleService saleService;

    @GetMapping("/harvestSales/new")
    public String showNewHarvestSaleForm(Model model) {
        List<Harvest> harvests = harvestService.findAllHarvest();
        List<Sale> sales = saleService.findAllSales();
        model.addAttribute("harvests", harvests);
        model.addAttribute("sales", sales);
        model.addAttribute("harvestSale", new HarvestSale());
        model.addAttribute("pageTitle", "Add New Harvest-Sale");
        return "harvest_sale/harvestSale_form";
    }

    @PostMapping("/harvestSales/save")
    public String saveHarvestSale(HarvestSale harvestSale, RedirectAttributes redirectAttributes) {
        harvestSaleService.saveHarvestSale(harvestSale);
        redirectAttributes.addFlashAttribute("message", "The harvest-sale has been saved successfully!");
        return "redirect:/harvests";
    }

    @GetMapping("/harvestSales/edit/{id}")
    public String showHarvestSaleEditForm(@PathVariable("id") Integer id, Model model) {
        HarvestSale harvestSale = harvestSaleService.findHarvestSaleById(id);
        List<Harvest> harvests = harvestService.findAllHarvest();
        List<Sale> sales = saleService.findAllSales();
        model.addAttribute("harvests", harvests);
        model.addAttribute("sales", sales);
        model.addAttribute("harvestSale", harvestSale);
        model.addAttribute("pageTitle", "Edit Harvest-Sale (ID: " + id + ")");
        return "harvest_sale/harvestSale_form";
    }

    @GetMapping("/harvestSales/delete/{id}")
    public String deleteHarvestSale(@PathVariable("id") Integer id, Model model,
                                RedirectAttributes redirectAttributes) {
        harvestSaleService.deleteHarvestSale(id);
        redirectAttributes.addFlashAttribute("message", "The harvest-sale ID " + id + " has been deleted!");
        return "redirect:/harvests";
    }

    @GetMapping("/harvestSales/findTotalSaleSum")
    public String findTotalSaleSum(Model model, @ModelAttribute("myHarvest") HarvestDate harvest) {
        Double resultSum = harvestSaleService.findTotalSaleSum();
        List<Harvest> harvests = harvestService.findAllHarvest();
        List<HarvestSale> harvestSales = harvestSaleService.findAllHarvestSales();
        model.addAttribute("harvests", harvests);
        model.addAttribute("harvestSales", harvestSales);
        model.addAttribute("resultSum", resultSum);
        return "harvest/harvests";
    }
}
