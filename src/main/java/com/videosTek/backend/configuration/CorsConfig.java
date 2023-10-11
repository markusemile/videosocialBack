package com.videosTek.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/myapp/socialvideo/api/**")
                    .allowedOrigins("http://localhost:4200","http://172.18.0.1","http://172.18.0.3","http:www.markusemile.be","*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);

    }
}
