import { Component } from '@angular/core';
import { NavbarComponent } from './view/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavbarComponent,
            RouterModule,
            CommonModule,
            ], // Asegura que está importado
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  
  isLoggedIn() {
    return !!localStorage.getItem('token');
  }
}

