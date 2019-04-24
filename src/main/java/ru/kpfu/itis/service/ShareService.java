package ru.kpfu.itis.service;

public interface ShareService {

    String auth();

    void sharePost(String token);
}
