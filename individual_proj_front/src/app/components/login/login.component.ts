import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errormess = '';

  constructor(private authService: AuthService, private tokenStorage: StorageService) {}
  
  ngOnInit(): void {
    if (this.tokenStorage.getToken()){
      this.isLoggedIn = true;
    }
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accesToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.reloadPage();
      },
      err => {
        this.errormess = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  
  reloadPage(): void {
    window.location.reload();
  }
}
