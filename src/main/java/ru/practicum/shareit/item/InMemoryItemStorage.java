package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InMemoryItemStorage implements ItemStorage {
    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item get(Long userId, Long idItem) {
        if (!items.containsKey(idItem)) {
            log.warn("Предмет c ID {} не найден", idItem);
            throw new NotFoundException("Предмет c ID" + idItem + "не найден");
        }
        if (!userId.equals(items.get(idItem).getOwner())) {
            log.warn("Предмет {}не принадлежит  пользователю {}", idItem, userId);
            throw new ValidationException("Предмет " + idItem + "не принадлежит  пользователю " + userId);
        }
        log.warn("Предмет успешно найден");
        return items.get(idItem);

    }

    @Override
    public Item add(Item item) {
        item.setId(getNextId());
        items.put(item.getId(), item);
        log.info("Предмет успешно добавлен" + item.getId());
        return item;
    }

    @Override
    public Item update(Item item) {
        if (!items.containsKey(item.getId())) {
            log.warn("При обновлении Предмет c ID {} не найден", item.getId());
            throw new NotFoundException("Предмет c ID" + item.getId() + "не найден");
        }

        if (!item.getOwner().equals(items.get(item.getId()).getOwner())) {
            log.warn("Предмет {}не принадлежит  пользователю {} обновление не возможно", item.getId(), item.getOwner());
            throw new ValidationException("Предмет " + item.getId() + "не принадлежит  пользователю " + item.getOwner()
                    + " обновление не возможно");
        }
        if (item.getName() == null) {
            item.setName(items.get(item.getId()).getName());
        }

        if (item.getDescription() == null) {
            item.setDescription(items.get(item.getId()).getDescription());
        }

        if (item.getAvailable() == null) {
            item.setAvailable(items.get(item.getId()).getAvailable());
        }
        items.remove(item.getId());
        items.put(item.getId(), item);
        log.warn("Предмет успешно обновлен");
        return item;
    }

    @Override
    public List<Item> getAllItemsUser(Long userId) {
        List<Item> itemList = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getOwner().equals(userId)) {
                itemList.add(item);
            }
        }
        log.warn("Получение списка предметов пользователя");
        return itemList;
    }

    @Override
    public List<Item> getItemsSearch(String text) {
        List<Item> itemList = new ArrayList<>();
        if (!text.isEmpty()) {
            for (Item item : items.values()) {
                if (item.getAvailable()) {
                    if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                        itemList.add(item);
                    } else if (item.getDescription().toLowerCase().contains(text.toLowerCase())) {
                        itemList.add(item);
                    }
                }
            }
        }
        log.warn("Получение списка предметов по запросу");
        return itemList;

    }

    private long getNextId() {
        long currentMaxId = items.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
