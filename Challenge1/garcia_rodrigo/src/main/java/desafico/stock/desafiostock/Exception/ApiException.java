package desafico.stock.desafiostock.Exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{

    private final String code;
    private final String description;
    private final Integer statusCode;

    public ApiException(String code, String description, Integer statusCode) {
        super(description);
        this.code = code;
        this.description = description;
        this.statusCode = statusCode;
    }

    public ApiException(HttpStatus httpStatus, String description){
        super(description);
        this.code = httpStatus.name();
        this.description = description;
        this.statusCode = httpStatus.value();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
