import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './component/register/register.component';
import {AuthenticationComponent} from './component/authentication/authentication.component';
import {TasksComponent} from './component/tasks/tasks.component';
import {CategoryTasksComponent} from './component/category-tasks/category-tasks.component';
import {AllTasksComponent} from './component/all-tasks/all-tasks.component';
import {TaskInfoComponent} from './component/task-info/task-info.component';
import {AddCategoryComponent} from './component/add-category/add-category.component';
import {EditTaskComponent} from './component/edit-task/edit-task.component';
import {ScheduledComponent} from './component/scheduled/scheduled.component';
import {AddTaskComponent} from './component/add-task/add-task.component';
import {MakeScheduledComponent} from './component/make-scheduled/make-scheduled.component';
import {ActivitiesComponent} from './component/activities/activities.component';
import {ScheduledsComponent} from './component/scheduleds/scheduleds.component';

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
        path: 'taskInfo/:taskId',
        component: TaskInfoComponent
    },
    {
        path: 'addCategory',
        component: AddCategoryComponent
    },
    {
        path: 'edit/:taskId',
        component: EditTaskComponent
    },
    {
        path: 'scheduled',
        component: ScheduledComponent,
        children: [
            {
                path: '',
                component: ScheduledsComponent

            },
            {
                path: 'activities',
                component: ActivitiesComponent
            }
        ]
    },
    {
        path: 'addTask',
        component: AddTaskComponent
    },
    {
        path: 'makeScheduled/:taskId',
        component: MakeScheduledComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
