package com.market.carmarketservice.api.exception;

import com.market.carmarketservice.exception.LinkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@PropertySource("classpath:notify.properties")
public class MainExceptionController {
    private final Environment env;

    @ExceptionHandler(value = LinkNotFoundException.class)
    public ResponseEntity<Object> linkNotFound() {
        return new ResponseEntity<>(env.getProperty("LinkNotFound"), HttpStatus.NOT_FOUND);
    }
}
