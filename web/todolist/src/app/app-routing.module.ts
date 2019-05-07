import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './component/register/register.component';
import {AuthenticationComponent} from './component/authentication/authentication.component';
import {TasksComponent} from './component/tasks/tasks.component';
import {CategoryTasksComponent} from './component/category-tasks/category-tasks.component';
import {AllTasksComponent} from './component/all-tasks/all-tasks.component';
import {TaskInfoComponent} from './component/task-info/task-info.component';

const routes: Routes = [
    {
        path: 'register',
        component: RegisterComponent
    },
    {
        path: 'auth',
        component: AuthenticationComponent
    },
    {
        path: 'tasks',
        component: TasksComponent,
        children: [
            {
                path: 'category/:categoryName',
                component: CategoryTasksComponent

            },
            {
                path: '',
                component: AllTasksComponent
            }
        ]
    },
    {
        path: 'taskInfo',
        component: TaskInfoComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
