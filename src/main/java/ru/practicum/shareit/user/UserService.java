package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoUpdate;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;

    public UserDto createUser(UserDto userDto) {
        User user = UserMappers.toModel(userDto);
        return UserMappers.toDto(userStorage.add(user));
    }

    public UserDto getUser(Long userId) {
        return UserMappers.toDto(userStorage.getUser(userId));
    }


    public UserDto updateUser(Long id, UserDtoUpdate newUser) {
        User user = UserMappers.toModel(newUser);
        return UserMappers.toDto(userStorage.update(id, user));
    }

    public UserDto delUser(Long id) {
        return UserMappers.toDto(userStorage.delUser(id));
    }

}

