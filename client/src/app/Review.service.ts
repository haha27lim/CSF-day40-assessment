import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject, lastValueFrom } from 'rxjs';
import { Review } from './models/movie';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private API_URI: string = "/reviews";

  onReview = new Subject<Promise<Review[]>>();

  constructor(private httpClient: HttpClient) { }

  searchReviews(query: string): Promise<Review[]> {
    const params = new HttpParams()
        .set("query", query);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    return lastValueFrom(this.httpClient.get<Review[]>(this.API_URI, {params: params, headers: headers}));
  }

  
}
