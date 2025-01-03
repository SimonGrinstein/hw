package ait.cohort49.shop_49.exceptionHandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Second Test Exception")
public class SecondTestException extends RuntimeException {

    public SecondTestException(String message) {
        super(message);
    }

}
