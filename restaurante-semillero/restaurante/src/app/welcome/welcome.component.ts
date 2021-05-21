import { Component, OnInit } from '@angular/core';
import { Producto } from '../estructuras';

const ELEMENT_DATA: Producto[] = [];

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  

  constructor() { }


  ngOnInit(): void {
  }

}
