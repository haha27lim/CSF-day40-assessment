import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {
  form!:FormGroup;
  searchQuery: string = '';
  isSearchButtonDisabled: boolean = true;

  constructor(private fb: FormBuilder) {}
  
  ngOnInit(): void {
    this.form = this.createForm(); 
  }

  onSearchInput() {
    this.searchQuery = this.searchQuery.trim();
    this.isSearchButtonDisabled = this.searchQuery.length < 2;
  }

  onSubmit() {
    console.log('Title:', this.searchQuery);
  }

  private createForm(): FormGroup{
    return this.fb.group({
        searchQuery: ['', Validators.minLength(2)]
    });
  }

}
