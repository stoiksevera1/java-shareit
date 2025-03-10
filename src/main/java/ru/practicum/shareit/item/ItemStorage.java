package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item get(Long userId, Long idItem);

    Item add(Item item);

    Item update(Item item);

    List<Item> getAllItemsUser(Long userId);

    List<Item> getItemsSearch(String text);

}
