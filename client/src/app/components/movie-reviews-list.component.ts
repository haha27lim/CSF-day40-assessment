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

  title!: string
  queryParams$!: Subscription
  reviews: Review[] = []

  constructor(private activatedRoute: ActivatedRoute, private reviewSvc: ReviewService, 
        private router: Router) {}

  ngOnInit() {
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      async (queryParams) => {
        this.title = queryParams['query']
        console.log("Getting results for.. " + this.title)
      }
    )
    this.reviewSvc.getSearch(this.title)
      .then(p => {
        this.reviews = p
      })
      .catch((err) => console.log(err));
  }

  onBackButton(): void {
    this.router.navigate(['/']);
  }

  onCommentClick(review: Review): void {
    console.log(`Comment on ${review.title}`)
    this.router.navigate(['/comment', review.title])
  }

  ngOnDestroy(): void {
    this.queryParams$.unsubscribe()
  }
}
