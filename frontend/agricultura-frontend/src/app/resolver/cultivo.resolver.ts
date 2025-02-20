import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { CultivoService } from '../service/cultivo.service';
import { Cultivo } from '../model/cultivo.model';

@Injectable({
  providedIn: 'root'
})
export class CultivoResolver implements Resolve<Cultivo> {
  constructor(private cultivoService: CultivoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cultivo> {
    const cultivoId = Number(route.paramMap.get('cultivoId')); // Obtener ID del cultivo desde la URL
    return this.cultivoService.getCultivoById(cultivoId);
  }
}
