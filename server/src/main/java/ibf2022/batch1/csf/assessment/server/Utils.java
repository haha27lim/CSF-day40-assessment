package ibf2022.batch1.csf.assessment.server;

import org.bson.Document;

import ibf2022.batch1.csf.assessment.server.models.Review;

public class Utils {

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
