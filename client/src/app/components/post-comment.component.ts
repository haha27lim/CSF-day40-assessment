import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {
  movieTitle!: string;
  commentForm!: FormGroup;
  ratings: number[] = [1, 2, 3, 4, 5];

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.commentForm = this.createForm() 
  }

  onSubmit(): void {
    
    this.commentForm.reset()
  }

  onBackClick(): void {
    this.router.navigate(['/']);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<string>('', Validators.required),
      comment: this.fb.control<string>('', Validators.required)
    });
  }

}
