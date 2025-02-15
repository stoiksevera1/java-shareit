package ru.practicum.shareit.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.User;

import java.time.LocalDate;

/**
 * TODO Sprint add-item-requests.
 */
@Builder
@Data
public class ItemRequest {
    Long id;
    String description;
    User requestor;
    LocalDate created;
}
