package ru.kpfu.itis.service;

import ru.kpfu.itis.model.Video;

import java.util.List;

public interface VideoService {

    void saveVideo(Video video, String username);

    List<Video> getAllVideos();

    List<Video> getAllVideos(String username);

    List<Video> getLikedVideos(String username);

    Video findVideoByYoutubeId(String id);

    List<Video> getOwnedVideos(String username);
}
