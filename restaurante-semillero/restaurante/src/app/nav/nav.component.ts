import { Component, OnInit, ViewContainerRef, ViewChild } from '@angular/core';
import { AuthService } from '../core/auth.service';
import { Http, Response, Headers, RequestOptions } from '@angular';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatMenu } from '@angular/material/menu';
import { Menu } from './../estructuras';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  constructor(private auth: AuthService, private http: Http) {
  }

  usuario: string = '';
  selected: string  = '';
  opcionesMenu: Array<Menu> = [];
  logo: string = '';
  menuItems: Array<{text: string, elementRef: MatMenu}> = [
      {text: "Tabledriven.Item1", elementRef: null },
      {text: "Tabledriven.Item2", elementRef: null},
  ];

  ngOnInit() {
    this.auth.getMenu().subscribe(
      result => {
        this.usuario = result.resultado[0];
        this.opcionesMenu = result.resultado[1];
        if(result.resultado.length > 2){
          this.logo = result.resultado[2];
        }
      },
      error => {
        var errMsg = <any>error;
        console.log(errMsg);
      }
    );
  }

  @ViewChild('submenu')
  set subMenu(value: MatMenu)  {
    this.menuItems[1].elementRef = value;
  }

  select(pText :string) {
    this.selected = pText;
  }

  logout() {
    this.usuario = '';
    this.selected = '';
    this.opcionesMenu = Array<Menu>();
    this.auth.isUserLoggedIn.next(false);
    this.auth.signOut();
  }

}
