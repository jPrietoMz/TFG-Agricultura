import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { CosechaService } from '../service/cosecha.service';
import { Cosecha } from '../model/cosecha.model';

@Injectable({
  providedIn: 'root'
})
export class CosechaResolver implements Resolve<Cosecha[]> {
  constructor(private cosechaService: CosechaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cosecha[]> {
    const cultivoId = route.paramMap.get('cultivoId')!;
    return this.cosechaService.getCosechasByCultivo(Number(cultivoId));
  }
}
