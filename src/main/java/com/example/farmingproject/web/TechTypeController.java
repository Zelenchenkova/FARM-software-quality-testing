package com.example.farmingproject.web;

import com.example.farmingproject.domain.TechType;
import com.example.farmingproject.service.TechTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class TechTypeController {

    private final TechTypeService techTypeService;

    @GetMapping("/techType")
    public String showAllTechTypes(Model model) {
        List<TechType> techTypes = techTypeService.findAllTechTypes();
        model.addAttribute("techTypes", techTypes);
        return "techTypes";
    }
}
