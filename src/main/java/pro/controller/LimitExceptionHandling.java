package pro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.dto.ErrorLimitDto;
import pro.dto.ErrorRollbackReservationDto;
import pro.exception.LimitException;
import pro.exception.RollbackReservationException;

@Slf4j
@RestControllerAdvice
public class LimitExceptionHandling {
    @ExceptionHandler(LimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorLimitDto getErrorLimit(LimitException exception) {
        var errorLimitDto = new ErrorLimitDto("Текущий лимит %s меньше резервируемого %s"
                .formatted(exception.getCurrentLimit(), exception.getReservation()));
        log.error(errorLimitDto.message());
        return errorLimitDto;
    }

    @ExceptionHandler(RollbackReservationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRollbackReservationDto getErrorLimit(RollbackReservationException exception) {
        if (exception.getLastReservation() == null) {
            return new ErrorRollbackReservationDto("У пользователя нет активных платежей");
        } else {
            return new ErrorRollbackReservationDto("У пользователя максимальный дневной лимит");
        }
    }
}
