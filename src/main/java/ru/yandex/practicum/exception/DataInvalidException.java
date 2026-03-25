package ru.yandex.practicum.exception;

public class DataInvalidException extends RuntimeException {
    public DataInvalidException(String message) {
        super(message);
    }
}
