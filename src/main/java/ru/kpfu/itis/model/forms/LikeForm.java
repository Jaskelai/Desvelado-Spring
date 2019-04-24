package ru.kpfu.itis.model.forms;

import java.util.Objects;

public class LikeForm {
    private Boolean isLiked;
    private String idLink;
    private String info;

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getIdLink() {
        return idLink;
    }

    public void setIdLink(String idLink) {
        this.idLink = idLink;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeForm likeForm = (LikeForm) o;
        return Objects.equals(isLiked, likeForm.isLiked) &&
                Objects.equals(idLink, likeForm.idLink) &&
                Objects.equals(info, likeForm.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLiked, idLink, info);
    }

    @Override
    public String toString() {
        return "LikeForm{" +
                "isLiked=" + isLiked +
                ", idLink='" + idLink + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
