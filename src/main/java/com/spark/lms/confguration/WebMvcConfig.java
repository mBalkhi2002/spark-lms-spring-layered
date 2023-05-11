package com.spark.lms.confguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@PropertySource({"classpath:/properties/lms.properties", "classpath:/properties/messages.properties"})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
    @Autowired
    private StringToCategoryDtoConverter stringToCategoryDtoConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToCategoryDtoConverter);
    }

}
