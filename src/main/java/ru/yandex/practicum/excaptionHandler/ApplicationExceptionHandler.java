package ru.yandex.practicum.excaptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.exception.DataInvalidException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(DataInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataInvalidException(DataInvalidException e) {
        return new ErrorResponse(e.getMessage());
    }
}
