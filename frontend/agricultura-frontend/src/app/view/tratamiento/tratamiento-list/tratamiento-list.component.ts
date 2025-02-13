import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TratamientoService } from '../../../service/tratamiento.service';
import { Tratamiento } from '../../../model/tratamiento.model';
import { CommonModule } from '@angular/common';
import { BackButtonComponent } from '../../../shared/components/back-button/back-button.component';
import { FormsModule } from '@angular/forms';

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
    if (!isNaN(this.cultivoId)) {
      this.tratamientoService.getTratamientosByCultivo(this.cultivoId).subscribe({
        next: (data) => {
          console.log("✅ Tratamientos obtenidos:", data);
          this.tratamientos = data;
        },
        error: (err) => {
          console.error('Error obteniendo tratamientos:', err);
        }
      });
    }
  }

  irAFormularioTratamiento() {
    this.router.navigate([`/cultivos/${this.cultivoId}/tratamientos/nuevo`]);
  }
}
