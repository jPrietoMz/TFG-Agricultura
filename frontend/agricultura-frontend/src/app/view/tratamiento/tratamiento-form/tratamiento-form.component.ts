import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TratamientoService } from '../../../service/tratamiento.service';
import { Tratamiento } from '../../../model/tratamiento.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';

@Component({
  selector: 'app-tratamiento-form',
  standalone: true,
  templateUrl: './tratamiento-form.component.html',
  styleUrls: ['./tratamiento-form.component.css'],
  imports: [CommonModule, FormsModule, BackButtonComponent]
})
export class TratamientoFormComponent {
  cultivoId!: number;
  nuevoTratamiento: Tratamiento = {
    id: 0,
    cultivoId: 0,
    fechaAplicacion: '',
    producto: '',
    dosis: 0,
    unidadMedida: '',
    metodoAplicacion: '',
    observaciones: ''
  };
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tratamientoService: TratamientoService
  ) {}

  ngOnInit() {
    const cultivoId = this.route.snapshot.paramMap.get('cultivoId');
  
    if (cultivoId && !isNaN(Number(cultivoId))) {
      this.cultivoId = Number(cultivoId);
      this.nuevoTratamiento.cultivoId = this.cultivoId; //  Se asigna correctamente
      console.log(" Cultivo ID asignado:", this.cultivoId);
    } else {
      console.error(" Error: `cultivoId` no es válido:", cultivoId);
      alert("Error: No se encontró el ID del cultivo.");
      this.router.navigate(['/cultivos']); // Redirigir si hay un error
    }
  }
  
  guardarTratamiento() {
    const token = localStorage.getItem('token');
    if (!token) {
      alert(" Error: No se encontró el token de autenticación.");
      this.router.navigate(['/login']);
      return;
    }

    // Decodificar el token para obtener el usuario_id
  const payload = JSON.parse(atob(token.split('.')[1])); // Decodificar el JWT
  const usuarioId = payload.userId; // Asegúrate de que el backend incluye `userId` en el token

  this.nuevoTratamiento.usuarioId = usuarioId; //  Asignamos el usuario
  console.log(" Token enviado en la petición:", `"Bearer ${token}"`); // 🔍 Depuración
  console.log(" Enviando cosecha con usuarioId:", this.nuevoTratamiento.usuarioId);

  this.tratamientoService.addTratamiento(this.nuevoTratamiento).subscribe({
    next: (data) => {
      console.log(' Tratamiento guardado:', data);
      alert('Tratamiento guardada exitosamente');
      this.router.navigate(['/cultivos', this.cultivoId, 'tratamientos']); // Redirigir a la lista de cosechas
    },
    error: (err) => {
      console.error(' Error guardando tratamiento:', err);
      alert('Error al guardar la tratamiento. Revisa la consola para más detalles.');
    }
  });
}

}
