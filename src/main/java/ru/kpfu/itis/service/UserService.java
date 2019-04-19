package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.enums.StateEnum;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRoleEnum;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserElevenRepository userElevenRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserElevenRepository userElevenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userElevenRepository = userElevenRepository;
    }


    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new DuplicateKeyException("User with this nickname already exist!");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateKeyException("User with this email already exist");
        }
        user.setState(StateEnum.ACTIVE);
        user.setUserRole(UserRoleEnum.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRepeatPassword(user.getPassword());
        userRepository.save(user);
    }

    public void saveUserEleven(UsersEleven user) {
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addToken(LoginForm loginForm, String token) {

    }

    public void checkToken(String token, LoginForm user) {

    }

    public LoginForm findUserByToken(String token) {
        return new LoginForm();
    }
}
