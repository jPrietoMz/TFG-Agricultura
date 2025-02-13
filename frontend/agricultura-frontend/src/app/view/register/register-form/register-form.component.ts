import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-register-form',
  standalone: true,
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
  imports: [FormsModule,
            RouterModule,
  ]
})
export class RegisterFormComponent {
  username: string = '';  // 🔥 Definimos username
  password: string = '';  // 🔥 Definimos password

  constructor(private http: HttpClient, private router: Router) {}

  register() {
    const registerData = { username: this.username, password: this.password };

    this.http.post('http://localhost:8080/api/users/register', registerData)
      .subscribe({
        next: (response) => {
          console.log('✅ Registro exitoso:', response);
          alert('Registro exitoso. Ahora puedes iniciar sesión.');
          this.router.navigate(['/login']); // 🔥 Redirige al login
        },
        error: (err) => {
          console.error('❌ Error en el registro:', err);
          alert('Error en el registro: ' + (err.error.message || 'Inténtalo de nuevo.'));
        }
      });
  }
}
