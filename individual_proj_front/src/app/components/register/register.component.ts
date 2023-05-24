import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  
  form: any = {};
  isSuccesful = false;
  isSignUpFailed = false;
  errormess = '';
  
  constructor(private authService: AuthService) {}
  
  ngOnInit(): void {}

  onSubmit(): void {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccesful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errormess = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
