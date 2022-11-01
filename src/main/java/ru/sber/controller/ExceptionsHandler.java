package ru.sber.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.sber.model.dto.ErrorDto;
import ru.sber.model.exception.HttpStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST);
        errorDto.setMessage("not valid due to validation error: " + e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST);
        errorDto.setMessage("not valid due to match error: " + e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleMethodIllegalArgumentException(IllegalArgumentException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST);
        errorDto.setMessage("not valid due to illegal argument error: " + e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.CONFLICT);
        errorDto.setMessage("not valid due to constraint violation error: " + e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleHttpStatusException(HttpStatusException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(e.getStatus());
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, e.getStatus());
    }
}
