package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemDtoList;
import ru.practicum.shareit.item.dto.ItemDtoUpdate;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDtoList> getAllItemsUser(@RequestHeader(value = "X-Sharer-User-Id") Long userId) {
        return itemService.getAllItemsUser(userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                           @PathVariable(name = "itemId") Long itemId) {
        return itemService.getItem(userId, itemId);
    }


    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                          @PathVariable(name = "itemId") Long itemId,
                          @Valid @RequestBody ItemDtoUpdate item) {
        return itemService.updateItem(userId, itemId, item);
    }

    @PostMapping
    public ItemDto createItem(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                              @Valid @RequestBody ItemDto item) {
        return itemService.addItem(userId, item);
    }

    @GetMapping("/search")
    public List<ItemDtoList> searchItem(@RequestHeader(value = "X-Sharer-User-Id") Long userId,
                                        @RequestParam String text) {
        return itemService.searchItem(text);
    }
}
