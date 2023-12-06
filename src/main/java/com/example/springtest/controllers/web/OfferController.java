package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.AddOfferDto;
import com.example.springtest.dtos.web.ShowDetailedOfferInfoDto;
import com.example.springtest.dtos.web.UpdateOfferDto;
import com.example.springtest.dtos.web.UpdateUserDto;
import com.example.springtest.exceptions.ServerException;
import com.example.springtest.services.ModelService;
import com.example.springtest.services.OfferService;
import com.example.springtest.services.UserService;
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
@RequestMapping("/offer")
public class OfferController {
    private OfferService offerService;
    private ModelService modelService;
    private UserService userService;

    @Autowired
    public OfferController(OfferService offerService, ModelService modelService, UserService userService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAllUsers());
        return "offer-add";
    }
    @ModelAttribute("offerModel")
    public AddOfferDto initModel() {
        return new AddOfferDto();
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);
            return "redirect:/offer/add";
        }
        offerService.addOffer(offerModel);
        return "redirect:/offer/all";
    }
    @GetMapping("/all")
    public String showAllOffers(Model model) {
        model.addAttribute("offerInfos", offerService.getAllOffers());
        return "offer-all";
    }

    @GetMapping("/{uuid}")
    public String showOfferDetails(@PathVariable("uuid") UUID uuid, Model model) {
        Optional<ShowDetailedOfferInfoDto> optional = offerService.getDetails(uuid);
        if (optional.isPresent()) {
            model.addAttribute("offerDetails", optional.get());
            return "offer-details";
        } else {
            throw new ServerException.NotFoundException("offerDetails not found with UUID is" + uuid);
        }
    }

    @GetMapping("/delete/{uuid}")
    public String deleteOffer(@PathVariable("uuid") UUID uuid) {
        offerService.delete(uuid);
        return "redirect:/offer/all";
    }
    @GetMapping("/update/{uuid}")
    public String updateOffer(@PathVariable("uuid") UUID uuid, Model model) {
        model.addAttribute("availableModels", modelService.getAllModels());
        model.addAttribute("availableUsers", userService.getAllUsers());
        model.addAttribute("offerModelUp", offerService.getUpdateOffer(uuid).get());
        return "offer-update";
    }

    @PostMapping("/update/{uuid}")
    public String updateOffer(@PathVariable("uuid") UUID uuid, @Valid UpdateOfferDto offerModelUp, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModelUp", offerModelUp);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModelUp", bindingResult);
            return String.format("redirect:/offer/update/%s", uuid);
        }
        offerService.update(uuid, offerModelUp);
        return String.format("redirect:/offer/%s", uuid);
    }
}
