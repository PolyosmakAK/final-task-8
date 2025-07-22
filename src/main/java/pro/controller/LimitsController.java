package pro.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.dto.LimitDto;
import pro.response.LimitResponse;
import pro.service.LimitService;

@RestController
@RequestMapping("/limits")
@AllArgsConstructor
public class LimitsController {

    private final LimitService limitService;

    @PostMapping(path = "/reservation")
    public LimitResponse reservationLimit(@RequestBody LimitDto limitDto) {
        return limitService.reservationLimitByUsers(limitDto);
    }

    @PostMapping(path = "/rollback/{id}")
    public void rollbackLimitById(@PathVariable("id") Long id) {
        limitService.rollbackReservationLimitById(id);
    }
}
