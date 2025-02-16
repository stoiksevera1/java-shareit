package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoList;
import ru.practicum.shareit.item.dto.ItemDtoUpdate;

import java.util.List;

public interface ItemService {
    List<ItemDtoList> getAllItemsUser(Long id);

    ItemDto getItem(Long userId, Long itemId);

    ItemDto updateItem(Long userId, Long itemId, ItemDtoUpdate item);

    ItemDto addItem(Long userId, ItemDto itemDto);

    List<ItemDtoList> searchItem(String text);
}
