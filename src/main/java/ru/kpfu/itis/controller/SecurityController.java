package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SecurityController {
    private UserServiceImpl userService;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityController(UserServiceImpl userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    protected String showRegistrationForm(ModelMap map) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Authentication auth, ModelMap model) {
        if (auth != null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("BC#profile").build();
        }
        model.put("user", new User());
        return showRegistrationForm(model);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerHandler(
            @ModelAttribute("user") @Valid User user,
            BindingResult result,
            ModelMap map
    ) {
        if (!result.hasErrors()) {
            try {
                userService.saveUser(user);
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#login").build();
            } catch (DuplicateKeyException ex) {
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
        } else {
            map.addAttribute("user", user);
        }
        return showRegistrationForm(map);
    }

    @RequestMapping(value = "/login")
    public String login(Authentication auth, HttpServletRequest req, HttpServletResponse resp, @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap map) {
        if (auth != null) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("BC#profile").build();
        }
        if (req.getParameterMap().containsKey("error")) {
            map.addAttribute("errorLogin", "Wrong email or password!");
        }
        return "login";
    }
}
