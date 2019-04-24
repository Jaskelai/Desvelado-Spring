package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Video;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User findUserByUsername(String username);
}
