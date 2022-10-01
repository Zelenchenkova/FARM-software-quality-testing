package com.example.farmingproject.web;

import com.example.farmingproject.entities.TechType;
import com.example.farmingproject.jpql.AvgTechYearByType;
import com.example.farmingproject.service.TechTypeService;
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
public class TechTypeController {

    private final TechTypeService techTypeService;

    @GetMapping("/techTypes")
    public String showAllTechTypes(Model model) {
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        return "techType/techTypes";
    }

    @GetMapping("/techTypes/new")
    public String showNewTechTypeForm(Model model) {
        model.addAttribute("techType", new TechType());
        model.addAttribute("pageTitle", "Add New Tech Type");
        return "techType/techType_form";
    }

    @PostMapping("/techTypes/save")
    public String saveTechType(TechType techType, RedirectAttributes redirectAttributes) {
        techTypeService.saveTechType(techType);
        redirectAttributes.addFlashAttribute("message", "The tech-type has been saved successfully!");
        return "redirect:/techTypes";
    }

    @GetMapping("/techTypes/edit/{id}")
    public String showTechTypeEditForm(@PathVariable("id") Integer id, Model model) {
        TechType techType = techTypeService.findTechTypeById(id);
        model.addAttribute("techType", techType);
        model.addAttribute("pageTitle", "Edit TechType (ID: " + id + ")");
        return "techType/techType_form";
    }

    @GetMapping("/techTypes/delete/{id}")
    public String deleteTechType(@PathVariable("id") Integer id, Model model,
                              RedirectAttributes redirectAttributes) {
        techTypeService.deleteTechType(id);
        redirectAttributes.addFlashAttribute("message", "The tech-type ID " + id + " has been deleted!");
        return "redirect:/techTypes";
    }

    @GetMapping("/techTypes/showAvgTechYearByType")
    public String showAvgTechYearByType(Model model) {
        Set<AvgTechYearByType> set = techTypeService.findTheAvgTechYearByType();
        model.addAttribute("set", set);
        model.addAttribute("pageTitle", "Avg Tech Year by Type");
        return "techType/avgTechYearByType";
    }

    @GetMapping("/techTypes/export")
    public void exportTechTypesToPDF(HttpServletResponse response) throws IOException {
        techTypeService.exportToPDFTechType(response);
    }
}
