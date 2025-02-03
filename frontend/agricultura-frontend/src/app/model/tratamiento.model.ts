export class Tratamiento {
    id: number;
    nombre: string;
    descripcion: string;
    fechaAplicacion: string;
    cultivoId: number;
    producto: string;
    dosis: number;
  
    constructor(id: number, nombre: string, descripcion: string, fechaAplicacion: string, cultivoId: number, producto: string, dosis: number) {
      this.id = id;
      this.nombre = nombre;
      this.descripcion = descripcion;
      this.fechaAplicacion = fechaAplicacion;
      this.cultivoId = cultivoId;
      this.producto = producto;
      this.dosis = dosis;
    }
  }
  