package com.example.farmingproject.web;

import com.example.farmingproject.entities.Tech;
import com.example.farmingproject.entities.TechType;
import com.example.farmingproject.jpql.TechByYear;
import com.example.farmingproject.service.TechService;
import com.example.farmingproject.service.TechTypeService;
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
public class TechController {

    private final TechService techService;

    private final TechTypeService techTypeService;

    @GetMapping("/teches")
    public String showAllTeches(Model model, @ModelAttribute("myTech") TechByYear tech) {
        List<Tech> teches = techService.findAllTeches();
        model.addAttribute("teches", teches);
        return "tech/teches";
    }

    @GetMapping("/teches/new")
    public String showNewTechForm(Model model) {
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        model.addAttribute("tech", new Tech());
        model.addAttribute("pageTitle", "Add New Tech");
        return "tech/tech_form";
    }

    @PostMapping("/teches/save")
    public String saveTech(Tech tech, RedirectAttributes redirectAttributes) {
        techService.saveTech(tech);
        redirectAttributes.addFlashAttribute("message", "The tech has been saved successfully!");
        return "redirect:/teches";
    }

    @GetMapping("/teches/edit/{id}")
    public String showTechEditForm(@PathVariable("id") Integer id, Model model) {
        Tech tech = techService.findTechById(id);
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        model.addAttribute("tech", tech);
        model.addAttribute("pageTitle", "Edit TechType (ID: " + id + ")");
        return "tech/tech_form";
    }

    @GetMapping("/teches/delete/{id}")
    public String deleteTech(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        techService.deleteTech(id);
        redirectAttributes.addFlashAttribute("message", "The tech ID " + id + " has been deleted!");
        return "redirect:/teches";
    }

    @GetMapping("/teches/findSpecificTech")
    public String findSpecificTech(Model model) {
        List<Tech> teches = techService.findWateringTech();
        model.addAttribute("teches", teches);
        return "tech/specific_tech";
    }

    @GetMapping("/teches/findTechesByYear")
    public String findTechByYear(Model model, @ModelAttribute ("myTech") TechByYear tech) {
        List<Tech> teches = techService.findTechByYear(tech.getStartYear(), tech.getEndYear());
        model.addAttribute("teches", teches);
        return "tech/tech_by_year";
    }

    @GetMapping("/teches/findNewestTech")
    public String findNewestTech(Model model) {
        List<Tech> teches = techService.findNewestTech();
        model.addAttribute("teches", teches);
        return "tech/newest_tech";
    }
}
