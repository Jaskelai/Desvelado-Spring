package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.service.UserService;

@Controller
@RequestMapping("/")
public class BaseController {
    private UserService userService;

    @Autowired
    public BaseController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap map) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map) {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("BC#home").build();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String profile() {
        return "profile";
    }
}
