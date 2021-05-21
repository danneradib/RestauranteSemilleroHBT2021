import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { AppRoutingModule } from "../app-routing.module";
import { AuthService } from "./auth.service";

@NgModule({
    imports: [
        CommonModule,
        AppRoutingModule
    ],
    declarations:[],
    providers: [AuthService]
})

export class CoreModule {
}
