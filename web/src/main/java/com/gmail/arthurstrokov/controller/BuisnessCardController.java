package com.gmail.arthurstrokov.controller;

import com.gmail.arthurstrokov.controller.properties.PageProperties;
import com.gmail.arthurstrokov.service.BuisnessCardService;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buisness")
public class BuisnessCardController {

    private final PageProperties pageProperties;
    private final BuisnessCardService buisnessCardService;
    private final Validator buisnessCardValidator;

    @Autowired
    public BuisnessCardController(
            @Qualifier("pageProperties") PageProperties pageProperties,
            @Qualifier("buisnessCardService") BuisnessCardService buisnessCardService,
            @Qualifier("buisnessCardValidator") Validator buisnessCardValidator
    ) {
        this.pageProperties = pageProperties;
        this.buisnessCardService = buisnessCardService;
        this.buisnessCardValidator = buisnessCardValidator;
    }

    @GetMapping("/user.cards")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public String getBuisnessCardPage(
            ModelMap modelMap
    ) {
        List<BuisnessCardDTO> buisnessCards = buisnessCardService.findAllByUserId();
        modelMap.addAttribute("buisnessCards", buisnessCards);
        return pageProperties.getBuisnessCardPagePath();
    }

    @GetMapping("/card.create")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public String getCreateBuisnessCardPage(ModelMap modelMap) {
        modelMap.addAttribute("buisnessCard", new BuisnessCardDTO());
        return pageProperties.getBuisnessCardCreatePagePath();
    }

    @PostMapping("/card.create")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public String createBuisnessCard(
            @ModelAttribute("buisnessCard") BuisnessCardDTO buisnessCard,
            ModelMap modelMap,
            BindingResult bindingResult
    ) {
        buisnessCardValidator.validate(buisnessCard, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("buisnessCard", buisnessCard);
            return pageProperties.getBuisnessCardCreatePagePath();
        } else {
            buisnessCardService.save(buisnessCard);
            return "redirect:/buisness/card.create";
        }
    }
}
