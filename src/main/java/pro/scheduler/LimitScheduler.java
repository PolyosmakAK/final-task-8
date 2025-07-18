package pro.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.config.ConfigurationLimit;
import pro.service.UsersLimitService;

@Service
@AllArgsConstructor
public class LimitScheduler {
    private final UsersLimitService usersLimitService;
    private final ConfigurationLimit configurationLimit;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetLimits() {
        usersLimitService.updateLimits(configurationLimit.getDayLimit());
    }
}
