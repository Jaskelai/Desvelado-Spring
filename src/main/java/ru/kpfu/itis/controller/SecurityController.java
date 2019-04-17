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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SecurityController {
    private UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    protected String showRegistrationForm(ModelMap map) {
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
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("SC#login").build();
            } catch (DuplicateKeyException ex) {
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
        } else {

        }
        return showRegistrationForm(map);
    }

    @RequestMapping(value = "/login")
    @PreAuthorize("isAnonymous()")
    public String login(HttpServletRequest req, @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, ModelMap map) {
        if (req.getParameterMap().containsKey("error")) {
            map.addAttribute("error", "Wrong email or password!");
        }
        return "login";
    }
}
