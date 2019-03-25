package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Profile;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("profileEntityConverter")
public class ProfileConverter implements EntityConverter<Profile, ProfileDTO> {

    @Override
    public Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());
        return profile;
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
