package ru.practicum.shareit.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();

    public User getUser(Long id) {
        if (!users.containsKey(id)) {
            log.warn("Пользователь c ID{} не найден", id);
            throw new NotFoundException("Пользователь c ID " + id + " не найден");
        }
        return users.get(id);
    }


    public User add(User user) {
        user.setId(getNextId());
        checkEmail(user);
        users.put(user.getId(), user);
        log.warn("Успешное добавление данных пользователя");
        return user;
    }

    public User update(Long id, User newUser) {
        if (!users.containsKey(id)) {
            log.warn("Ошибка поиска пользователя ID не найден");
            throw new NotFoundException("Пользователь " + newUser.getId() + " не найден");
        }
        newUser.setId(id);

        if (newUser.getEmail() == null) {
            newUser.setEmail(users.get(id).getEmail());
        }
        if (newUser.getName() == null) {
            newUser.setName(users.get(id).getName());
        }
        checkEmail(newUser);
        users.remove(id);
        users.put(id, newUser);
        log.info("Успешное обновление данных пользователя");
        return newUser;
    }

    @Override
    public User delUser(Long id) {
        if (!users.containsKey(id)) {
            log.warn("Ошибка поиска пользователя ID не найден");
            throw new NotFoundException("Пользователь " + id + " не найден");
        }
        User user = users.get(id);
        users.remove(id);
        return user;

    }


    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }

    private void checkEmail(User user) {
        if (users.values().stream()
                .filter(user1 -> !user1.id.equals(user.id))
                .anyMatch(user1 -> user1.email.equals(user.email))) {
            log.warn("Ошибка добавления емайла");
            throw new ValidationException("Такой емаил уже существует");

        }
    }

}
