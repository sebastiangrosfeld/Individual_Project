import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ListComponent } from './list/list.component';
import { PagComponent } from './pag/pag.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ListComponent,
    PagComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
