package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
