package com.jtech.toa;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p> Api应用，采用 War形式启动，开发执行运行 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SpringBootApplication
@EnableScheduling
public class ToaApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ToaApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}
