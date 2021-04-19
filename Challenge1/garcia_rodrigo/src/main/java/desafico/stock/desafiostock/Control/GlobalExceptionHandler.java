package desafico.stock.desafiostock.Control;



import desafico.stock.desafiostock.Exception.ApiError;
import desafico.stock.desafiostock.Exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ApiError> handleApiException(ApiException e){
        Integer statusCode = e.getStatusCode();
        String message = e.getMessage();
        String status = e.getCode();
        ApiError apiError = new ApiError(status,message,statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(value = {IOException.class})
    protected ResponseEntity<ApiError> handleApieException(IOException e){

        Integer statusCode = 404;
        String message = e.getMessage();
        String status = "Not Found";
        ApiError apiError = new ApiError(status,message,statusCode);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);

    }


}
