import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ReviewService } from '../Review.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy  {
  movieTitle!: string;
  commentForm!: FormGroup;
  queryParams$! :  Subscription;

  constructor(private fb: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService) { }

  ngOnInit(): void {
    this.commentForm = this.createForm()
    this.queryParams$ = this.activatedRoute.queryParams.subscribe(
      (queryParams) => {
        const title = queryParams['title'];
      }
    );
  }

  saveComment() {
    const comment = this.commentForm.value as Comment;
  
    this.reviewSvc.saveComment(comment);
    this.router.navigate(['/list']);
  }
  

  onBackClick(): void {
    this.commentForm.reset()
    this.router.navigate(['/list']);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<string>('', Validators.required),
      comment: this.fb.control<string>('', Validators.required)
    });
  }

  ngOnDestroy(): void{
    this.queryParams$.unsubscribe();
  }

}
