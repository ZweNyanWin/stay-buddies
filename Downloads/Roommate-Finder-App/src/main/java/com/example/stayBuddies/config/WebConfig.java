package com.example.stayBuddies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestURIInterceptor requestURIInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestURIInterceptor);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1) Your existing external‐file mapping
        registry
                .addResourceHandler("/files/**")
                .addResourceLocations("file:/Users/zwenyanwin/Downloads/Roommate-Finder-App");

        // 2) Re-enable static img serving from classpath:/static/img/
        registry
                .addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/access-denied").setViewName("access-denied");
    }
}
