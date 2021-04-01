package linktracker.link_tracker.Controls;


import linktracker.link_tracker.Exeptions.ApiError;
import linktracker.link_tracker.Exeptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

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


}
