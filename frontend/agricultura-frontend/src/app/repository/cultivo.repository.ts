import { Injectable } from '@angular/core';
import { CultivoService } from '../service/cultivo.service';
import { Cultivo } from '../model/cultivo.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CultivoRepository {
  constructor(private cultivoService: CultivoService) {}

  getCultivos(): Observable<Cultivo[]> {
    return this.cultivoService.getCultivos();
  }

  getCultivosByUsuario(usuarioId: number): Observable<Cultivo[]> {
    return this.cultivoService.getCultivosByUsuario(usuarioId);
  }
}
