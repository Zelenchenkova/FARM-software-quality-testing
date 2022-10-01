package com.example.farmingproject.web;

import com.example.farmingproject.entities.CropWork;
import com.example.farmingproject.entities.CropWorkTech;
import com.example.farmingproject.entities.Tech;
import com.example.farmingproject.service.CropWorkService;
import com.example.farmingproject.service.CropWorkTechService;
import com.example.farmingproject.service.TechService;
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
public class CropWorkTechController {

    private final CropWorkTechService cropWorkTechService;

    private final CropWorkService cropWorkService;

    private final TechService techService;

    @GetMapping("/cropWorkTeches/new")
    public String showAllCropWorkTeches(Model model) {
        List<CropWork> cropWorks = cropWorkService.findAllCropWorks();
        List<Tech> teches = techService.findAllTeches();
        model.addAttribute("cropWorks", cropWorks);
        model.addAttribute("teches", teches);
        model.addAttribute("cropWorkTech", new CropWorkTech());
        return "crop_work_tech/crop_work_tech_form";
    }

    @PostMapping("/cropWorkTeches/save")
    public String saveCropWorkTech(CropWorkTech cropWorkTech, RedirectAttributes redirectAttributes) {
        cropWorkTechService.saveCropWorkTech(cropWorkTech);
        redirectAttributes.addFlashAttribute("message", "The crop-work-tech has been saved successfully!");
        return "redirect:/cropWorks";
    }

    @GetMapping("/cropWorkTeches/edit/{id}")
    public String showCropWorkTechEditForm(@PathVariable("id") Integer id, Model model) {
        CropWorkTech cropWorkTech = cropWorkTechService.findCropWorkTechById(id);
        List<CropWork> cropWorks = cropWorkService.findAllCropWorks();
        List<Tech> teches = techService.findAllTeches();
        model.addAttribute("cropWorks", cropWorks);
        model.addAttribute("teches", teches);
        model.addAttribute("cropWorkTech", cropWorkTech);
        model.addAttribute("pageTitle", "Edit CropWorkTech (ID: " + id + ")");
        return "crop_work_tech/crop_work_tech_form";
    }

    @GetMapping("/cropWorkTeches/delete/{id}")
    public String deleteCropWorkTech(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        cropWorkTechService.deleteCropWorkTech(id);
        redirectAttributes.addFlashAttribute("message", "The crop-work-tech ID " + id + " has been deleted!");
        return "redirect:/cropWorks";
    }
}
