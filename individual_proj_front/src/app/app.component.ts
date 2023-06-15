import { Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  username?: string;

  constructor(private storageService: StorageService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();

    if(this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

     // this.showAdminBoard = this.roles.includes('ADMIN');
      //this.showManagerBoard = this.roles.includes('MANAGER');

      this.username = user.username;
    }
  }
  
  logout(): void {
    this.authService.logout().subscribe({
      next: res => {
        console.log(res);
        this.storageService.singOut();

        window.location.reload();
      },
      error: err => {
        console.log(err);
      }
    })
    
  }
  
}
