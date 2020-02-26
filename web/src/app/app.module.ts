import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {RegisterComponent} from './Components/register/register.component';
import {AppRoutingModule} from './app-routing.module';
import {AuthComponent} from './Components/auth/auth.component';
import {HttpClientModule} from '@angular/common/http';
import {MainComponent} from './Components/main/main.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { CategoriesComponent } from './Components/categories/categories.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    AuthComponent,
    MainComponent,
    CategoriesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
