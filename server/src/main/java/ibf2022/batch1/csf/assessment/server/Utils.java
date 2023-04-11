package ibf2022.batch1.csf.assessment.server;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Utils {

    // Convert JSON object to Review object
    public static Review toReview(JsonObject json) {
        Review review = new Review();
        review.setTitle(json.getString("display_title"));
        review.setRating(json.getString("mpaa_rating"));
        review.setByline(json.getString("byline"));
        review.setHeadline(json.getString("headline"));
        review.setSummary(json.getString("summary_short"));

        JsonObject link = json.getJsonObject("link");
        if (link != null) {
            review.setReviewURL(link.getString("url"));
        }

        JsonObject multimedia = json.getJsonObject("multimedia");
        if (multimedia != null) {
            JsonObject multimediaSrc = multimedia.getJsonObject("src");
            if (multimediaSrc != null) {
                review.setImage(multimediaSrc.getString("src"));
            }
        }

        return review;
    }

    // Convert JSON object string to JSON object
    public static JsonObject toJsonObject(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        return reader.readObject();
    }

    // Convert JSON object string to list of Review objects
    public static List<Review> toReviewList(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonArray jsonArray = reader.readArray();
        List<Review> reviews = new ArrayList<>();
        for (JsonValue value : jsonArray) {
            if (value.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonObject = (JsonObject) value;
                Review review = toReview(jsonObject);
                reviews.add(review);
            }
        }
        return reviews;
    }

    // Convert Review object to Document
    public static Document toDocument(Review review) {
        Document document = new Document();
        document.put("title", review.getTitle());
        document.put("rating", review.getRating());
        document.put("byline", review.getByline());
        document.put("headline", review.getHeadline());
        document.put("summary", review.getSummary());
        document.put("reviewURL", review.getReviewURL());
        document.put("image", review.getImage());
        return document;
    }

    // Convert Document to Review object
    public static Review toReview(Document document) {
        Review review = new Review();
        review.setTitle(document.getString("title"));
        review.setRating(document.getString("rating"));
        review.setByline(document.getString("byline"));
        review.setHeadline(document.getString("headline"));
        review.setSummary(document.getString("summary"));
        review.setReviewURL(document.getString("reviewURL"));
        review.setImage(document.getString("image"));
        return review;
    }
}
