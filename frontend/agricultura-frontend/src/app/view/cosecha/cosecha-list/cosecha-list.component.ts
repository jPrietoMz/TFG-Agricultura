import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CosechaService } from '../../../service/cosecha.service';
import { Cosecha } from '../../../model/cosecha.model';
import { CommonModule } from '@angular/common'; // 🔥 Importa CommonModule
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cosecha-list',
  standalone: true,
  templateUrl: './cosecha-list.component.html',
  styleUrls: ['./cosecha-list.component.css'],
  imports: [CommonModule,
            BackButtonComponent,
            FormsModule,
            RouterModule,
  ] // 🔥 Agrégalo aquí
})
export class CosechaListComponent implements OnInit {
  cosechas: Cosecha[] = [];
  cultivoId!: number;
  mostrarFormulario = false;
  nuevaCosecha: Cosecha = {
    id: 0,
    fechaInicio: '',
    fechaFin: '',
    kilosObtenidos: 0,
    precioObtenido: 0,
    cultivoId: 0 // Se asignará el cultivo correspondiente
  };

  constructor(
    private route: ActivatedRoute,
    private cosechaService: CosechaService
  ) {}

  ngOnInit() {
    const cultivoId = Number(this.route.snapshot.paramMap.get('cultivoId'));
    if (!isNaN(cultivoId)) {
      this.cosechaService.getCosechasByCultivo(cultivoId).subscribe({
        next: (data) => {
          console.log("✅ Cosechas obtenidas:", data);
          this.cosechas = data;
        },
        error: (err) => {
          console.error('Error obteniendo cosechas:', err);
        }
      });
    }
  }

  cargarCosechas() {
    this.cosechaService.getCosechasByCultivo(this.cultivoId).subscribe((data) => {
      this.cosechas = data;
    });
  }

  mostrarFormularioCosecha() {
    this.mostrarFormulario = true;
  }

  agregarCosecha() {
    this.nuevaCosecha.cultivoId = this.cultivoId; // Asignamos el cultivo actual
    this.cosechaService.addCosecha(this.nuevaCosecha).subscribe(() => {
      this.mostrarFormulario = false;
      this.cargarCosechas(); // Recargar la lista después de agregar
    });
  }
}

