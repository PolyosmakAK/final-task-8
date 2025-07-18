package pro.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "value.limit")
public class ConfigurationLimit {

    private String dayLimit;

    public ConfigurationLimit() {
    }

    public void setDayLimit(String dayLimit) {
        this.dayLimit = dayLimit;
    }
}
