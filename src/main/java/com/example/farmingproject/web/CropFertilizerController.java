package com.example.farmingproject.web;

import com.example.farmingproject.domain.Crop;
import com.example.farmingproject.domain.CropFertilizer;
import com.example.farmingproject.domain.Fertilizer;
import com.example.farmingproject.service.CropFertilizerService;
import com.example.farmingproject.service.CropService;
import com.example.farmingproject.service.FertilizerService;
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
public class CropFertilizerController {

    private final CropFertilizerService cropFertilizerService;
    private final FertilizerService fertilizerService;
    private final CropService cropService;

    @GetMapping(value = "/cropFertilizers")
    public String showAllCropFertilizers(Model model) {
        List<CropFertilizer> cropFertilizers = cropFertilizerService.findAllCropFertilizers();
        model.addAttribute("cropFertilizers", cropFertilizers);
        return "crop_fertilizer/cropFertilizers";
    }

    @GetMapping("/cropFertilizers/new")
    public String showNewCropFertilizerForm(Model model) {
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        List<Crop> crops = cropService.findAllCrops();
        model.addAttribute("fertilizers", fertilizers);
        model.addAttribute("crops", crops);
        model.addAttribute("cropFertilizer", new CropFertilizer());
        model.addAttribute("pageTitle", "Add New Crop-Fertilizer");
        return "crop_fertilizer/crop_fertilizer_form";
    }

    @PostMapping("/cropFertilizers/save")
    public String saveCropFertilizer(CropFertilizer cropFertilizer, RedirectAttributes redirectAttributes) {
        cropFertilizerService.saveCropFertilizer(cropFertilizer);
        redirectAttributes.addFlashAttribute("message", "The crop-fertilizer has been saved successfully!");
        return "redirect:/cropFertilizers";
    }

    @GetMapping("/cropFertilizers/edit/{id}")
    public String showCropFertilizerEditForm(@PathVariable("id") Integer id, Model model) {
        List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
        List<Crop> crops = cropService.findAllCrops();
        CropFertilizer cropFertilizer = cropFertilizerService.findCropFertilizerById(id);
        model.addAttribute("fertilizers", fertilizers);
        model.addAttribute("crops", crops);
        model.addAttribute("cropFertilizer", cropFertilizer);
        model.addAttribute("pageTitle", "Edit Crop-Fertilizer (ID: " + id + ")");
        return "crop_fertilizer/crop_fertilizer_form";
    }

    @GetMapping("/cropFertilizers/delete/{id}")
    public String deleteCropFertilizer(@PathVariable("id") Integer id, Model model,
                                   RedirectAttributes redirectAttributes) {
        cropFertilizerService.deleteCropFertilizer(id);
        redirectAttributes.addFlashAttribute("message", "The crop-fertilizer ID " + id + " has been deleted!");
        return "redirect:/cropFertilizers";
    }
}
