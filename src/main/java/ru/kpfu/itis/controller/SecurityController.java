package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.UserEleven;
import ru.kpfu.itis.service.UserService;

import java.util.Date;

@Controller
public class SecurityController {
    private UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String newUser(ModelMap map) {
        userService.deleteUser(new User("adasd", "sdasdaasd", "adasdsad", true, new Date()));
        return "index";
    }

//    @RequestMapping(value = "/user11", method = RequestMethod.GET)
//    public String newUserEleven(ModelMap map) {
//        map.put("userEleven", new UserEleven());
//        return "usersEleven";
//    }
//
//    @RequestMapping(value = "/user11", method = RequestMethod.POST)
//    public String newUserElevenPost(
//            @ModelAttribute("userEleven") UserEleven userEleven,
//            ModelMap map
//    ) {
//        userService.saveUserEleven(userEleven);
//        return "usersEleven";
//    }
}
