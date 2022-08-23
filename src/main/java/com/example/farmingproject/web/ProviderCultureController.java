package com.example.farmingproject.web;

import com.example.farmingproject.domain.Culture;
import com.example.farmingproject.domain.Provider;
import com.example.farmingproject.domain.ProviderCulture;
import com.example.farmingproject.service.CultureService;
import com.example.farmingproject.service.ProviderCultureService;
import com.example.farmingproject.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProviderCultureController {

    private final ProviderCultureService providerCultureService;
    private final ProviderService providerService;
    private final CultureService cultureService;

    @GetMapping(value = "/providerCultures")
    public String showAllProviderCultures(Model model) {
        List<ProviderCulture> providerCultures = providerCultureService.findAllProviderCultures();
        model.addAttribute("providerCultures", providerCultures);
        return "provider_culture/providerCultures";
    }

    @GetMapping("/providerCultures/new")
    public String showNewProviderCultureForm(Model model) {
        List<Provider> providers = providerService.findAllProviders();
        List<Culture> cultures = cultureService.findAllCultures();
        model.addAttribute("providers", providers);
        model.addAttribute("cultures", cultures);
        model.addAttribute("providerCulture", new ProviderCulture());
        model.addAttribute("pageTitle", "Add New Provider-Culture");
        return "provider_culture/provider_cultures_form";
    }

    @PostMapping("/providerCultures/save")
    public String saveProviderCulture(ProviderCulture providerCulture, RedirectAttributes redirectAttributes) {
        providerCultureService.saveProviderCulture(providerCulture);
        redirectAttributes.addFlashAttribute("message", "The provider-culture has been saved successfully!");
        return "redirect:/providerCultures";
    }

    @GetMapping("/providerCultures/edit/{id}")
    public String showProviderCultureEditForm(@PathVariable("id") Integer id, Model model) {
        ProviderCulture providerCulture = providerCultureService.findProviderCultureById(id);
        List<Provider> providers = providerService.findAllProviders();
        List<Culture> cultures = cultureService.findAllCultures();
        model.addAttribute("providers", providers);
        model.addAttribute("cultures", cultures);
        model.addAttribute("providerCulture", providerCulture);
        model.addAttribute("pageTitle", "Edit Provider-Culture (ID: " + id + ")");
        return "provider_culture/provider_cultures_form";
    }

    @GetMapping("/providerCultures/delete/{id}")
    public String deleteProviderCulture(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        providerCultureService.deleteProviderCulture(id);
        redirectAttributes.addFlashAttribute("message", "The provider-culture ID " + id + " has been deleted!");
        return "redirect:/providerCultures";
    }
}
