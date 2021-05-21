export class Usuarios {
    login: string = '';
    admin: boolean = false;

    constructor(authData){
        this.login = authData.login;
        this.admin = false;
    }
}