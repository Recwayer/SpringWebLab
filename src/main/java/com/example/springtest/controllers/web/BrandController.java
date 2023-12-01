package com.example.springtest.controllers.web;

import com.example.springtest.dtos.BrandDTO;
import com.example.springtest.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {
   private BrandService brandService;
    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String viewAllBrands(Model model) {
        List<BrandDTO> brands = brandService.getAll();
        model.addAttribute("brands", brands);
        return "brands";
    }
}
