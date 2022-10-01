package com.example.farmingproject.web;

import com.example.farmingproject.entities.Fertilizer;
import com.example.farmingproject.entities.Provider;
import com.example.farmingproject.entities.ProviderFertilizer;
import com.example.farmingproject.jpql.SumByDate;
import com.example.farmingproject.service.FertilizerService;
import com.example.farmingproject.service.ProviderFertilizerService;
import com.example.farmingproject.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class ProviderFertilizerController {

    private final ProviderFertilizerService providerFertilizerService;
    private final FertilizerService fertilizerService;
    private final ProviderService providerService;

    @GetMapping(value = "/providerFertilizers")
    public String showAllProviderFertilizers(Model model) {
        List<ProviderFertilizer> providerFertilizers = providerFertilizerService.findAllProviderFertilizers();
        model.addAttribute("providerFertilizers", providerFertilizers);
        return "provider_fertilizer/providerFertilizers";
    }

    @GetMapping("/providerFertilizers/new")
    public String showNewProviderFertilizerForm(Model model) {
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        List<Provider> providers = providerService.findAllProviders();
        model.addAttribute("fertilizers", fertilizers);
        model.addAttribute("providers", providers);
        model.addAttribute("providerFertilizer", new ProviderFertilizer());
        model.addAttribute("pageTitle", "Add New Provider-Fertilizer");
        return "provider_fertilizer/provider_fertilizer_form";
    }

    @PostMapping("/providerFertilizers/save")
    public String saveProviderFertilizer(ProviderFertilizer providerFertilizer, RedirectAttributes redirectAttributes) {
        providerFertilizerService.saveProviderFertilizer(providerFertilizer);
        redirectAttributes.addFlashAttribute("message", "The provider-fertilizer has been saved successfully!");
        return "redirect:/providerFertilizers";
    }

    @GetMapping("/providerFertilizers/edit/{id}")
    public String showProviderFertilizerEditForm(@PathVariable("id") Integer id, Model model) {
        ProviderFertilizer providerFertilizer = providerFertilizerService.findProviderFertilizerById(id);
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        List<Provider> providers = providerService.findAllProviders();
        model.addAttribute("fertilizers", fertilizers);
        model.addAttribute("providers", providers);
        model.addAttribute("providerFertilizer", providerFertilizer);
        model.addAttribute("pageTitle", "Edit Fertilizer (ID: " + id + ")");
        return "provider_fertilizer/provider_fertilizer_form";
    }

    @GetMapping("/providerFertilizers/delete/{id}")
    public String deleteProviderFertilizer(@PathVariable("id") Integer id, Model model,
                                   RedirectAttributes redirectAttributes) {
        providerFertilizerService.deleteProviderFertilizer(id);
        redirectAttributes.addFlashAttribute("message", "The provider-fertilizer ID " + id + " has been deleted!");
        return "redirect:/providerFertilizers";
    }

    @GetMapping("/providerFertilizers/sumByDate")
    public String showProviderFertilizersSumByDate(Model model) {
        Set<SumByDate> set = providerFertilizerService.findSumByDate();
        model.addAttribute("set", set);
        return "provider_fertilizer/sum_by_date";
    }

    @GetMapping("/providerFertilizers/export")
    public void exportProviderFertilizersToPDF(HttpServletResponse response) throws IOException {
        providerFertilizerService.exportToPDFProviderFertilizer(response);
    }
}
