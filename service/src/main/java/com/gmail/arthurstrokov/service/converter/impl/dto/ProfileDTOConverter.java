package com.gmail.arthurstrokov.service.converter.impl.dto;

import com.gmail.arthurstrokov.dao.model.Profile;
import com.gmail.arthurstrokov.service.converter.DTOConverter;
import com.gmail.arthurstrokov.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("profileDTOConverter")
public class ProfileDTOConverter implements DTOConverter<ProfileDTO, Profile> {

    @Override
    public ProfileDTO toDTO(Profile entity) {
        ProfileDTO profile = new ProfileDTO();
        profile.setAddress(entity.getAddress());
        profile.setPhone(entity.getPhone());
        profile.setUserId(entity.getUserId());
        return profile;
    }

    @Override
    public List<ProfileDTO> toDTOList(List<Profile> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
