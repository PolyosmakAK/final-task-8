package pro.exception;

import lombok.Getter;

@Getter
public class LimitException extends RuntimeException {
    private final Float currentLimit;
    private final Float reservation;

    public LimitException(Float currentLimit, Float reservation) {
        this.currentLimit = currentLimit;
        this.reservation = reservation;
    }
}
