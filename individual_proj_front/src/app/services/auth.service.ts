import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = "environment.apiUrl";

  constructor(private http: HttpClient) { }

  login(login: string, password: string): Observable<any> {
    return this.http.post(
      this.apiUrl + 'authenticate', {
      login,
      password,
    }, httpOptions);
  }

  register(name: string, surname: string, email: string,password: string): Observable<any> {
    return this.http.post(this.apiUrl + 'register', {
      name,
      surname,
      email,
      password,
    }, httpOptions);
  }

  logout(): Observable<any> {
    return this.http.post(this.apiUrl + 'signout',{}, httpOptions)
  }
}
