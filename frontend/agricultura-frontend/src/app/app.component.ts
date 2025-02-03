import { Component } from '@angular/core';
import { NavbarComponent } from './view/navbar/navbar.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent,
    RouterModule,
  ], // Asegura que está importado
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {}

