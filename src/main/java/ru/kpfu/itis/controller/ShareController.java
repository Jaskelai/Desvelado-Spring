package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.service.ShareService;

@Controller
public class ShareController {
    private ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/share")
    public String index(ModelMap map) {
        String vkAuth = shareService.auth();
        return "redirect: " + vkAuth;
    }

    @GetMapping("/shareVK")
    public String share(@RequestParam(value = "access_token", required = false) String token, ModelMap map) {
        return "token_view";
    }

    @ResponseBody
    @GetMapping("/shareAjax")
    public ResponseEntity shareAjax(@RequestParam(value = "token") String tokenUrl) {
        shareService.sharePost(tokenUrl);
        return ResponseEntity.ok().build();
    }
}
