package ru.kpfu.itis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("GET")) {
            Cookie[] cookies = request.getCookies();
            Authentication user = SecurityContextHolder.getContext().getAuthentication();
            if (user.getPrincipal() instanceof String) {
                if (cookies != null) {
                    Optional<Cookie> cookie = Arrays.stream(cookies)
                            .filter(c -> c.getName().equals("token"))
                            .findAny();
                    if (cookie.isPresent()) {
                        String myToken = cookie.get().getValue();
//                      LoginForm loginForm = userService.findUserByToken(token);
//                        if (loginForm != null) {
//                          String username = loginForm.getUsername();
//                          String password = loginForm.getPassword();
//                          UsernamePasswordAuthenticationToken token =
//                                  new UsernamePasswordAuthenticationToken(username, password);
//                          token.setDetails(myToken);
//                          SecurityContextHolder.getContext().setAuthentication(token);
//                          }
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }


}
