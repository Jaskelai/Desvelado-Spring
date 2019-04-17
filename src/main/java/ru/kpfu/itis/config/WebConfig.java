package ru.kpfu.itis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.kpfu.itis.service.UserDetailsServiceImpl;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.converter.GenderStringToBooleanConverter;
import ru.kpfu.itis.util.converter.StringToSetConverter;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.controller", "ru.kpfu.itis.service"})
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setRedirectContextRelative(false);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GenderStringToBooleanConverter());
        registry.addConverter(new StringToSetConverter());
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService){
        return new UserDetailsServiceImpl(userService);
    }

}

