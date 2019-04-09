package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dao.UserElevenDao;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.model.User;

@Service
public class UserService {
    private UsersDao usersDao;
    private UserElevenDao userElevenDao;

    @Autowired
    public UserService(UsersDao usersDao, UserElevenDao userElevenDao) {
        this.usersDao = usersDao;
        this.userElevenDao = userElevenDao;
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

//    public void saveUserEleven(UserEleven user) {
//        userElevenDao.save(user);
//    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }
}
