package pl.swozniak.register;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
//@ConfigurationProperties(prefix = "mail")
public class BeanConfiguration {

    @Value("${mail.host}")
    private String emailHost;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.port}")
    private Integer emailPort;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(emailHost);
        sender.setPort(emailPort);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties mailProps = new Properties();
        mailProps.setProperty("mail.transport.protocol","smtp");
        mailProps.setProperty("mail.smtp.auth","true");
        mailProps.setProperty("mail.smtp.starttls.enable","true");
        mailProps.setProperty("mail.debug","false");

        sender.setJavaMailProperties(mailProps);

        return sender;
    }
}
