package com.example.farmingproject.web;

import com.example.farmingproject.domain.Crop;
import com.example.farmingproject.domain.Harvest;
import com.example.farmingproject.domain.HarvestSale;
import com.example.farmingproject.jpql.HarvestDate;
import com.example.farmingproject.service.CropService;
import com.example.farmingproject.service.HarvestSaleService;
import com.example.farmingproject.service.HarvestService;
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
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestSaleService harvestSaleService;
    private final CropService cropService;

    @GetMapping(value = "/harvests")
    public String showAllHarvests(Model model, @ModelAttribute("myHarvest") HarvestDate harvest) {
        List<Harvest> harvests = harvestService.findAllHarvest();
        List<HarvestSale> harvestSales = harvestSaleService.findAllHarvestSales();
        model.addAttribute("harvests", harvests);
        model.addAttribute("harvestSales", harvestSales);
        return "harvest/harvests";
    }

    @GetMapping("/harvests/new")
    public String showNewHarvestForm(Model model) {
        List<Crop> crops = cropService.findAllCrops();
        model.addAttribute("crops", crops);
        model.addAttribute("harvest", new Harvest());
        model.addAttribute("pageTitle", "Add New Harvest");
        return "harvest/harvest_form";
    }

    @PostMapping("/harvests/save")
    public String saveHarvest(Harvest harvest, RedirectAttributes redirectAttributes) {
        harvestService.saveHarvest(harvest);
        redirectAttributes.addFlashAttribute("message", "The harvest has been saved successfully!");
        return "redirect:/harvests";
    }

    @GetMapping("/harvests/edit/{id}")
    public String showHarvestEditForm(@PathVariable("id") Integer id, Model model) {
        Harvest harvest = harvestService.findHarvestById(id);
        List<Crop> crops = cropService.findAllCrops();
        model.addAttribute("crops", crops);
        model.addAttribute("harvest", harvest);
        model.addAttribute("pageTitle", "Edit Harvest (ID: " + id + ")");
        return "harvest/harvest_form";
    }

    @GetMapping("/harvests/delete/{id}")
    public String deleteHarvest(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes) {
        harvestService.deleteHarvest(id);
        redirectAttributes.addFlashAttribute("message", "The harvest ID " + id + " has been deleted!");
        return "redirect:/harvests";
    }

    @GetMapping("/harvests/harvestByWeight")
    public String showHarvestByWeight(Model model, @ModelAttribute("myHarvest") HarvestDate harvest) {
        Double result = harvestService.findHarvestByWeight(harvest.getDateStart(), harvest.getDateFinish());
        List<Harvest> harvests = harvestService.findAllHarvest();
        List<HarvestSale> harvestSales = harvestSaleService.findAllHarvestSales();
        model.addAttribute("harvests", harvests);
        model.addAttribute("harvestSales", harvestSales);
        model.addAttribute("resultNum", result);
        return "harvest/harvests";
    }
}
