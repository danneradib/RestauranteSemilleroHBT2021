import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login = '';
  password = '';

  success = false;

  constructor() { }

  ngOnInit(): void {

  }

  autenticar(form: NgForm) {
    console.log(form.value);
    console.log(this.login + " " + this.password);
  }

}
