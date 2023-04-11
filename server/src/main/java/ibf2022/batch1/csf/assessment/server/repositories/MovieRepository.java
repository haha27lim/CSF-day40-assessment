package ibf2022.batch1.csf.assessment.server.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ibf2022.batch1.csf.assessment.server.models.Comment;

public class MovieRepository {

	@Autowired
  	private MongoTemplate mongoTemplate;

	  private static final String COMMENTS_COL = "comments";


	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//
	//db.comments.find({ title: "your_title_here" }).count()

	public int countComments(String title) {
		Query query = new Query(Criteria.where("title").is(title));
    	return (int) mongoTemplate.count(query, COMMENTS_COL);
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//

	// db.comments.insert({
		// title:title,
		// name:name,
		// rating:rating,
		// comment:comment
	// })

	public Comment insertComment(Comment r) {
        return mongoTemplate.insert(r, COMMENTS_COL);
    }
}
