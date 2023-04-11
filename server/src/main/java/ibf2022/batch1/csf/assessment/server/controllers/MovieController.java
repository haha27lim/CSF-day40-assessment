package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api")
public class MovieController {

	// TODO: Task 3, Task 4, Task 8

	private Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieSvc;

	@GetMapping(path = "/search")
	public ResponseEntity<String> getSearch(@RequestParam(name="query", required = true) String title) {

		JsonArray result = null;
		List<Review> results = movieSvc.searchReviews(title);
	
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		if (results != null) {
			for (Review rv : results) {
				arrBuilder.add(rv.toJson(rv));
			}
		}
	
		result = arrBuilder.build();
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(result.toString());
	}

	@PostMapping(path = "/comment", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> saveComment(@RequestParam String title, @RequestParam String name, 
			@RequestParam Integer rating, @RequestParam String comment) {
		Comment c= new Comment();
		c.setTitle(title);
		c.setName(name);
		c.setRating(rating);
		c.setComment(comment);
		Comment r = movieSvc.insertComment(c);
		return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r.toJSON().toString());
	}
}

