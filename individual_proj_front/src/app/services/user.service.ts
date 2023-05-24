import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const apiUrl = "apiurl";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getPublicContent(): Observable<any> {
    return this.http.get(apiUrl + 'all', {responseType : 'text'});
  }

  getUserBoard(): Observable<any> {
    return this.http.get(apiUrl + 'user', {responseType: 'text'});
  }
}
