import { Component, OnInit } from '@angular/core';
import { Review } from '../models/movie';
import { Router } from '@angular/router';
import { ReviewService } from '../Review.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit {

  reviews: Review[] = [];

  constructor(private reviewService: ReviewService, private router: Router) { }

  ngOnInit(): void {
   
  }

  onBackButton(): void {
    this.router.navigate(['/']);
  }

  onCommentClick(review: Review): void {
    console.log(`Comment on ${review.title}`);
  }
}
