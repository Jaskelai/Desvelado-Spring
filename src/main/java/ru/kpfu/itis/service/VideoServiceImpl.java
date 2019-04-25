package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.aspect.MyAnnotation;
import ru.kpfu.itis.model.Like;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Video;
import ru.kpfu.itis.model.enums.UserRoleEnum;
import ru.kpfu.itis.repository.LikeRepository;
import ru.kpfu.itis.repository.VideoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {
    private UserServiceImpl userService;
    private VideoRepository videoRepository;
    private LikeRepository likeRepository;
    private final String ID_YOUTUBE_PATTERN =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

    @Autowired
    public VideoServiceImpl(UserServiceImpl userService, VideoRepository videoRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.videoRepository = videoRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void saveVideo(Video video, String username) {
        User user = userService.findUserByUsername(username);
        String link = video.getYoutubeLink();
        String id = getYoutubeId(link);
        video.setDate(new Date());
        video.setYoutubeId(id);
        video.setUser(user);
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return ((List<Video>) videoRepository.findAll())
                .stream()
                .peek(it -> it.setLikes(likeRepository.countAllByVideo(it)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Video> getAllVideos(String username) {
        List<Video> likedVideos = getLikedVideos(username);
        List<Video> videos = getAllVideos();
        for (Video likedVideo : likedVideos) {
            for (Video video : videos) {
                if (likedVideo.equals(video)) {
                    video.setLiked(true);
                }
            }
        }
        return videos;
    }

    @Override
    public List<Video> getLikedVideos(String username) {
        User user = userService.findUserByUsername(username);
        List<Like> likes = (List<Like>) likeRepository.findAllByUserId(user.getId());
        return likes.stream()
                .map(Like::getVideo)
                .peek(it -> it.setLikes(likeRepository.countAllByVideo(it)))
                .collect(Collectors.toList());
    }

    @Override
    public Video findVideoByYoutubeId(String id) {
        return videoRepository.findVideoByYoutubeId(id);
    }

    @Override
    @MyAnnotation
    public List<Video> getOwnedVideos(String username) {
        User user = userService.findUserByUsername(username);
        return new ArrayList<>(user.getVideos()).stream()
                .peek(it -> it.setLikes(likeRepository.countAllByVideo(it)))
                .collect(Collectors.toList());
    }

    private String getYoutubeId(String link) {
        Pattern compiledPattern = Pattern.compile(ID_YOUTUBE_PATTERN);
        Matcher matcher = compiledPattern.matcher(link);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    @Override
    public void deleteVideo(String username, String id) {
        User user = userService.findUserByUsername(username);
        if (user.getUserRole().equals(UserRoleEnum.ADMIN)) {
            videoRepository.delete(videoRepository.findVideoByYoutubeId(id));
        } else {
            throw new AccessDeniedException("YOU ARE NOT ADMIN");
        }
    }
}
