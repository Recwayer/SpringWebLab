package com.example.springtest.controllers.web;

import com.example.springtest.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/model")
public class ModelController {
    private ModelService modelService;
    @Autowired
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
