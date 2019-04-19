package ru.kpfu.itis.util;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class RememberMeAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());
//      LoginForm loginForm = service.getUserByUsername();
//        if (!loginForm.getPassword().equals(password)) {
//            throw new BadCredentialsException("Bad Credentials");
//        }
//        return new UsernamePasswordAuthenticationToken(loginForm, null, loginForm.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
