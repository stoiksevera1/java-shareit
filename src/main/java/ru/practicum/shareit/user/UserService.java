package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoWithOutEmail;


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


    public UserDto updateUser(Long id, UserDtoWithOutEmail newUser) {
        User user = UserMappers.toModelWithOutEmail(newUser);
        return UserMappers.toDto(userStorage.update(id, user));
    }

    public UserDto delUser(Long id) {
        return UserMappers.toDto(userStorage.delUser(id));
    }

    private boolean checkName(User user) {
        return user.getName() == null || user.getName().isEmpty();
    }
}

