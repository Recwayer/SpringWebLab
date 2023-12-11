package com.example.springtest.controllers.web;

import com.example.springtest.dtos.web.AddUserDto;
import com.example.springtest.dtos.web.ShowDetailedUserInfoDto;
import com.example.springtest.dtos.web.UpdateUserDto;
import com.example.springtest.exceptions.ClientSecurityException;
import com.example.springtest.exceptions.ServerException;
import com.example.springtest.services.UserService;
import com.example.springtest.services.sec.AuthService;
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
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private AuthService authService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/add")
    public String addUser() {
        return "user-add";
    }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/user/add";
        }
        try {
            authService.register(userModel);
        } catch (ClientSecurityException e) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("badConfirm", true);
            return "redirect:/user/add";
        }
        return "redirect:/user/all";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("userInfos", userService.getAllUsers());
        return "user-all";
    }

    @GetMapping("/{uuid}")
    public String showUserDetails(@PathVariable("uuid") UUID uuid, Model model) {
        Optional<ShowDetailedUserInfoDto> optional = userService.getDetails(uuid);
        if (optional.isPresent()) {
            model.addAttribute("userDetails", optional.get());
            return "user-details";
        } else {
            throw new ServerException.NotFoundException("userDetails not found with UUID is" + uuid);
        }
    }

    @GetMapping("/delete/{uuid}")
    public String deleteUser(@PathVariable("uuid") UUID uuid) {
        userService.delete(uuid);
        return "redirect:/user/all";
    }

    @GetMapping("/update/{uuid}")
    public String updateModel(@PathVariable("uuid") UUID uuid, Model model) {
        model.addAttribute("userModelUp", userService.getUpdateUser(uuid).get());
        return "user-update";
    }

    @PostMapping("/update/{uuid}")
    public String updateModel(@PathVariable("uuid") UUID uuid, @Valid UpdateUserDto userModelUp, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModelUp", userModelUp);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModelUp", bindingResult);
            return String.format("redirect:/user/update/%s", uuid);
        }
        userService.update(uuid, userModelUp);
        return String.format("redirect:/user/%s", uuid);
    }

    @PostMapping("/changeActive/{uuid}")
    public String ban(@PathVariable("uuid") UUID uuid) {
        userService.changeActive(uuid);
        return String.format("redirect:/user/%s", uuid);
    }
}
