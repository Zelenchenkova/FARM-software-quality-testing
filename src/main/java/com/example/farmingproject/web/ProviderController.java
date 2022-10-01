package com.example.farmingproject.web;

import com.example.farmingproject.entities.Provider;
import com.example.farmingproject.jpql.CustomersAndProviders;
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
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping(value = "/providers")
    public String showAllProviders(Model model, @ModelAttribute("myProvider") Provider provider) {
        List<Provider> providers = providerService.findAllProviders();
        model.addAttribute("providers", providers);
        return "provider/providers";
    }

    @GetMapping("/providers/new")
    public String showNewProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        model.addAttribute("pageTitle", "Add New Provider");
        return "provider/provider_form";
    }

    @PostMapping("/providers/save")
    public String saveProvider(Provider provider, RedirectAttributes redirectAttributes) {
        providerService.saveProvider(provider);
        redirectAttributes.addFlashAttribute("message", "The provider has been saved successfully!");
        return "redirect:/providers";
    }

    @GetMapping("/providers/edit/{id}")
    public String showProviderEditForm(@PathVariable("id") Integer id, Model model) {
        Provider provider = providerService.findProviderById(id);
        model.addAttribute("provider", provider);
        model.addAttribute("pageTitle", "Edit Provider (ID: " + id + ")");
        return "provider/provider_form";
    }

    @GetMapping("/providers/delete/{id}")
    public String deleteProvider(@PathVariable("id") Integer id, Model model,
                                           RedirectAttributes redirectAttributes) {
        providerService.deleteProvider(id);
        redirectAttributes.addFlashAttribute("message", "The provider ID " + id + " has been deleted!");
        return "redirect:/providers";
    }

    @GetMapping("/providers/providerByName")
    public String showProviderByName(Model model, @ModelAttribute("myProvider") Provider provider) {
        List<Provider> providersByName = providerService.findProviderByName(provider.getName());
        model.addAttribute("providers", providersByName);
        return "provider/providers";
    }

    @GetMapping("/providers/customersAndProviders")
    public String showCustomersAndProviders(Model model) {
        Set<CustomersAndProviders> set = providerService.findAllCustomersAndProviders();
        model.addAttribute("set", set);
        return "provider/customers_and_providers";
    }

    @GetMapping("/providers/export")
    public void exportProvidersToPDF(HttpServletResponse response) throws IOException {
        providerService.exportToPDFProvider(response);
    }
}
