package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.ShowModelInfoDto;
import com.example.springtest.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private final ModelService modelService;

    @Autowired
    public HomeController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/catalog")
    public String catalogPage(@RequestParam(name = "searchQuery", required = false) String searchQuery, Model model) {
        List<ShowModelInfoDto> searchResults;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            searchResults = modelService.searchModels(searchQuery.trim());
        } else {
            searchResults = modelService.getAllModels();
        }
        model.addAttribute("models", searchResults);
        return "catalog";
    }
}
