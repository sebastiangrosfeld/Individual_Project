import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const apiUrl = "http://localhost:8080/bankapp/v1/accounts/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  getPublicContent(): Observable<any> {
    return this.http.get(apiUrl, {responseType : 'text'});
  }

  getUserBoard(): Observable<any> {
    const rawToken = sessionStorage.getItem('auth-user');
    const token = rawToken ? JSON.parse(rawToken) : [];

    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token.access_token
    ,'Content-Type': 'application/json' });

    return this.http.get(apiUrl + 'all', {headers: headers});
  }
}
