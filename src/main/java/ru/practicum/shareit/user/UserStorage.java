package ru.practicum.shareit.user;



public interface UserStorage {

    User add(User user);

    User update(Long id, User user);

    User getUser(Long id);

    User delUser(Long id);
}
