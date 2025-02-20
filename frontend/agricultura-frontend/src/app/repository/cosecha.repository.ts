import { Injectable } from '@angular/core';
import { CosechaService } from '../service/cosecha.service';
import { Cosecha } from '../model/cosecha.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CosechaRepository {
  constructor(private cosechaService: CosechaService) {}

  getCosechasByCultivo(): Observable<Cosecha[]> {  // ❌ Eliminamos cultivoId
    return this.cosechaService.getCosechasByCultivo(); // 🔹 Llama al nuevo método sin parámetro
  }
}
