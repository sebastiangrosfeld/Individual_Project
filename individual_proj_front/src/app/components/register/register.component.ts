import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  
  form: any = {
    name: null,
    surname: null,
    email: null,
    password: null
  };
  isSuccesful = false;
  isSignUpFailed = false;
  errorMessage = '';
  
  constructor(private authService: AuthService) {}
  
  ngOnInit(): void {}

  onSubmit(): void {
    const {name, surname, email, password} = this.form;

    this.authService.register(name, surname, email, password).subscribe({
      next: data => {
        console.log(data);
        this.isSuccesful = true;
        this.isSignUpFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
  });
  }
}
