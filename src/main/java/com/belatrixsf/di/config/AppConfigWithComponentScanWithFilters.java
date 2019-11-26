package com.belatrixsf.di.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = {"com.belatrixsf.beans", "com.belatrixsf.di.basic"},
    excludeFilters = @Filter(type = FilterType.REGEX,
        pattern = {".*InMemory.*Finder"})
)
public class AppConfigWithComponentScanWithFilters {

}
