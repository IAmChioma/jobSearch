package miu.edu.cs544.chioma.jobapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

@SpringBootApplication
@EnableJms
@EnableAspectJAutoProxy
public class JobAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobAppApplication.class, args);
    }

}
