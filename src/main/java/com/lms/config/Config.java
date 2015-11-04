package com.lms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
// Marks this class as configuration
@PropertySource({ "classpath:freemarker.properties", "classpath:application.properties" })
// Specifies which package to scan
@ComponentScan("com.lms")
// Enables Spring's annotations
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        // registry.addResourceHandler("/files/**").addResourceLocations("file:" + env.getProperty("filesPath"));
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=utf-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setFreemarkerSettings(this.freemarkerProperties());
        result.setTemplateLoaderPath("/WEB-INF/views/");
        return result;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver setupMultipartResolver() {
        CommonsMultipartResolver mr = new CommonsMultipartResolver();
        // if (user_is_not_admin) {
        // mr.setMaxUploadSize(10000);
        // }
        return mr;
    }

    private Properties freemarkerProperties() {
        return new Properties() {
            {
                setProperty("template_update_delay", env.getProperty("template_update_delay"));
                setProperty("auto_import", env.getProperty("auto_import"));
                setProperty("default_encoding", env.getProperty("default_encoding"));
                setProperty("output_encoding", env.getProperty("output_encoding"));
            }
        };
    }

}
