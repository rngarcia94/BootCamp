package challenge.challenge.Control;



import challenge.challenge.Exception.ApiError;
import challenge.challenge.Exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ApiError> handleApiException(ApiException e) {
        Integer statusCode = e.getStatusCode();
        String message = e.getMessage();
        String status = e.getCode();
        ApiError apiError = new ApiError(status, message, statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = {IOException.class})
    protected ResponseEntity<ApiError> handleApieException(IOException e) {

        Integer statusCode = 404;
        String message = e.getMessage();
        String status = "Not Found";
        ApiError apiError = new ApiError(status, message, statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);

    }

    @ExceptionHandler(value = {BindException.class})
    protected ResponseEntity<ApiError> handleApiException(BindException e) {
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        String message = "Date format must be dd/mm/yyyy";
        String status = HttpStatus.BAD_REQUEST.toString();
        ApiError apiError = new ApiError(status, message, statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    protected ResponseEntity<ApiError> handleInvalidFormatException(HttpMessageNotReadableException e) {
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        String status = HttpStatus.BAD_REQUEST.name();
        String message = "Malformed JSON request";
        ApiError apiError = new ApiError(status, message, statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}



