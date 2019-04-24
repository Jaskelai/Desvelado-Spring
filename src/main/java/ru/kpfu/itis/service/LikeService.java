package ru.kpfu.itis.service;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Video;
import ru.kpfu.itis.model.forms.LikeForm;

public interface LikeService {

    void saveLike(LikeForm likeForm, String username);

    void deleteByUserAndVideo(User user, Video video);
}
