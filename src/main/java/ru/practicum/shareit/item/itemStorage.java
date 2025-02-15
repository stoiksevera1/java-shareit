package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

public interface itemStorage {
    Item get(Long id);
    Item add(Item item);
    Item update(Item item);
}
