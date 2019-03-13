import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './component/register/register.component';
import {AuthenticationComponent} from './component/authentication/authentication.component';
import {TasksComponent} from './component/tasks/tasks.component';

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
        component: TasksComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
