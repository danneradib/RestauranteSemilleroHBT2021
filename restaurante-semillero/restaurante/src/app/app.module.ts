import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { FormsModule } from '@angular/forms';
import { NavComponent } from './nav/nav.component';
import { RestangularModule, Restangular } from 'ngx-restangular';

export function RestangularConfigFactory (RestangularProvider: any) {
  RestangularProvider.setBaseUrl('http://localhost:8082');
  RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'});
  RestangularProvider.setDefaultHeaders({'Access-Control-Allow-Origin': '*'});
  RestangularProvider.setDefaultHeaders({'Access-Control-Allow-Headers': 'Content-Type, Accept'});
  RestangularProvider.setDefaultHeaders({'Authorizacion': sessionStorage.getItem('token') });
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
