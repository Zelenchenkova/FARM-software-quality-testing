package com.example.farmingproject.web;

import com.example.farmingproject.domain.*;
import com.example.farmingproject.service.TechTypeService;
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
public class WorkController {

    private final WorkService workService;

    private final TechTypeService techTypeService;

    @GetMapping("/works")
    public String showAllWorks(Model model) {
        List<Work> works = workService.findAllWorks();
        model.addAttribute("works", works);
        return "work/works";
    }

    @GetMapping("/works/new")
    public String showNewWorkForm(Model model) {
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        model.addAttribute("work", new Work());
        model.addAttribute("pageTitle", "Add New Work");
        return "work/work_form";
    }

    @PostMapping("/works/save")
    public String saveWork(Work work, RedirectAttributes redirectAttributes) {
        workService.saveWork(work);
        redirectAttributes.addFlashAttribute("message", "The work has been saved successfully!");
        return "redirect:/works";
    }

    @GetMapping("/works/edit/{id}")
    public String showWorkEditForm(@PathVariable("id") Integer id,  Model model) {
        Work work = workService.findWorkById(id);
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        model.addAttribute("work", work);
        model.addAttribute("pageTitle", "Edit Work (ID: " + id + ")");
        return "work/work_form";
    }

    @GetMapping("/works/delete/{id}")
    public String deleteWork(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        workService.deleteWork(id);
        redirectAttributes.addFlashAttribute("message", "The work ID " + id + " has been deleted!");
        return "redirect:/works";
    }
}
