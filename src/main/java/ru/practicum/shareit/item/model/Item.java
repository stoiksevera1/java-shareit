package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Builder
@Data
public class Item {
    Long id;
    String name;
    String description;
    Boolean available;
    Long owner;
    ItemRequest request;
}
