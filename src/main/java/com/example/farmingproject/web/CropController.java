package com.example.farmingproject.web;

import com.example.farmingproject.entities.Crop;
import com.example.farmingproject.entities.Culture;
import com.example.farmingproject.jpql.CropDate;
import com.example.farmingproject.service.CropService;
import com.example.farmingproject.service.CultureService;
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
public class CropController {

    private final CropService cropService;
    private final CultureService cultureService;

    @GetMapping(value = "/crops")
    public String showAllCrops(Model model, @ModelAttribute("myCrop") CropDate cropDate) {
        List<Crop> crops = cropService.findAllCrops();
        model.addAttribute("crops", crops);
        return "crop/crops";
    }

    @GetMapping("/crops/new")
    public String showNewCropForm(Model model) {
        List<Culture> cultures = cultureService.findAllCultures();
        model.addAttribute("cultures", cultures);
        model.addAttribute("crop", new Crop());
        model.addAttribute("pageTitle", "Add New Crop");
        return "crop/crop_form";
    }

    @PostMapping("/crops/save")
    public String saveCrop(Crop crop, RedirectAttributes redirectAttributes) {
        cropService.saveCrop(crop);
        redirectAttributes.addFlashAttribute("message", "The crop has been saved successfully!");
        return "redirect:/crops";
    }

    @GetMapping("/crops/edit/{id}")
    public String showCropEditForm(@PathVariable("id") Integer id, Model model) {
        Crop crop = cropService.findCropById(id);
        List<Culture> cultures = cultureService.findAllCultures();
        model.addAttribute("cultures", cultures);
        model.addAttribute("crop", crop);
        model.addAttribute("pageTitle", "Edit Crop (ID: " + id + ")");
        return "crop/crop_form";
    }

    @GetMapping("/crops/delete/{id}")
    public String deleteCrops(@PathVariable("id") Integer id, Model model,
                                RedirectAttributes redirectAttributes) {
        cropService.deleteCrop(id);
        redirectAttributes.addFlashAttribute("message", "The crop ID " + id + " has been deleted!");
        return "redirect:/crops";
    }

    @GetMapping("/crops/cropByDate")
    public String showCultureByName(Model model, @ModelAttribute("myCrop") CropDate cropDate) {
        List<Crop> crops = cropService.findCropsByDate(cropDate.getDateStart(), cropDate.getDateEnd());
        model.addAttribute("crops", crops);
        return "crop/crops";
    }
}
