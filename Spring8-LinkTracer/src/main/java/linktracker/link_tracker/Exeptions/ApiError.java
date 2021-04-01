package linktracker.link_tracker.Exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiError {

    private String error;
    private String message;
    private Integer status;

    public ApiError(String error, String message, Integer status) {
        super();
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
