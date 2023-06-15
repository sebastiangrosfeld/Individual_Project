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

  private apiUrl = "http://localhost:8080/bankapp/v1/auth/";

  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      this.apiUrl + 'authenticate', {
      email,
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
    return this.http.post(this.apiUrl + 'logout',{}, httpOptions)
  }
}
