package ru.kpfu.itis.dao;

import ru.kpfu.itis.model.User;

import java.util.List;

public interface UsersDao extends CrudDao<User> {
    List findAll(int limit, int offset);
}
