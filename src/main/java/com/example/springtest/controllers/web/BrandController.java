package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.AddBrandDto;
import com.example.springtest.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brand-add";
    }

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @PostMapping("/add")
    public String addBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel", bindingResult);
            return "redirect:/brand/add";
        }
        brandService.addBrand(brandModel);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brandInfos", brandService.getAllBrands());
        return "brand-all";
    }
}
