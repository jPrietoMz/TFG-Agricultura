import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  @Input() isOpen = false; // Estado de apertura de la barra lateral

  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('token'); // Eliminar token
    this.router.navigate(['/login']); // Redirigir al login
  }
}
