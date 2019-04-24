package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.VideoService;

@Controller
@RequestMapping("/")
public class BaseController {
    private UserService userService;
    private VideoService videoService;

    @Autowired
    public BaseController(UserService userService, VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
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
    public String profile(Authentication auth, ModelMap modelMap) {
        modelMap.put("favVideos", videoService.getLikedVideos(auth.getName()));
        modelMap.put("ownedVideos",videoService.getOwnedVideos(auth.getName()));
        return "profile";
    }
}
