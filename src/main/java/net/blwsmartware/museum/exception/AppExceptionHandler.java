package net.blwsmartware.museum.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import net.blwsmartware.museum.dto.response.MessageResponse;
import net.blwsmartware.museum.enums.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.StringJoiner;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

    private MessageResponse<?> responseBody(ErrorResponse err) {
        return MessageResponse.builder().success(false).code(err.getCode()).message(err.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<MessageResponse<?>> handleGlobalException(Exception exception) {
        log.info(">>>>>>>>>Exception: {}", exception.getMessage());
        return ResponseEntity.status(ErrorResponse.UNCATEGORIZED_ERROR.getHttpCode()).body(responseBody(ErrorResponse.UNKNOWN_EXCEPTION));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<MessageResponse<?>> handleGlobalRuntimeException(RuntimeException exception) {
        log.info(">>>>>>>>>RuntimeException: {}", exception.getMessage());
        return ResponseEntity.status(ErrorResponse.UNCATEGORIZED_ERROR.getHttpCode()).body(responseBody(ErrorResponse.UNCATEGORIZED_ERROR));
    }

    @ExceptionHandler(value = AppRuntimeException.class)
    public ResponseEntity<MessageResponse<?>> handleIdentityException(AppRuntimeException exception) {
        return ResponseEntity.status(exception.getErrorResponse().getHttpCode()).body(responseBody(exception.getErrorResponse()));

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponse<?>> handleUniqueData(DataIntegrityViolationException exception) {

        log.info("DataIntegrityViolationException: {}", exception.getMessage());

        Throwable rootCause = getRootCause(exception);
        ErrorResponse err = ErrorResponse. UNIQUE_EXISTED;

        String errorMessage = rootCause.getMessage();

        log.info("ConstraintViolationException: {}", errorMessage);

        if (errorMessage.contains("foreign key")) {
            err = ErrorResponse.QUERY_KEY_INVALID;
        } else if (errorMessage.contains("email")) {
            err = ErrorResponse.EMAIL_EXISTED;
        } else if (errorMessage.contains("username")) {
            err = ErrorResponse.USERNAME_EXISTED;
        } else if (errorMessage.contains("name")) {
            err = ErrorResponse.NAME_EXISTED;
        }


        return ResponseEntity
                .status(err.getHttpCode())
                .body(responseBody(err));
    }

    private Throwable getRootCause(Throwable throwable) {
        log.info("-------------: {}", throwable.getMessage());
        Throwable cause = throwable;
        while (cause.getCause() != null && cause != cause.getCause()) {
            cause = cause.getCause();
        }
        log.info("+++++++++++++: {}", cause.getMessage());
        return cause;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse<?>> handleValidationData(MethodArgumentNotValidException exception) {

        MessageResponse<?> response = MessageResponse.builder().build();
        response.setSuccess(false);
        response.setCode(ErrorResponse.MANY_DATA_INVALID.getCode());
        StringJoiner x = new StringJoiner(" \n ");

        exception.getBindingResult().getAllErrors().forEach((error) -> {

            ErrorResponse err = ErrorResponse.UNCATEGORIZED_KEY;
            try {
                err = ErrorResponse.valueOf(error.getDefaultMessage());
            } catch (IllegalArgumentException e) {
                log.info(e.getMessage());
            }

            x.add(err.getMessage());

        });
        response.setMessage(x.toString());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageResponse<?>> handleValidData(ConstraintViolationException exception) {

        MessageResponse<?> response = MessageResponse.builder().build();
        response.setSuccess(false);
        response.setCode(ErrorResponse.MANY_DATA_INVALID.getCode());
        StringJoiner x = new StringJoiner(" \n ");

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String errorMessage = violation.getMessageTemplate();
            ErrorResponse err = ErrorResponse.UNCATEGORIZED_KEY;
            try {
                err = ErrorResponse.valueOf(errorMessage);
            } catch (IllegalArgumentException e) {
                log.info(e.getMessage());
            }

            x.add(err.getMessage());
        }
        response.setMessage(x.toString());


        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<MessageResponse<?>> handleAccessException(AccessDeniedException exception) {
        log.error("AccessDeniedException in Handler:{}", exception.getMessage());
        return ResponseEntity.status(ErrorResponse.ACCESS_DENIED.getHttpCode()).body(responseBody(ErrorResponse.ACCESS_DENIED));
    }


}
