import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {APP_BASE_HREF} from '@angular/common';
import {BaseRequestOptions, HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {AppRoutingModule, routing} from './app-routing.module';

import {AboutModule} from './about/about.module';
import {SharedModule} from './shared/shared.module';
import {AlertComponent} from "./_directives/alert.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {FormsModule} from "@angular/forms";
import {MockBackend} from "@angular/http/testing";
import {fakeBackendProvider} from "./_helpers/fake-backend";
import {UserService} from "./_services/user.service";
import {AuthenticationService} from "./_services/authentication.service";
import {AlertService} from "./_services/alert.service";
import {AuthGuard} from "./_guards/auth.guard";

import {DxButtonModule, DxResponsiveBoxModule} from 'devextreme-angular';


@NgModule({
  imports: [BrowserModule,
    HttpModule,
    AppRoutingModule,
    AboutModule,
    FormsModule,

    DxButtonModule,
    DxResponsiveBoxModule,
    routing,
    SharedModule.forRoot()],
  declarations: [AppComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent],
  providers: [AuthGuard,
    AlertService,
    AuthenticationService,
    UserService,

    // providers used to create fake backend
    //fakeBackendProvider,
    //MockBackend,
    //BaseRequestOptions,
    {
      provide: APP_BASE_HREF,
      useValue: '<%= APP_BASE %>'
    }],
  bootstrap: [AppComponent]

})
export class AppModule {
}
