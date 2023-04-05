package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import ibf2022.batch1.csf.assessment.server.models.Movie;

public class MovieRepository {

	@Autowired
  	private MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//
	public int countComments() {
		Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.lookup("comments", "_id", "movieId", "comments"),
        Aggregation.project()
            .and("title").as("title")
            .and("reviews").size().as("reviewCount"),
        Aggregation.sort(Sort.by(Direction.DESC, "commentCount"))
		);
		AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "movies", Document.class);
		List<Document> documents = results.getMappedResults();
		int totalCommentCount = 0;
		for (Document document : documents) {
		  int commentCount = document.getInteger("commentCount", 0);
		  totalCommentCount += commentCount;
		}
		return totalCommentCount;
	  }

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//


	

	public List<Movie> searchMovies (String query) {
	  TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(query);
	  Query searchQuery = TextQuery.queryText(criteria).sortByScore();
	  List<Movie> movies = mongoTemplate.find(searchQuery, Movie.class);
	  return movies;
	}
}
