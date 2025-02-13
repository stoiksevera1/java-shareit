package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserDto {

    Long id;
    @NotBlank(message = "email Не может быть пустым")
    @Email(message = "Неправильно введен email")
    String email;
    String name;
}
