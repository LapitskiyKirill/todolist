import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RegisterComponent} from './component/register/register.component';
import {AuthenticationComponent} from './component/authentication/authentication.component';
import {TasksComponent} from './component/tasks/tasks.component';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { HeaderComponent } from './component/header/header.component';
import { CategoryComponent } from './component/category/category.component';
import { TaskMenuComponent } from './component/task-menu/task-menu.component';
import { TaskListComponent } from './component/task-list/task-list.component';

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        AuthenticationComponent,
        TasksComponent,
        HeaderComponent,
        CategoryComponent,
        TaskMenuComponent,
        TaskListComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule

    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
