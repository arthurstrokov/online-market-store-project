package com.gmail.arthurstrokov.controller.api;

import com.gmail.arthurstrokov.service.BuisnessCardService;
import com.gmail.arthurstrokov.service.UserService;
import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;
import com.gmail.arthurstrokov.service.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buisness")
public class BuisnessCardAPIController {

    private final BuisnessCardService buisnessCardService;
    private final UserService userService;

    public BuisnessCardAPIController(
            BuisnessCardService buisnessCardService,
            UserService userService) {
        this.buisnessCardService = buisnessCardService;
        this.userService = userService;
    }

    @GetMapping(value = "/user.cards/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public List<BuisnessCardDTO> getBusinessCards(
            @PathVariable(name = "id") Long id
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        UserDTO user = userService.findById(userDTO.getId());
        return buisnessCardService.findByUserId(user.getId());
    }


    @DeleteMapping(value = "/user.cards/{id}/delete")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public String deleteBuisnessCard
            (@PathVariable(name = "id") Long id
            ) {
        buisnessCardService.removeById(id);
        return String.valueOf(id);
    }

    @GetMapping("/user.cards")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public List<BuisnessCardDTO> findAll() {
        return buisnessCardService.findAll();
    }
}
