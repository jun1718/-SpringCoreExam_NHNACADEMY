package com.nhnacademy.exam.main.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.nhnacademy.exam.main")
@EnableAspectJAutoProxy
public class MainConfig {
}
