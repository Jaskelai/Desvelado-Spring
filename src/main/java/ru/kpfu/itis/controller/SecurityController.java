package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.model.LoginForm;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserAuthorityRepository;
import ru.kpfu.itis.service.UserService;

import javax.validation.Valid;

@Controller
public class SecurityController {
    private UserService userService;
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    public SecurityController(UserService userService, UserAuthorityRepository userAuthorityRepository) {
        this.userService = userService;
        this.userAuthorityRepository = userAuthorityRepository;
    }

    protected String showRegistrationForm(ModelMap map) {
        map.put("userAuthorities", userAuthorityRepository.findAll());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String registration(ModelMap model) {
        model.put("user", new User());
        return showRegistrationForm(model);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String registerHandler(
            RedirectAttributes redirectAttributes,
            @ModelAttribute("user") @Valid User user,
            BindingResult result,
            ModelMap map
    ) {
        if (!result.hasErrors()) {
            try {
                userService.saveUser(user);
                redirectAttributes.addFlashAttribute("message", "You has been registered successfully");
                redirectAttributes.addFlashAttribute("messageType", "success");
                return "redirect:" + UriComponentsBuilder.fromPath("/login").build();
            } catch (DuplicateKeyException ex) {
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
        } else {

        }
        return showRegistrationForm(map);
    }

    @RequestMapping(value = "/login")
    @PreAuthorize("isAnonymous()")
    public String login(@RequestParam(required = false) String error, @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap map) {
        map.put("error", error);
        return "login";
    }
}
