import { Routes } from '@angular/router';

// Importación de los componentes
import { LoginFormComponent } from './view/login/login-form/login-form.component';
import { RegisterFormComponent } from './view/register/register-form/register-form.component';
import { CultivoListComponent } from './view/cultivo/cultivo-list/cultivo-list.component';
import { CosechaListComponent } from './view/cosecha/cosecha-list/cosecha-list.component';
import { TratamientoListComponent } from './view/tratamiento/tratamiento-list/tratamiento-list.component';
import { InicioPageComponent } from './view/inicio/inicio-page/inicio-page.component';
import { CultivoDetailComponent } from './view/cultivo/cultivo-detail/cultivo-detail.component';
import { CosechaFormComponent } from './view/cosecha/cosecha-form/cosecha-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' }, // Redirigir a Login por defecto
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'cultivos', component: CultivoListComponent },
  { path: 'cultivos/:cultivoId', component: CultivoDetailComponent },
  { path: 'inicio', component: InicioPageComponent},
  
   // Listado de cultivos
   { path: 'cultivos', component: CultivoListComponent },

   // Detalle de cultivo y sus secciones (Cosechas y Tratamientos)
   { path: 'cultivos/:cultivoId', component: CultivoDetailComponent },
   { path: 'cultivos/:cultivoId/cosechas', component: CosechaListComponent },
   { path: 'cultivos/:cultivoId/tratamientos', component: TratamientoListComponent },

   //Listado de cosechas
   { path: 'cultivos/:cultivoId/cosechas', component: CosechaListComponent },
   { path: 'cultivos/:cultivoId/cosechas/nueva', component: CosechaFormComponent },
 
   { path: '**', redirectTo: 'login' } 
];
