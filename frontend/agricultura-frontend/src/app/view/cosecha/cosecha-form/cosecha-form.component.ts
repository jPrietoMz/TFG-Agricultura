import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CosechaService } from '../../../service/cosecha.service';
import { Cosecha } from '../../../model/cosecha.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';

@Component({
  selector: 'app-cosecha-form',
  standalone: true,
  templateUrl: './cosecha-form.component.html',
  styleUrls: ['./cosecha-form.component.css'],
  imports: [CommonModule, FormsModule, BackButtonComponent]
})
export class CosechaFormComponent {
  cultivoId!: number; // ✅ Se declara `cultivoId` aquí
  nuevaCosecha: Cosecha = {
    id: 0,
    kilosObtenidos: 0,
    cultivoId: 0,
    precioObtenido: 0,
    fechaInicio: '',
    fechaFin: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cosechaService: CosechaService
  ) {}
  
  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('cultivoId'));
    if (!isNaN(id) && id > 0) {
      this.cultivoId = id;
      this.nuevaCosecha.cultivoId = id; // ✅ Asignamos el `cultivoId` a la cosecha correctamente
    } else {
      console.error("⚠️ Error: `cultivoId` es inválido:", id);
      alert("Error: No se encontró el ID del cultivo.");
      this.router.navigate(['/cultivos']); // Redirigir si hay un error
    }
  }

  guardarCosecha() {
  const token = localStorage.getItem('token');
  if (!token) {
    alert("⚠️ Error: No se encontró el token de autenticación.");
    this.router.navigate(['/login']);
    return;
  }

  // Decodificar el token para obtener el usuario_id
  const payload = JSON.parse(atob(token.split('.')[1])); // Decodificar el JWT
  const usuarioId = payload.userId; // Asegúrate de que el backend incluye `userId` en el token

  this.nuevaCosecha.usuarioId = usuarioId; // ✅ Asignamos el usuario

  console.log("🌱 Enviando cosecha con usuarioId:", this.nuevaCosecha.usuarioId);

  this.cosechaService.agregarCosecha(this.nuevaCosecha).subscribe({
    next: (data) => {
      console.log('✅ Cosecha guardada:', data);
      alert('Cosecha guardada exitosamente');
      this.router.navigate(['/cultivos', this.cultivoId, 'cosechas']); // Redirigir a la lista de cosechas
    },
    error: (err) => {
      console.error('❌ Error guardando cosecha:', err);
      alert('Error al guardar la cosecha. Revisa la consola para más detalles.');
    }
  });
}

  
}
