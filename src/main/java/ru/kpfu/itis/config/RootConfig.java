package ru.kpfu.itis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.repository"})
public class RootConfig {
}