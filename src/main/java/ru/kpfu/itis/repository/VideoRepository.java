package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Video;

public interface VideoRepository extends CrudRepository<Video, Integer> {

    Video findVideoByYoutubeId(String id);
}
