package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDtoUpdate {
        Long id;
        @Email(message = "Неправильно введен email")
        String email;
        String name;
    }

