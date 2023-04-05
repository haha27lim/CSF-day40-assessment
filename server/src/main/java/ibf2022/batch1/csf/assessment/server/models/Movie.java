package ibf2022.batch1.csf.assessment.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String display_title;
    private String summary_short;
    private String publication_date;
    private String opening_date;
    private String[] multimedia;
}
