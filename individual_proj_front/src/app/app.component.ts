import { Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  username!: string;
  isLoggedIn = false;
  title = 'individual_proj_front';

  constructor(private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.storageService.getToken();

    if(this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.username = user.username;
    }
  }
  
  logout() {
    this.storageService.singOut();
    window.location.reload();
  }
  
}
