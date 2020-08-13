package com.my.dashboard.config;

import com.deviceatlas.cloud.deviceidentification.cacheprovider.CacheException;
import com.deviceatlas.cloud.deviceidentification.cacheprovider.SimpleCacheProvider;
import com.deviceatlas.cloud.deviceidentification.client.Client;
import com.google.gson.Gson;
import com.my.dashboard.mvc.service.LetseeService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfiguration implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(ThymeleafConfiguration.class);

    private final WebApplicationContext appContext;

    public ThymeleafConfiguration(WebApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Bean(name = "templateResolver")
    @Description("Thymeleaf template resolver serving HTML")
    public ITemplateResolver templateResolver() {
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean(name = "templateEngine")
    @Description("Thymeleaf template engine with Spring integration")
    public ISpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver((ITemplateResolver)appContext.getBean("templateResolver"));
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

    @Bean(name = "thymeleafViewResolver")
    @Description("Thymeleaf view resolver")
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine((ISpringTemplateEngine)appContext.getBean("templateEngine"));
        return viewResolver;
    }

    @Bean(name = "gson")
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public Client getClient() {
        Client client = null;
        try {
            String licenceKey = /*"ee7c97373f9ead7f683e59c626c4fb0c"*/"265d225ee068a9ff3e26a3ff11afc9c8";
            if (licenceKey == null || licenceKey.length() == 0) {
                LOG.error("the licencekey environment variable needs to be set");
            }

            client = Client.getInstance(new SimpleCacheProvider());
            client.setLicenceKey(licenceKey);
        } catch (CacheException ex) {
            LOG.warn("{}", ex.getMessage());
        }

        LOG.info("DeviceAtlas API client  set");

        return client;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("http").setViewName("http");
        registry.addViewController("webSocket").setViewName("webSocket");
        registry.addViewController("oop").setViewName("oop");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }

}
