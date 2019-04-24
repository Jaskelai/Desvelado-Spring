package ru.kpfu.itis.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "desvelado.video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Transient
    @Pattern(regexp = "http(?:s?)://(?:www\\.)?youtu(?:be\\.com/watch\\?v=|\\.be/)([\\w\\-_]*)(&(amp;)?[\\w?\u200C\u200B=]*)?", message = "Wrong link to Youtube")
    private String youtubeLink;

    @Column(name = "video_link_id")
    private String youtubeId;

    @Size(min = 1, max = 63, message = "Wrong header's size")
    private String header;

    @Size(min = 1, max = 500, message = "Wrong description's size")
    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Transient
    private Boolean liked;

    @Transient
    private Integer likes;

    public String getYoutubeId() {
        return youtubeId;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        Video video = (Video) o;
        return Objects.equals(id, video.id) &&
                Objects.equals(youtubeLink, video.youtubeLink) &&
                Objects.equals(header, video.header) &&
                Objects.equals(description, video.description) &&
                Objects.equals(date, video.date) &&
                Objects.equals(user, video.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, youtubeLink, header, description, date, user);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }
}
