import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Review } from './models/movie';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private API_URI: string = "/api/search";

  constructor(private httpClient: HttpClient) { }

  getSearch(title: string): Promise<Review[]> {

    const params = new HttpParams()
              .set('query', title)

    // set headers for request
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    // send GET request to API with query parameters and headers
    return lastValueFrom(this.httpClient
        .get<Review[]>(this.API_URI, {params: params, headers: headers}))
    }
  

  saveComment(c: Comment): Promise<any>{
    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    const body = JSON.stringify(c);
    console.log("save comment !");
    return lastValueFrom(this.httpClient.post<any>(this.API_URI, body, {headers: headers}));
  }    

  
}
