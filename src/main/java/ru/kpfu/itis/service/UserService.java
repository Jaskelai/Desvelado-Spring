package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.model.User;

@Service
public class UserService {
    private UsersDao usersDao;

    @Autowired
    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void saveUser(User user) {
        System.out.println(user.getClass());
        usersDao.save(user);
    }
}
