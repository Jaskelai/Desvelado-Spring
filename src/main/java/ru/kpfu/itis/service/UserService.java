package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.UsersEleven;
import ru.kpfu.itis.repository.UserAuthorityRepository;
import ru.kpfu.itis.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserAuthorityRepository userAuthorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAuthorityRepository = userAuthorityRepository;
    }


    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new DuplicateKeyException("User with this nickname already exist!");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateKeyException("User with this email already exist");
        }
        user.addAuthority(userAuthorityRepository.findByAuthority("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRepeatPassword(user.getPassword());
        userRepository.save(user);
    }

    public void saveUserEleven(UsersEleven user) {

    }
}
