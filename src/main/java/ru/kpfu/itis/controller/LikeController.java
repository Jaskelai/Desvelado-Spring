package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.forms.LikeForm;
import ru.kpfu.itis.service.LikeService;

@Controller
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ResponseBody
    @PostMapping(value = "/like")
    public ResponseEntity likeVideo(Authentication auth, @ModelAttribute LikeForm likeForm) {
        likeService.saveLike(likeForm, auth.getName());
        return ResponseEntity.ok().build();
    }
}
