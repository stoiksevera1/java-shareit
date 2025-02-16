package ru.practicum.shareit.item.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

@Builder
@Data
public class ItemDtoUpdate {
    Long id;
    String name;
    String description;
    Boolean available;
    Long owner;
    ItemRequest request;
}

