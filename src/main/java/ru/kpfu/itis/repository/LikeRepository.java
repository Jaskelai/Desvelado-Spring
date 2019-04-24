package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.model.Like;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.Video;

public interface LikeRepository extends CrudRepository<Like, Integer> {

    Iterable<Like> findAllByUserId(Integer id);

    void deleteByUserAndVideo(User user, Video video);

    Integer countAllByVideo(Video video);
}
