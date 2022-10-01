package com.example.farmingproject.web;

import com.example.farmingproject.entities.Culture;
import com.example.farmingproject.jpql.LatestCropDateForCultures;
import com.example.farmingproject.service.CultureService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class CultureController {

    private final CultureService cultureService;

    @GetMapping(value = "/cultures")
    public String showAllCultures(Model model, @ModelAttribute("myCulture") Culture culture) {
        List<Culture> cultures = cultureService.findAllCultures();
        model.addAttribute("cultures", cultures);
        return "culture/cultures";
    }

    @GetMapping("/cultures/new")
    public String showNewCultureForm(Model model) {
        List<String> seasons = Arrays.asList("SPRING", "SUMMER", "WINTER", "AUTUMN");
        model.addAttribute("seasons", seasons);
        model.addAttribute("culture", new Culture());
        model.addAttribute("pageTitle", "Add New Culture");
        return "culture/culture_form";
    }

    @PostMapping("/cultures/save")
    public String saveCulture(Culture culture, RedirectAttributes redirectAttributes) {
        cultureService.saveCulture(culture);
        redirectAttributes.addFlashAttribute("message", "The culture has been saved successfully!");
        return "redirect:/cultures";
    }

    @GetMapping("/cultures/edit/{id}")
    public String showCultureEditForm(@PathVariable("id") Integer id, Model model) {
        Culture culture = cultureService.findCultureById(id);
        List<String> seasons = Arrays.asList("SPRING", "SUMMER", "WINTER", "AUTUMN");
        model.addAttribute("seasons", seasons);
        model.addAttribute("culture", culture);
        model.addAttribute("pageTitle", "Edit Culture (ID: " + id + ")");
        return "culture/culture_form";
    }

    @GetMapping("/cultures/delete/{id}")
    public String deleteCulture(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        cultureService.deleteCulture(id);
        redirectAttributes.addFlashAttribute("message", "The culture ID " + id + " has been deleted!");
        return "redirect:/cultures";
    }

    @GetMapping("/cultures/cultureByName")
    public String showCultureByName(Model model, @ModelAttribute("myCulture") Culture culture) {
        List<Culture> cultures = cultureService.findCultureByName(culture.getName());
        model.addAttribute("cultures", cultures);
        return "culture/cultures";
    }

    @GetMapping("/cultures/latestCropDate")
    public String showLatestCropDate(Model model) {
        Set<LatestCropDateForCultures> set = cultureService.findLatestCropDateForCultures();
        model.addAttribute("set", set);
        return "culture/latest_crop_date";
    }

    @GetMapping("/cultures/unusedCultures")
    public String showUnusedCultures(Model model) {
        List<String> list = cultureService.findUnusedCultures();
        model.addAttribute("list", list);
        return "culture/unused_cultures";
    }

    @GetMapping("/cultures/mostPopularCulture")
    public String findMostPopularCulture(Model model, RedirectAttributes redirectAttributes,
                                         @ModelAttribute("myCulture") Culture culture) {
        List<Culture> cultures = cultureService.findAllCultures();
        cultureService.setColumnMostPopularCulture();
        model.addAttribute("cultures", cultures);
        redirectAttributes.addFlashAttribute("message", "The most popular culture has been found!");
        return "redirect:/cultures";
    }

    @GetMapping("/cultures/export")
    public void exportCulturesToPDF(HttpServletResponse response) throws IOException {
        cultureService.exportToPDFCulture(response);
    }
}
