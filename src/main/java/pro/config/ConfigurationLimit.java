package pro.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "value.limit")
public class ConfigurationLimit {

    private Double dayLimit;

    public ConfigurationLimit() {
    }

    public void setDayLimit(Double dayLimit) {
        this.dayLimit = dayLimit;
    }
}
