package com.gmail.arthurstrokov.service;

import com.gmail.arthurstrokov.service.dto.BuisnessCardDTO;

import java.util.List;

public interface BuisnessCardService {

    List<BuisnessCardDTO> findAll();

    List<BuisnessCardDTO> findAllByUserId();

    List<BuisnessCardDTO> findByUserId(Long userId);

    void save(BuisnessCardDTO buisnessCardDTO);

    void removeById(Long id);
}
