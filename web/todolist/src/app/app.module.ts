import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {RegisterComponent} from './component/register/register.component';
import {AuthenticationComponent} from './component/authentication/authentication.component';
import {TasksComponent} from './component/tasks/tasks.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {HeaderComponent} from './component/header/header.component';
import {CategoryComponent} from './component/category/category.component';
import { AllTasksComponent } from './component/all-tasks/all-tasks.component';
import { CategoryTasksComponent } from './component/category-tasks/category-tasks.component';
import { TaskInfoComponent } from './component/task-info/task-info.component';
import { EditTaskComponent } from './component/edit-task/edit-task.component';
import { AddCategoryComponent } from './component/add-category/add-category.component';
import { CategoriesComponent } from './component/categories/categories.component';
import { CreateTaskComponent } from './component/create-task/create-task.component';
import { ScheduledComponent } from './component/scheduled/scheduled.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AddTaskComponent } from './component/add-task/add-task.component';
import { MakeScheduledComponent } from './component/make-scheduled/make-scheduled.component';
import { ScheduledsComponent } from './component/scheduleds/scheduleds.component';
import { ActivitiesComponent } from './component/activities/activities.component';

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        AuthenticationComponent,
        TasksComponent,
        HeaderComponent,
        CategoryComponent,
        AllTasksComponent,
        CategoryTasksComponent,
        TaskInfoComponent,
        EditTaskComponent,
        AddCategoryComponent,
        CategoriesComponent,
        CreateTaskComponent,
        ScheduledComponent,
        AddTaskComponent,
        MakeScheduledComponent,
        ScheduledsComponent,
        ActivitiesComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        AngularFontAwesomeModule

    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
