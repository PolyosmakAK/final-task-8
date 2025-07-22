package pro.exception;

import lombok.Getter;

@Getter
public class RollbackReservationException extends RuntimeException {
    private final Double lastReservation;
    private final Double currentLimit;

    public RollbackReservationException(Double lastReservation, Double currentLimit) {
        this.lastReservation = lastReservation;
        this.currentLimit = currentLimit;
    }
}
