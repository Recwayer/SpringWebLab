package com.example.springtest;

import com.example.springtest.dtos.*;
import com.example.springtest.models.enums.Category;
import com.example.springtest.models.enums.Engine;
import com.example.springtest.models.enums.Role;
import com.example.springtest.models.enums.Transmission;
import com.example.springtest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public void run(String... args) {
        BrandDTO brand = new BrandDTO(null,"Brand1");
        BrandDTO brand2 = new BrandDTO(null, "Brand2");
        brand = brandService.register(brand);
        brand2 = brandService.register(brand2);
        ModelDTO model = new ModelDTO(null,"Model1", Category.CAR, "image1.jpg", 2020, 2025, brand.getUuid());
        ModelDTO model2 = new ModelDTO(null,"Model2", Category.TRUCK, "image2.jpg", 2022, 2027, brand2.getUuid());
        model = modelService.register(model);
        model2 = modelService.register(model2);
        UserRoleDTO role = new UserRoleDTO(null, Role.ADMIN);
        UserRoleDTO role2 = new UserRoleDTO(null, Role.USER);
        role = userRoleService.register(role);
        role2 = userRoleService.register(role2);
        UserDTO user = new UserDTO(null,"user1", "password1", "Andrey", "Karpushin", true, role.getUuid(), "image3.jpg");
        UserDTO user2 = new UserDTO(null, "user2", "password2", "Matvey", "Ivanov", true, role2.getUuid(), "image4.jpg");
        user = userService.register(user);
        user2 = userService.register(user2);
        OfferDTO offer = new OfferDTO(null,"Description1", Engine.ELECTRIC, "image1.jpg", 10000, new BigDecimal("15000.00"), Transmission.AUTOMATIC, 2022, model.getUuid(), user.getUuid());
        OfferDTO offer2 = new OfferDTO(null,"Description2", Engine.DIESEL, "image2.jpg", 8000, new BigDecimal("18000.00"), Transmission.MANUAL, 2020, model2.getUuid(), user2.getUuid());
        OfferDTO offer3 = new OfferDTO(null,"Description3", Engine.DIESEL, "image3.jpg", 8000, new BigDecimal("20000.00"), Transmission.MANUAL, 2020, model2.getUuid(), user2.getUuid());
        offer = offerService.register(offer);
        offer2 = offerService.register(offer2);
        offer3 = offerService.register(offer3);
        brand = brandService.update(brand);
        userService.getAll().forEach(System.out::println);
//        brandService.delete(brand2.getUuid());
//        userRoleService.delete(role2.getUuid());
    }
}
