import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './Components/register/register.component';
import {AuthComponent} from './Components/auth/auth.component';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'auth',
    component: AuthComponent
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
