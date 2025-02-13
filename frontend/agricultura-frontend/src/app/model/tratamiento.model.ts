export class Tratamiento {
  id: number;
  cultivoId: number;
  fechaAplicacion: string;
  producto: string;
  dosis: number;
  unidadMedida: string;
  metodoAplicacion: string;
  observaciones: string;

  constructor(id: number, cultivoId: number, fechaAplicacion: string, producto: string, dosis: number, unidadMedida: string, metodoAplicacion: string, observaciones: string) {
    this.id = id;
    this.cultivoId = cultivoId;
    this.fechaAplicacion = fechaAplicacion;
    this.producto = producto;
    this.dosis = dosis;
    this.unidadMedida = unidadMedida;
    this.metodoAplicacion = metodoAplicacion;
    this.observaciones = observaciones;
  }
}
