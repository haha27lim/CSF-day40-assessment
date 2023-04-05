package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;;

@Service
public class MovieService {

	@Value("${nytimes_Url}")
	private String BASE_URL;

	@Value("${nytimeskey}")
    private String API_KEY;


	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		
		List<Review> reviews = new ArrayList<>();


        try {
			String url = UriComponentsBuilder
								.fromUriString(BASE_URL + "reviews")
                    			.queryParam("api-key", API_KEY)
                    			.queryParam("query", query)
								.toUriString();

            RestTemplate template = new RestTemplate();
			ResponseEntity<String> resp = null;
            resp = template.getForEntity(url, String.class);

            JsonObject json = Json.createReader(new StringReader(resp.getBody())).readObject();
        	JsonArray results = json.getJsonArray("results");

            for (JsonValue resultValue : results) {
                if (resultValue.getValueType() == JsonValue.ValueType.OBJECT) {
                    JsonObject result = (JsonObject) resultValue;

                    Review review = new Review();
                    review.setTitle(result.getString("display_title", ""));
                    review.setRating(result.getString("mpaa_rating", ""));
                    review.setByline(result.getString("byline", ""));
                    review.setHeadline(result.getString("headline", ""));
                    review.setSummary(result.getString("summary_short", ""));

                    JsonObject link = result.getJsonObject("link");
                    if (link != null) {
                        review.setReviewURL(link.getString("url", ""));
                    }

                    JsonObject multimedia = result.getJsonObject("multimedia");
                    if (multimedia != null) {
                        JsonObject multimediaSrc = multimedia.getJsonObject("src");
                        if (multimediaSrc != null) {
                            review.setImage(multimediaSrc.getString("src", ""));
                        }
                    }

                    reviews.add(review);
                }
            }

        } catch (RestClientException e) {
            System.err.println("Error while calling NY Times API: " + e.getMessage());
        }

        return reviews;
    }

}
