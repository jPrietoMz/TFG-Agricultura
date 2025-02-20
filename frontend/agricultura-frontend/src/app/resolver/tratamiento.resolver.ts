import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { TratamientoService } from '../service/tratamiento.service';
import { Tratamiento } from '../model/tratamiento.model';

@Injectable({
  providedIn: 'root'
})
export class TratamientoResolver implements Resolve<Tratamiento[]> {
  constructor(private tratamientoService: TratamientoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Tratamiento[]> {
    return this.tratamientoService.getTratamientosByCultivo();
  }
}
