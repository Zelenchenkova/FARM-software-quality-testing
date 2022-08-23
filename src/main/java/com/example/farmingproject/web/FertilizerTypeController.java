package com.example.farmingproject.web;

import com.example.farmingproject.domain.FertilizerType;
import com.example.farmingproject.jpql.MostExpensiveFertilizerForTypes;
import com.example.farmingproject.service.FertilizerTypeService;
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
public class FertilizerTypeController {

    private final FertilizerTypeService fertilizerTypeService;

    @GetMapping(value = "/fertilizerTypes")
    public String showAllFertilizerTypes(Model model) {
        List<FertilizerType> fertilizerTypes = fertilizerTypeService.findAllFertilizerTypes();
        model.addAttribute("fertilizerTypes", fertilizerTypes);
        return "fertilizer_type/fertilizerTypes";
    }

    @GetMapping("/fertilizerTypes/new")
    public String showNewFertilizerTypesForm(Model model) {
        model.addAttribute("fertilizerType", new FertilizerType());
        model.addAttribute("pageTitle", "Add New Fertilizer-Type");
        return "fertilizer_type/fertilizerType_form";
    }

    @PostMapping("/fertilizerTypes/save")
    public String saveFertilizerType(FertilizerType fertilizerType, RedirectAttributes redirectAttributes) {
        fertilizerTypeService.saveFertilizerType(fertilizerType);
        redirectAttributes.addFlashAttribute("message", "The fertilizer-type has been saved successfully!");
        return "redirect:/fertilizerTypes";
    }

    @GetMapping("/fertilizerTypes/edit/{id}")
    public String showFertilizerTypeEditForm(@PathVariable("id") Integer id, Model model) {
        FertilizerType fertilizerType = fertilizerTypeService.findFertilizerTypeById(id);
        model.addAttribute("fertilizerType", fertilizerType);
        model.addAttribute("pageTitle", "Edit Fertilizer-Type (ID: " + id + ")");
        return "fertilizer_type/fertilizerType_form";
    }

    @GetMapping("/fertilizerTypes/delete/{id}")
    public String deleteFertilizerType(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        fertilizerTypeService.deleteFertilizerType(id);
        redirectAttributes.addFlashAttribute("message", "The fertilizer-type ID " + id + " has been deleted!");
        return "redirect:/fertilizerTypes";
    }

    @GetMapping("/fertilizerTypes/mostExpensiveFertilizersByTypes")
    public String showMostExpensiveFertilizerByTypes(Model model) {
        Set<MostExpensiveFertilizerForTypes> fertilizerForTypes = fertilizerTypeService.findMostExpensiveFertilizerForTypes();
        model.addAttribute("fertilizerForTypes", fertilizerForTypes);
        return "fertilizer_type/most_expensive_fertilizers";
    }

    @GetMapping("/fertilizerTypes/export")
    public void exportFertilizerTypesToPDF(HttpServletResponse response) throws IOException {
        fertilizerTypeService.exportToPDFFertilizerType(response);
    }
}
