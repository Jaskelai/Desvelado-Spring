package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;

public interface UserService {

    void saveUser(User user);

    User findUserByUsername(String username);
}
