package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.repository.ShareRepository;

@Service
public class ShareServiceImpl implements ShareService {
    private ShareRepository shareRepository;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public String auth() {
        return shareRepository.authorize();
    }

    @Override
    public void sharePost(String token) {
        String[] parts = token.split("=");
        parts = parts[1].split("&");
        shareRepository.addPost(parts[0]);
    }
}
