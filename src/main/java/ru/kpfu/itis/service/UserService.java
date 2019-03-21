package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dao.UsersDaoImpl;
import ru.kpfu.itis.model.User;

@Service
public class UserService {
    private UsersDaoImpl usersDao;

    @Autowired
    public UserService(UsersDaoImpl usersDao) {
        this.usersDao = usersDao;
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }
}
