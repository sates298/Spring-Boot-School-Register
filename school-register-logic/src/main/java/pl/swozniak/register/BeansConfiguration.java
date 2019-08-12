package pl.swozniak.register;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeansConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender(){
        return new JavaMailSenderImpl();
    }
}
