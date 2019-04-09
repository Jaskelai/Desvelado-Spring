package ru.kpfu.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.dao.UsersDao;
import ru.kpfu.itis.dao.UsersDaoJPQLimpl;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.service","ru.kpfu.itis.dao"})
public class UsersConfig {
    @Bean
    public UsersDao usersDao() {
        return new UsersDaoJPQLimpl();
    }

//    @Bean
//    public UserElevenDao userElevenDao() {
//        return new UserElevenDaoImpl();
//    }
}
