import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TratamientoService } from '../../../service/tratamiento.service';
import { Tratamiento } from '../../../model/tratamiento.model';
import { CommonModule } from '@angular/common';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tratamiento-list',
  standalone: true,
  templateUrl: './tratamiento-list.component.html',
  styleUrls: ['./tratamiento-list.component.css'],
  imports: [CommonModule, BackButtonComponent, FormsModule, RouterModule]
})
export class TratamientoListComponent implements OnInit {
  tratamientos: Tratamiento[] = [];
  cultivoId!: number;

  constructor(
    private route: ActivatedRoute,
    private tratamientoService: TratamientoService,
    private router: Router
  ) {}

  ngOnInit() {
    this.cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
  
    if (!isNaN(this.cultivoId) && this.cultivoId > 0) {
      console.log("✅ Cultivo ID cargado en lista de tratamientos:", this.cultivoId);
      this.cargarTratamientos();
    } else {
      console.error("❌ Error: `cultivoId` no es válido:", this.cultivoId);
      alert("Error: No se encontró el ID del cultivo.");
      this.router.navigate(['/cultivos']); // Redirigir si hay un error
    }
  }
  

  irAFormularioTratamiento() {
    this.router.navigate([`/cultivos/${this.cultivoId}/tratamientos/nuevo`]);
  }

  cargarTratamientos(): void {
    this.tratamientoService.getTratamientosByCultivo().subscribe({
      next: (data: Tratamiento[]) => {
        this.tratamientos = data;
        console.log('✅ Tratamientos cargados correctamente');
      },
      error: (err) => {
        console.error('❌ Error al cargar tratamientos:', err);
      }
    });
  }

  eliminarTratamiento(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar este tratamiento?')) {
      this.tratamientoService.eliminarTratamiento(id).subscribe(
        () => {
          alert('Tratamiento eliminado con éxito.');
          this.cargarTratamientos(); // 🔄 Refrescar la lista después de eliminar
        });
    }
  }
}
