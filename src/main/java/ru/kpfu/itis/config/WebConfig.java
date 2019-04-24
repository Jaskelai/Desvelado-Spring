package ru.kpfu.itis.config;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.kpfu.itis.repository.ShareRepository;
import ru.kpfu.itis.service.UserDetailsServiceImpl;
import ru.kpfu.itis.service.UserServiceImpl;
import ru.kpfu.itis.util.converter.GenderStringToBooleanConverter;
import ru.kpfu.itis.util.converter.StringToDateConverter;

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
        registry.addConverter(new StringToDateConverter());
    }

    @Bean
    public UserDetailsService userDetailsService(UserServiceImpl userService) {
        return new UserDetailsServiceImpl(userService);
    }

    @Bean
    public ShareRepository shareRepository() {
        return new ShareRepository(okHttpClient());
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}

