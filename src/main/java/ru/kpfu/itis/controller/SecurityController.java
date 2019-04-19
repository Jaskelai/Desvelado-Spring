package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserDetailsServiceImpl;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.TokenGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SecurityController {
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityController(UserService userService, UserDetailsServiceImpl userDetailsService) {
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
            Authentication auth,
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
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#login").build();
            } catch (DuplicateKeyException ex) {
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
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
        String token = new TokenGenerator(20).nextString();
        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setMaxAge(60 * 60 * 24 * 365);
        resp.addCookie(tokenCookie);
        loginForm.setToken(token);
        userDetailsService.addToken(loginForm, token);
        return "login";
    }
}
