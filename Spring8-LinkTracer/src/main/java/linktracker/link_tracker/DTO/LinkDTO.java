package linktracker.link_tracker.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkDTO {
    private String link;
    private int count;
    private String pass = "";

    public LinkDTO(String link, int count) {
        this.link = link;
        this.count = count;
    }
}
