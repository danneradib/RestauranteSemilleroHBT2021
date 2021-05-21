import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Restangular } from "ngx-restangular";
import { BehaviorSubject } from "rxjs";
import { Usuario } from "../estructuras";
import { Usuarios } from "./usuario";

@Injectable()
export class AuthService {

    authEstado: any = null;
    usuario = new BehaviorSubject(Usuarios);
    public isUserLoggedIn = new BehaviorSubject(false);
    private user: Usuario = new Usuario();

    constructor(private restangular: Restangular, private router: Router) {

    }

    getMenu() {
        return this.restangular.one('usaurios-ws/menu').get();
    }

    singOut(): void {
        sessionStorage.removeItem('token');
        this.authEstado = null;
        this.router.navigate(['/']);
    }

    get isLoggedIn() {
        return true;
    }

}
