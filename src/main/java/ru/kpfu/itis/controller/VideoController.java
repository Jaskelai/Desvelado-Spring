package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.kpfu.itis.model.Video;
import ru.kpfu.itis.service.VideoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String videos(Authentication auth, ModelMap modelMap) {
        modelMap.put("video", new Video());
        if (auth != null) {
            modelMap.put("videos", videoService.getAllVideos(auth.getName()));
        } else {
            modelMap.put("videos", videoService.getAllVideos());
        }
        return "videos";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String videosPost(
            @ModelAttribute("video") @Valid Video video,
            ModelMap map,
            BindingResult result,
            Authentication auth
    ) {
        if (!result.hasErrors()) {
            videoService.saveVideo(video, auth.getName());
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("VC#videos").build();
    }
}
