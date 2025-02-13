package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDtoWithOutEmail {
        Long id;
        @Email(message = "Неправильно введен email")
        String email;
        String name;
    }

