import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';
import { ReviewService } from '../Review.service';


@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {

  form!: FormGroup;
  ButtonDisabled: boolean = true;

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.form = this.createForm();
  }

  onSearchInput() {
    const title = this.form.value.title.trim();
    this.ButtonDisabled = title.length < 2;
  }
  
  onSubmit() {
    const title = this.form?.value['title']
    console.log('>>>title: ', title)
    this.router.navigate(['/list'], {queryParams: {query: title}});
  }

  private createForm(): FormGroup{
    return this.fb.group({
      title: this.fb.control<string>('', [Validators.required, Validators.minLength(2)])

    });
  }
}

