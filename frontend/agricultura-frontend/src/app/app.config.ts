import { ApplicationConfig } from '@angular/core';
import { provideRouter, Routes } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// Importamos los componentes
import { LoginFormComponent } from './view/login/login-form/login-form.component';
import { RegisterFormComponent } from './view/register/register-form/register-form.component';
import { CultivoListComponent } from './view/cultivo/cultivo-list/cultivo-list.component';
import { CosechaListComponent } from './view/cosecha/cosecha-list/cosecha-list.component'; 
import { TratamientoListComponent } from './view/tratamiento/tratamiento-list/tratamiento-list.component'; 

// Importamos los resolvers
import { CultivoResolver } from './resolver/cultivo.resolver';
import { CosechaResolver } from './resolver/cosecha.resolver';
import { TratamientoResolver } from './resolver/tratamiento.resolver';
import { NavbarComponent } from './view/navbar/navbar.component';

// Definimos las rutas
const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirigir a login por defecto
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'cultivos', component: CultivoListComponent, resolve: { cultivos: CultivoResolver } },
  { path: 'cultivos/:cultivoId/cosechas', component: CosechaListComponent, resolve: { cosechas: CosechaResolver } },
  { path: 'cultivos/:cultivoId/tratamientos', component: TratamientoListComponent, resolve: { tratamientos: TratamientoResolver } }
];

// Configuración de la app
export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(FormsModule, HttpClientModule), // Importa HttpClientModule y FormsModule
    provideRouter(routes) // Proporciona correctamente las rutas
  ]
};
