package pro.exception;

import lombok.Getter;

@Getter
public class LimitException extends RuntimeException {
    private final Double currentLimit;
    private final Double reservation;

    public LimitException(Double currentLimit, Double reservation) {
        this.currentLimit = currentLimit;
        this.reservation = reservation;
    }
}
