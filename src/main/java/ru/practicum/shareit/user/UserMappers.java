package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoUpdate;

public class UserMappers {
    public static UserDto toDto(User model) {
        return UserDto.builder()
                .id(model.getId())
                .name(model.getName())
                .email(model.getEmail())
                .build();
    }

    public static User toModel(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    public static User toModel(UserDtoUpdate dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
}