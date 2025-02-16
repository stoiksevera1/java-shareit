package ru.practicum.shareit.item;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoList;
import ru.practicum.shareit.item.dto.ItemDtoUpdate;
import ru.practicum.shareit.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStorage itemStorage;
    private final UserStorage userStorage;


    @Override
    public List<ItemDtoList> getAllItemsUser(Long userId) {
        checkUser(userId);
        return itemStorage.getAllItemsUser(userId).stream()
                .map(ItemMappers::toDtoList)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItem(Long userId, Long itemId) {
        checkUser(userId);
        return ItemMappers.toDto(itemStorage.get(userId, itemId));
    }

    @Override
    public ItemDto updateItem(Long userId, Long itemId, ItemDtoUpdate item) {
        checkUser(userId);
        item.setId(itemId);
        item.setOwner(userId);
        return ItemMappers.toDto(itemStorage.update(ItemMappers.toModel(item)));
    }

    @Override
    public ItemDto addItem(Long userId, ItemDto itemDto) {
        checkUser(userId);
        itemDto.setOwner(userId);
       return ItemMappers.toDto(itemStorage.add(ItemMappers.toModel(itemDto)));

    }

    @Override
    public List<ItemDtoList> searchItem(String text) {
        return itemStorage.getItemsSearch(text).stream()
                .map(ItemMappers::toDtoList)
                .collect(Collectors.toList());
    }

    private void checkUser(Long userId) {
        userStorage.getUser(userId);
    }
}
