import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './Components/register/register.component';
import {AuthComponent} from './Components/auth/auth.component';
import {MainComponent} from './Components/main/main.component';
import {CategoriesComponent} from './Components/categories/categories.component';
import {AllTasksComponent} from './Components/all-tasks/all-tasks.component';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'auth',
    component: AuthComponent
  },
  {
    path: 'main',
    component: MainComponent,
    children: [
      {
        path: 'categories',
        component: CategoriesComponent
      },
      {
        path: 'alltasks',
        component: AllTasksComponent
      }
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
