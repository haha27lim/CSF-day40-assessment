package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	// TODO: Task 3, Task 4, Task 8

	private Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieSvc;

	@GetMapping("/search")
	public ResponseEntity<String> searchMovies(@RequestParam(value = "query", required = true) String title) {
		List<Review> reviews = movieSvc.searchReviews(title);
		if (reviews.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			String reviewsJson = null;
			try {
				reviewsJson = objectMapper.writeValueAsString(reviews);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return ResponseEntity
					.status(HttpStatus.OK)
					.contentType(MediaType.APPLICATION_JSON)
					.body(reviewsJson);
		}
	}




}
