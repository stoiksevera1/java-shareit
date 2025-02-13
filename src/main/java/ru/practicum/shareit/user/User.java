package ru.practicum.shareit.user;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Builder
@Data
public class User {
    Long id;
    String name;
    String email;

}
