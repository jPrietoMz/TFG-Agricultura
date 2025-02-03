import { Routes } from '@angular/router';
import { LoginFormComponent } from './view/login/login-form/login-form.component';
import { RegisterComponent } from './view/register/register-form/register-form.component';
import { CultivoComponent } from './view/cultivo/cultivo.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'cultivo', component: CultivoComponent }
];
