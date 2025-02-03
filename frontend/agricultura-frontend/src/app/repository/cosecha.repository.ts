import { Injectable } from '@angular/core';
import { CosechaService } from '../service/cosecha.service';
import { Cosecha } from '../model/cosecha.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CosechaRepository {
  constructor(private cosechaService: CosechaService) {}

  getCosechasByCultivo(cultivoId: number): Observable<Cosecha[]> {
    return this.cosechaService.getCosechasByCultivo(cultivoId);
  }
}
