package com.belatrixsf.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.belatrixsf.beans"})
public class AppConfigWithComponentScan {

}
