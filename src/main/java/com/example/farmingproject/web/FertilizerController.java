package com.example.farmingproject.web;

import com.example.farmingproject.domain.Fertilizer;
import com.example.farmingproject.domain.FertilizerType;
import com.example.farmingproject.domain.Provider;
import com.example.farmingproject.jpql.FertsNamesByProvider;
import com.example.farmingproject.service.FertilizerService;
import com.example.farmingproject.service.FertilizerTypeService;
import com.example.farmingproject.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class FertilizerController {

    private final FertilizerService fertilizerService;
    private final FertilizerTypeService fertilizerTypeService;
    private final ProviderService providerService;

    private List<String> ls;

    @GetMapping(value = "/fertilizers")
    public String showAllFertilizers(Model model, @ModelAttribute ("providerName")Provider provider) {
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        List<Provider> providers = providerService.findAllProviders();
        model.addAttribute("providers", providers);
        model.addAttribute("fertilizers", fertilizers);
        return "fertilizer/fertilizers";
    }

    @GetMapping("/fertilizers/new")
    public String showNewFertilizerForm(Model model) {
        List<FertilizerType> fertilizerTypes = fertilizerTypeService.findAllFertilizerTypes();
        model.addAttribute("fertilizerTypes", fertilizerTypes);
        model.addAttribute("fertilizer", new Fertilizer());
        model.addAttribute("pageTitle", "Add New Fertilizer");
        return "fertilizer/fertilizer_form";
    }

    @PostMapping("/fertilizers/save")
    public String saveFertilizer(Fertilizer fertilizer, RedirectAttributes redirectAttributes) {
        fertilizerService.saveFertilizer(fertilizer);
        redirectAttributes.addFlashAttribute("message", "The fertilizer has been saved successfully!");
        return "redirect:/fertilizers";
    }

    @GetMapping("/fertilizers/edit/{id}")
    public String showFertilizerEditForm(@PathVariable("id") Integer id, Model model) {
        Fertilizer fertilizer = fertilizerService.findFertilizerById(id);
        List<FertilizerType> fertilizerTypes = fertilizerTypeService.findAllFertilizerTypes();
        model.addAttribute("fertilizerTypes", fertilizerTypes);
        model.addAttribute("fertilizer", fertilizer);
        model.addAttribute("pageTitle", "Edit Fertilizer (ID: " + id + ")");
        return "fertilizer/fertilizer_form";
    }

    @GetMapping("/fertilizers/delete/{id}")
    public String deleteFertilizer(@PathVariable("id") Integer id, Model model,
                                       RedirectAttributes redirectAttributes) {
        fertilizerService.deleteFertilizer(id);
        redirectAttributes.addFlashAttribute("message", "The fertilizer ID " + id + " has been deleted!");
        return "redirect:/fertilizers";
    }

    @GetMapping("/fertilizers/organicFertilizers")
    public String showOrganicFertilizers(Model model) {
        List<Fertilizer> organicFertilizers = fertilizerService.findOrganicFertilizers();
        model.addAttribute("organicFertilizers", organicFertilizers);
        return "fertilizer/organic_fertilizers";
    }

    @GetMapping("/fertilizers/unusedFertilizers")
    public String showUnusedFertilizers(Model model) {
        List<Fertilizer> unusedFertilizers = fertilizerService.findUnusedFertilizers();
        model.addAttribute("unusedFertilizers", unusedFertilizers);
        return "fertilizer/unused_fertilizers";
    }

    @GetMapping("/fertilizers/fertsNamesByProvider")
    public String showFertsNamesByProvider(Model model,
                                           @ModelAttribute ("providerName")Provider provider) {
        Set<FertsNamesByProvider> set = fertilizerService.findFertsNamesByProvider(provider.getName());
        model.addAttribute("set", set);
        ls.add(provider.getName());
        return "fertilizer/ferts_names_by_provider";
    }

    @GetMapping("/fertilizers/mostPopularFertilizer")
    public String showMostPopularFertilizer(Model model,
                                            @ModelAttribute ("providerName")Provider provider,
                                            RedirectAttributes redirectAttributes) {
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        fertilizerService.setColumnMostPopularFertilizer();
        model.addAttribute("fertilizers", fertilizers);
        redirectAttributes.addFlashAttribute("message", "The most popular fertilizer has been found!");
        return "redirect:/fertilizers";
    }

    @GetMapping("/fertilizers/export")
    public void exportFertilizersToPDF(HttpServletResponse response) throws IOException {
        fertilizerService.exportToPDFFertilizer(response, ls.get(0));
    }
}
