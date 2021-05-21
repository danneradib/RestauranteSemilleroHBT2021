import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../login/login.component";
import { WelcomeComponent } from "../welcome/welcome.component";
import { AuthService } from "./auth.service";

const appRouters: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'welcome', component: WelcomeComponent }
]

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forRoot(appRouters)
    ],
    declarations:[],
    providers: [AuthService]
})
export class AppRoutingModule {

}