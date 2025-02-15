package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Builder
@Data
public class ItemDto {
    String name;
    String description;
    Boolean available;
}
