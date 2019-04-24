package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Like;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Video;
import ru.kpfu.itis.model.forms.LikeForm;
import ru.kpfu.itis.repository.LikeRepository;

@Service
public class LikeServiceImpl implements LikeService {
    private UserService userService;
    private VideoService videoService;
    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(UserService userService, VideoService videoService, LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.videoService = videoService;
    }

    @Override
    public void saveLike(LikeForm likeForm, String username) {
        User user = userService.findUserByUsername(username);
        Video video = videoService.findVideoByYoutubeId(likeForm.getIdLink());
        if (likeForm.getInfo().equals("true")) {
            Like like = new Like();
            like.setUser(user);
            like.setVideo(video);
            likeRepository.save(like);
        } else {
            likeRepository.deleteByUserAndVideo(user, video);
        }
    }

    @Override
    public void deleteByUserAndVideo(User user, Video video) {
        likeRepository.deleteByUserAndVideo(user, video);
    }
}
