package ru.kpfu.itis.repository;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class ShareRepository {
    private OkHttpClient okHttpClient;
    private final  String REQ_VALUE = "255a916c854e7a4bfb575bdef84374270e0956ae8f75aff5a97a7750caa78998eb49393931ec592831525";

    @Autowired
    public ShareRepository(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public String authorize() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://oauth.vk.com/authorize")
                .newBuilder();
        urlBuilder.addQueryParameter("client_id", "6958475");
        urlBuilder.addQueryParameter("display", "page");
        urlBuilder.addQueryParameter("scope", "wall");
        urlBuilder.addQueryParameter("redirect_uri", "http://127.0.0.1:8080/Desvelado/shareVK");
        urlBuilder.addQueryParameter("response_type", "token");
        return urlBuilder.build().toString();
    }


    public void addPost(String token) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.vk.com/method/wall.post")
                .newBuilder();
        urlBuilder.addQueryParameter("owner_id", "168167050");
        urlBuilder.addQueryParameter("message", "DOSVIDALDO IS REALLY GOOOOOOD");
        urlBuilder.addQueryParameter("v", "5.95");
        urlBuilder.addQueryParameter("access_token", REQ_VALUE);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(token);
        }
    }
}
