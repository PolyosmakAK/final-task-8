package pro.exception;

import lombok.Getter;

@Getter
public class RollbackReservationException extends RuntimeException {
    private final Float lastReservation;
    private final Float currentLimit;

    public RollbackReservationException(Float lastReservation, Float currentLimit) {
        this.lastReservation = lastReservation;
        this.currentLimit = currentLimit;
    }
}
