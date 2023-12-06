package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.AddModelDto;
import com.example.springtest.dtos.web.ShowDetailedModelInfoDto;
import com.example.springtest.dtos.web.UpdateModelDto;
import com.example.springtest.exceptions.ServerException;
import com.example.springtest.services.BrandService;
import com.example.springtest.services.ModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModelController {
    private ModelService modelService;
    private BrandService brandService;

    @Autowired
    public void setModelService(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("availableBrands", brandService.getAllBrands());
        return "model-add";
    }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel", bindingResult);
            return "redirect:/model/add";
        }
        modelService.addModel(modelModel);
        return "redirect:/model/all";
    }

    @GetMapping("/all")
    public String showAllModels(Model model) {
        model.addAttribute("modelInfos", modelService.getAllModels());
        return "model-all";
    }

    @GetMapping("/{uuid}")
    public String showModelDetails(@PathVariable("uuid") UUID uuid, Model model) {
        Optional<ShowDetailedModelInfoDto> optional = modelService.getDetails(uuid);
        if (optional.isPresent()) {
            model.addAttribute("modelDetails", optional.get());
            return "model-details";
        } else {
            throw new ServerException.NotFoundException("modelDetails not found with UUID is" + uuid);
        }
    }

    @GetMapping("/delete/{uuid}")
    public String deleteModel(@PathVariable("uuid") UUID uuid) {
        modelService.delete(uuid);
        return "redirect:/model/all";
    }

    @GetMapping("/update/{uuid}")
    public String updateModel(@PathVariable("uuid") UUID uuid, Model model) {
        model.addAttribute("modelModelUp", modelService.getUpdateModel(uuid).get());
        model.addAttribute("availableBrands", brandService.getAllBrands());
        return "model-update";
    }

    @PostMapping("/update/{uuid}")
    public String updateModel(@PathVariable("uuid") UUID uuid, @Valid UpdateModelDto modelModelUp, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModelUp", modelModelUp);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModelUp", bindingResult);
            return String.format("redirect:/model/update/%s", uuid);
        }
        modelService.update(uuid, modelModelUp);
        return String.format("redirect:/model/%s", uuid);
    }
}
