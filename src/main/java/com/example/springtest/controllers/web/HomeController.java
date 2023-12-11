package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.ShowModelInfoDto;
import com.example.springtest.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public String homePage(Model model) {
        model.addAttribute("popularModels", modelService.popularModels());
        return "home";
    }

    @GetMapping("/catalog")
    public String catalogPage(@RequestParam(name = "searchQuery", required = false) String searchQuery,
                              @RequestParam(name = "sortField", defaultValue = "name") String sortField,
                              @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder,
                              Model model) {
        List<ShowModelInfoDto> searchResults;
        Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc") ? Sort.Order.asc(sortField) : Sort.Order.desc(sortField));
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            searchResults = modelService.searchModels(searchQuery.trim(), sort);
            model.addAttribute("searchQuery", searchQuery.trim());
        } else {
            searchResults = modelService.getAllModels(sort);
            model.addAttribute("searchQuery", searchQuery);
        }
        model.addAttribute("models", searchResults);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);

        return "catalog";
    }
}
