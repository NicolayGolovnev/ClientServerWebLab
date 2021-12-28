package ru.ananev.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebHandler {
    /**
     * Метод обработки исключения, когда контрагент не найден в БД
     * @param exception вызванное исключение
     * @return страницу http со статусом {@link HttpStatus#NOT_FOUND} и сообщением исключения
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> agentNotFound(RuntimeException exception) {
//        log.error("[ExceptionHandler]\tThrowed AgentNotFoundException - return ResponseEntity with HttpStatus NOT_FOUND: " + exception);
        return new ResponseEntity<>("RuntimeException: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обработки всех остальных исключений
     * @param exception вызванное исключение
     * @return страницу http со статусом {@link HttpStatus#INTERNAL_SERVER_ERROR} и сообщением исключения
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> responseEntityException(Exception exception) {
        log.error("[ExceptionHandler]\tThrowed some exception - return ResponseEntity with HttpStatus INTERNAL_SERVER_ERROR: " + exception);
        return new ResponseEntity<>("Caught exception: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}