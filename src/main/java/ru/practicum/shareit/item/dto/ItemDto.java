package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Builder
@Data
public class ItemDto {
    Long id;

    @NotEmpty(message = "Имя Не может быть пустым.")
    String name;
    @NotEmpty(message = "Описание не может быть пустым.")
    String description;
    @NotNull
    Boolean available;
    Long owner;
    ItemRequest request;
}
