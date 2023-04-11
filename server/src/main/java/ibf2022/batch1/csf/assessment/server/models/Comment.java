package ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String title;
    private String name;
    private Integer rating;
    private String comment;

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setTitle(d.getString("title"));
        c.setName(d.getString("name"));
        c.setRating(d.getInteger("rating"));
        c.setComment(d.getString("comment"));
        return c;
    }
    
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
            .add("title", getTitle())
            .add("name", getName())
            .add("rating", getRating())
            .add("comment", getComment())
            .build();
    }
}
