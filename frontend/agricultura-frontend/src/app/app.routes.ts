import { Routes } from '@angular/router';

// Importación de los componentes
import { LoginFormComponent } from './view/login/login-form/login-form.component';
import { RegisterFormComponent } from './view/register/register-form/register-form.component';
import { CultivoListComponent } from './view/cultivo/cultivo-list/cultivo-list.component';
import { CosechaListComponent } from './view/cosecha/cosecha-list/cosecha-list.component';
import { TratamientoListComponent } from './view/tratamiento/tratamiento-list/tratamiento-list.component';
import { InicioPageComponent } from './view/inicio/inicio-page/inicio-page.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redirigir a Login por defecto
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'cultivos', component: CultivoListComponent },
  { path: 'inicio', component: InicioPageComponent},
  { path: 'cosechas', component: CosechaListComponent },
  { path: 'tratamientos', component: TratamientoListComponent },
  { path: '**', redirectTo: 'login' } // Redirigir a login si la ruta no existe
];
