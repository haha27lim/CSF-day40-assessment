import { Component, OnDestroy, OnInit } from '@angular/core';
import { Review } from '../models/movie';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from '../Review.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {

  reviews: Review[] = [];
  queryParams$!: Subscription;
  replacementPicture: string = "/assets/placeholder.jpg"

  constructor(private reviewService: ReviewService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      async (queryParams) => {
        const title = queryParams['title'];
        this.reviewService.getSearch(title)
        .then(result => {
          this.reviews = result;
          console.info("Getting results for.. " + title)
        })
        .catch((err) => console.log(err));
      }
    );
  }

  onBackButton(): void {
    this.router.navigate(['/']);
  }

  onCommentClick(review: Review): void {
    console.log(`Comment on ${review.title}`);
  }

  ngOnDestroy(): void {
    this.queryParams$.unsubscribe()
  }
}
