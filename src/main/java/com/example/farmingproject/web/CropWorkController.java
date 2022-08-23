package com.example.farmingproject.web;

import com.example.farmingproject.domain.*;
import com.example.farmingproject.service.CropService;
import com.example.farmingproject.service.CropWorkService;
import com.example.farmingproject.service.CropWorkTechService;
import com.example.farmingproject.service.WorkService;
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
public class CropWorkController {

    private final CropWorkService cropWorkService;

    private final CropService cropService;

    private final WorkService workService;

    private final CropWorkTechService cropWorkTechService;

    @GetMapping("/cropWorks")
    public String showAllCropWorks(Model model) {
        List<CropWork> cropWorks = cropWorkService.findAllCropWorks();
        List<CropWorkTech> cropWorkTeches = cropWorkTechService.findAllCropWorkTeches();
        model.addAttribute("cropWorkTeches", cropWorkTeches);
        model.addAttribute("cropWorks", cropWorks);
        return "crop_work/crop_works";
    }

    @GetMapping("/cropWorks/new")
    public String showNewCropWorkForm(Model model) {
        List<Crop> crops = cropService.findAllCrops();
        List<Work> works = workService.findAllWorks();
        model.addAttribute("crops", crops);
        model.addAttribute("works", works);
        model.addAttribute("cropWork", new CropWork());
        model.addAttribute("pageTitle", "Add New Crop-Work");
        return "crop_work/crop_work_form";
    }

    @PostMapping("/cropWorks/save")
    public String saveCropWork(CropWork cropWork, RedirectAttributes redirectAttributes) {
        cropWorkService.saveCropWork(cropWork);
        redirectAttributes.addFlashAttribute("message", "The crop-work has been saved successfully!");
        return "redirect:/cropWorks";
    }

    @GetMapping("/cropWorks/edit/{id}")
    public String showCropWorkEditForm(@PathVariable("id") Integer id, Model model) {
        CropWork cropWork = cropWorkService.findCropWorkById(id);
        List<Crop> crops = cropService.findAllCrops();
        List<Work> works = workService.findAllWorks();
        model.addAttribute("crops", crops);
        model.addAttribute("works", works);
        model.addAttribute("cropWork", cropWork);
        model.addAttribute("pageTitle", "Edit CropWork (ID: " + id + ")");
        return "crop_work/crop_work_form";
    }

    @GetMapping("/cropWorks/delete/{id}")
    public String deleteCropWork(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes) {
        cropWorkService.deleteCropWork(id);
        redirectAttributes.addFlashAttribute("message", "The crop-work ID " + id + " has been deleted!");
        return "redirect:/cropWorks";
    }
}
