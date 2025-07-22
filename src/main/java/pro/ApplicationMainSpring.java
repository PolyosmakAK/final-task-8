package pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pro.config.ConfigurationLimit;

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationLimit.class)
public class ApplicationMainSpring {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMainSpring.class, args);
    }
}