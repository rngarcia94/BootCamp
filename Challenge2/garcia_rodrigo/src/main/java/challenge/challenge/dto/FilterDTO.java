package challenge.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private String destination;
    @DateTimeFormat (pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @DateTimeFormat (pattern = "dd/MM/yyyy")
    private Date dateTo;
}
