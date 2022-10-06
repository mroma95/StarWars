package com.romaniak.marcin.starwars.exception.handler;

import com.romaniak.marcin.starwars.exception.IllegalUserActionException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerExceptionHandler {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(Exception ex,
      HttpServletRequest httpRequest) {
    return handleWarn(ex, httpRequest, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {IllegalUserActionException.class})
  public ResponseEntity<Object> handleIllegalUserActionException(Exception ex,
      HttpServletRequest httpRequest) {
    return handleWarn(ex, httpRequest, HttpStatus.BAD_REQUEST);
  }

  public static ResponseEntity<Object> handleWarn(Exception ex, HttpServletRequest httpRequest,
      HttpStatus httpStatus) {
    Map<String, Object> body = prepareBody(ex, true);
    log.warn("Exception for request {}: {}", httpRequest.getRequestURI(), ex.getMessage());
    return new ResponseEntity<>(body, httpStatus);
  }

  public static Map<String, Object> prepareBody(Exception ex, boolean includeStack) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now().toString());
    body.put("message", ExceptionUtils.getMessage(ex));
    if (includeStack) {
      body.put("stacktrace", ExceptionUtils.getStackTrace(ex));
    }
    return body;
  }

}