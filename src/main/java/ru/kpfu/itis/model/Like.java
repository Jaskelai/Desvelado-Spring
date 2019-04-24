package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "desvelado.like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private Video video;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(video, like.video) &&
                Objects.equals(user, like.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, video, user);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", video=" + video +
                ", user=" + user +
                '}';
    }
}
